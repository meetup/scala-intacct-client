package com.meetup.intacct.client

import com.meetup.intacct.request._
import com.meetup.intacct.response.{Revrecscheduleentry, Revrecschedule, Gltransaction, Invoice}

// For now, we will explicitly bless the types we know to work to avoid user error
// These are final because the reflection in IntacctClient expects to find a JAXB
// entity on the superclass' type signature. It could be rewritten to be more general
// but doesn't need the flexibility.

// Feel free to add whatever other types you need here

///
/// Request types
///

final class CreateCustomerItem(underlying: CreateCustomer) extends RequestType[CreateCustomer](underlying)
final class CreateSotransactionItem(underlying: CreateSotransaction) extends RequestType[CreateSotransaction](underlying)
final class CreateArpaymentItem(underlying: CreateArpayment) extends RequestType[CreateArpayment](underlying)
final class CreateArpaymentbatchItem(underlying: CreateArpaymentbatch) extends RequestType[CreateArpaymentbatch](underlying)
final class CreateGltransactionItem(underlying: CreateGltransaction) extends RequestType[CreateGltransaction](underlying)
final class HoldRevrecscheduleItem(underlying: HoldRevrecschedule) extends RequestType[HoldRevrecschedule](underlying)

///
/// Response types
///

final class InvoiceItem(underlying: Invoice) extends ResponseType[Invoice](underlying)
final class GltransactionItem(underlying: Gltransaction) extends ResponseType[Gltransaction](underlying)
final class RevrecscheduleItem(underlying: Revrecschedule) extends ResponseType[Revrecschedule](underlying)
final class RevrecscheduleentryItem(underlying: Revrecscheduleentry) extends ResponseType[Revrecscheduleentry](underlying)
