package com.meetup.intacct.client

import scala.reflect.ClassTag

trait OutgoingClient {

  /**
   * Retreives items of type ItemT according to the passed Query if provided
   *
   * @param query An optional query
   * @param fields An optional list of fields to return
   * @param ct
   * @tparam ItemT
   * @return Matching items or an error
   */
  def getItems[ItemT <: ResponseType[_]](query: Option[Query], fields: Option[Iterable[String]] = None)(implicit ct: ClassTag[ItemT]): Either[Exception, Seq[ItemT]]

  /**
   * Creates items of type ItemT according to the passed template
   *
   * @param template A Seq of all entities that should be created
   * @param ct
   * @tparam ItemT
   * @return A throwable for a failed request, or a Seq of individual results for each requested item
   */
  def createItems[ItemT <: RequestType[_]](template: Seq[ItemT])(implicit ct: ClassTag[ItemT]): Either[Exception, Seq[Either[OperationFailure, OperationSuccess]]]

  /**
   * Creates item of type ItemT according to the passed template
   *
   * @param template The entity that should be created
   * @param ct
   * @tparam ItemT
   * @return A throwable for a failed request, or the individual result of the requested item
   */
  def createItem[ItemT <: RequestType[_]](template: ItemT)(implicit ct: ClassTag[ItemT]): Either[Exception, Option[Either[OperationFailure, OperationSuccess]]] =
    createItems[ItemT](Seq(template)).right.map(_.headOption)

}
