package com.meetup.intacct.client

import com.meetup.intacct.client.DSL._
import java.io.StringWriter
import java.time.{ZonedDateTime, ZoneId}
import javax.xml.bind.{JAXBContext, Marshaller}
import org.scalatest.{FunSpec, Matchers}
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.ClassTag
import scala.util.Failure
import com.meetup.intacct.request.{
  Request,
  Expression,
  Field,
  Logical,
  Value,
  Function => RequestFunction,
  Control,
  Operation,
  Authentication,
  Login,
  Content,
  GetList,
  Filter => RequestFilter,
  Fields,
  CreateApaccountlabel,
  Accountlabel,
  Glaccountno
}
import com.meetup.intacct.response.{
  Error,
  Response,
  Operation => ResponseOperation,
  Result,
  Key,
  Errormessage,
  Apaccountlabel,
  Data
}

object Helper {
  def jaxbToString[T: ClassTag](jaxb: T): String = {
    val jaxbMarshaller = JAXBContext.newInstance(
      implicitly[ClassTag[T]].runtimeClass)
      .createMarshaller()
    val stringWriter = new StringWriter()
    jaxbMarshaller.marshal(jaxb, stringWriter)

    stringWriter.toString
  }

  def createClientMock(response: Future[String] = Future("")) = IntacctClientMock(IntacctClientConfiguration(
    senderId = "send",
    senderPassword = "sendPw",
    userId = "userId",
    userPassword = "userPw",
    companyId = "compId"
  ), response, None, Some("cid1"), Some("cid2"))
}

class IntacctClientTests extends FunSpec with Matchers {
  describe("Filters") {
    it("should properly handle simple filter expressions") {
      val filter1 = "myField" is "123"
      val filter2 = "myField" like "345"
      val filter3 = "myField" lt "135"
      val filter4 = "myField" gt "975"

      val expected1 = new Expression()
        .withField(new Field().withvalue("myField"))
        .withOperator("=")
        .withValue(new Value().withvalue("123"))

      val expected2 = new Expression()
        .withField(new Field().withvalue("myField"))
        .withOperator("like")
        .withValue(new Value().withvalue("345"))

      val expected3 = new Expression()
        .withField(new Field().withvalue("myField"))
        .withOperator("<")
        .withValue(new Value().withvalue("135"))

      val expected4 = new Expression()
        .withField(new Field().withvalue("myField"))
        .withOperator(">")
        .withValue(new Value().withvalue("975"))

      Helper.jaxbToString(filter1.toJaxb) shouldBe Helper.jaxbToString(expected1)
      Helper.jaxbToString(filter2.toJaxb) shouldBe Helper.jaxbToString(expected2)
      Helper.jaxbToString(filter3.toJaxb) shouldBe Helper.jaxbToString(expected3)
      Helper.jaxbToString(filter4.toJaxb) shouldBe Helper.jaxbToString(expected4)
    }

    it("should properly handle compound filter expressions") {
      val filter1 = "myField" is "123"
      val filter2 = "myOtherField" like "456"
      val filter3 = "yetAnotherField" lt "abc"

      val logical1 = filter1 and filter2
      val logical2 = filter1 or filter2
      val logical3 = filter1 and filter2 or filter3
      val logical4 = filter1 and (filter2 or filter3)

      val expression1 = new Expression()
        .withField(new Field().withvalue("myField"))
        .withOperator("=")
        .withValue(new Value().withvalue("123"))
      val expression2 = new Expression()
        .withField(new Field().withvalue("myOtherField"))
        .withOperator("like")
        .withValue(new Value().withvalue("456"))
      val expression3 = new Expression()
        .withField(new Field().withvalue("yetAnotherField"))
        .withOperator("<")
        .withValue(new Value().withvalue("abc"))

      val logicalJaxb1 = new Logical()
        .withLogicalOperator("and")
        .withLogicalOrExpression(expression1, expression2)

      val logicalJaxb2 = new Logical()
        .withLogicalOperator("or")
        .withLogicalOrExpression(expression1, expression2)

      val logicalJaxb3 = new Logical()
        .withLogicalOperator("or")
        .withLogicalOrExpression(logicalJaxb1, expression3)

      val logicalJaxb4 = new Logical()
        .withLogicalOperator("and")
        .withLogicalOrExpression(
          expression1,
          new Logical()
            .withLogicalOperator("or")
            .withLogicalOrExpression(expression2, expression3))

      Helper.jaxbToString(logical1.toJaxb) shouldBe Helper.jaxbToString(logicalJaxb1)
      Helper.jaxbToString(logical2.toJaxb) shouldBe Helper.jaxbToString(logicalJaxb2)
      Helper.jaxbToString(logical3.toJaxb) shouldBe Helper.jaxbToString(logicalJaxb3)
      Helper.jaxbToString(logical4.toJaxb) shouldBe Helper.jaxbToString(logicalJaxb4)

    }

    it("should work for some randomly complex thing") {
      val filter = ("field1" is "123") or ("field2" lt "987") and ("field3" gt "146") or ("field4" like "iii")
      val expected = new Logical()
        .withLogicalOperator("or")
        .withLogicalOrExpression(
          new Logical()
            .withLogicalOperator("and")
            .withLogicalOrExpression(
              new Logical()
                .withLogicalOperator("or")
                .withLogicalOrExpression(
                  new Expression()
                    .withField(new Field().withvalue("field1"))
                    .withValue(new Value().withvalue("123"))
                    .withOperator("="),
                  new Expression()
                    .withField(new Field().withvalue("field2"))
                    .withValue(new Value().withvalue("987"))
                    .withOperator("<")),
              new Expression()
                .withField(new Field().withvalue("field3"))
                .withValue(new Value().withvalue("146"))
                .withOperator(">")),
          new Expression()
            .withField(new Field().withvalue("field4"))
            .withValue(new Value().withvalue("iii"))
            .withOperator("like"))

      Helper.jaxbToString(filter.toJaxb) shouldBe Helper.jaxbToString(expected)
    }

    it("should handle dates properly") {
      val filter = "field1" is ZonedDateTime.of(2016, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())
      val expected = new Expression()
        .withField(new Field().withvalue("field1"))
        .withValue(new Value().withvalue("01/01/2016"))
        .withOperator("=")

      Helper.jaxbToString(filter.toJaxb) shouldBe Helper.jaxbToString(expected)
    }
  }

  describe("Intacct client") {
    it("should produce the correct XML for getListXml") {
      val filter = "field1" is "123"
      val expected = new Request()
        .withControl(new Control()
          .withSenderid("send")
          .withPassword("sendPw")
          .withControlid("cid1")
          .withUniqueid("false")
          .withDtdversion("2.1"))
        .withOperation(new Operation()
          .withTransaction("true")
          .withAuthentication(new Authentication()
            .withLoginOrSessionid(new Login()
              .withCompanyid("compId")
              .withUserid("userId")
              .withPassword("userPw")))
          .withContent(new Content()
            .withFunction(new RequestFunction()
              .withControlid("cid2")
              .withOperation(new GetList()
                .withObject("accountgroup")
                .withFilter(new RequestFilter()
                  .withLogicalOrExpression(new Expression()
                    .withField(new Field().withvalue("field1"))
                    .withOperator("=")
                    .withValue(new Value().withvalue("123"))))
                .withFields(new Fields().withField(new Field().withvalue("field2"), new Field().withvalue("field3")))))))

      val clientWithMockTransport = Helper.createClientMock()

      val result = clientWithMockTransport.getListXml[AccountgroupItem](Some(filter), Seq("field2", "field3"))

      Helper.jaxbToString(expected) shouldBe result
    }

    it("should produce the correct XML for createItemXml") {
      val clientWithMockTransport = Helper.createClientMock()

      val request = new CreateApaccountlabel()
        .withAccountlabel(new Accountlabel().withvalue("al"))
        .withDescription("desc")
        .withExternalid("123")
        .withGlaccountno(new Glaccountno().withvalue("gl"))
        .withIgnoreduplicates("ok")
        .withOffsetglaccountno("off")
        .withStatus("456")

      val requestItem = CreateApaccountlabelItem(request)

      val req = new Request()
        .withControl(new Control()
          .withSenderid("send")
          .withPassword("sendPw")
          .withControlid("cid1")
          .withUniqueid("false")
          .withDtdversion("2.1"))
        .withOperation(new Operation()
          .withAuthentication(new Authentication()
            .withLoginOrSessionid(new Login()
              .withUserid("userId")
              .withCompanyid("compId")
              .withPassword("userPw")))
          .withTransaction("false")
          .withContent(new Content()
            .withFunction(new RequestFunction()
              .withControlid("cid2")
              .withOperation(request.asInstanceOf[Object]))))

      clientWithMockTransport.createItemXml[CreateApaccountlabelItem](Seq(requestItem)) shouldBe Helper.jaxbToString(req)
    }

    it("createItems should succeed when the response is expected") {

      val request = new CreateApaccountlabel()
        .withAccountlabel(new Accountlabel().withvalue("al"))
        .withDescription("desc")
        .withExternalid("123")
        .withGlaccountno(new Glaccountno().withvalue("gl"))
        .withIgnoreduplicates("ok")
        .withOffsetglaccountno("off")
        .withStatus("456")

      val requestItem = CreateApaccountlabelItem(request)

      val response = new Response()
        .withOperationOrErrormessage(
          new ResponseOperation()
            .withErrormessageOrResult(
              new Result()
                .withStatus("success")
                .withControlid("456")
                .withListtypeOrKey(
                  new Key()
                    .withvalue("123"))))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.createItems(Seq(requestItem)) shouldBe Right(Seq(Right(OperationSuccess("456", "123"))))
    }

    it("createItems should fail when the response is not expected") {

      val requestItem = CreateApaccountlabelItem(new CreateApaccountlabel())

      val response = new Response()
        .withOperationOrErrormessage(
          new ResponseOperation()
            .withErrormessageOrResult(
              new Result()
                .withStatus("failure")
                .withControlid("456")
                .withErrormessage(new Errormessage().withError(
                  new Error()
                    .withCorrection("cor")
                    .withDescription("d1")
                    .withDescription2("d2")
                    .withErrorno("444")))))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.createItems(Seq(requestItem)) shouldBe Right(Seq(Left(OperationFailure("456", Seq(ErrorMessage("444", "d1d2", "cor"))))))
    }

    it("createItems should fail when the response fails") {
      val requestItem = CreateApaccountlabelItem(new CreateApaccountlabel())
      val clientWithMockTransport = Helper.createClientMock(Future.failed(new Exception()))

      clientWithMockTransport.createItems(Seq(requestItem)) match {
        case Left(_: Exception) =>
        case _ => fail
      }

    }

    it("createItems should fail when there's an error at Operation") {
      val requestItem = CreateApaccountlabelItem(new CreateApaccountlabel())

      val response = new Response()
        .withOperationOrErrormessage(
          new Errormessage().withError(
            new Error()
              .withCorrection("cor")
              .withDescription("d1")
              .withDescription2("d2")
              .withErrorno("444")))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.createItems(Seq(requestItem)) match {
        case Left(_: Exception) =>
        case _ => fail
      }
    }

    it("createItems should fail when there's an error at Result") {
      val requestItem = CreateApaccountlabelItem(new CreateApaccountlabel())

      val response = new Response()
        .withOperationOrErrormessage(
          new ResponseOperation()
            .withErrormessageOrResult(
              new Errormessage().withError(
                new Error()
                  .withCorrection("cor")
                  .withDescription("d1")
                  .withDescription2("d2")
                  .withErrorno("444"))))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.createItems(Seq(requestItem)) match {
        case Left(_: Exception) =>
        case _ => fail
      }
    }

    it("getItems should succeed when the response is expected") {
      val requestItem = GetListItem(new GetList())

      val items = Seq(new Apaccountlabel(), new Apaccountlabel())

      val response = new Response()
        .withOperationOrErrormessage(
          new ResponseOperation()
            .withErrormessageOrResult(
              new Result()
                .withStatus("success")
                .withData(
                  new Data()
                    .withItems(items: _*))))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.getItems[ApaccountlabelItem]() match {
        case Right(Seq(ApaccountlabelItem(_), ApaccountlabelItem(_))) =>
        case _ => fail
      }
    }

    it("getItems should fail when the response is not expected") {
      val requestItem = GetListItem(new GetList())

      val items = Seq(new Apaccountlabel(), new Apaccountlabel())

      val response = new Response()
        .withOperationOrErrormessage(
          new ResponseOperation()
            .withErrormessageOrResult(
              new Result()
                .withStatus("failure")
                .withControlid("456")
                .withErrormessage(
                  new Errormessage().withError(
                    new Error()
                      .withCorrection("cor")
                      .withDescription("d1")
                      .withDescription2("d2")
                      .withErrorno("444")))))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.getItems[ApaccountlabelItem]() match {
        case Left(_: Exception) =>
        case _ => fail
      }
    }

    it("getItems should fail when the response fails") {
      val requestItem = CreateApaccountlabelItem(new CreateApaccountlabel())
      val clientWithMockTransport = Helper.createClientMock(Future.failed(new Exception()))

      clientWithMockTransport.getItems[ApaccountlabelItem]() match {
        case Left(_: Exception) =>
        case _ => fail
      }

    }

    it("getItems should fail when there's an error at Operation") {
      val requestItem = GetListItem(new GetList())

      val response = new Response()
        .withOperationOrErrormessage(
          new Errormessage().withError(
            new Error()
              .withCorrection("cor")
              .withDescription("d1")
              .withDescription2("d2")
              .withErrorno("444")))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.getItems[ApaccountlabelItem]() match {
        case Left(_: Exception) =>
        case _ => fail
      }
    }

    it("getItems should fail when there's an error at Result") {
      val requestItem = GetListItem(new GetList())

      val response = new Response()
        .withOperationOrErrormessage(
          new ResponseOperation()
            .withErrormessageOrResult(
              new Errormessage().withError(
                new Error()
                  .withCorrection("cor")
                  .withDescription("d1")
                  .withDescription2("d2")
                  .withErrorno("444"))))

      val clientWithMockTransport = Helper.createClientMock(Future(Helper.jaxbToString(response)))

      clientWithMockTransport.getItems[ApaccountlabelItem]() match {
        case Left(_: Exception) =>
        case _ => fail
      }
    }

    it("should format an Error object properly") {
      val err = new Error().withCorrection("cor").withDescription("d1").withDescription2("d2").withErrorno("777")

      val clientWithMockTransport = Helper.createClientMock()

      val result = clientWithMockTransport.formatError(err)

      result should include("cor")
      result should include("d1")
      result should include("d2")
      result should include("777")
    }

    it("should format multiple Error objects properly") {
      val err1 = new Error().withCorrection("cor1").withDescription("d11").withDescription2("d21").withErrorno("7771")
      val err2 = new Error().withCorrection("cor2").withDescription("d12").withDescription2("d22").withErrorno("7772")
      val err3 = new Error().withCorrection("cor3").withDescription("d13").withDescription2("d23").withErrorno("7773")

      val clientWithMockTransport = Helper.createClientMock()

      val result = clientWithMockTransport.formatError(Seq(err1, err2, err3))

      result should include("cor1")
      result should include("cor2")
      result should include("cor3")
      result should include("d11")
      result should include("d12")
      result should include("d13")
      result should include("d21")
      result should include("d22")
      result should include("d23")
      result should include("7771")
      result should include("7772")
      result should include("7773")
    }
  }
}