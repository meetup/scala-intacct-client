package com.meetup.intacct.client

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.meetup.intacct.request.{Expression, Logical}

import scala.language.{existentials, implicitConversions}
import scala.collection.JavaConverters._
import com.meetup.intacct.request.Field
import com.meetup.intacct.request.Value

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
 */
sealed trait Filter {
  def toJaxb: Object
  final def and(other: Filter): Filter =
    FilterLogical(Operator.and, Seq(this, other))
  final def and(other: Seq[Filter]): Filter =
    FilterLogical(Operator.and, this +: other)
  final def or(other: Filter): Filter =
    FilterLogical(Operator.or, Seq(this, other))
  final def or(other: Seq[Filter]): Filter =
    FilterLogical(Operator.or, this +: other)
}
object DSL {
  implicit class filterFromFieldName(fieldName: String) {
    def is(value: QueryValue[_]): Filter = FilterExpression(fieldName, Operator.`=`, value)
    def gt(value: QueryValue[_]): Filter = FilterExpression(fieldName, Operator.>, value)
    def lt(value: QueryValue[_]): Filter = FilterExpression(fieldName, Operator.<, value)
    def like(value: QueryValue[_]): Filter = FilterExpression(fieldName, Operator.like, value)
  }
}
case class FilterExpression(
    val fieldName: String,
    val operator: QueryOperator,
    val value: QueryValue[_]) extends Filter {
  override def toJaxb: Object =
    new Expression()
      .withField(new Field().withvalue(fieldName))
      .withOperator(operator.toString())
      .withValue(new Value().withvalue(value.repr))
}
case class FilterLogical(
    val operator: LogicalOperator,
    val clauses: Seq[Filter]) extends Filter {
  override def toJaxb: Object =
    new Logical()
      .withLogicalOperator(operator.toString())
      .withLogicalOrExpression(clauses.map(_.toJaxb).asJavaCollection)
}

/**
 * All available filter operators are members of this object
 */
sealed trait QueryOperator
sealed trait LogicalOperator
object Operator {
  case object `=` extends QueryOperator { override def toString = "=" }
  case object > extends QueryOperator { override def toString = ">" }
  case object < extends QueryOperator { override def toString = "<" }
  case object like extends QueryOperator { override def toString = "like" }
  case object and extends LogicalOperator { override def toString = "and" }
  case object or extends LogicalOperator { override def toString = "or" }
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
