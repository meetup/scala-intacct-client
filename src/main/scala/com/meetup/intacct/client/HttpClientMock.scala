package com.meetup.intacct.client

import scala.concurrent.Future

trait HttpClientMock extends IntacctTransporter[HttpPostRequest, String] {
  this: HttpPostResponseProvider =>

  override def request(req: HttpPostRequest): Future[String] = this.getResponse(req)
}
