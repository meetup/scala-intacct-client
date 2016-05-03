package com.meetup.intacct.client

import scala.concurrent.Future

trait IntacctTransporter[Req, Res] {
  def request(request: Req): Future[Res]
}
