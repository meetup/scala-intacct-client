package com.meetup.intacct.client

import scala.concurrent.Future

trait OutgoingTransporter[Req, Res] {
  def request(request: Req): Future[Res]
}
