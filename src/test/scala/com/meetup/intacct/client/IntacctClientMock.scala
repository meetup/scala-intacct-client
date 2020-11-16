package com.meetup.intacct.client

import scala.concurrent.Future

/**
 * An intacct client that will dutifully handle any response you choose to pass to it
 */

object IntacctClientMock {
  def apply(
    config: IntacctClientConfiguration,
    response: Future[String],
    sessionId: Option[String] = None,
    controlId1: Option[String] = None,
    controlId2: Option[String] = None
  ) =
    new IntacctClient(config, sessionId, controlId1, controlId2) with HttpClientMock with HttpPostResponseProvider {
      def getResponse(req: HttpPostRequest): Future[String] = response
    }
}
