package com.meetup.intacct.client

import scala.concurrent.duration._

case class IntacctClientConfiguration(
  senderId: String = "",
  senderPassword: String = "",
  userId: String = "",
  userPassword: String = "",
  companyId: String = "",
  url: String = "",
  timeout: Duration = 5.seconds
)
