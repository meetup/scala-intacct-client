package com.meetup.intacct.client

abstract class RequestType[T](val underlying: T)

abstract class ResponseType[T](val underlying: T)

case class OperationSuccess(controlid: String, key: String)

case class OperationFailure(controlId: String, messages: Seq[ErrorMessage])

case class ErrorMessage(
  errorNumber: String,
  description: String,
  correction: String
)
