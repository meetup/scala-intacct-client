package com.meetup.intacct.client

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import dispatch._

trait DispatchXmlHttpClient extends OutgoingTransporter[HttpPostRequest, String] with Logger {
  override def request(request: HttpPostRequest): Future[String] = {

    Logger.debug(s"Posting request to ${request.url} with ${request.payload}")

    val req = url(request.url)
      .addHeader("Content-Type", "x-intacct-xml-request")
      .setBody(request.payload)
      .setMethod("POST")

    HttpInstance.exec(req OK as.String)
  }
}

object HttpInstance extends Logger {
  lazy val exec = new Http()
  // This should be called just once when the application closes
  def shutdown(): Unit = {
    Logger.info("Closing HTTP client instance")
    exec.client.close()
  }
}