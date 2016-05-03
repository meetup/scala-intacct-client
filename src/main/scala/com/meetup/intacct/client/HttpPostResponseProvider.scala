package com.meetup.intacct.client

import scala.concurrent.Future

trait HttpPostResponseProvider {
  def getResponse(req: HttpPostRequest): Future[String]
}
