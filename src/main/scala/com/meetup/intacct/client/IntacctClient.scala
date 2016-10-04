package com.meetup.intacct.client

import com.meetup.intacct.request.{
  Filter => RequestFilter,
  Function => RequestFunction,
  Operation => RequestOperation,
  Fields,
  Value,
  Field,
  Expression,
  GetList,
  Content,
  Sessionid,
  Login,
  Authentication,
  Control,
  Request,
  Logical
}
import com.meetup.intacct.response.{Data, Error, Errormessage, Key, Response, Result, Operation => ResponseOperation}
import java.io.{StringReader, StringWriter}
import java.util.UUID
import javax.xml.bind.JAXBContext

import scala.collection.JavaConverters._
import scala.concurrent.{Await, Future}
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl

class IntacctClient(
    config: IntacctClientConfiguration,
    sessionId: Option[String] = None,
    controlId1: Option[String] = None,
    controlId2: Option[String] = None
) extends IntacctClientBase with Logger {

  this: IntacctTransporter[HttpPostRequest, String] =>

  // Passing these values into the constructor is preferred but it will autogenerate
  // if you're feeling lazy about it
  private def getControlId: String = controlId1.getOrElse("Meetup: " + UUID.randomUUID().toString)

  private def getControlId2: String = controlId2.getOrElse(UUID.randomUUID().toString)

  /**
   * Provides a raw XML string representing a request to intacct
   * for a particular item type
   *
   * @param query a Query instance
   * @param ct
   * @tparam ItemT
   * @return
   */
  def getListXml[ItemT <: ResponseType[_]](filter: Option[Filter] = None, fields: Iterable[String] = Seq())(implicit ct: ClassTag[ItemT]): String = {

    // Taking advantage of the fact that jaxb
    // will always name the class after the object
    // text we need here
    // For example, given ItemT extends ResponseType[Customer],
    // the value of `object` gets will be the string "customer"
    val `object` = ct.runtimeClass
      .getGenericSuperclass
      .asInstanceOf[ParameterizedTypeImpl]
      .getActualTypeArguments
      .head
      .asInstanceOf[Class[_]]
      .getSimpleName
      .toLowerCase

    val getList = {
      val list = new GetList()
        .withObject(`object`)

      val listWithFilters = filter.fold {
        list
      } { ftr =>
        list.withFilter(new RequestFilter()
          .withLogicalOrExpression {
            ftr.toJaxb.asInstanceOf[Object]
          })
      }

      if (fields.nonEmpty) {
        listWithFilters.withFields(new Fields()
          .withField(
            fields.map(new Field().withvalue(_)).asJavaCollection
          ))
      } else {
        listWithFilters
      }
    }

    val req = new Request()
      .withControl(getControl(config))
      .withOperation(getOperation(config, transaction = true)
        .withContent(new Content()
          .withFunction(new RequestFunction()
            .withControlid(getControlId2)
            .withOperation(getList))))

    val stringWriter = new StringWriter()
    val jaxbContext = JAXBContext.newInstance(classOf[Request])
    val jaxbMarshaller = jaxbContext.createMarshaller()
    jaxbMarshaller.marshal(req, stringWriter)

    stringWriter.toString
  }

  /**
   * Provides a raw XML string representing a request to intacct to create entities
   * of a particular type
   *
   * @param template The instances that we are requesting intacct to create
   * @param ct
   * @tparam ItemT
   * @return
   */
  def createItemXml[ItemT <: RequestType[_]](template: Seq[ItemT])(implicit ct: ClassTag[ItemT]): String = {
    val content = new Content()
    template.foreach { item =>
      content.withFunction(new RequestFunction()
        .withControlid(getControlId2)
        .withOperation(item.underlying.asInstanceOf[Object]))
    }

    val req = new Request()
      .withControl(getControl(config))
      .withOperation(getOperation(config, transaction = false)
        .withContent(content))

    val stringWriter = new StringWriter()
    val jaxbContext = JAXBContext.newInstance(classOf[Request])
    val jaxbMarshaller = jaxbContext.createMarshaller()
    jaxbMarshaller.marshal(req, stringWriter)

    stringWriter.toString
  }

  /**
   * Roll up an Intacct error element to a line of text
   *
   * @param error
   * @return
   */
  def formatError(error: Error): String = {
    s"Error number: ${error.getErrorno}, Error description: ${error.getDescription} ${error.getDescription2}, Error correction: ${error.getCorrection}."
  }

  /**
   * Roll up multiple errors into a line of text
   *
   * @param error
   * @return
   */
  def formatError(error: Seq[Error]): String = {
    error.foldRight("")((e, acc) => acc + " " + formatError(e))
  }

  override def createItems[ItemT <: RequestType[_]](template: Seq[ItemT])(implicit ct: ClassTag[ItemT]): Either[Exception, Seq[Either[OperationFailure, OperationSuccess]]] = {

    val processResults: (Seq[Result]) => Try[Seq[Either[OperationFailure, OperationSuccess]]] =
      (results) => {
        lazy val genericFailure = new Exception("Unexpected response from intacct")

        val allowedStatus = Seq("success", "failure", "aborted")

        if (!results.forall(r => allowedStatus.contains(r.getStatus))) {
          Failure(genericFailure)
        } else {
          val parsed = results.map { r =>
            r.getStatus match {
              case "success" =>
                Right(OperationSuccess(
                  r.getControlid,
                  r.getListtypeOrKey
                    .asScala
                    .headOption
                    .map(_.asInstanceOf[Key].getvalue)
                    .getOrElse("")
                ))
              case "failure" | "aborted" =>
                Left(OperationFailure(
                  r.getControlid,
                  r.getErrormessage
                    .getError
                    .asScala
                    .map(errorToErrorMessage)
                ))
            }
          }
          Success(parsed)
        }
      }

    handleResponse(createItemXml[ItemT](template), processResults)
  }

  override def getItems[ItemT <: ResponseType[_]](filter: Option[Filter] = None, fields: Iterable[String] = Seq())(implicit ct: ClassTag[ItemT]): Either[Exception, Seq[ItemT]] = {

    val processResults: (Seq[Result]) => Try[Seq[ItemT]] =
      (results) => {
        lazy val genericFailure = new Exception("Unexpected response from intacct")

        val maybeItems = for {
          result <- results.headOption.map(Success(_)).getOrElse(Failure(genericFailure))
          successResult <- result.getStatus match {
            case "success" =>
              Success(result)
            case "failure" | "aborted" =>
              Failure(new Exception(formatError(result.getErrormessage.getError.asScala)))
            case _ =>
              Failure(genericFailure)
          }
          data <- Option(successResult.getData).map(Success(_)).getOrElse(Success(new Data))
          items <- Option(data.getItems).map(Success(_)).getOrElse(Success(List().asJava))
        } yield items

        maybeItems.map {
          items =>
            items.asScala.map {
              item =>
                // wrap the raw JAXB instance in a
                // new ItemT instance. this is all to
                // to produce compile errors if a user tries
                // to pass in a type for ItemT that can't actually
                // be requested or deserialized
                ct.runtimeClass
                  .getDeclaredConstructor(item.getClass)
                  .newInstance(item)
                  .asInstanceOf[ItemT]
            }.toSeq
        }
      }

    handleResponse(getListXml[ItemT](filter, fields), processResults)
  }

  /**
   * Posts a raw XML string to intacct and handles boilerplate for failure types
   * accross all requests. The actual processing of the results are handled by
   * the function passed in
   *
   * @param req Raw intacct XML
   * @param handle A function to handle the actual results element
   * @tparam T
   * @return
   */
  private def handleResponse[T](req: String, handle: Seq[Result] => Try[T]): Either[Exception, T] = {

    val maybeResponse = request(HttpPostRequest(req, config.url, config.timeout))

    Try(Await.result(maybeResponse, config.timeout)) match {
      case Success(response) => {
        Logger.debug(s"Success response from intacct: $response")

        val stringReader = new StringReader(response)
        val jaxbContext = JAXBContext.newInstance(classOf[Response])
        val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
        lazy val genericFail = new Exception("Unexpected response from intacct")

        val maybeItems = for {

          resp <- Try(jaxbUnmarshaller.unmarshal(stringReader).asInstanceOf[Response])

          operation <- resp.getOperationOrErrormessage.asScala.headOption.map { h =>
            h match {
              case op: ResponseOperation =>
                Success(op)
              case err: Errormessage =>
                val e = new Exception(formatError(err.getError.asScala))
                Logger.error(e.getMessage)
                Failure(e)
              case _ =>
                Logger.error(genericFail.getMessage)
                Failure(genericFail)
            }
          }.getOrElse(Failure(genericFail))

          items <- operation.getErrormessageOrResult.asScala.headOption.map { r =>
            r match {
              case res: Result =>
                handle(operation.getErrormessageOrResult.asScala.map(_.asInstanceOf[Result]))
              case err: Errormessage =>
                val e = new Exception(formatError(err.getError.asScala))
                Logger.error(e.getMessage)
                Failure(e)
              case _ =>
                Logger.error(genericFail.getMessage)
                Failure(genericFail)
            }
          }.getOrElse(Failure(genericFail))
        } yield items

        maybeItems match {
          case Success(items) =>
            Right(items)
          case Failure(err: Exception) =>
            Logger.error(err.getMessage)
            Left(err)
          case Failure(err) =>
            Logger.error(err.getMessage)
            Left(new Exception(err))
        }
      }
      case Failure(err: Exception) =>
        Logger.error(err.getMessage)
        Left(err)
      case Failure(err) =>
        Logger.error(err.getMessage)
        Left(new Exception(err))
    }
  }

  private def getControl(c: IntacctClientConfiguration): Control =
    new Control()
      .withSenderid(config.senderId)
      .withPassword(config.senderPassword)
      .withControlid(getControlId)
      .withUniqueid("false")
      .withDtdversion("2.1")

  private def getAuthentication(c: IntacctClientConfiguration): Authentication = {
    val auth = new Authentication()

    sessionId.fold {
      auth.withLoginOrSessionid(new Login()
        .withUserid(config.userId)
        .withCompanyid(config.companyId)
        .withPassword(config.userPassword))
    } { sid =>
      auth.withLoginOrSessionid(new Sessionid().withvalue(sid))
    }
  }

  private def errorToErrorMessage(error: Error): ErrorMessage =
    ErrorMessage(
      error.getErrorno,
      Option(error.getDescription).getOrElse("") + Option(error.getDescription2).getOrElse(""),
      error.getCorrection
    )

  private def getOperation(c: IntacctClientConfiguration, transaction: Boolean): RequestOperation =
    new RequestOperation()
      .withAuthentication(getAuthentication(c))
      .withTransaction(if (transaction) "true" else "false")

}

/**
 * A live intacct client using dispatch for HTTP
 */
object IntacctClient {
  def apply(
    config: IntacctClientConfiguration,
    sessionId: Option[String] = None,
    controlId1: Option[String] = None,
    controlId2: Option[String] = None
  ) = new IntacctClient(config) with DispatchXmlHttpClient
}
