package com.meetup.intacct.client

import com.meetup.intacct.request._
import com.meetup.intacct.response.{
  Accountgroup => RespAccountgroup,
  Contact => RespContact,
  Department => RespDepartment,
  Glentry => RespGlentry,
  Gltransaction => RespGltransaction,
  Contacttaxgroup => RespContacttaxgroup,
  Itemtaxgroup => RespItemtaxgroup,
  _}

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

final class AccountgroupItem(underlying: RespAccountgroup) extends ResponseType[RespAccountgroup](underlying)

final class AdjjournalItem(underlying: Adjjournal) extends ResponseType[Adjjournal](underlying)

final class StatjournalItem(underlying: Statjournal) extends ResponseType[Statjournal](underlying)

final class ApaccountlabelItem(underlying: Apaccountlabel) extends ResponseType[Apaccountlabel](underlying)

final class ApadjustmentItem(underlying: Apadjustment) extends ResponseType[Apadjustment](underlying)

final class ApadjustmentbatchItem(underlying: Apadjustmentbatch) extends ResponseType[Apadjustmentbatch](underlying)

final class AppaymentItem(underlying: Appayment) extends ResponseType[Appayment](underlying)

final class AptermItem(underlying: Apterm) extends ResponseType[Apterm](underlying)

final class ProjectItem(underlying: Project) extends ResponseType[Project](underlying)

final class ClassItem(underlying: Class) extends ResponseType[Class](underlying)

final class AraccountlabelItem(underlying: Araccountlabel) extends ResponseType[Araccountlabel](underlying)

final class AradjustmentItem(underlying: Aradjustment) extends ResponseType[Aradjustment](underlying)

final class AradjustmentbatchItem(underlying: Aradjustmentbatch) extends ResponseType[Aradjustmentbatch](underlying)

final class ArpaymentItem(underlying: Arpayment) extends ResponseType[Arpayment](underlying)

final class ArpaymentbatchItem(underlying: Arpaymentbatch) extends ResponseType[Arpaymentbatch](underlying)

final class ArtermItem(underlying: Arterm) extends ResponseType[Arterm](underlying)

final class ArtransactiondefItem(underlying: Artransactiondef) extends ResponseType[Artransactiondef](underlying)

final class BankaccountItem(underlying: Bankaccount) extends ResponseType[Bankaccount](underlying)

final class BillbatchItem(underlying: Billbatch) extends ResponseType[Billbatch](underlying)

final class ContactItem(underlying: RespContact) extends ResponseType[RespContact](underlying)

final class CustglgroupItem(underlying: Custglgroup) extends ResponseType[Custglgroup](underlying)

final class CustomerItem(underlying: Customer) extends ResponseType[Customer](underlying)

final class CustomervisibilityItem(underlying: Customervisibility) extends ResponseType[Customervisibility](underlying)

final class CustomerachinfoItem(underlying: Customerachinfo) extends ResponseType[Customerachinfo](underlying)

final class CustomerchargecardItem(underlying: Customerchargecard) extends ResponseType[Customerchargecard](underlying)

final class CustomerbankaccountItem(underlying: Customerbankaccount) extends ResponseType[Customerbankaccount](underlying)

final class DepartmentItem(underlying: RespDepartment) extends ResponseType[RespDepartment](underlying)

final class EmployeeItem(underlying: Employee) extends ResponseType[Employee](underlying)

final class ExpensereportItem(underlying: Expensereport) extends ResponseType[Expensereport](underlying)

final class ExpensereportbatchItem(underlying: Expensereportbatch) extends ResponseType[Expensereportbatch](underlying)

final class ExpensetypesItem(underlying: Expensetypes) extends ResponseType[Expensetypes](underlying)

final class ExpensepaymenttypeItem(underlying: Expensepaymenttype) extends ResponseType[Expensepaymenttype](underlying)

final class GlaccountItem(underlying: Glaccount) extends ResponseType[Glaccount](underlying)

final class StatglaccountItem(underlying: Statglaccount) extends ResponseType[Statglaccount](underlying)

final class GlbudgetitemItem(underlying: Glbudgetitem) extends ResponseType[Glbudgetitem](underlying)

final class GlentryItem(underlying: RespGlentry) extends ResponseType[RespGlentry](underlying)

final class GltransactionItem(underlying: RespGltransaction) extends ResponseType[RespGltransaction](underlying)

final class IcitemItem(underlying: Icitem) extends ResponseType[Icitem](underlying)

final class IctotalItem(underlying: Ictotal) extends ResponseType[Ictotal](underlying)

final class StkittransactionItem(underlying: Stkittransaction) extends ResponseType[Stkittransaction](underlying)

final class IctransactionItem(underlying: Ictransaction) extends ResponseType[Ictransaction](underlying)

final class IctransactiondefItem(underlying: Ictransactiondef) extends ResponseType[Ictransactiondef](underlying)

final class InvoiceItem(underlying: Invoice) extends ResponseType[Invoice](underlying)

final class InvoicebatchItem(underlying: Invoicebatch) extends ResponseType[Invoicebatch](underlying)

final class ItemglgroupItem(underlying: Itemglgroup) extends ResponseType[Itemglgroup](underlying)

final class JournalItem(underlying: Journal) extends ResponseType[Journal](underlying)

final class LocationItem(underlying: Location) extends ResponseType[Location](underlying)

final class LocationentityItem(underlying: Locationentity) extends ResponseType[Locationentity](underlying)

final class SupdocfolderItem(underlying: Supdocfolder) extends ResponseType[Supdocfolder](underlying)

final class SupdocItem(underlying: Supdoc) extends ResponseType[Supdoc](underlying)

final class Company_infoItem(underlying: CompanyInfo) extends ResponseType[CompanyInfo](underlying)

final class TrxcurrenciesItem(underlying: Trxcurrencies) extends ResponseType[Trxcurrencies](underlying)

final class PopricelistItem(underlying: Popricelist) extends ResponseType[Popricelist](underlying)

final class VsoepricelistItem(underlying: Vsoepricelist) extends ResponseType[Vsoepricelist](underlying)

final class VsoeitempricelistItem(underlying: Vsoeitempricelist) extends ResponseType[Vsoeitempricelist](underlying)

final class PotransactionItem(underlying: Potransaction) extends ResponseType[Potransaction](underlying)

final class PotransactiondefItem(underlying: Potransactiondef) extends ResponseType[Potransactiondef](underlying)

final class CsnhistoryItem(underlying: Csnhistory) extends ResponseType[Csnhistory](underlying)

final class LocationgroupItem(underlying: Locationgroup) extends ResponseType[Locationgroup](underlying)

final class PricelistitemItem(underlying: Pricelistitem) extends ResponseType[Pricelistitem](underlying)

final class ProductlineItem(underlying: Productline) extends ResponseType[Productline](underlying)

final class RenewalmacroItem(underlying: Renewalmacro) extends ResponseType[Renewalmacro](underlying)

final class ReportingperiodItem(underlying: Reportingperiod) extends ResponseType[Reportingperiod](underlying)

final class RevrecscheduleItem(underlying: Revrecschedule) extends ResponseType[Revrecschedule](underlying)

final class RevrecscheduleentryItem(underlying: Revrecscheduleentry) extends ResponseType[Revrecscheduleentry](underlying)

final class RevrectemplateItem(underlying: Revrectemplate) extends ResponseType[Revrectemplate](underlying)

final class SopricelistItem(underlying: Sopricelist) extends ResponseType[Sopricelist](underlying)

final class SotransactionItem(underlying: Sotransaction) extends ResponseType[Sotransaction](underlying)

final class RecursotransactionItem(underlying: Recursotransaction) extends ResponseType[Recursotransaction](underlying)

final class SotransactiondefItem(underlying: Sotransactiondef) extends ResponseType[Sotransactiondef](underlying)

final class SubscriptionItem(underlying: Subscription) extends ResponseType[Subscription](underlying)

final class TerritoryItem(underlying: Territory) extends ResponseType[Territory](underlying)

final class UomItem(underlying: Uom) extends ResponseType[Uom](underlying)

final class VendglgroupItem(underlying: Vendglgroup) extends ResponseType[Vendglgroup](underlying)

final class VendorItem(underlying: Vendor) extends ResponseType[Vendor](underlying)

final class VendorvisibilityItem(underlying: Vendorvisibility) extends ResponseType[Vendorvisibility](underlying)

final class WarehouseItem(underlying: Warehouse) extends ResponseType[Warehouse](underlying)

final class TaxdetailItem(underlying: Taxdetail) extends ResponseType[Taxdetail](underlying)

final class TaxscheduleItem(underlying: Taxschedule) extends ResponseType[Taxschedule](underlying)

final class TaxscheduledetailItem(underlying: Taxscheduledetail) extends ResponseType[Taxscheduledetail](underlying)

final class ContacttaxgroupItem(underlying: RespContacttaxgroup) extends ResponseType[RespContacttaxgroup](underlying)

final class ItemtaxgroupItem(underlying: RespItemtaxgroup) extends ResponseType[RespItemtaxgroup](underlying)

final class TaxschedulemapItem(underlying: Taxschedulemap) extends ResponseType[Taxschedulemap](underlying)

final class SmarteventlogItem(underlying: Smarteventlog) extends ResponseType[Smarteventlog](underlying)

final class AppaymentrequestItem(underlying: Appaymentrequest) extends ResponseType[Appaymentrequest](underlying)

final class IcitemtotalsItem(underlying: Icitemtotals) extends ResponseType[Icitemtotals](underlying)

final class ItemtotalItem(underlying: Itemtotal) extends ResponseType[Itemtotal](underlying)

final class TimesheetItem(underlying: Timesheet) extends ResponseType[Timesheet](underlying)

final class TaskItem(underlying: Task) extends ResponseType[Task](underlying)

final class ProjecttypeItem(underlying: Projecttype) extends ResponseType[Projecttype](underlying)

final class ProjectstatusItem(underlying: Projectstatus) extends ResponseType[Projectstatus](underlying)

final class TimetypeItem(underlying: Timetype) extends ResponseType[Timetype](underlying)

final class AllocationItem(underlying: Allocation) extends ResponseType[Allocation](underlying)

final class CctransactionItem(underlying: Cctransaction) extends ResponseType[Cctransaction](underlying)

final class RevrecchangelogItem(underlying: Revrecchangelog) extends ResponseType[Revrecchangelog](underlying)

final class VsoeallocationItem(underlying: Vsoeallocation) extends ResponseType[Vsoeallocation](underlying)

final class ExpenseadjustmentreportItem(underlying: Expenseadjustmentreport) extends ResponseType[Expenseadjustmentreport](underlying)

final class EarningtypeItem(underlying: Earningtype) extends ResponseType[Earningtype](underlying)

final class EmployeerateItem(underlying: Employeerate) extends ResponseType[Employeerate](underlying)

final class AchbankconfigurationItem(underlying: Achbankconfiguration) extends ResponseType[Achbankconfiguration](underlying)

final class VendorentityaccountItem(underlying: Vendorentityaccount) extends ResponseType[Vendorentityaccount](underlying)

final class VendorprefItem(underlying: Vendorpref) extends ResponseType[Vendorpref](underlying)

final class EmployeeprefItem(underlying: Employeepref) extends ResponseType[Employeepref](underlying)


