package com.meetup.intacct.client

import scala.reflect.ClassTag

class GenericOutgoingClientMock(
    getItemsResult: Either[Exception, Seq[_]] = Right(Seq()),
    createItemResult: Either[Exception, Option[Either[OperationFailure, OperationSuccess]]] = Right(None),
    createItemsResult: Either[Exception, Seq[Either[OperationFailure, OperationSuccess]]] = Right(Seq())
) extends OutgoingClient {

  override def getItems[ItemT <: ResponseType[_]](
    query: Option[Query],
    fields: Option[Iterable[String]]
  )(implicit ct: ClassTag[ItemT]): Either[Exception, Seq[ItemT]] =
    getItemsResult.asInstanceOf[Either[Exception, Seq[ItemT]]]

  override def createItems[ItemT <: RequestType[_]](
    template: Seq[ItemT]
  )(implicit ct: ClassTag[ItemT]): Either[Exception, Seq[Either[OperationFailure, OperationSuccess]]] =
    createItemsResult

  override def createItem[ItemT <: RequestType[_]](
    template: ItemT
  )(implicit ct: ClassTag[ItemT]): Either[Exception, Option[Either[OperationFailure, OperationSuccess]]] =
    createItemResult
}

