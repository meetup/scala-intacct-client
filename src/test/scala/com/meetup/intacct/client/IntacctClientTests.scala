package com.meetup.intacct.client

import com.meetup.intacct.client.DSL._
import com.meetup.intacct.request.{Expression, Field, Logical, Value}
import java.io.StringWriter
import javax.xml.bind.{JAXBContext, Marshaller}
import org.scalatest.{FunSpec, Matchers}
import scala.collection.JavaConverters._
import scala.reflect.ClassTag

object Helper {
  def jaxbToString[T: ClassTag](jaxb: Any): String = {
    val jaxbContext = JAXBContext.newInstance(implicitly[ClassTag[T]].runtimeClass)
    val jaxbMarshaller = jaxbContext.createMarshaller()
    val stringWriter = new StringWriter()
    jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true)
    jaxbMarshaller.marshal(jaxb, stringWriter)

    stringWriter.toString
  }
}

class IntacctClientTests extends FunSpec with Matchers {
  describe("Query filters") {
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

      Helper.jaxbToString[Expression](filter1.toJaxb) shouldBe Helper.jaxbToString[Expression](expected1)
      Helper.jaxbToString[Expression](filter2.toJaxb) shouldBe Helper.jaxbToString[Expression](expected2)
      Helper.jaxbToString[Expression](filter3.toJaxb) shouldBe Helper.jaxbToString[Expression](expected3)
      Helper.jaxbToString[Expression](filter4.toJaxb) shouldBe Helper.jaxbToString[Expression](expected4)
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
        .withLogicalOrExpression(Seq(expression1, expression2): _*)

      val logicalJaxb2 = new Logical()
        .withLogicalOperator("or")
        .withLogicalOrExpression(Seq(expression1, expression2): _*)

      val logicalJaxb3 = new Logical()
        .withLogicalOperator("or")
        .withLogicalOrExpression(
          Seq(logicalJaxb1, expression3): _*)

      val logicalJaxb4 = new Logical()
        .withLogicalOperator("and")
        .withLogicalOrExpression(
          Seq(
            expression1,
            new Logical()
              .withLogicalOperator("or")
              .withLogicalOrExpression(Seq(expression2, expression3): _*)): _*)

      Helper.jaxbToString[Logical](logical1.toJaxb) shouldBe Helper.jaxbToString[Logical](logicalJaxb1)
      Helper.jaxbToString[Logical](logical2.toJaxb) shouldBe Helper.jaxbToString[Logical](logicalJaxb2)
      Helper.jaxbToString[Logical](logical3.toJaxb) shouldBe Helper.jaxbToString[Logical](logicalJaxb3)
      Helper.jaxbToString[Logical](logical4.toJaxb) shouldBe Helper.jaxbToString[Logical](logicalJaxb4)

    }
  }
}