package com.meetup.intacct.client

import scala.concurrent.duration.Duration

case class HttpPostRequest(payload: String, url: String, timeout: Duration)
