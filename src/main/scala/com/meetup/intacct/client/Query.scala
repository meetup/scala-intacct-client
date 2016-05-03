package com.meetup.intacct.client

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.meetup.intacct.request.{Expression, Logical}

import scala.language.{existentials, implicitConversions}

/**
 * Represents a new query
 *
 * @param filters
 */
class Query(val filters: Seq[Filter] = Nil) {

  def this(filter: Filter) {
    this(Seq(filter))
  }

  /**
   * Return a new query object with the additional provided filter
   *
   * @param filter
   * @return
   */
  def withFilter(filter: Filter): Query = new Query(filters :+ filter)

  /**
   * Return a new quest object with the additional provided filters
   *
   * @param filter
   * @return
   */
  def withFilter(filter: Seq[Filter]): Query = filter.foldLeft(this)((acc, f) => acc.withFilter(f))

  /**
   * Return a new query object representing the combination of each
   * query's filters
   *
   * @param other
   * @return
   */
  def combine(other: Query) = new Query(filters ++ other.filters)

}
object Query {
  def apply(filters: Seq[Filter] = Nil) = new Query(filters)
  def apply(filter: Filter): Query = apply(Seq(filter))
  def unapply(query: Query) = Some(query.filters)
}

/**
 * A class representing a single filter for a query. The root of the filter can either contain a Logical or an Expression
 * We provide an ExpressionTemplate class for convenience.
 */
case class Filter(expressionOrLogical: Either[Expression, Logical])

/**
 * All available filter operators are members of this object
 */
object Operator {

  sealed trait QueryOperator

  case object eq extends QueryOperator { override def toString = "=" }
  case object > extends QueryOperator { override def toString = ">" }
  case object < extends QueryOperator { override def toString = "<" }
  case object like extends QueryOperator { override def toString = "like" }
}

// These types are allowed as filter values
abstract class QueryValue[T](v: T) {
  def repr: String
}
object QueryValue {
  implicit def stringToQueryValue(s: String): StringValue = new StringValue(s)
  implicit def dateToQueryValue(d: ZonedDateTime): DateValue = new DateValue(d)
}
class StringValue(v: String) extends QueryValue[String](v) {
  override def repr: String = v
}
class DateValue(v: ZonedDateTime) extends QueryValue[ZonedDateTime](v) {
  override def repr: String = v.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}
