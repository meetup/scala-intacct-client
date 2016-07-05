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
  _
}

///
/// Request types
///

final class CreateApaccountlabelItem(underlying: CreateApaccountlabel) extends RequestType[CreateApaccountlabel](underlying)

final class CreateApadjustmentItem(underlying: CreateApadjustment) extends RequestType[CreateApadjustment](underlying)

final class CreateApadjustmentbatchItem(underlying: CreateApadjustmentbatch) extends RequestType[CreateApadjustmentbatch](underlying)

final class CreateAraccountlabelItem(underlying: CreateAraccountlabel) extends RequestType[CreateAraccountlabel](underlying)

final class CreateAradjustmentItem(underlying: CreateAradjustment) extends RequestType[CreateAradjustment](underlying)

final class CreateAradjustmentbatchItem(underlying: CreateAradjustmentbatch) extends RequestType[CreateAradjustmentbatch](underlying)

final class CreateArpaymentItem(underlying: CreateArpayment) extends RequestType[CreateArpayment](underlying)

final class CreateArpaymentbatchItem(underlying: CreateArpaymentbatch) extends RequestType[CreateArpaymentbatch](underlying)

final class CreateBillItem(underlying: CreateBill) extends RequestType[CreateBill](underlying)

final class CreateRecurringbillItem(underlying: CreateRecurringbill) extends RequestType[CreateRecurringbill](underlying)

final class CreateBillbatchItem(underlying: CreateBillbatch) extends RequestType[CreateBillbatch](underlying)

final class CreateCheckingaccountItem(underlying: CreateCheckingaccount) extends RequestType[CreateCheckingaccount](underlying)

final class CreateSavingsaccountItem(underlying: CreateSavingsaccount) extends RequestType[CreateSavingsaccount](underlying)

final class UpdateCheckingaccountItem(underlying: UpdateCheckingaccount) extends RequestType[UpdateCheckingaccount](underlying)

final class UpdateSavingsaccountItem(underlying: UpdateSavingsaccount) extends RequestType[UpdateSavingsaccount](underlying)

final class DeleteCheckingaccountItem(underlying: DeleteCheckingaccount) extends RequestType[DeleteCheckingaccount](underlying)

final class DeleteSavingsaccountItem(underlying: DeleteSavingsaccount) extends RequestType[DeleteSavingsaccount](underlying)

final class CreateContactItem(underlying: CreateContact) extends RequestType[CreateContact](underlying)

final class CreateCustomerItem(underlying: CreateCustomer) extends RequestType[CreateCustomer](underlying)

final class CreateDepartmentItem(underlying: CreateDepartment) extends RequestType[CreateDepartment](underlying)

final class CreateEmployeeItem(underlying: CreateEmployee) extends RequestType[CreateEmployee](underlying)

final class CreateExpensereportItem(underlying: CreateExpensereport) extends RequestType[CreateExpensereport](underlying)

final class CreateExpensereportbatchItem(underlying: CreateExpensereportbatch) extends RequestType[CreateExpensereportbatch](underlying)

final class CreateExpensetypeItem(underlying: CreateExpensetype) extends RequestType[CreateExpensetype](underlying)

final class CreateGlaccountItem(underlying: CreateGlaccount) extends RequestType[CreateGlaccount](underlying)

final class CreateStatglaccountItem(underlying: CreateStatglaccount) extends RequestType[CreateStatglaccount](underlying)

final class CreateGltransactionItem(underlying: CreateGltransaction) extends RequestType[CreateGltransaction](underlying)

final class DeleteGltransactionItem(underlying: DeleteGltransaction) extends RequestType[DeleteGltransaction](underlying)

final class CreateRecurringgltransactionItem(underlying: CreateRecurringgltransaction) extends RequestType[CreateRecurringgltransaction](underlying)

final class DeleteRecurringgltransactionItem(underlying: DeleteRecurringgltransaction) extends RequestType[DeleteRecurringgltransaction](underlying)

final class CreateStatgltransactionItem(underlying: CreateStatgltransaction) extends RequestType[CreateStatgltransaction](underlying)

final class CreateRecurringstatgltransItem(underlying: CreateRecurringstatgltrans) extends RequestType[CreateRecurringstatgltrans](underlying)

final class DeleteRecurringstatgltransItem(underlying: DeleteRecurringstatgltrans) extends RequestType[DeleteRecurringstatgltrans](underlying)

final class CreateInvoiceItem(underlying: CreateInvoice) extends RequestType[CreateInvoice](underlying)

final class CreateRecurringinvoiceItem(underlying: CreateRecurringinvoice) extends RequestType[CreateRecurringinvoice](underlying)

final class CreateInvoicebatchItem(underlying: CreateInvoicebatch) extends RequestType[CreateInvoicebatch](underlying)

final class CreateJournalItem(underlying: CreateJournal) extends RequestType[CreateJournal](underlying)

final class CreateLocationItem(underlying: CreateLocation) extends RequestType[CreateLocation](underlying)

final class CreateStatjournalItem(underlying: CreateStatjournal) extends RequestType[CreateStatjournal](underlying)

final class CreateProjectItem(underlying: CreateProject) extends RequestType[CreateProject](underlying)

final class UpdateProjectItem(underlying: UpdateProject) extends RequestType[UpdateProject](underlying)

final class DeleteProjectItem(underlying: DeleteProject) extends RequestType[DeleteProject](underlying)

final class CreateClassItem(underlying: CreateClass) extends RequestType[CreateClass](underlying)

final class UpdateClassItem(underlying: UpdateClass) extends RequestType[UpdateClass](underlying)

final class DeleteClassItem(underlying: DeleteClass) extends RequestType[DeleteClass](underlying)

final class CreateVendorItem(underlying: CreateVendor) extends RequestType[CreateVendor](underlying)

final class DeleteApaccountlabelItem(underlying: DeleteApaccountlabel) extends RequestType[DeleteApaccountlabel](underlying)

final class DeleteApadjustmentItem(underlying: DeleteApadjustment) extends RequestType[DeleteApadjustment](underlying)

final class CreateLocationgroupItem(underlying: CreateLocationgroup) extends RequestType[CreateLocationgroup](underlying)

final class DeleteAraccountlabelItem(underlying: DeleteAraccountlabel) extends RequestType[DeleteAraccountlabel](underlying)

final class DeleteAradjustmentItem(underlying: DeleteAradjustment) extends RequestType[DeleteAradjustment](underlying)

final class DeleteBillItem(underlying: DeleteBill) extends RequestType[DeleteBill](underlying)

final class DeleteRecurringbillItem(underlying: DeleteRecurringbill) extends RequestType[DeleteRecurringbill](underlying)

final class DeleteContactItem(underlying: DeleteContact) extends RequestType[DeleteContact](underlying)

final class DeleteCustomerItem(underlying: DeleteCustomer) extends RequestType[DeleteCustomer](underlying)

final class DeleteDepartmentItem(underlying: DeleteDepartment) extends RequestType[DeleteDepartment](underlying)

final class DeleteEmployeeItem(underlying: DeleteEmployee) extends RequestType[DeleteEmployee](underlying)

final class DeleteExpensereportItem(underlying: DeleteExpensereport) extends RequestType[DeleteExpensereport](underlying)

final class ReverseExpensereportItem(underlying: ReverseExpensereport) extends RequestType[ReverseExpensereport](underlying)

final class DeleteExpensetypeItem(underlying: DeleteExpensetype) extends RequestType[DeleteExpensetype](underlying)

final class DeleteGlaccountItem(underlying: DeleteGlaccount) extends RequestType[DeleteGlaccount](underlying)

final class DeleteStatglaccountItem(underlying: DeleteStatglaccount) extends RequestType[DeleteStatglaccount](underlying)

final class DeleteInvoiceItem(underlying: DeleteInvoice) extends RequestType[DeleteInvoice](underlying)

final class ReverseInvoiceItem(underlying: ReverseInvoice) extends RequestType[ReverseInvoice](underlying)

final class DeleteRecurringinvoiceItem(underlying: DeleteRecurringinvoice) extends RequestType[DeleteRecurringinvoice](underlying)

final class DeleteJournalItem(underlying: DeleteJournal) extends RequestType[DeleteJournal](underlying)

final class DeleteStatjournalItem(underlying: DeleteStatjournal) extends RequestType[DeleteStatjournal](underlying)

final class DeleteLocationItem(underlying: DeleteLocation) extends RequestType[DeleteLocation](underlying)

final class DeleteVendorItem(underlying: DeleteVendor) extends RequestType[DeleteVendor](underlying)

final class GetAccountbalancesItem(underlying: GetAccountbalances) extends RequestType[GetAccountbalances](underlying)

final class GetAccountgroupdetailsItem(underlying: GetAccountgroupdetails) extends RequestType[GetAccountgroupdetails](underlying)

final class GetApadjustmentItem(underlying: GetApadjustment) extends RequestType[GetApadjustment](underlying)

final class GetAradjustmentItem(underlying: GetAradjustment) extends RequestType[GetAradjustment](underlying)

final class GetBillItem(underlying: GetBill) extends RequestType[GetBill](underlying)

final class GetExpensereportItem(underlying: GetExpensereport) extends RequestType[GetExpensereport](underlying)

final class GetInvoiceItem(underlying: GetInvoice) extends RequestType[GetInvoice](underlying)

final class GetListItem(underlying: GetList) extends RequestType[GetList](underlying)

final class GetTrialbalanceItem(underlying: GetTrialbalance) extends RequestType[GetTrialbalance](underlying)

final class GetMyclientsItem(underlying: GetMyclients) extends RequestType[GetMyclients](underlying)

final class InitSessionItem(underlying: InitSession) extends RequestType[InitSession](underlying)

final class GetAPISessionItem(underlying: GetAPISession) extends RequestType[GetAPISession](underlying)

final class UpdateApaccountlabelItem(underlying: UpdateApaccountlabel) extends RequestType[UpdateApaccountlabel](underlying)

final class UpdateAraccountlabelItem(underlying: UpdateAraccountlabel) extends RequestType[UpdateAraccountlabel](underlying)

final class UpdateContactItem(underlying: UpdateContact) extends RequestType[UpdateContact](underlying)

final class UpdateCustomerItem(underlying: UpdateCustomer) extends RequestType[UpdateCustomer](underlying)

final class UpdateCustomervisibilityItem(underlying: UpdateCustomervisibility) extends RequestType[UpdateCustomervisibility](underlying)

final class UpdateDepartmentItem(underlying: UpdateDepartment) extends RequestType[UpdateDepartment](underlying)

final class UpdateInvoiceItem(underlying: UpdateInvoice) extends RequestType[UpdateInvoice](underlying)

final class UpdateBillItem(underlying: UpdateBill) extends RequestType[UpdateBill](underlying)

final class UpdateApadjustmentItem(underlying: UpdateApadjustment) extends RequestType[UpdateApadjustment](underlying)

final class UpdateAradjustmentItem(underlying: UpdateAradjustment) extends RequestType[UpdateAradjustment](underlying)

final class UpdateEmployeeItem(underlying: UpdateEmployee) extends RequestType[UpdateEmployee](underlying)

final class UpdateExpensereportItem(underlying: UpdateExpensereport) extends RequestType[UpdateExpensereport](underlying)

final class UpdateExpensetypeItem(underlying: UpdateExpensetype) extends RequestType[UpdateExpensetype](underlying)

final class UpdateGlaccountItem(underlying: UpdateGlaccount) extends RequestType[UpdateGlaccount](underlying)

final class UpdateStatglaccountItem(underlying: UpdateStatglaccount) extends RequestType[UpdateStatglaccount](underlying)

final class UpdateJournalItem(underlying: UpdateJournal) extends RequestType[UpdateJournal](underlying)

final class UpdateLocationItem(underlying: UpdateLocation) extends RequestType[UpdateLocation](underlying)

final class UpdateVendorItem(underlying: UpdateVendor) extends RequestType[UpdateVendor](underlying)

final class UpdateVendorvisibilityItem(underlying: UpdateVendorvisibility) extends RequestType[UpdateVendorvisibility](underlying)

final class CreatePaymentrequestItem(underlying: CreatePaymentrequest) extends RequestType[CreatePaymentrequest](underlying)

final class ReclassifyBillItem(underlying: ReclassifyBill) extends RequestType[ReclassifyBill](underlying)

final class ReclassifyInvoiceItem(underlying: ReclassifyInvoice) extends RequestType[ReclassifyInvoice](underlying)

final class DeletePaymentrequestItem(underlying: DeletePaymentrequest) extends RequestType[DeletePaymentrequest](underlying)

final class CreateAppaymentItem(underlying: CreateAppayment) extends RequestType[CreateAppayment](underlying)

final class ApproveAppaymentrequestItem(underlying: ApproveAppaymentrequest) extends RequestType[ApproveAppaymentrequest](underlying)

final class SendAppaymentrequestItem(underlying: SendAppaymentrequest) extends RequestType[SendAppaymentrequest](underlying)

final class ConfirmAppaymentrequestItem(underlying: ConfirmAppaymentrequest) extends RequestType[ConfirmAppaymentrequest](underlying)

final class VoidAppaymentrequestItem(underlying: VoidAppaymentrequest) extends RequestType[VoidAppaymentrequest](underlying)

final class CreateStkittransactionItem(underlying: CreateStkittransaction) extends RequestType[CreateStkittransaction](underlying)

final class CreateIctransactionItem(underlying: CreateIctransaction) extends RequestType[CreateIctransaction](underlying)

final class UpdateIctransactionItem(underlying: UpdateIctransaction) extends RequestType[UpdateIctransaction](underlying)

final class CreateSotransactionItem(underlying: CreateSotransaction) extends RequestType[CreateSotransaction](underlying)

final class UpdateSotransactionItem(underlying: UpdateSotransaction) extends RequestType[UpdateSotransaction](underlying)

final class CreateRecursotransactionItem(underlying: CreateRecursotransaction) extends RequestType[CreateRecursotransaction](underlying)

final class DeleteRecursotransactionItem(underlying: DeleteRecursotransaction) extends RequestType[DeleteRecursotransaction](underlying)

final class CreatePotransactionItem(underlying: CreatePotransaction) extends RequestType[CreatePotransaction](underlying)

final class UpdatePotransactionItem(underlying: UpdatePotransaction) extends RequestType[UpdatePotransaction](underlying)

final class GetSalestotalsItem(underlying: GetSalestotals) extends RequestType[GetSalestotals](underlying)

final class GetIcitemtotalsItem(underlying: GetIcitemtotals) extends RequestType[GetIcitemtotals](underlying)

final class RecordCctransactionItem(underlying: RecordCctransaction) extends RequestType[RecordCctransaction](underlying)

final class RecordWucctransactionsItem(underlying: RecordWucctransactions) extends RequestType[RecordWucctransactions](underlying)

final class RecordWureceiptsItem(underlying: RecordWureceipts) extends RequestType[RecordWureceipts](underlying)

final class RecordWudisbursementsItem(underlying: RecordWudisbursements) extends RequestType[RecordWudisbursements](underlying)

final class RecordWuadjjournalentriesItem(underlying: RecordWuadjjournalentries) extends RequestType[RecordWuadjjournalentries](underlying)

final class RecordWujournalentriesItem(underlying: RecordWujournalentries) extends RequestType[RecordWujournalentries](underlying)

final class GetCompanyprefsItem(underlying: GetCompanyprefs) extends RequestType[GetCompanyprefs](underlying)

final class SetCompanyprefsItem(underlying: SetCompanyprefs) extends RequestType[SetCompanyprefs](underlying)

final class GetApplicationsItem(underlying: GetApplications) extends RequestType[GetApplications](underlying)

final class RecordOtherreceiptItem(underlying: RecordOtherreceipt) extends RequestType[RecordOtherreceipt](underlying)

final class RecordDepositItem(underlying: RecordDeposit) extends RequestType[RecordDeposit](underlying)

final class CreateTerritoryItem(underlying: CreateTerritory) extends RequestType[CreateTerritory](underlying)

final class DeleteTerritoryItem(underlying: DeleteTerritory) extends RequestType[DeleteTerritory](underlying)

final class UpdateTerritoryItem(underlying: UpdateTerritory) extends RequestType[UpdateTerritory](underlying)

final class ApplyaRpaymentItem(underlying: ApplyArpayment) extends RequestType[ApplyArpayment](underlying)

final class DeleteSotransactionItem(underlying: DeleteSotransaction) extends RequestType[DeleteSotransaction](underlying)

final class DeletePotransactionItem(underlying: DeletePotransaction) extends RequestType[DeletePotransaction](underlying)

final class DeleteIctransactionItem(underlying: DeleteIctransaction) extends RequestType[DeleteIctransaction](underlying)

final class CreateItemItem(underlying: CreateItem) extends RequestType[CreateItem](underlying)

final class UpdateItemItem(underlying: UpdateItem) extends RequestType[UpdateItem](underlying)

final class DeleteItemItem(underlying: DeleteItem) extends RequestType[DeleteItem](underlying)

final class CreateSopricelistItem(underlying: CreateSopricelist) extends RequestType[CreateSopricelist](underlying)

final class DeleteSopricelistItem(underlying: DeleteSopricelist) extends RequestType[DeleteSopricelist](underlying)

final class UpdateSopricelistItem(underlying: UpdateSopricelist) extends RequestType[UpdateSopricelist](underlying)

final class CreatePopricelistItem(underlying: CreatePopricelist) extends RequestType[CreatePopricelist](underlying)

final class DeletePopricelistItem(underlying: DeletePopricelist) extends RequestType[DeletePopricelist](underlying)

final class UpdatePopricelistItem(underlying: UpdatePopricelist) extends RequestType[UpdatePopricelist](underlying)

final class CreateVsoepricelistItem(underlying: CreateVsoepricelist) extends RequestType[CreateVsoepricelist](underlying)

final class UpdateVsoepricelistItem(underlying: UpdateVsoepricelist) extends RequestType[UpdateVsoepricelist](underlying)

final class DeleteVsoepricelistItem(underlying: DeleteVsoepricelist) extends RequestType[DeleteVsoepricelist](underlying)

final class CreateVsoeitempricelistItem(underlying: CreateVsoeitempricelist) extends RequestType[CreateVsoeitempricelist](underlying)

final class UpdateVsoeitempricelistItem(underlying: UpdateVsoeitempricelist) extends RequestType[UpdateVsoeitempricelist](underlying)

final class DeleteVsoeitempricelistItem(underlying: DeleteVsoeitempricelist) extends RequestType[DeleteVsoeitempricelist](underlying)

final class CreateInvpricelistentryItem(underlying: CreateInvpricelistentry) extends RequestType[CreateInvpricelistentry](underlying)

final class DeleteInvpricelistentryItem(underlying: DeleteInvpricelistentry) extends RequestType[DeleteInvpricelistentry](underlying)

final class UpdateInvpricelistentryItem(underlying: UpdateInvpricelistentry) extends RequestType[UpdateInvpricelistentry](underlying)

final class GetClosedbooksdateItem(underlying: GetClosedbooksdate) extends RequestType[GetClosedbooksdate](underlying)

final class GetAragingItem(underlying: GetAraging) extends RequestType[GetAraging](underlying)

final class DeleteArpaymentItem(underlying: DeleteArpayment) extends RequestType[DeleteArpayment](underlying)

final class CreateCustomerachinfoItem(underlying: CreateCustomerachinfo) extends RequestType[CreateCustomerachinfo](underlying)

final class UpdateCustomerachinfoItem(underlying: UpdateCustomerachinfo) extends RequestType[UpdateCustomerachinfo](underlying)

final class DeleteCustomerachinfoItem(underlying: DeleteCustomerachinfo) extends RequestType[DeleteCustomerachinfo](underlying)

final class CreateCustomerchargecardItem(underlying: CreateCustomerchargecard) extends RequestType[CreateCustomerchargecard](underlying)

final class UpdateCustomerchargecardItem(underlying: UpdateCustomerchargecard) extends RequestType[UpdateCustomerchargecard](underlying)

final class DeleteCustomerchargecardItem(underlying: DeleteCustomerchargecard) extends RequestType[DeleteCustomerchargecard](underlying)

final class CreateCustomerbankaccountItem(underlying: CreateCustomerbankaccount) extends RequestType[CreateCustomerbankaccount](underlying)

final class UpdateCustomerbankaccountItem(underlying: UpdateCustomerbankaccount) extends RequestType[UpdateCustomerbankaccount](underlying)

final class DeleteCustomerbankaccountItem(underlying: DeleteCustomerbankaccount) extends RequestType[DeleteCustomerbankaccount](underlying)

final class CreateTaxdetailItem(underlying: CreateTaxdetail) extends RequestType[CreateTaxdetail](underlying)

final class UpdateTaxdetailItem(underlying: UpdateTaxdetail) extends RequestType[UpdateTaxdetail](underlying)

final class DeleteTaxdetailItem(underlying: DeleteTaxdetail) extends RequestType[DeleteTaxdetail](underlying)

final class CreateTaxscheduleItem(underlying: CreateTaxschedule) extends RequestType[CreateTaxschedule](underlying)

final class UpdateTaxscheduleItem(underlying: UpdateTaxschedule) extends RequestType[UpdateTaxschedule](underlying)

final class DeleteTaxscheduleItem(underlying: DeleteTaxschedule) extends RequestType[DeleteTaxschedule](underlying)

final class CreateTaxscheduledetailItem(underlying: CreateTaxscheduledetail) extends RequestType[CreateTaxscheduledetail](underlying)

final class DeleteTaxscheduledetailItem(underlying: DeleteTaxscheduledetail) extends RequestType[DeleteTaxscheduledetail](underlying)

final class CreateContacttaxgroupItem(underlying: CreateContacttaxgroup) extends RequestType[CreateContacttaxgroup](underlying)

final class DeleteContacttaxgroupItem(underlying: DeleteContacttaxgroup) extends RequestType[DeleteContacttaxgroup](underlying)

final class CreateItemtaxgroupItem(underlying: CreateItemtaxgroup) extends RequestType[CreateItemtaxgroup](underlying)

final class DeleteItemtaxgroupItem(underlying: DeleteItemtaxgroup) extends RequestType[DeleteItemtaxgroup](underlying)

final class CreateTaxschedulemapItem(underlying: CreateTaxschedulemap) extends RequestType[CreateTaxschedulemap](underlying)

final class DeleteTaxschedulemapItem(underlying: DeleteTaxschedulemap) extends RequestType[DeleteTaxschedulemap](underlying)

final class DescribeItem(underlying: Describe) extends RequestType[Describe](underlying)

final class DescribeallItem(underlying: Describeall) extends RequestType[Describeall](underlying)

final class ReconcileBankItem(underlying: ReconcileBank) extends RequestType[ReconcileBank](underlying)

final class ReverseBillItem(underlying: ReverseBill) extends RequestType[ReverseBill](underlying)

final class ReverseAppaymentItem(underlying: ReverseAppayment) extends RequestType[ReverseAppayment](underlying)

final class ReverseArpaymentItem(underlying: ReverseArpayment) extends RequestType[ReverseArpayment](underlying)

final class CreateAptermItem(underlying: CreateApterm) extends RequestType[CreateApterm](underlying)

final class UpdateAptermItem(underlying: UpdateApterm) extends RequestType[UpdateApterm](underlying)

final class DeleteAptermItem(underlying: DeleteApterm) extends RequestType[DeleteApterm](underlying)

final class CreateArtermItem(underlying: CreateArterm) extends RequestType[CreateArterm](underlying)

final class UpdateArtermItem(underlying: UpdateArterm) extends RequestType[UpdateArterm](underlying)

final class DeleteArtermItem(underlying: DeleteArterm) extends RequestType[DeleteArterm](underlying)

final class CreateTimesheetItem(underlying: CreateTimesheet) extends RequestType[CreateTimesheet](underlying)

final class UpdateTimesheetItem(underlying: UpdateTimesheet) extends RequestType[UpdateTimesheet](underlying)

final class DeleteTimesheetItem(underlying: DeleteTimesheet) extends RequestType[DeleteTimesheet](underlying)

final class CreateTaskItem(underlying: CreateTask) extends RequestType[CreateTask](underlying)

final class UpdateTaskItem(underlying: UpdateTask) extends RequestType[UpdateTask](underlying)

final class DeleteTaskItem(underlying: DeleteTask) extends RequestType[DeleteTask](underlying)

final class CreateAllocationItem(underlying: CreateAllocation) extends RequestType[CreateAllocation](underlying)

final class UpdateAllocationItem(underlying: UpdateAllocation) extends RequestType[UpdateAllocation](underlying)

final class DeleteAllocationItem(underlying: DeleteAllocation) extends RequestType[DeleteAllocation](underlying)

final class UpdateCctransactionItem(underlying: UpdateCctransaction) extends RequestType[UpdateCctransaction](underlying)

final class ReverseCctransactionItem(underlying: ReverseCctransaction) extends RequestType[ReverseCctransaction](underlying)

final class HoldRevrecscheduleItem(underlying: HoldRevrecschedule) extends RequestType[HoldRevrecschedule](underlying)

final class ResumeRevrecscheduleItem(underlying: ResumeRevrecschedule) extends RequestType[ResumeRevrecschedule](underlying)

final class TerminateRevrecscheduleItem(underlying: TerminateRevrecschedule) extends RequestType[TerminateRevrecschedule](underlying)

final class UpdateRevrecscheduleItem(underlying: UpdateRevrecschedule) extends RequestType[UpdateRevrecschedule](underlying)

final class CreateRevrecscheduleentryItem(underlying: CreateRevrecscheduleentry) extends RequestType[CreateRevrecscheduleentry](underlying)

final class PostRevrecscheduleentryItem(underlying: PostRevrecscheduleentry) extends RequestType[PostRevrecscheduleentry](underlying)

final class UnpostRevrecscheduleentryItem(underlying: UnpostRevrecscheduleentry) extends RequestType[UnpostRevrecscheduleentry](underlying)

final class ReallocateRevrecscheduleItem(underlying: ReallocateRevrecschedule) extends RequestType[ReallocateRevrecschedule](underlying)

final class CreateExpenseadjustmentreportItem(underlying: CreateExpenseadjustmentreport) extends RequestType[CreateExpenseadjustmentreport](underlying)

final class UpdateExpenseadjustmentreportItem(underlying: UpdateExpenseadjustmentreport) extends RequestType[UpdateExpenseadjustmentreport](underlying)

final class DeleteExpenseadjustmentreportItem(underlying: DeleteExpenseadjustmentreport) extends RequestType[DeleteExpenseadjustmentreport](underlying)

final class GetExpenseadjustmentreportItem(underlying: GetExpenseadjustmentreport) extends RequestType[GetExpenseadjustmentreport](underlying)

final class CreateSupdocItem(underlying: CreateSupdoc) extends RequestType[CreateSupdoc](underlying)

final class UpdateSupdocItem(underlying: UpdateSupdoc) extends RequestType[UpdateSupdoc](underlying)

final class DeleteSupdocItem(underlying: DeleteSupdoc) extends RequestType[DeleteSupdoc](underlying)

final class CreateSupdocfolderItem(underlying: CreateSupdocfolder) extends RequestType[CreateSupdocfolder](underlying)

final class UpdateSupdocfolderItem(underlying: UpdateSupdocfolder) extends RequestType[UpdateSupdocfolder](underlying)

final class DeleteSupdocfolderItem(underlying: DeleteSupdocfolder) extends RequestType[DeleteSupdocfolder](underlying)

final class CreateTimetypeItem(underlying: CreateTimetype) extends RequestType[CreateTimetype](underlying)

final class UpdateTimetypeItem(underlying: UpdateTimetype) extends RequestType[UpdateTimetype](underlying)

final class DeleteTimetypeItem(underlying: DeleteTimetype) extends RequestType[DeleteTimetype](underlying)

final class CreateEarningtypeItem(underlying: CreateEarningtype) extends RequestType[CreateEarningtype](underlying)

final class UpdateEarningtypeItem(underlying: UpdateEarningtype) extends RequestType[UpdateEarningtype](underlying)

final class DeleteEarningtypeItem(underlying: DeleteEarningtype) extends RequestType[DeleteEarningtype](underlying)

final class CreateEmployeerateItem(underlying: CreateEmployeerate) extends RequestType[CreateEmployeerate](underlying)

final class UpdateEmployeerateItem(underlying: UpdateEmployeerate) extends RequestType[UpdateEmployeerate](underlying)

final class DeleteEmployeerateItem(underlying: DeleteEmployeerate) extends RequestType[DeleteEmployeerate](underlying)

final class CreateAchbankconfigurationItem(underlying: CreateAchbankconfiguration) extends RequestType[CreateAchbankconfiguration](underlying)

final class DeleteAchbankconfigurationItem(underlying: DeleteAchbankconfiguration) extends RequestType[DeleteAchbankconfiguration](underlying)

final class UpdateAchbankconfigurationItem(underlying: UpdateAchbankconfiguration) extends RequestType[UpdateAchbankconfiguration](underlying)

final class CreateVendorentityaccountnoItem(underlying: CreateVendorentityaccountno) extends RequestType[CreateVendorentityaccountno](underlying)

final class CreateConsolidationItem(underlying: CreateConsolidation) extends RequestType[CreateConsolidation](underlying)

final class ConvertGlbudgetFpAmountsToEopItem(underlying: ConvertGlbudgetFpAmountsToEop) extends RequestType[ConvertGlbudgetFpAmountsToEop](underlying)

final class CreateExpensepaymenttypeItem(underlying: CreateExpensepaymenttype) extends RequestType[CreateExpensepaymenttype](underlying)

final class UpdateExpensepaymenttypeItem(underlying: UpdateExpensepaymenttype) extends RequestType[UpdateExpensepaymenttype](underlying)

final class DeleteExpensepaymenttypeItem(underlying: DeleteExpensepaymenttype) extends RequestType[DeleteExpensepaymenttype](underlying)

final class GetUserPermissionsItem(underlying: GetUserPermissions) extends RequestType[GetUserPermissions](underlying)

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

