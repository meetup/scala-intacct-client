package com.meetup.intacct.client

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import com.meetup.intacct.request.{Expression, Field, Logical, Value}

import scala.language.{existentials, implicitConversions}
import scala.collection.JavaConverters._

/**
 * A trait representing a single or compound filter.
 */
sealed trait Filter {
  type T
  def toJaxb: T
  final def and(other: Filter) =
    FilterLogical(Operator.and, Seq(this, other))
  final def or(other: Filter) =
    FilterLogical(Operator.or, Seq(this, other))
}

/**
 * Useful helper methods to simplify creating filters.
 *
 *     import com.meetup.intacct.client.DSL._
 *     "my_field_name" is "my_value"
 *
 * rather than
 *
 *     FilterExpression("my_field_name", Operator.`=`, "my_value")
 */
object DSL {
  implicit class filterFromFieldName(fieldName: String) {
    def is(value: FilterValue[_]) = FilterExpression(fieldName, Operator.`=`, value)
    def gt(value: FilterValue[_]) = FilterExpression(fieldName, Operator.>, value)
    def lt(value: FilterValue[_]) = FilterExpression(fieldName, Operator.<, value)
    def like(value: FilterValue[_]) = FilterExpression(fieldName, Operator.like, value)
  }
}
case class FilterExpression(
    val fieldName: String,
    val operator: FilterOperator,
    val value: FilterValue[_]) extends Filter {
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

sealed trait FilterOperator
sealed trait LogicalOperator

/**
 * All available filter operators are members of this object
 */
object Operator {
  case object `=` extends FilterOperator { override def toString = "=" }
  case object > extends FilterOperator { override def toString = ">" }
  case object < extends FilterOperator { override def toString = "<" }
  case object like extends FilterOperator { override def toString = "like" }
  case object and extends LogicalOperator { override def toString = "and" }
  case object or extends LogicalOperator { override def toString = "or" }
}

// These types are allowed as filter values. The implicit conversions should
// happen without needing to import anything.
abstract class FilterValue[T](v: T) {
  def repr: String
}
object FilterValue {
  implicit def stringToFilterValue(s: String): StringValue = new StringValue(s)
  implicit def dateToFilterValue(d: ZonedDateTime): DateValue = new DateValue(d)
}
class StringValue(v: String) extends FilterValue[String](v) {
  override def repr: String = v
}
class DateValue(v: ZonedDateTime) extends FilterValue[ZonedDateTime](v) {
  override def repr: String = v.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}
