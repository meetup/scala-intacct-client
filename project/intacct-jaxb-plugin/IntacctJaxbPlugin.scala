/**
  *
  * This file contains code from sbt-xjc (https://github.com/sbt/sbt-xjc)
  *
  * Copyright (c) 2012, 2013 Jason Zaugg
  * All rights reserved.
  *
  *  Redistribution and use in source and binary forms, with or without
  * modification, are permitted provided that the following conditions
  * are met:
  * 1. Redistributions of source code must retain the above copyright
  *    notice, this list of conditions and the following disclaimer.
  * 2. Redistributions in binary form must reproduce the above copyright
  *    notice, this list of conditions and the following disclaimer in the
  *    documentation and/or other materials provided with the distribution.
  * 3. The name of the author may not be used to endorse or promote products
  *    derived from this software without specific prior written permission.
  *
  * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
  * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
  * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
  * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
  * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
  * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  **/

import java.io.{File, FileWriter}

import sbt._
import Keys._
import sbt.{Fork, ForkOptions, Logger}

import scala.io.Source

object Forker {
  def apply(javaHome: Option[File], options: Seq[String], log: Logger): Int = {
    new Fork("java", None).apply(new ForkOptions(javaHome = javaHome), options)
  }
}

/**
  * Compile Xml Schemata with JAXB XJC.
  */
object IntacctJaxbPlugin extends AutoPlugin {

  override def requires = sbt.plugins.JvmPlugin

  override def trigger = allRequirements

  object autoImport {
    val xjc            = TaskKey[Seq[File]]("xjc", "Generate JAXB Java sources from XSD files(s)")
    val xjcLibs        = SettingKey[Seq[ModuleID]]("xjc-libs", "Core XJC libraries")
    val xjcPlugins     = SettingKey[Seq[ModuleID]]("xjc-plugins", "Plugins for XJC code generation")
    val xjcJvmOpts     = SettingKey[Seq[String]]("xjc-jvm-opts", "Extra command line parameters to the JVM XJC runs in.")
    val xjcCommandLine = SettingKey[Seq[String]]("xjc-plugin-command-line", "Extra command line parameters to XJC. Can be used to enable a plugin.")
    val xjcBindings    = SettingKey[Seq[String]]("xjc-plugin-bindings", "Binding files to add to XJC.")
    val xjcRequestDtd  = SettingKey[File]("xjc-request-dtd", "Request DTD")
    val xjcResponseDtd  = SettingKey[File]("xjc-response-dtd", "Response DTD")
  }
  import autoImport._

  /** An Ivy scope for the XJC compiler */
  val XjcTool   = config("xjc-tool").hide

  /** An Ivy scope for XJC compiler plugins, such as the Fluent API plugin */
  val XjcPlugin = config("xjc-plugin").hide

  /** Main settings to enable XSD compilation */
  override lazy val projectSettings = Seq[Def.Setting[_]](
    ivyConfigurations ++= Seq(XjcTool, XjcPlugin),
    xjcCommandLine    := Seq(),
    xjcJvmOpts        := Seq(),
    xjcBindings       := Seq(),
    xjcPlugins        := Seq(),
    xjcLibs           := Seq(
      "org.glassfish.jaxb" % "jaxb-xjc"  % "2.2.11",
      "com.sun.xml.bind"   % "jaxb-impl" % "2.2.11"
    ),
    libraryDependencies <++= (xjcLibs)(_.map(_ % XjcTool.name)),
    libraryDependencies <++= (xjcPlugins)(_.map(_ % XjcPlugin.name))
  ) ++ xjcSettingsIn(Compile) //++ xjcSettingsIn(Test)

  /** Settings to enable the Fluent API plugin, that provides `withXxx` methods, in addition to `getXxx` and `setXxx`
    *  Requires this resolver http://download.java.net/maven/2/
    **/
  val fluentApiSettings = Seq[Def.Setting[_]](
    xjcPlugins     += "net.java.dev.jaxb2-commons" % "jaxb-fluent-api" % "2.1.8",
    xjcCommandLine += "-Xfluent-api"
  )

  def xjcSettingsIn(conf: Configuration): Seq[Def.Setting[_]] =
    inConfig(conf)(xjcSettings0) ++ Seq(clean <<= clean.dependsOn(clean in xjc in conf))

  /**
    * Unscoped settings, do not use directly, instead use `xjcSettingsIn(IntegrationTest)`
    */
  private def xjcSettings0 = Seq[Def.Setting[_]](
    xjc                  <<= (xjcRequestDtd, xjcResponseDtd, javaHome, classpathTypes in xjc, update,
                              xjcCommandLine, xjcJvmOpts, xjcBindings, streams, sourceManaged).map(xjcCompile),
    sourceGenerators     <+= xjc,
    clean in xjc         <<= (sourceManaged in xjc, streams).map(xjcClean)
  )

  /**
    * @return the .java files in `sourceManaged` after compilation.
    */
  private def xjcCompile(requestDtd: File, responseDtd: File, javaHome: Option[File], classpathTypes: Set[String], updateReport: UpdateReport,
                         extraCommandLine: Seq[String], xjcJvmOpts: Seq[String],
                         xjcBindings: Seq[String], streams: TaskStreams, sources: File): Seq[File] = {
    import streams.log
    val javaRoot = sources / "main" / "java"
    val intacctRoot = javaRoot / "com" / "meetup" / "intacct"
    def generated = (javaRoot ** "*.java").get

    log.info(javaRoot.getAbsolutePath)
    val dtdFiles = Seq(requestDtd, responseDtd)


    val shouldProcess = dtdFiles.map(xjcSources => (xjcSources, generated) match {
        case (Seq(), _)  => false
        case (_, Seq())  => true
        case (ins, outs) =>
          val inLastMod = ins.lastModified
          val outLasMod = outs.map(_.lastModified()).min
          outLasMod < inLastMod
      })

    val (shouldProcessRequest, shouldProcessResponse) = (shouldProcess.head, shouldProcess.tail.head)

    def getOptions(destPackage: String, sourcePath: String, otherOpts: Seq[String] = Nil): Seq[String] = {
      import File.pathSeparator
      def jars(config: Configuration): Seq[File] = Classpaths.managedJars(config, classpathTypes, updateReport).map(_.data)
      val pluginJars      = jars(XjcPlugin)
      val mainJars        = jars(XjcTool)
      val destPackageOpts = Seq("-p", destPackage)
      val jvmCpOptions    = Seq("-classpath", mainJars.mkString(pathSeparator))
      val pluginCpOptions = pluginJars match {
        case Seq() => Seq()
        case js    => Seq("-extension", "-classpath", js.mkString(pathSeparator))
      }
      val appOptions = pluginCpOptions ++ Seq("-d", javaRoot.getAbsolutePath)
      val mainClass  = "com.sun.tools.xjc.XJCFacade"
      val bindings = xjcBindings.map(List("-b",_)).flatten
      jvmCpOptions ++ xjcJvmOpts ++ List(mainClass) ++ appOptions ++ destPackageOpts ++ extraCommandLine ++ otherOpts ++ List(sourcePath) ++ bindings
    }

    if (shouldProcessRequest) {
      val srcLocation = intacctRoot / "request"
      val options = getOptions("com.meetup.intacct.request", requestDtd.toString, Seq("-dtd"))
      srcLocation.mkdirs()
      log.info("Compiling DTD file(s) to %s".format(srcLocation.getAbsolutePath))
      log.debug("XJC java command line: " + options.mkString("\n"))
      val returnCode = Forker(javaHome, options, log)
      if (returnCode != 0) sys.error("Non zero return code from xjc [%d]".format(returnCode))
      else {

        val `Function.java` = Source.fromFile(intacctRoot / "request" / "Function.java").getLines.map(
          // hello
          _.replaceAllLiterally("CreateApaccountlabelOrCreateApadjustmentOrCreateApadjustmentbatchOrCreateAraccountlabelOrCreateAradjustmentOrCreateAradjustmentbatchOrCreateArpaymentOrCreateArpaymentbatchOrCreateBillOrCreateRecurringbillOrCreateBillbatchOrCreateCheckingaccountOrCreateSavingsaccountOrUpdateCheckingaccountOrUpdateSavingsaccountOrDeleteCheckingaccountOrDeleteSavingsaccountOrCreateContactOrCreateCustomerOrCreateDepartmentOrCreateEmployeeOrCreateExpensereportOrCreateExpensereportbatchOrCreateExpensetypeOrCreateGlaccountOrCreateStatglaccountOrCreateGltransactionOrDeleteGltransactionOrCreateRecurringgltransactionOrDeleteRecurringgltransactionOrCreateStatgltransactionOrCreateRecurringstatgltransOrDeleteRecurringstatgltransOrCreateInvoiceOrCreateRecurringinvoiceOrCreateInvoicebatchOrCreateJournalOrCreateLocationOrCreateStatjournalOrCreateProjectOrUpdateProjectOrDeleteProjectOrCreateClassOrUpdateClassOrDeleteClassOrCreateVendorOrDeleteApaccountlabelOrDeleteApadjustmentOrCreateLocationgroupOrDeleteAraccountlabelOrDeleteAradjustmentOrDeleteBillOrDeleteRecurringbillOrDeleteContactOrDeleteCustomerOrDeleteDepartmentOrDeleteEmployeeOrDeleteExpensereportOrReverseExpensereportOrDeleteExpensetypeOrDeleteGlaccountOrDeleteStatglaccountOrDeleteInvoiceOrReverseInvoiceOrDeleteRecurringinvoiceOrDeleteJournalOrDeleteStatjournalOrDeleteLocationOrDeleteVendorOrGetOrGetAccountbalancesOrGetAccountgroupdetailsOrGetApadjustmentOrGetAradjustmentOrGetBillOrGetExpensereportOrGetInvoiceOrGetListOrGetTrialbalanceOrGetMyclientsOrInitSessionOrGetAPISessionOrUpdateApaccountlabelOrUpdateAraccountlabelOrUpdateContactOrUpdateCustomerOrUpdateCustomervisibilityOrUpdateDepartmentOrUpdateInvoiceOrUpdateBillOrUpdateApadjustmentOrUpdateAradjustmentOrUpdateEmployeeOrUpdateExpensereportOrUpdateExpensetypeOrUpdateGlaccountOrUpdateStatglaccountOrUpdateJournalOrUpdateLocationOrUpdateVendorOrUpdateVendorvisibilityOrCreatePaymentrequestOrReclassifyBillOrReclassifyInvoiceOrDeletePaymentrequestOrCreateAppaymentOrApproveAppaymentrequestOrSendAppaymentrequestOrConfirmAppaymentrequestOrVoidAppaymentrequestOrCreateStkittransactionOrCreateIctransactionOrUpdateIctransactionOrCreateSotransactionOrUpdateSotransactionOrCreateRecursotransactionOrDeleteRecursotransactionOrCreatePotransactionOrUpdatePotransactionOrGetSalestotalsOrGetIcitemtotalsOrRecordCctransactionOrRecordWucctransactionsOrRecordWureceiptsOrRecordWudisbursementsOrRecordWuadjjournalentriesOrRecordWujournalentriesOrGetCompanyprefsOrSetCompanyprefsOrGetApplicationsOrRecordOtherreceiptOrRecordDepositOrCreateTerritoryOrDeleteTerritoryOrUpdateTerritoryOrApplyArpaymentOrDeleteSotransactionOrDeletePotransactionOrDeleteIctransactionOrCreateItemOrUpdateItemOrDeleteItemOrCreateSopricelistOrDeleteSopricelistOrUpdateSopricelistOrCreatePopricelistOrDeletePopricelistOrUpdatePopricelistOrCreateVsoepricelistOrUpdateVsoepricelistOrDeleteVsoepricelistOrCreateVsoeitempricelistOrUpdateVsoeitempricelistOrDeleteVsoeitempricelistOrCreateInvpricelistentryOrDeleteInvpricelistentryOrUpdateInvpricelistentryOrGetClosedbooksdateOrGetAragingOrDeleteArpaymentOrCreateCustomerachinfoOrUpdateCustomerachinfoOrDeleteCustomerachinfoOrCreateCustomerchargecardOrUpdateCustomerchargecardOrDeleteCustomerchargecardOrCreateCustomerbankaccountOrUpdateCustomerbankaccountOrDeleteCustomerbankaccountOrCreateTaxdetailOrUpdateTaxdetailOrDeleteTaxdetailOrCreateTaxscheduleOrUpdateTaxscheduleOrDeleteTaxscheduleOrCreateTaxscheduledetailOrDeleteTaxscheduledetailOrCreateContacttaxgroupOrDeleteContacttaxgroupOrCreateItemtaxgroupOrDeleteItemtaxgroupOrCreateTaxschedulemapOrDeleteTaxschedulemapOrDescribeOrDescribeallOrReconcileBankOrReverseBillOrReverseAppaymentOrReverseArpaymentOrCreateAptermOrUpdateAptermOrDeleteAptermOrCreateArtermOrUpdateArtermOrDeleteArtermOrCreateTimesheetOrUpdateTimesheetOrDeleteTimesheetOrCreateTaskOrUpdateTaskOrDeleteTaskOrCreateAllocationOrUpdateAllocationOrDeleteAllocationOrUpdateCctransactionOrReverseCctransactionOrHoldRevrecscheduleOrResumeRevrecscheduleOrTerminateRevrecscheduleOrUpdateRevrecscheduleOrCreateRevrecscheduleentryOrPostRevrecscheduleentryOrUnpostRevrecscheduleentryOrReallocateRevrecscheduleOrCreateExpenseadjustmentreportOrUpdateExpenseadjustmentreportOrDeleteExpenseadjustmentreportOrGetExpenseadjustmentreportOrCreateSupdocOrUpdateSupdocOrDeleteSupdocOrCreateSupdocfolderOrUpdateSupdocfolderOrDeleteSupdocfolderOrCreateTimetypeOrUpdateTimetypeOrDeleteTimetypeOrCreateEarningtypeOrUpdateEarningtypeOrDeleteEarningtypeOrCreateEmployeerateOrUpdateEmployeerateOrDeleteEmployeerateOrCreateAchbankconfigurationOrDeleteAchbankconfigurationOrUpdateAchbankconfigurationOrCreateVendorentityaccountnoOrCreateConsolidationOrConvertGlbudgetFpAmountsToEopOrCreateExpensepaymenttypeOrUpdateExpensepaymenttypeOrDeleteExpensepaymenttypeOrGetUserPermissions", "Operation")
            .replaceAllLiterally("createApaccountlabelOrCreateApadjustmentOrCreateApadjustmentbatchOrCreateAraccountlabelOrCreateAradjustmentOrCreateAradjustmentbatchOrCreateArpaymentOrCreateArpaymentbatchOrCreateBillOrCreateRecurringbillOrCreateBillbatchOrCreateCheckingaccountOrCreateSavingsaccountOrUpdateCheckingaccountOrUpdateSavingsaccountOrDeleteCheckingaccountOrDeleteSavingsaccountOrCreateContactOrCreateCustomerOrCreateDepartmentOrCreateEmployeeOrCreateExpensereportOrCreateExpensereportbatchOrCreateExpensetypeOrCreateGlaccountOrCreateStatglaccountOrCreateGltransactionOrDeleteGltransactionOrCreateRecurringgltransactionOrDeleteRecurringgltransactionOrCreateStatgltransactionOrCreateRecurringstatgltransOrDeleteRecurringstatgltransOrCreateInvoiceOrCreateRecurringinvoiceOrCreateInvoicebatchOrCreateJournalOrCreateLocationOrCreateStatjournalOrCreateProjectOrUpdateProjectOrDeleteProjectOrCreateClassOrUpdateClassOrDeleteClassOrCreateVendorOrDeleteApaccountlabelOrDeleteApadjustmentOrCreateLocationgroupOrDeleteAraccountlabelOrDeleteAradjustmentOrDeleteBillOrDeleteRecurringbillOrDeleteContactOrDeleteCustomerOrDeleteDepartmentOrDeleteEmployeeOrDeleteExpensereportOrReverseExpensereportOrDeleteExpensetypeOrDeleteGlaccountOrDeleteStatglaccountOrDeleteInvoiceOrReverseInvoiceOrDeleteRecurringinvoiceOrDeleteJournalOrDeleteStatjournalOrDeleteLocationOrDeleteVendorOrGetOrGetAccountbalancesOrGetAccountgroupdetailsOrGetApadjustmentOrGetAradjustmentOrGetBillOrGetExpensereportOrGetInvoiceOrGetListOrGetTrialbalanceOrGetMyclientsOrInitSessionOrGetAPISessionOrUpdateApaccountlabelOrUpdateAraccountlabelOrUpdateContactOrUpdateCustomerOrUpdateCustomervisibilityOrUpdateDepartmentOrUpdateInvoiceOrUpdateBillOrUpdateApadjustmentOrUpdateAradjustmentOrUpdateEmployeeOrUpdateExpensereportOrUpdateExpensetypeOrUpdateGlaccountOrUpdateStatglaccountOrUpdateJournalOrUpdateLocationOrUpdateVendorOrUpdateVendorvisibilityOrCreatePaymentrequestOrReclassifyBillOrReclassifyInvoiceOrDeletePaymentrequestOrCreateAppaymentOrApproveAppaymentrequestOrSendAppaymentrequestOrConfirmAppaymentrequestOrVoidAppaymentrequestOrCreateStkittransactionOrCreateIctransactionOrUpdateIctransactionOrCreateSotransactionOrUpdateSotransactionOrCreateRecursotransactionOrDeleteRecursotransactionOrCreatePotransactionOrUpdatePotransactionOrGetSalestotalsOrGetIcitemtotalsOrRecordCctransactionOrRecordWucctransactionsOrRecordWureceiptsOrRecordWudisbursementsOrRecordWuadjjournalentriesOrRecordWujournalentriesOrGetCompanyprefsOrSetCompanyprefsOrGetApplicationsOrRecordOtherreceiptOrRecordDepositOrCreateTerritoryOrDeleteTerritoryOrUpdateTerritoryOrApplyArpaymentOrDeleteSotransactionOrDeletePotransactionOrDeleteIctransactionOrCreateItemOrUpdateItemOrDeleteItemOrCreateSopricelistOrDeleteSopricelistOrUpdateSopricelistOrCreatePopricelistOrDeletePopricelistOrUpdatePopricelistOrCreateVsoepricelistOrUpdateVsoepricelistOrDeleteVsoepricelistOrCreateVsoeitempricelistOrUpdateVsoeitempricelistOrDeleteVsoeitempricelistOrCreateInvpricelistentryOrDeleteInvpricelistentryOrUpdateInvpricelistentryOrGetClosedbooksdateOrGetAragingOrDeleteArpaymentOrCreateCustomerachinfoOrUpdateCustomerachinfoOrDeleteCustomerachinfoOrCreateCustomerchargecardOrUpdateCustomerchargecardOrDeleteCustomerchargecardOrCreateCustomerbankaccountOrUpdateCustomerbankaccountOrDeleteCustomerbankaccountOrCreateTaxdetailOrUpdateTaxdetailOrDeleteTaxdetailOrCreateTaxscheduleOrUpdateTaxscheduleOrDeleteTaxscheduleOrCreateTaxscheduledetailOrDeleteTaxscheduledetailOrCreateContacttaxgroupOrDeleteContacttaxgroupOrCreateItemtaxgroupOrDeleteItemtaxgroupOrCreateTaxschedulemapOrDeleteTaxschedulemapOrDescribeOrDescribeallOrReconcileBankOrReverseBillOrReverseAppaymentOrReverseArpaymentOrCreateAptermOrUpdateAptermOrDeleteAptermOrCreateArtermOrUpdateArtermOrDeleteArtermOrCreateTimesheetOrUpdateTimesheetOrDeleteTimesheetOrCreateTaskOrUpdateTaskOrDeleteTaskOrCreateAllocationOrUpdateAllocationOrDeleteAllocationOrUpdateCctransactionOrReverseCctransactionOrHoldRevrecscheduleOrResumeRevrecscheduleOrTerminateRevrecscheduleOrUpdateRevrecscheduleOrCreateRevrecscheduleentryOrPostRevrecscheduleentryOrUnpostRevrecscheduleentryOrReallocateRevrecscheduleOrCreateExpenseadjustmentreportOrUpdateExpenseadjustmentreportOrDeleteExpenseadjustmentreportOrGetExpenseadjustmentreportOrCreateSupdocOrUpdateSupdocOrDeleteSupdocOrCreateSupdocfolderOrUpdateSupdocfolderOrDeleteSupdocfolderOrCreateTimetypeOrUpdateTimetypeOrDeleteTimetypeOrCreateEarningtypeOrUpdateEarningtypeOrDeleteEarningtypeOrCreateEmployeerateOrUpdateEmployeerateOrDeleteEmployeerateOrCreateAchbankconfigurationOrDeleteAchbankconfigurationOrUpdateAchbankconfigurationOrCreateVendorentityaccountnoOrCreateConsolidationOrConvertGlbudgetFpAmountsToEopOrCreateExpensepaymenttypeOrUpdateExpensepaymenttypeOrDeleteExpensepaymenttypeOrGetUserPermissions", "operation")
        ).mkString(System.lineSeparator())

        val functionWriter = new FileWriter(intacctRoot / "request" / "Function.java" getAbsolutePath)
        try {
          functionWriter.write(`Function.java`)
          functionWriter.flush()
        } finally {
          functionWriter.close()
        }
      }
    } else {
      log.debug("No sources newer than products, skipping.")
    }

    if (shouldProcessResponse) {
      val srcLocation = intacctRoot / "response"
      val options = getOptions("com.meetup.intacct.response", responseDtd.toString, Seq("-dtd"))
      srcLocation.mkdirs()
      log.info("Compiling DTD file(s) to %s".format(srcLocation.getAbsolutePath))
      log.debug("XJC java command line: " + options.mkString("\n"))
      val returnCode = Forker(javaHome, options, log)
      if (returnCode != 0) sys.error("Non zero return code from xjc [%d]".format(returnCode))
      else {
        val `Data.java` = Source.fromFile(intacctRoot / "response" / "Data.java").getLines().map(
          _.replaceAllLiterally("TrialbalanceOrAccountgroupdetailOrSessioninfoOrClientOrCompanyprefOrAppSubscriptionOrClosedbooksdateOrAragingOrIcitemtotalsOrItemtotalOrAccountgroupOrAdjjournalOrStatjournalOrApaccountlabelOrApadjustmentOrApadjustmentbatchOrAppaymentOrAptermOrProjectOrClazzOrAraccountlabelOrAradjustmentOrAradjustmentbatchOrArpaymentOrArpaymentbatchOrArtermOrArtransactiondefOrBankaccountOrBillOrBillbatchOrContactOrCustglgroupOrCustomerOrCustomervisibilityOrCustomerachinfoOrCustomerchargecardOrCustomerbankaccountOrCustomerppackageOrDepartmentOrEmployeeOrExpensereportOrExpensereportbatchOrExpensetypesOrExpensepaymenttypeOrGlaccountOrStatglaccountOrGlbudgetOrGlbudgetitemOrGlentryOrGltransactionOrIcitemOrIctotalOrStkittransactionOrIctransactionOrIctransactiondefOrInvoiceOrInvoicebatchOrItemglgroupOrJournalOrLocationOrLocationentityOrSupdocfolderOrSupdocOrCompanyInfoOrTrxcurrenciesOrPopricelistOrVsoepricelistOrVsoeitempricelistOrPotransactionOrPotransactiondefOrCsnhistoryOrLocationgroupOrPricelistitemOrProductlineOrRenewalmacroOrReportingperiodOrRevrecscheduleOrRevrecscheduleentryOrRevrectemplateOrSopricelistOrSotransactionOrRecursotransactionOrSotransactiondefOrSubscriptionOrTerritoryOrUomOrVendglgroupOrVendorOrVendorvisibilityOrWarehouseOrTaxdetailOrTaxscheduleOrTaxscheduledetailOrContacttaxgroupOrItemtaxgroupOrTaxschedulemapOrSmarteventlogOrAppaymentrequestOrTimesheetOrTaskOrProjecttypeOrProjectstatusOrTimetypeOrAllocationOrCctransactionOrRevrecchangelogOrVsoeallocationOrExpenseadjustmentreportOrEarningtypeOrEmployeerateOrAchbankconfigurationOrVendorentityaccountOrVendorprefOrEmployeeprefOrPermissionsOrApi", "Items")
            .replaceAllLiterally("trialbalanceOrAccountgroupdetailOrSessioninfoOrClientOrCompanyprefOrAppSubscriptionOrClosedbooksdateOrAragingOrIcitemtotalsOrItemtotalOrAccountgroupOrAdjjournalOrStatjournalOrApaccountlabelOrApadjustmentOrApadjustmentbatchOrAppaymentOrAptermOrProjectOrClazzOrAraccountlabelOrAradjustmentOrAradjustmentbatchOrArpaymentOrArpaymentbatchOrArtermOrArtransactiondefOrBankaccountOrBillOrBillbatchOrContactOrCustglgroupOrCustomerOrCustomervisibilityOrCustomerachinfoOrCustomerchargecardOrCustomerbankaccountOrCustomerppackageOrDepartmentOrEmployeeOrExpensereportOrExpensereportbatchOrExpensetypesOrExpensepaymenttypeOrGlaccountOrStatglaccountOrGlbudgetOrGlbudgetitemOrGlentryOrGltransactionOrIcitemOrIctotalOrStkittransactionOrIctransactionOrIctransactiondefOrInvoiceOrInvoicebatchOrItemglgroupOrJournalOrLocationOrLocationentityOrSupdocfolderOrSupdocOrCompanyInfoOrTrxcurrenciesOrPopricelistOrVsoepricelistOrVsoeitempricelistOrPotransactionOrPotransactiondefOrCsnhistoryOrLocationgroupOrPricelistitemOrProductlineOrRenewalmacroOrReportingperiodOrRevrecscheduleOrRevrecscheduleentryOrRevrectemplateOrSopricelistOrSotransactionOrRecursotransactionOrSotransactiondefOrSubscriptionOrTerritoryOrUomOrVendglgroupOrVendorOrVendorvisibilityOrWarehouseOrTaxdetailOrTaxscheduleOrTaxscheduledetailOrContacttaxgroupOrItemtaxgroupOrTaxschedulemapOrSmarteventlogOrAppaymentrequestOrTimesheetOrTaskOrProjecttypeOrProjectstatusOrTimetypeOrAllocationOrCctransactionOrRevrecchangelogOrVsoeallocationOrExpenseadjustmentreportOrEarningtypeOrEmployeerateOrAchbankconfigurationOrVendorentityaccountOrVendorprefOrEmployeeprefOrPermissionsOrApi", "items")
        ).mkString(System.lineSeparator())

        val dataWriter = new FileWriter(intacctRoot / "response" / "Data.java" getAbsolutePath)
        try {
          dataWriter.write(`Data.java`)
          dataWriter.flush()
        } finally {
          dataWriter.close()
        }
      }
    } else {
      log.debug("No sources newer than products, skipping.")
    }



    generated
  }

  private def xjcClean(sourceManaged: File, streams: TaskStreams) {
    import streams.log
    val filesToDelete = (sourceManaged / "main" / "java" ** "*.java").get
    log.debug("Cleaning Files:\n%s".format(filesToDelete.mkString("\n")))
    if (filesToDelete.nonEmpty) {
      log.info("Cleaning %d XJC generated files in %s".format(filesToDelete.size, (sourceManaged / "main" / "java" ).getAbsolutePath))
      IO.delete(filesToDelete)
    }
  }
}
