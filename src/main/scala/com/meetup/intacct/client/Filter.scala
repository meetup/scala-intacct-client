package com.meetup.intacct.client

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.meetup.intacct.request.{Expression, Logical}

import scala.language.{existentials, implicitConversions}
import scala.collection.JavaConverters._
import com.meetup.intacct.request.Field
import com.meetup.intacct.request.Value

/**
 * A class representing a single filter for a query. The root of the filter can either contain a Logical or an Expression
 */
sealed trait Filter {
  type T
  def toJaxb: T
  final def and(other: Filter) =
    FilterLogical(Operator.and, Seq(this, other))
  final def or(other: Filter) =
    FilterLogical(Operator.or, Seq(this, other))
}
object DSL {
  implicit class filterFromFieldName(fieldName: String) {
    def is(value: QueryValue[_]) = FilterExpression(fieldName, Operator.`=`, value)
    def gt(value: QueryValue[_]) = FilterExpression(fieldName, Operator.>, value)
    def lt(value: QueryValue[_]) = FilterExpression(fieldName, Operator.<, value)
    def like(value: QueryValue[_]) = FilterExpression(fieldName, Operator.like, value)
  }
}
case class FilterExpression(
    val fieldName: String,
    val operator: QueryOperator,
    val value: QueryValue[_]) extends Filter {
  override type T = Expression
  override def toJaxb: T =
    new Expression()
      .withField(new Field().withvalue(fieldName))
      .withOperator(operator.toString())
      .withValue(new Value().withvalue(value.repr))
}
case class FilterLogical(
    val operator: LogicalOperator,
    val clauses: Seq[Filter]) extends Filter {
  override type T = Logical
  override def toJaxb: T =
    new Logical()
      .withLogicalOperator(operator.toString())
      .withLogicalOrExpression(clauses.map(_.toJaxb.asInstanceOf[Object]).asJavaCollection)
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
