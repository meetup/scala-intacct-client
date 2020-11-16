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

final case class CreateApaccountlabelItem(override val underlying: CreateApaccountlabel) extends RequestType[CreateApaccountlabel](underlying)

final case class CreateApadjustmentItem(override val underlying: CreateApadjustment) extends RequestType[CreateApadjustment](underlying)

final case class CreateApadjustmentbatchItem(override val underlying: CreateApadjustmentbatch) extends RequestType[CreateApadjustmentbatch](underlying)

final case class CreateAraccountlabelItem(override val underlying: CreateAraccountlabel) extends RequestType[CreateAraccountlabel](underlying)

final case class CreateAradjustmentItem(override val underlying: CreateAradjustment) extends RequestType[CreateAradjustment](underlying)

final case class CreateAradjustmentbatchItem(override val underlying: CreateAradjustmentbatch) extends RequestType[CreateAradjustmentbatch](underlying)

final case class CreateArpaymentItem(override val underlying: CreateArpayment) extends RequestType[CreateArpayment](underlying)

final case class CreateArpaymentbatchItem(override val underlying: CreateArpaymentbatch) extends RequestType[CreateArpaymentbatch](underlying)

final case class CreateBillItem(override val underlying: CreateBill) extends RequestType[CreateBill](underlying)

final case class CreateRecurringbillItem(override val underlying: CreateRecurringbill) extends RequestType[CreateRecurringbill](underlying)

final case class CreateBillbatchItem(override val underlying: CreateBillbatch) extends RequestType[CreateBillbatch](underlying)

final case class CreateCheckingaccountItem(override val underlying: CreateCheckingaccount) extends RequestType[CreateCheckingaccount](underlying)

final case class CreateSavingsaccountItem(override val underlying: CreateSavingsaccount) extends RequestType[CreateSavingsaccount](underlying)

final case class UpdateCheckingaccountItem(override val underlying: UpdateCheckingaccount) extends RequestType[UpdateCheckingaccount](underlying)

final case class UpdateSavingsaccountItem(override val underlying: UpdateSavingsaccount) extends RequestType[UpdateSavingsaccount](underlying)

final case class DeleteCheckingaccountItem(override val underlying: DeleteCheckingaccount) extends RequestType[DeleteCheckingaccount](underlying)

final case class DeleteSavingsaccountItem(override val underlying: DeleteSavingsaccount) extends RequestType[DeleteSavingsaccount](underlying)

final case class CreateContactItem(override val underlying: CreateContact) extends RequestType[CreateContact](underlying)

final case class CreateCustomerItem(override val underlying: CreateCustomer) extends RequestType[CreateCustomer](underlying)

final case class CreateDepartmentItem(override val underlying: CreateDepartment) extends RequestType[CreateDepartment](underlying)

final case class CreateEmployeeItem(override val underlying: CreateEmployee) extends RequestType[CreateEmployee](underlying)

final case class CreateExpensereportItem(override val underlying: CreateExpensereport) extends RequestType[CreateExpensereport](underlying)

final case class CreateExpensereportbatchItem(override val underlying: CreateExpensereportbatch) extends RequestType[CreateExpensereportbatch](underlying)

final case class CreateExpensetypeItem(override val underlying: CreateExpensetype) extends RequestType[CreateExpensetype](underlying)

final case class CreateGlaccountItem(override val underlying: CreateGlaccount) extends RequestType[CreateGlaccount](underlying)

final case class CreateStatglaccountItem(override val underlying: CreateStatglaccount) extends RequestType[CreateStatglaccount](underlying)

final case class CreateGltransactionItem(override val underlying: CreateGltransaction) extends RequestType[CreateGltransaction](underlying)

final case class DeleteGltransactionItem(override val underlying: DeleteGltransaction) extends RequestType[DeleteGltransaction](underlying)

final case class CreateRecurringgltransactionItem(override val underlying: CreateRecurringgltransaction) extends RequestType[CreateRecurringgltransaction](underlying)

final case class DeleteRecurringgltransactionItem(override val underlying: DeleteRecurringgltransaction) extends RequestType[DeleteRecurringgltransaction](underlying)

final case class CreateStatgltransactionItem(override val underlying: CreateStatgltransaction) extends RequestType[CreateStatgltransaction](underlying)

final case class CreateRecurringstatgltransItem(override val underlying: CreateRecurringstatgltrans) extends RequestType[CreateRecurringstatgltrans](underlying)

final case class DeleteRecurringstatgltransItem(override val underlying: DeleteRecurringstatgltrans) extends RequestType[DeleteRecurringstatgltrans](underlying)

final case class CreateInvoiceItem(override val underlying: CreateInvoice) extends RequestType[CreateInvoice](underlying)

final case class CreateRecurringinvoiceItem(override val underlying: CreateRecurringinvoice) extends RequestType[CreateRecurringinvoice](underlying)

final case class CreateInvoicebatchItem(override val underlying: CreateInvoicebatch) extends RequestType[CreateInvoicebatch](underlying)

final case class CreateJournalItem(override val underlying: CreateJournal) extends RequestType[CreateJournal](underlying)

final case class CreateLocationItem(override val underlying: CreateLocation) extends RequestType[CreateLocation](underlying)

final case class CreateStatjournalItem(override val underlying: CreateStatjournal) extends RequestType[CreateStatjournal](underlying)

final case class CreateProjectItem(override val underlying: CreateProject) extends RequestType[CreateProject](underlying)

final case class UpdateProjectItem(override val underlying: UpdateProject) extends RequestType[UpdateProject](underlying)

final case class DeleteProjectItem(override val underlying: DeleteProject) extends RequestType[DeleteProject](underlying)

final case class CreateclassItem(override val underlying: CreateClass) extends RequestType[CreateClass](underlying)

final case class UpdateclassItem(override val underlying: UpdateClass) extends RequestType[UpdateClass](underlying)

final case class DeleteclassItem(override val underlying: DeleteClass) extends RequestType[DeleteClass](underlying)

final case class CreateVendorItem(override val underlying: CreateVendor) extends RequestType[CreateVendor](underlying)

final case class DeleteApaccountlabelItem(override val underlying: DeleteApaccountlabel) extends RequestType[DeleteApaccountlabel](underlying)

final case class DeleteApadjustmentItem(override val underlying: DeleteApadjustment) extends RequestType[DeleteApadjustment](underlying)

final case class CreateLocationgroupItem(override val underlying: CreateLocationgroup) extends RequestType[CreateLocationgroup](underlying)

final case class DeleteAraccountlabelItem(override val underlying: DeleteAraccountlabel) extends RequestType[DeleteAraccountlabel](underlying)

final case class DeleteAradjustmentItem(override val underlying: DeleteAradjustment) extends RequestType[DeleteAradjustment](underlying)

final case class DeleteBillItem(override val underlying: DeleteBill) extends RequestType[DeleteBill](underlying)

final case class DeleteRecurringbillItem(override val underlying: DeleteRecurringbill) extends RequestType[DeleteRecurringbill](underlying)

final case class DeleteContactItem(override val underlying: DeleteContact) extends RequestType[DeleteContact](underlying)

final case class DeleteCustomerItem(override val underlying: DeleteCustomer) extends RequestType[DeleteCustomer](underlying)

final case class DeleteDepartmentItem(override val underlying: DeleteDepartment) extends RequestType[DeleteDepartment](underlying)

final case class DeleteEmployeeItem(override val underlying: DeleteEmployee) extends RequestType[DeleteEmployee](underlying)

final case class DeleteExpensereportItem(override val underlying: DeleteExpensereport) extends RequestType[DeleteExpensereport](underlying)

final case class ReverseExpensereportItem(override val underlying: ReverseExpensereport) extends RequestType[ReverseExpensereport](underlying)

final case class DeleteExpensetypeItem(override val underlying: DeleteExpensetype) extends RequestType[DeleteExpensetype](underlying)

final case class DeleteGlaccountItem(override val underlying: DeleteGlaccount) extends RequestType[DeleteGlaccount](underlying)

final case class DeleteStatglaccountItem(override val underlying: DeleteStatglaccount) extends RequestType[DeleteStatglaccount](underlying)

final case class DeleteInvoiceItem(override val underlying: DeleteInvoice) extends RequestType[DeleteInvoice](underlying)

final case class ReverseInvoiceItem(override val underlying: ReverseInvoice) extends RequestType[ReverseInvoice](underlying)

final case class DeleteRecurringinvoiceItem(override val underlying: DeleteRecurringinvoice) extends RequestType[DeleteRecurringinvoice](underlying)

final case class DeleteJournalItem(override val underlying: DeleteJournal) extends RequestType[DeleteJournal](underlying)

final case class DeleteStatjournalItem(override val underlying: DeleteStatjournal) extends RequestType[DeleteStatjournal](underlying)

final case class DeleteLocationItem(override val underlying: DeleteLocation) extends RequestType[DeleteLocation](underlying)

final case class DeleteVendorItem(override val underlying: DeleteVendor) extends RequestType[DeleteVendor](underlying)

final case class GetAccountbalancesItem(override val underlying: GetAccountbalances) extends RequestType[GetAccountbalances](underlying)

final case class GetAccountgroupdetailsItem(override val underlying: GetAccountgroupdetails) extends RequestType[GetAccountgroupdetails](underlying)

final case class GetApadjustmentItem(override val underlying: GetApadjustment) extends RequestType[GetApadjustment](underlying)

final case class GetAradjustmentItem(override val underlying: GetAradjustment) extends RequestType[GetAradjustment](underlying)

final case class GetBillItem(override val underlying: GetBill) extends RequestType[GetBill](underlying)

final case class GetExpensereportItem(override val underlying: GetExpensereport) extends RequestType[GetExpensereport](underlying)

final case class GetInvoiceItem(override val underlying: GetInvoice) extends RequestType[GetInvoice](underlying)

final case class GetListItem(override val underlying: GetList) extends RequestType[GetList](underlying)

final case class GetTrialbalanceItem(override val underlying: GetTrialbalance) extends RequestType[GetTrialbalance](underlying)

final case class GetMyclientsItem(override val underlying: GetMyclients) extends RequestType[GetMyclients](underlying)

final case class InitSessionItem(override val underlying: InitSession) extends RequestType[InitSession](underlying)

final case class GetAPISessionItem(override val underlying: GetAPISession) extends RequestType[GetAPISession](underlying)

final case class UpdateApaccountlabelItem(override val underlying: UpdateApaccountlabel) extends RequestType[UpdateApaccountlabel](underlying)

final case class UpdateAraccountlabelItem(override val underlying: UpdateAraccountlabel) extends RequestType[UpdateAraccountlabel](underlying)

final case class UpdateContactItem(override val underlying: UpdateContact) extends RequestType[UpdateContact](underlying)

final case class UpdateCustomerItem(override val underlying: UpdateCustomer) extends RequestType[UpdateCustomer](underlying)

final case class UpdateCustomervisibilityItem(override val underlying: UpdateCustomervisibility) extends RequestType[UpdateCustomervisibility](underlying)

final case class UpdateDepartmentItem(override val underlying: UpdateDepartment) extends RequestType[UpdateDepartment](underlying)

final case class UpdateInvoiceItem(override val underlying: UpdateInvoice) extends RequestType[UpdateInvoice](underlying)

final case class UpdateBillItem(override val underlying: UpdateBill) extends RequestType[UpdateBill](underlying)

final case class UpdateApadjustmentItem(override val underlying: UpdateApadjustment) extends RequestType[UpdateApadjustment](underlying)

final case class UpdateAradjustmentItem(override val underlying: UpdateAradjustment) extends RequestType[UpdateAradjustment](underlying)

final case class UpdateEmployeeItem(override val underlying: UpdateEmployee) extends RequestType[UpdateEmployee](underlying)

final case class UpdateExpensereportItem(override val underlying: UpdateExpensereport) extends RequestType[UpdateExpensereport](underlying)

final case class UpdateExpensetypeItem(override val underlying: UpdateExpensetype) extends RequestType[UpdateExpensetype](underlying)

final case class UpdateGlaccountItem(override val underlying: UpdateGlaccount) extends RequestType[UpdateGlaccount](underlying)

final case class UpdateStatglaccountItem(override val underlying: UpdateStatglaccount) extends RequestType[UpdateStatglaccount](underlying)

final case class UpdateJournalItem(override val underlying: UpdateJournal) extends RequestType[UpdateJournal](underlying)

final case class UpdateLocationItem(override val underlying: UpdateLocation) extends RequestType[UpdateLocation](underlying)

final case class UpdateVendorItem(override val underlying: UpdateVendor) extends RequestType[UpdateVendor](underlying)

final case class UpdateVendorvisibilityItem(override val underlying: UpdateVendorvisibility) extends RequestType[UpdateVendorvisibility](underlying)

final case class CreatePaymentrequestItem(override val underlying: CreatePaymentrequest) extends RequestType[CreatePaymentrequest](underlying)

final case class ReclassifyBillItem(override val underlying: ReclassifyBill) extends RequestType[ReclassifyBill](underlying)

final case class ReclassifyInvoiceItem(override val underlying: ReclassifyInvoice) extends RequestType[ReclassifyInvoice](underlying)

final case class DeletePaymentrequestItem(override val underlying: DeletePaymentrequest) extends RequestType[DeletePaymentrequest](underlying)

final case class CreateAppaymentItem(override val underlying: CreateAppayment) extends RequestType[CreateAppayment](underlying)

final case class ApproveAppaymentrequestItem(override val underlying: ApproveAppaymentrequest) extends RequestType[ApproveAppaymentrequest](underlying)

final case class SendAppaymentrequestItem(override val underlying: SendAppaymentrequest) extends RequestType[SendAppaymentrequest](underlying)

final case class ConfirmAppaymentrequestItem(override val underlying: ConfirmAppaymentrequest) extends RequestType[ConfirmAppaymentrequest](underlying)

final case class VoidAppaymentrequestItem(override val underlying: VoidAppaymentrequest) extends RequestType[VoidAppaymentrequest](underlying)

final case class CreateStkittransactionItem(override val underlying: CreateStkittransaction) extends RequestType[CreateStkittransaction](underlying)

final case class CreateIctransactionItem(override val underlying: CreateIctransaction) extends RequestType[CreateIctransaction](underlying)

final case class UpdateIctransactionItem(override val underlying: UpdateIctransaction) extends RequestType[UpdateIctransaction](underlying)

final case class CreateSotransactionItem(override val underlying: CreateSotransaction) extends RequestType[CreateSotransaction](underlying)

final case class UpdateSotransactionItem(override val underlying: UpdateSotransaction) extends RequestType[UpdateSotransaction](underlying)

final case class CreateRecursotransactionItem(override val underlying: CreateRecursotransaction) extends RequestType[CreateRecursotransaction](underlying)

final case class DeleteRecursotransactionItem(override val underlying: DeleteRecursotransaction) extends RequestType[DeleteRecursotransaction](underlying)

final case class CreatePotransactionItem(override val underlying: CreatePotransaction) extends RequestType[CreatePotransaction](underlying)

final case class UpdatePotransactionItem(override val underlying: UpdatePotransaction) extends RequestType[UpdatePotransaction](underlying)

final case class GetSalestotalsItem(override val underlying: GetSalestotals) extends RequestType[GetSalestotals](underlying)

final case class GetIcitemtotalsItem(override val underlying: GetIcitemtotals) extends RequestType[GetIcitemtotals](underlying)

final case class RecordCctransactionItem(override val underlying: RecordCctransaction) extends RequestType[RecordCctransaction](underlying)

final case class RecordWucctransactionsItem(override val underlying: RecordWucctransactions) extends RequestType[RecordWucctransactions](underlying)

final case class RecordWureceiptsItem(override val underlying: RecordWureceipts) extends RequestType[RecordWureceipts](underlying)

final case class RecordWudisbursementsItem(override val underlying: RecordWudisbursements) extends RequestType[RecordWudisbursements](underlying)

final case class RecordWuadjjournalentriesItem(override val underlying: RecordWuadjjournalentries) extends RequestType[RecordWuadjjournalentries](underlying)

final case class RecordWujournalentriesItem(override val underlying: RecordWujournalentries) extends RequestType[RecordWujournalentries](underlying)

final case class GetCompanyprefsItem(override val underlying: GetCompanyprefs) extends RequestType[GetCompanyprefs](underlying)

final case class SetCompanyprefsItem(override val underlying: SetCompanyprefs) extends RequestType[SetCompanyprefs](underlying)

final case class GetApplicationsItem(override val underlying: GetApplications) extends RequestType[GetApplications](underlying)

final case class RecordOtherreceiptItem(override val underlying: RecordOtherreceipt) extends RequestType[RecordOtherreceipt](underlying)

final case class RecordDepositItem(override val underlying: RecordDeposit) extends RequestType[RecordDeposit](underlying)

final case class CreateTerritoryItem(override val underlying: CreateTerritory) extends RequestType[CreateTerritory](underlying)

final case class DeleteTerritoryItem(override val underlying: DeleteTerritory) extends RequestType[DeleteTerritory](underlying)

final case class UpdateTerritoryItem(override val underlying: UpdateTerritory) extends RequestType[UpdateTerritory](underlying)

final case class ApplyaRpaymentItem(override val underlying: ApplyArpayment) extends RequestType[ApplyArpayment](underlying)

final case class DeleteSotransactionItem(override val underlying: DeleteSotransaction) extends RequestType[DeleteSotransaction](underlying)

final case class DeletePotransactionItem(override val underlying: DeletePotransaction) extends RequestType[DeletePotransaction](underlying)

final case class DeleteIctransactionItem(override val underlying: DeleteIctransaction) extends RequestType[DeleteIctransaction](underlying)

final case class CreateItemItem(override val underlying: CreateItem) extends RequestType[CreateItem](underlying)

final case class UpdateItemItem(override val underlying: UpdateItem) extends RequestType[UpdateItem](underlying)

final case class DeleteItemItem(override val underlying: DeleteItem) extends RequestType[DeleteItem](underlying)

final case class CreateSopricelistItem(override val underlying: CreateSopricelist) extends RequestType[CreateSopricelist](underlying)

final case class DeleteSopricelistItem(override val underlying: DeleteSopricelist) extends RequestType[DeleteSopricelist](underlying)

final case class UpdateSopricelistItem(override val underlying: UpdateSopricelist) extends RequestType[UpdateSopricelist](underlying)

final case class CreatePopricelistItem(override val underlying: CreatePopricelist) extends RequestType[CreatePopricelist](underlying)

final case class DeletePopricelistItem(override val underlying: DeletePopricelist) extends RequestType[DeletePopricelist](underlying)

final case class UpdatePopricelistItem(override val underlying: UpdatePopricelist) extends RequestType[UpdatePopricelist](underlying)

final case class CreateVsoepricelistItem(override val underlying: CreateVsoepricelist) extends RequestType[CreateVsoepricelist](underlying)

final case class UpdateVsoepricelistItem(override val underlying: UpdateVsoepricelist) extends RequestType[UpdateVsoepricelist](underlying)

final case class DeleteVsoepricelistItem(override val underlying: DeleteVsoepricelist) extends RequestType[DeleteVsoepricelist](underlying)

final case class CreateVsoeitempricelistItem(override val underlying: CreateVsoeitempricelist) extends RequestType[CreateVsoeitempricelist](underlying)

final case class UpdateVsoeitempricelistItem(override val underlying: UpdateVsoeitempricelist) extends RequestType[UpdateVsoeitempricelist](underlying)

final case class DeleteVsoeitempricelistItem(override val underlying: DeleteVsoeitempricelist) extends RequestType[DeleteVsoeitempricelist](underlying)

final case class CreateInvpricelistentryItem(override val underlying: CreateInvpricelistentry) extends RequestType[CreateInvpricelistentry](underlying)

final case class DeleteInvpricelistentryItem(override val underlying: DeleteInvpricelistentry) extends RequestType[DeleteInvpricelistentry](underlying)

final case class UpdateInvpricelistentryItem(override val underlying: UpdateInvpricelistentry) extends RequestType[UpdateInvpricelistentry](underlying)

final case class GetClosedbooksdateItem(override val underlying: GetClosedbooksdate) extends RequestType[GetClosedbooksdate](underlying)

final case class GetAragingItem(override val underlying: GetAraging) extends RequestType[GetAraging](underlying)

final case class DeleteArpaymentItem(override val underlying: DeleteArpayment) extends RequestType[DeleteArpayment](underlying)

final case class CreateCustomerachinfoItem(override val underlying: CreateCustomerachinfo) extends RequestType[CreateCustomerachinfo](underlying)

final case class UpdateCustomerachinfoItem(override val underlying: UpdateCustomerachinfo) extends RequestType[UpdateCustomerachinfo](underlying)

final case class DeleteCustomerachinfoItem(override val underlying: DeleteCustomerachinfo) extends RequestType[DeleteCustomerachinfo](underlying)

final case class CreateCustomerchargecardItem(override val underlying: CreateCustomerchargecard) extends RequestType[CreateCustomerchargecard](underlying)

final case class UpdateCustomerchargecardItem(override val underlying: UpdateCustomerchargecard) extends RequestType[UpdateCustomerchargecard](underlying)

final case class DeleteCustomerchargecardItem(override val underlying: DeleteCustomerchargecard) extends RequestType[DeleteCustomerchargecard](underlying)

final case class CreateCustomerbankaccountItem(override val underlying: CreateCustomerbankaccount) extends RequestType[CreateCustomerbankaccount](underlying)

final case class UpdateCustomerbankaccountItem(override val underlying: UpdateCustomerbankaccount) extends RequestType[UpdateCustomerbankaccount](underlying)

final case class DeleteCustomerbankaccountItem(override val underlying: DeleteCustomerbankaccount) extends RequestType[DeleteCustomerbankaccount](underlying)

final case class CreateTaxdetailItem(override val underlying: CreateTaxdetail) extends RequestType[CreateTaxdetail](underlying)

final case class UpdateTaxdetailItem(override val underlying: UpdateTaxdetail) extends RequestType[UpdateTaxdetail](underlying)

final case class DeleteTaxdetailItem(override val underlying: DeleteTaxdetail) extends RequestType[DeleteTaxdetail](underlying)

final case class CreateTaxscheduleItem(override val underlying: CreateTaxschedule) extends RequestType[CreateTaxschedule](underlying)

final case class UpdateTaxscheduleItem(override val underlying: UpdateTaxschedule) extends RequestType[UpdateTaxschedule](underlying)

final case class DeleteTaxscheduleItem(override val underlying: DeleteTaxschedule) extends RequestType[DeleteTaxschedule](underlying)

final case class CreateTaxscheduledetailItem(override val underlying: CreateTaxscheduledetail) extends RequestType[CreateTaxscheduledetail](underlying)

final case class DeleteTaxscheduledetailItem(override val underlying: DeleteTaxscheduledetail) extends RequestType[DeleteTaxscheduledetail](underlying)

final case class CreateContacttaxgroupItem(override val underlying: CreateContacttaxgroup) extends RequestType[CreateContacttaxgroup](underlying)

final case class DeleteContacttaxgroupItem(override val underlying: DeleteContacttaxgroup) extends RequestType[DeleteContacttaxgroup](underlying)

final case class CreateItemtaxgroupItem(override val underlying: CreateItemtaxgroup) extends RequestType[CreateItemtaxgroup](underlying)

final case class DeleteItemtaxgroupItem(override val underlying: DeleteItemtaxgroup) extends RequestType[DeleteItemtaxgroup](underlying)

final case class CreateTaxschedulemapItem(override val underlying: CreateTaxschedulemap) extends RequestType[CreateTaxschedulemap](underlying)

final case class DeleteTaxschedulemapItem(override val underlying: DeleteTaxschedulemap) extends RequestType[DeleteTaxschedulemap](underlying)

final case class DescribeItem(override val underlying: Describe) extends RequestType[Describe](underlying)

final case class DescribeallItem(override val underlying: Describeall) extends RequestType[Describeall](underlying)

final case class ReconcileBankItem(override val underlying: ReconcileBank) extends RequestType[ReconcileBank](underlying)

final case class ReverseBillItem(override val underlying: ReverseBill) extends RequestType[ReverseBill](underlying)

final case class ReverseAppaymentItem(override val underlying: ReverseAppayment) extends RequestType[ReverseAppayment](underlying)

final case class ReverseArpaymentItem(override val underlying: ReverseArpayment) extends RequestType[ReverseArpayment](underlying)

final case class CreateAptermItem(override val underlying: CreateApterm) extends RequestType[CreateApterm](underlying)

final case class UpdateAptermItem(override val underlying: UpdateApterm) extends RequestType[UpdateApterm](underlying)

final case class DeleteAptermItem(override val underlying: DeleteApterm) extends RequestType[DeleteApterm](underlying)

final case class CreateArtermItem(override val underlying: CreateArterm) extends RequestType[CreateArterm](underlying)

final case class UpdateArtermItem(override val underlying: UpdateArterm) extends RequestType[UpdateArterm](underlying)

final case class DeleteArtermItem(override val underlying: DeleteArterm) extends RequestType[DeleteArterm](underlying)

final case class CreateTimesheetItem(override val underlying: CreateTimesheet) extends RequestType[CreateTimesheet](underlying)

final case class UpdateTimesheetItem(override val underlying: UpdateTimesheet) extends RequestType[UpdateTimesheet](underlying)

final case class DeleteTimesheetItem(override val underlying: DeleteTimesheet) extends RequestType[DeleteTimesheet](underlying)

final case class CreateTaskItem(override val underlying: CreateTask) extends RequestType[CreateTask](underlying)

final case class UpdateTaskItem(override val underlying: UpdateTask) extends RequestType[UpdateTask](underlying)

final case class DeleteTaskItem(override val underlying: DeleteTask) extends RequestType[DeleteTask](underlying)

final case class CreateAllocationItem(override val underlying: CreateAllocation) extends RequestType[CreateAllocation](underlying)

final case class UpdateAllocationItem(override val underlying: UpdateAllocation) extends RequestType[UpdateAllocation](underlying)

final case class DeleteAllocationItem(override val underlying: DeleteAllocation) extends RequestType[DeleteAllocation](underlying)

final case class UpdateCctransactionItem(override val underlying: UpdateCctransaction) extends RequestType[UpdateCctransaction](underlying)

final case class ReverseCctransactionItem(override val underlying: ReverseCctransaction) extends RequestType[ReverseCctransaction](underlying)

final case class HoldRevrecscheduleItem(override val underlying: HoldRevrecschedule) extends RequestType[HoldRevrecschedule](underlying)

final case class ResumeRevrecscheduleItem(override val underlying: ResumeRevrecschedule) extends RequestType[ResumeRevrecschedule](underlying)

final case class TerminateRevrecscheduleItem(override val underlying: TerminateRevrecschedule) extends RequestType[TerminateRevrecschedule](underlying)

final case class UpdateRevrecscheduleItem(override val underlying: UpdateRevrecschedule) extends RequestType[UpdateRevrecschedule](underlying)

final case class CreateRevrecscheduleentryItem(override val underlying: CreateRevrecscheduleentry) extends RequestType[CreateRevrecscheduleentry](underlying)

final case class PostRevrecscheduleentryItem(override val underlying: PostRevrecscheduleentry) extends RequestType[PostRevrecscheduleentry](underlying)

final case class UnpostRevrecscheduleentryItem(override val underlying: UnpostRevrecscheduleentry) extends RequestType[UnpostRevrecscheduleentry](underlying)

final case class ReallocateRevrecscheduleItem(override val underlying: ReallocateRevrecschedule) extends RequestType[ReallocateRevrecschedule](underlying)

final case class CreateExpenseadjustmentreportItem(override val underlying: CreateExpenseadjustmentreport) extends RequestType[CreateExpenseadjustmentreport](underlying)

final case class UpdateExpenseadjustmentreportItem(override val underlying: UpdateExpenseadjustmentreport) extends RequestType[UpdateExpenseadjustmentreport](underlying)

final case class DeleteExpenseadjustmentreportItem(override val underlying: DeleteExpenseadjustmentreport) extends RequestType[DeleteExpenseadjustmentreport](underlying)

final case class GetExpenseadjustmentreportItem(override val underlying: GetExpenseadjustmentreport) extends RequestType[GetExpenseadjustmentreport](underlying)

final case class CreateSupdocItem(override val underlying: CreateSupdoc) extends RequestType[CreateSupdoc](underlying)

final case class UpdateSupdocItem(override val underlying: UpdateSupdoc) extends RequestType[UpdateSupdoc](underlying)

final case class DeleteSupdocItem(override val underlying: DeleteSupdoc) extends RequestType[DeleteSupdoc](underlying)

final case class CreateSupdocfolderItem(override val underlying: CreateSupdocfolder) extends RequestType[CreateSupdocfolder](underlying)

final case class UpdateSupdocfolderItem(override val underlying: UpdateSupdocfolder) extends RequestType[UpdateSupdocfolder](underlying)

final case class DeleteSupdocfolderItem(override val underlying: DeleteSupdocfolder) extends RequestType[DeleteSupdocfolder](underlying)

final case class CreateTimetypeItem(override val underlying: CreateTimetype) extends RequestType[CreateTimetype](underlying)

final case class UpdateTimetypeItem(override val underlying: UpdateTimetype) extends RequestType[UpdateTimetype](underlying)

final case class DeleteTimetypeItem(override val underlying: DeleteTimetype) extends RequestType[DeleteTimetype](underlying)

final case class CreateEarningtypeItem(override val underlying: CreateEarningtype) extends RequestType[CreateEarningtype](underlying)

final case class UpdateEarningtypeItem(override val underlying: UpdateEarningtype) extends RequestType[UpdateEarningtype](underlying)

final case class DeleteEarningtypeItem(override val underlying: DeleteEarningtype) extends RequestType[DeleteEarningtype](underlying)

final case class CreateEmployeerateItem(override val underlying: CreateEmployeerate) extends RequestType[CreateEmployeerate](underlying)

final case class UpdateEmployeerateItem(override val underlying: UpdateEmployeerate) extends RequestType[UpdateEmployeerate](underlying)

final case class DeleteEmployeerateItem(override val underlying: DeleteEmployeerate) extends RequestType[DeleteEmployeerate](underlying)

final case class CreateAchbankconfigurationItem(override val underlying: CreateAchbankconfiguration) extends RequestType[CreateAchbankconfiguration](underlying)

final case class DeleteAchbankconfigurationItem(override val underlying: DeleteAchbankconfiguration) extends RequestType[DeleteAchbankconfiguration](underlying)

final case class UpdateAchbankconfigurationItem(override val underlying: UpdateAchbankconfiguration) extends RequestType[UpdateAchbankconfiguration](underlying)

final case class CreateVendorentityaccountnoItem(override val underlying: CreateVendorentityaccountno) extends RequestType[CreateVendorentityaccountno](underlying)

final case class CreateConsolidationItem(override val underlying: CreateConsolidation) extends RequestType[CreateConsolidation](underlying)

final case class ConvertGlbudgetFpAmountsToEopItem(override val underlying: ConvertGlbudgetFpAmountsToEop) extends RequestType[ConvertGlbudgetFpAmountsToEop](underlying)

final case class CreateExpensepaymenttypeItem(override val underlying: CreateExpensepaymenttype) extends RequestType[CreateExpensepaymenttype](underlying)

final case class UpdateExpensepaymenttypeItem(override val underlying: UpdateExpensepaymenttype) extends RequestType[UpdateExpensepaymenttype](underlying)

final case class DeleteExpensepaymenttypeItem(override val underlying: DeleteExpensepaymenttype) extends RequestType[DeleteExpensepaymenttype](underlying)

final case class GetUserPermissionsItem(override val underlying: GetUserPermissions) extends RequestType[GetUserPermissions](underlying)

///
/// Response types
///

final case class AccountgroupItem(override val underlying: RespAccountgroup) extends ResponseType[RespAccountgroup](underlying)

final case class AdjjournalItem(override val underlying: Adjjournal) extends ResponseType[Adjjournal](underlying)

final case class StatjournalItem(override val underlying: Statjournal) extends ResponseType[Statjournal](underlying)

final case class ApaccountlabelItem(override val underlying: Apaccountlabel) extends ResponseType[Apaccountlabel](underlying)

final case class ApadjustmentItem(override val underlying: Apadjustment) extends ResponseType[Apadjustment](underlying)

final case class ApadjustmentbatchItem(override val underlying: Apadjustmentbatch) extends ResponseType[Apadjustmentbatch](underlying)

final case class AppaymentItem(override val underlying: Appayment) extends ResponseType[Appayment](underlying)

final case class AptermItem(override val underlying: Apterm) extends ResponseType[Apterm](underlying)

final case class ProjectItem(override val underlying: Project) extends ResponseType[Project](underlying)

final case class ClassItem(override val underlying: Class) extends ResponseType[Class](underlying)

final case class AraccountlabelItem(override val underlying: Araccountlabel) extends ResponseType[Araccountlabel](underlying)

final case class AradjustmentItem(override val underlying: Aradjustment) extends ResponseType[Aradjustment](underlying)

final case class AradjustmentbatchItem(override val underlying: Aradjustmentbatch) extends ResponseType[Aradjustmentbatch](underlying)

final case class ArpaymentItem(override val underlying: Arpayment) extends ResponseType[Arpayment](underlying)

final case class ArpaymentbatchItem(override val underlying: Arpaymentbatch) extends ResponseType[Arpaymentbatch](underlying)

final case class ArtermItem(override val underlying: Arterm) extends ResponseType[Arterm](underlying)

final case class ArtransactiondefItem(override val underlying: Artransactiondef) extends ResponseType[Artransactiondef](underlying)

final case class BankaccountItem(override val underlying: Bankaccount) extends ResponseType[Bankaccount](underlying)

final case class BillbatchItem(override val underlying: Billbatch) extends ResponseType[Billbatch](underlying)

final case class ContactItem(override val underlying: RespContact) extends ResponseType[RespContact](underlying)

final case class CustglgroupItem(override val underlying: Custglgroup) extends ResponseType[Custglgroup](underlying)

final case class CustomerItem(override val underlying: Customer) extends ResponseType[Customer](underlying)

final case class CustomervisibilityItem(override val underlying: Customervisibility) extends ResponseType[Customervisibility](underlying)

final case class CustomerachinfoItem(override val underlying: Customerachinfo) extends ResponseType[Customerachinfo](underlying)

final case class CustomerchargecardItem(override val underlying: Customerchargecard) extends ResponseType[Customerchargecard](underlying)

final case class CustomerbankaccountItem(override val underlying: Customerbankaccount) extends ResponseType[Customerbankaccount](underlying)

final case class DepartmentItem(override val underlying: RespDepartment) extends ResponseType[RespDepartment](underlying)

final case class EmployeeItem(override val underlying: Employee) extends ResponseType[Employee](underlying)

final case class ExpensereportItem(override val underlying: Expensereport) extends ResponseType[Expensereport](underlying)

final case class ExpensereportbatchItem(override val underlying: Expensereportbatch) extends ResponseType[Expensereportbatch](underlying)

final case class ExpensetypesItem(override val underlying: Expensetypes) extends ResponseType[Expensetypes](underlying)

final case class ExpensepaymenttypeItem(override val underlying: Expensepaymenttype) extends ResponseType[Expensepaymenttype](underlying)

final case class GlaccountItem(override val underlying: Glaccount) extends ResponseType[Glaccount](underlying)

final case class StatglaccountItem(override val underlying: Statglaccount) extends ResponseType[Statglaccount](underlying)

final case class GlbudgetitemItem(override val underlying: Glbudgetitem) extends ResponseType[Glbudgetitem](underlying)

final case class GlentryItem(override val underlying: RespGlentry) extends ResponseType[RespGlentry](underlying)

final case class GltransactionItem(override val underlying: RespGltransaction) extends ResponseType[RespGltransaction](underlying)

final case class IcitemItem(override val underlying: Icitem) extends ResponseType[Icitem](underlying)

final case class IctotalItem(override val underlying: Ictotal) extends ResponseType[Ictotal](underlying)

final case class StkittransactionItem(override val underlying: Stkittransaction) extends ResponseType[Stkittransaction](underlying)

final case class IctransactionItem(override val underlying: Ictransaction) extends ResponseType[Ictransaction](underlying)

final case class IctransactiondefItem(override val underlying: Ictransactiondef) extends ResponseType[Ictransactiondef](underlying)

final case class InvoiceItem(override val underlying: Invoice) extends ResponseType[Invoice](underlying)

final case class InvoicebatchItem(override val underlying: Invoicebatch) extends ResponseType[Invoicebatch](underlying)

final case class ItemglgroupItem(override val underlying: Itemglgroup) extends ResponseType[Itemglgroup](underlying)

final case class JournalItem(override val underlying: Journal) extends ResponseType[Journal](underlying)

final case class LocationItem(override val underlying: Location) extends ResponseType[Location](underlying)

final case class LocationentityItem(override val underlying: Locationentity) extends ResponseType[Locationentity](underlying)

final case class SupdocfolderItem(override val underlying: Supdocfolder) extends ResponseType[Supdocfolder](underlying)

final case class SupdocItem(override val underlying: Supdoc) extends ResponseType[Supdoc](underlying)

final case class Company_infoItem(override val underlying: CompanyInfo) extends ResponseType[CompanyInfo](underlying)

final case class TrxcurrenciesItem(override val underlying: Trxcurrencies) extends ResponseType[Trxcurrencies](underlying)

final case class PopricelistItem(override val underlying: Popricelist) extends ResponseType[Popricelist](underlying)

final case class VsoepricelistItem(override val underlying: Vsoepricelist) extends ResponseType[Vsoepricelist](underlying)

final case class VsoeitempricelistItem(override val underlying: Vsoeitempricelist) extends ResponseType[Vsoeitempricelist](underlying)

final case class PotransactionItem(override val underlying: Potransaction) extends ResponseType[Potransaction](underlying)

final case class PotransactiondefItem(override val underlying: Potransactiondef) extends ResponseType[Potransactiondef](underlying)

final case class CsnhistoryItem(override val underlying: Csnhistory) extends ResponseType[Csnhistory](underlying)

final case class LocationgroupItem(override val underlying: Locationgroup) extends ResponseType[Locationgroup](underlying)

final case class PricelistitemItem(override val underlying: Pricelistitem) extends ResponseType[Pricelistitem](underlying)

final case class ProductlineItem(override val underlying: Productline) extends ResponseType[Productline](underlying)

final case class RenewalmacroItem(override val underlying: Renewalmacro) extends ResponseType[Renewalmacro](underlying)

final case class ReportingperiodItem(override val underlying: Reportingperiod) extends ResponseType[Reportingperiod](underlying)

final case class RevrecscheduleItem(override val underlying: Revrecschedule) extends ResponseType[Revrecschedule](underlying)

final case class RevrecscheduleentryItem(override val underlying: Revrecscheduleentry) extends ResponseType[Revrecscheduleentry](underlying)

final case class RevrectemplateItem(override val underlying: Revrectemplate) extends ResponseType[Revrectemplate](underlying)

final case class SopricelistItem(override val underlying: Sopricelist) extends ResponseType[Sopricelist](underlying)

final case class SotransactionItem(override val underlying: Sotransaction) extends ResponseType[Sotransaction](underlying)

final case class RecursotransactionItem(override val underlying: Recursotransaction) extends ResponseType[Recursotransaction](underlying)

final case class SotransactiondefItem(override val underlying: Sotransactiondef) extends ResponseType[Sotransactiondef](underlying)

final case class SubscriptionItem(override val underlying: Subscription) extends ResponseType[Subscription](underlying)

final case class TerritoryItem(override val underlying: Territory) extends ResponseType[Territory](underlying)

final case class UomItem(override val underlying: Uom) extends ResponseType[Uom](underlying)

final case class VendglgroupItem(override val underlying: Vendglgroup) extends ResponseType[Vendglgroup](underlying)

final case class VendorItem(override val underlying: Vendor) extends ResponseType[Vendor](underlying)

final case class VendorvisibilityItem(override val underlying: Vendorvisibility) extends ResponseType[Vendorvisibility](underlying)

final case class WarehouseItem(override val underlying: Warehouse) extends ResponseType[Warehouse](underlying)

final case class TaxdetailItem(override val underlying: Taxdetail) extends ResponseType[Taxdetail](underlying)

final case class TaxscheduleItem(override val underlying: Taxschedule) extends ResponseType[Taxschedule](underlying)

final case class TaxscheduledetailItem(override val underlying: Taxscheduledetail) extends ResponseType[Taxscheduledetail](underlying)

final case class ContacttaxgroupItem(override val underlying: RespContacttaxgroup) extends ResponseType[RespContacttaxgroup](underlying)

final case class ItemtaxgroupItem(override val underlying: RespItemtaxgroup) extends ResponseType[RespItemtaxgroup](underlying)

final case class TaxschedulemapItem(override val underlying: Taxschedulemap) extends ResponseType[Taxschedulemap](underlying)

final case class SmarteventlogItem(override val underlying: Smarteventlog) extends ResponseType[Smarteventlog](underlying)

final case class AppaymentrequestItem(override val underlying: Appaymentrequest) extends ResponseType[Appaymentrequest](underlying)

final case class IcitemtotalsItem(override val underlying: Icitemtotals) extends ResponseType[Icitemtotals](underlying)

final case class ItemtotalItem(override val underlying: Itemtotal) extends ResponseType[Itemtotal](underlying)

final case class TimesheetItem(override val underlying: Timesheet) extends ResponseType[Timesheet](underlying)

final case class TaskItem(override val underlying: Task) extends ResponseType[Task](underlying)

final case class ProjecttypeItem(override val underlying: Projecttype) extends ResponseType[Projecttype](underlying)

final case class ProjectstatusItem(override val underlying: Projectstatus) extends ResponseType[Projectstatus](underlying)

final case class TimetypeItem(override val underlying: Timetype) extends ResponseType[Timetype](underlying)

final case class AllocationItem(override val underlying: Allocation) extends ResponseType[Allocation](underlying)

final case class CctransactionItem(override val underlying: Cctransaction) extends ResponseType[Cctransaction](underlying)

final case class RevrecchangelogItem(override val underlying: Revrecchangelog) extends ResponseType[Revrecchangelog](underlying)

final case class VsoeallocationItem(override val underlying: Vsoeallocation) extends ResponseType[Vsoeallocation](underlying)

final case class ExpenseadjustmentreportItem(override val underlying: Expenseadjustmentreport) extends ResponseType[Expenseadjustmentreport](underlying)

final case class EarningtypeItem(override val underlying: Earningtype) extends ResponseType[Earningtype](underlying)

final case class EmployeerateItem(override val underlying: Employeerate) extends ResponseType[Employeerate](underlying)

final case class AchbankconfigurationItem(override val underlying: Achbankconfiguration) extends ResponseType[Achbankconfiguration](underlying)

final case class VendorentityaccountItem(override val underlying: Vendorentityaccount) extends ResponseType[Vendorentityaccount](underlying)

final case class VendorprefItem(override val underlying: Vendorpref) extends ResponseType[Vendorpref](underlying)

final case class EmployeeprefItem(override val underlying: Employeepref) extends ResponseType[Employeepref](underlying)

