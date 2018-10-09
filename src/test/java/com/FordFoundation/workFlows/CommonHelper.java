/** Purpose: This class is the source class of all the Fluxx- Grantee and Grant Approval Workflow functions. 
* It contains all the project related functions. It internally calls the methods and functions authored in other java classes (e.g: FluxxObj.java, ActionEngine.java etc) 
* Author: Satyadeep Behera
*/

package com.FordFoundation.workFlows;

import java.util.List;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.handler.codec.frame.CorruptedFrameException;
import org.openqa.selenium.Alert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jacada.accelerators.ActionEngine;
import com.jacada.objectRepository.FluxObjects;
import com.jacada.objectRepository.FluxObjects;
import com.jacada.utilities.ReadingExcel;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.mail.util.BEncoderStream;
import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

@SuppressWarnings("all")
public class CommonHelper extends ActionEngine {

	public TreeMap<Object, Object> treemap;
	static final String testDataFileName = "FluxxOrgManagement";
	String sheetName="Organization";

	String responseFileCreationTime;
	public static String timeNow = "";
	FluxObjects fluxobs = new FluxObjects();
	public static String stat="";
	

	

	/**
	 * Method Name: launchFluxxUrl
	 * This method is used to launch the Fluxx application 
	 * @throws Throwable
	 */
	synchronized public void launchFluxxUrl (String username ,String password, String role) throws Throwable {

		try
		{
			String fordurl;
			fordurl = "https://ford-build.fluxx.io";
			launchUrl(fordurl);
			//	switchWindowByTitle1("BUILD - Ford");
			waitForElementPresent(fluxobs.titleWlcm_fordfoundation(), "Ford Foundation Welcome title", true);	
			type_input1(fluxobs.txt_username(), username, "username","Username :"+username, true);
			type_input1(fluxobs.txt_password(), password,"password","Password :" +password, true);
			click(fluxobs.btn_Signin(), "Navigating to Fluxx website","navigate to flux website", true);
			if(exists(fluxobs.cookie_msg("Okay")))
				click(fluxobs.cookie_msg("Okay"), "Click Okay", "click Okay", false);
			SuccessReport("Launch Fluxx-Build URL with '"+role+"' Login","Successfully launched the Fluxx-Build URL");
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	

	/**
	 * Method Name: launchFluxxUrl_SU
	 * This method is used to launch the Fluxx application (Not mentioned in report) 
	 * @throws Throwable
	 */
	synchronized public void launchFluxxUrl_SU (String username ,String password) throws Throwable {

		try
		{
			String fordurl;
			fordurl = "https://ford-build.fluxx.io";
			launchUrl(fordurl);
			waitForElementPresent(fluxobs.titleWlcm_fordfoundation(), "Ford Foundation Welcome title", true);	
			type_input1(fluxobs.txt_username(), username, "username","Username :"+username, true);
			type_input1(fluxobs.txt_password(), password,"password","Password :" +password, true);
			click(fluxobs.btn_Signin(), "Navigating to Fluxx website","navigate to flux website", true);
			if(exists(fluxobs.cookie_msg("Okay")))
				click(fluxobs.cookie_msg("Okay"), "Click Okay", "click Okay", false);
		}
		catch(Exception e)
		{
			throw e;
		}

	}


	/**
	 * Method Name: selectDashboard
	 * This method is used to select the dashboard after Fluxx application gets launched 
	 * @throws Throwable
	 */
	synchronized public void selectDashboard (String DashBoardname) throws Throwable {

		click(fluxobs.btn_dashboardline(), "Open the left pane to choose any dashboard", "select the left pane",true);
		try
		{
			//Select an already selected dashboard
			if (exists(fluxobs.dashboard_select(DashBoardname))) {
	
				click(fluxobs.btn_dashboardline(), "Select dashboard", "sucessfully able to select the dashboard",false);
	
			}
			
			//Create new dashboard from templates
			else {
				click(fluxobs.dashboard_arrow(), "Choose required Dashboard", "required Dashboard",false);
				Thread.sleep(3000);
				if(exists(fluxobs.select_Dashboard(DashBoardname)))
				{
					JSClick(fluxobs.select_Dashboard(DashBoardname), "Choose Dashboard", false); 
					click(fluxobs.btn_dashboardline(), "Close the left pane", "sucessfully able to Close the left pane",false);
				}
				else
				{
					click(fluxobs.select_frm_Dashboard("New"),"Select New From Dashboard Template","Sucessfully able to Select New From Dashboard Template",true);
					click(fluxobs.select_Template(),"Select Template From Dashboard Panel","Sucessfully able to Select Template From Dashboard Panel",true);
					click(fluxobs.select_DashboardfrmTemp(DashBoardname),"Select Dashboard From Dashboard Panel","Sucessfully able to Select Dashboard From Dashboard Panel",true);
					Thread.sleep(2000);
				}
					
	
			}
			SuccessReport("Select "+DashBoardname+" dashboard", "Selected "+DashBoardname+" dashboard Successfully");
		}
		catch(Exception e)
		{
			throw new Exception("Select '"+DashBoardname+"' dashboard~Unable to Select '"+DashBoardname+"' dashboard");
		}

	}

	/**
	 * Method Name: logout_fluxwebsite
	 * This method is used to log out from Fluxx application 
	 * @throws Throwable
	 */
	synchronized public void logout_fluxwebsite() throws Throwable {
		try
		{
			waitForElementToBeClickable(fluxobs.linkByTitle("My Profile and Settings"), "Click on My Profile", true);
			Thread.sleep(3000);
			JSClick(fluxobs.linkByTitle("My Profile and Settings"),"Click on My Profile",true);
			waitForElementPresent(fluxobs.fluxxSignOutLink("My Profile and Settings"), "Click on 'Sign Out'", true);
			JSClick(fluxobs.fluxxSignOutLink("My Profile and Settings"),"Click on 'Sign Out'",true);
			Thread.sleep(3000);
			if(isAlertPresent()){
	
				Alert alert = driver.switchTo().alert();
				alert.accept();
	
			}
	
			waitForElementPresent(fluxobs.titleWlcm_fordfoundation(), "Ford Foundation Welcome title", true);
			
		}
		catch(Exception e)
		{
			throw new Exception("Logout from fluxx~Unable to logout from fluxx");
		}
	}
	
	
	/**
	 * Method Name: logout_from_fluxx
	 * This method is used to log out from Fluxx application (Role Mentioned in report)
	 * @throws Throwable
	 */
	synchronized public void logout_from_fluxx(String portalName) throws Throwable {
		try
		{
			if(exists(fluxobs.close_btn()))
			{
				click(fluxobs.close_btn(), "Cancel the edited details","Cancel the edited details", true);
				Thread.sleep(3000);
			}
			  
			waitForElementToBeClickable(fluxobs.linkByTitle("My Profile and Settings"), "Click on My Profile", true);
			Thread.sleep(3000);
			JSClick(fluxobs.linkByTitle("My Profile and Settings"),"Click on My Profile",true);
			waitForElementPresent(fluxobs.fluxxSignOutLink("My Profile and Settings"), "Click on 'Sign Out'", true);
			JSClick(fluxobs.fluxxSignOutLink("My Profile and Settings"),"Click on 'Sign Out'",true);
			Thread.sleep(7000);
			for(int i=0;i<3;i++)
			{
				if(isAlertPresent()){
		
					Alert alert = driver.switchTo().alert();
					alert.accept();
					Thread.sleep(3000);
				}
			}
			Thread.sleep(5000);
			SuccessReport("Log out from "+portalName, "Successfully logged out");
		}
		catch(Exception e)
		{
			throw new Exception("Logout from fluxx~Unable to logout from fluxx");
		}
	}
	
	
	

	/**
	 * Method Name: logout_fluxgranteeExtportal
	 * This method is used to log out from Fluxx Grantee external portal application (Role Mentioned in report)
	 * @throws Throwable
	 */
	synchronized public void logout_fluxgranteeExtportal() throws Throwable {
		try
		{
			Thread.sleep(4000);
			if(exists(fluxobs.close_btn()))
			{
				click(fluxobs.close_btn(), "Cancel the edited details","Cancel the edited details", true);
				Thread.sleep(5000);
			}
			
			if(exists(fluxobs.btn_ui("Cancel")))
			{
				click(fluxobs.btn_ui("Cancel"), "Cancel the edited details","Cancel the edited details", true);
				Thread.sleep(5000);
			}
			waitForElementPresent(fluxobs.btn_settings(), "Cancel the edited details", false);
			click(fluxobs.btn_settings(), "open settings in grantee external portal","open the settings tab", true);
			click(fluxobs.btn_logoutextPortal(), "Log out from the grantee external portal","log out form the grantee external portal", true);
			waitForElementPresent(fluxobs.titleWlcm_fordfoundation(), "Ford Foundation Welcome title", true);	
			SuccessReport("Log out from Grantee Portal", "Successfully logged out");
		}
		catch(Exception e)
		{
			throw new Exception("Log out from Grantee portal~Unable to logout from Grantee portal");
		}

	}
	
	
	/**
	 * Method Name: logout_fluxx
	 * This method is used to log out from Fluxx application 
	 * @throws Throwable
	 */
	synchronized public void logout_fluxx() throws Throwable {
		try
		{
			if(exists(fluxobs.fluxx_logo_load()))
				logout_from_fluxx("Fluxx"); 
			else if(exists(fluxobs.fluxxGrantee_logo_load()))
				logout_fluxgranteeExtportal();
		}
		catch(Exception e)
		{
			throw new Exception("Log out from Fluxx~Unable to logout from Fluxx");
		}

	}



	/**
	 * Method Name: createNewOrg_withOrgBtn
	 * This method is used to create new org Fluxx application 
	 * @throws Throwable
	 */
	synchronized public void createNewOrg_withOrgBtn (String OrgName) throws Throwable {

		CloseCards();
		Thread.sleep(2000); 
		waitForElementPresent(fluxobs.btn_new("New Org"), "New organization card", true);
		click(fluxobs.btn_new("New Org"), "Opening the new Organization card","open the new organization card", true);
		waitForElementPresent(fluxobs.txt_cardname("New Org"), "New organization card", true);
		type_input(fluxobs.txtbox("organization_name"), OrgName, "Organization name","Organization name "+OrgName +" in Organization Name Field", true);
		click(fluxobs.btn_save(), "saving the organiztion card to create new organization","save the card and create new organization ", true);
		Thread.sleep(5000);

	}   

	
	/**
	 * Method Name: GM_reviewStage
	 * This method is used to login and approve the req by Grants Manager
	 * @throws Throwable
	 */
	synchronized public void GM_reviewStage () throws Throwable {

		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String gmLogin = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String gmpwd = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			launchFluxxUrl(gmLogin , gmpwd, "Grants Manager");
			selectDashboard("GM Organization"); 
			
			mouseover(fluxobs.first_card("gm review"), "mouseover",false);
			waitForElementToBeClickable(fluxobs.first_card("gm review"),"Click on Compliance Review", true);
			waitForElementPresent(fluxobs.first_card("gm review"),"Click on Compliance Review", true);
			waitForVisibilityOfElement(fluxobs.first_card("gm review"),"Click on Compliance Review", true);
			mouseover(fluxobs.card_Name("gm review"), "mouseover",false);
			waitForElementToBeClickable(fluxobs.card_Name("gm review"),"Organization Name", true);
			waitForElementPresent(fluxobs.card_Name("gm review"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.card_Name("gm review"),"Organization Name", true);
			
			Thread.sleep(15000);
			click(fluxobs.card_Name("gm review"), "Click on Org Name", "ClickonOrgName", true);
			typeClear(fluxobs.searchTextInCard("gm review"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2containsStrng(OrgName), "Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2containsStrng(OrgName), "Click on Org Name", "ClickonOrgName", true);
			
			//waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgName), "Organization Name", true);
			waitForVisibilityOfElement(fluxobs.btn_WorkFlowcGAL("gm review"), "Workflow button", true);
			Thread.sleep(3000);
			JSClick(fluxobs.btn_WorkFlowcGAL("gm review"), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("gm review","GM Verify"), "select GM Verify", true);
			Thread.sleep(2000);
			JSClick(fluxobs.Comp_wfbtn("gm review","GM Verify"), "select GM Verify", true);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.btn_witha("OK"), "OK", true);
			click(fluxobs.btn_witha("OK"), "select OK","Select OK", true);
			waitForVisibilityOfElement(fluxobs.btn_witha("Compliance Review"), "Compliance Review", true);
			String status = VerifyStatus("gm review", "Compliance Review");
			logout_from_fluxx("Fluxx with Grants manager Login"); 
			SuccessReport("Review and submit the grantee record via Grant Manager", "Successfully submitted the organization record");
			SuccessReport("Verify the Status of the Grantee", "Status of the Grantee: <B>"+status+"</B>");
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in GM_reviewStage");
			System.out.println("#####################");
			//failureReport("Review and submit the organization record via Grant Manager", "Unable to submit the organization record");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Grants Manager Review Stage of 'Grantee Creation')", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}


	}

	
	
	/**
	 * Method Name: LegallyVerifyStage
	 * This method is used to login and approve the req by Grants Processing Unit
	 * @throws Throwable
	 */
	synchronized public boolean LegallyVerifyStage () throws Throwable {

		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String knownAs = treemap.get("Organization Card_ORGANIZATION INFORMATION_NA_Formerly Known As").toString().trim();
			String locLangName = treemap.get("Organization Card_ORGANIZATION INFORMATION_NA_Local Language Name").toString().trim();
			String sortAsName = treemap.get("Organization Card_ORGANIZATION INFORMATION_NA_Sort As Name").toString().trim();
			String pplManual = treemap.get("Organization Card_ORGANIZATION INFORMATION_NA_PPL Manual").toString().trim();
			String trstOffrAff = treemap.get("Organization Card_AFFILIATIONS_NA_Trustee/Officer Affiliated").toString().trim();
			String trstOffrAffDtl = treemap.get("Organization Card_AFFILIATIONS_NA_Trustee/Officer Affiliated Details").toString().trim();
			String stfAff = treemap.get("Organization Card_AFFILIATIONS_NA_Staff Affiliated").toString().trim();
			String stfAffDtl = treemap.get("Organization Card_AFFILIATIONS_NA_Staff Affiliated Details").toString().trim();
			String taxSts = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_Tax Status").toString().trim();
			String taxClf = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_Tax Classification").toString().trim();
			String erFlag = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_ER Flag").toString().trim();
			String taxDocType = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_Tax Document Type").toString().trim();
			String taxGeoInd = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_Tax Geographic Indicator").toString().trim();
			String taxEffDate = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_Tax Effective Date").toString().trim();
			String lstFinStmtDate = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_Last Financial Statement Date").toString().trim();
			String irsAdvDt = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_IRS Advance Ruling Effective Date").toString().trim();
			String irsFinalDt = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_IRS Final Ruling Effective Date").toString().trim();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			String granteeType = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Grantee Type").toString().trim();
			
			String gpuLogin = treemap.get("Login_NA_NA_Grants Processing_Login").toString().trim();
			String gpupwd = treemap.get("Login_NA_NA_Grants Processing_Login").toString().trim();
			launchFluxxUrl(gpuLogin , gpupwd,"Grants Processing");
			selectDashboard("Compliance Action");
			
			//if(exists(fluxobs.card_Name("ORG - compliance rev")))
				//click(fluxobs.card_Name("ORG - compliance rev"), "Click on Org Name", "ClickonOrgName", true);
			//else
			mouseover(fluxobs.first_card("ORG - compliance rev"), "mouseover",false);
			waitForElementToBeClickable(fluxobs.first_card("ORG - compliance rev"),"Click on Compliance Review", true);
			waitForElementPresent(fluxobs.first_card("ORG - compliance rev"),"Click on Compliance Review", true);
			waitForVisibilityOfElement(fluxobs.first_card("ORG - compliance rev"),"Click on Compliance Review", true);
			Thread.sleep(10000);
			click(fluxobs.first_card("ORG - compliance rev"),"Click on Compliance Review","Click on Compliance Review", true);
			typeClear(fluxobs.searchTextInCard("ORG - compliance rev"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2containsStrng(OrgName), "Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2containsStrng(OrgName), "Click on Org Name", "ClickonOrgName", true);
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgName), "Organization Name", true);

			click(fluxobs.Comp_wfbtn("ORG - compliance rev","Edit"),"Click on Edit", "Click on Edit", true);

			//Organization Information section
			waitForElementToBeClickable(fluxobs.Comp_grantFields("ORG - compliance rev","ORGANIZATION INFORMATION"), "Open the Organization information details", true);
			waitForElementPresent(fluxobs.Comp_grantFields("ORG - compliance rev","ORGANIZATION INFORMATION"), "Open the Organization information details", true);
			waitForVisibilityOfElement(fluxobs.Comp_grantFields("ORG - compliance rev","ORGANIZATION INFORMATION"), "Open the Organization information details", true);
			Thread.sleep(10000);
			click(fluxobs.Comp_grantFields("ORG - compliance rev","ORGANIZATION INFORMATION"), "Open the Organization information details","opened the Organization Information details", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_sort_as_name"), sortAsName, "Organization sort as name","Organization sort as name", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_fka_name"), knownAs, "Formerly Known as name","Formerly Known as name", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_name_foreign_language"), locLangName, "Local Language Name","Local Language Name", true);
			mouseover(fluxobs.select_contactfields("organization_ppl_manual"), "PPL Manual", true);
			selectByVisibleText(fluxobs.select_contactfields("organization_ppl_manual"), pplManual, "PPL Manual", true);
			
			
			//Affiliations Section
			click(fluxobs.Comp_grantFields("ORG - compliance rev","AFFILIATIONS"), "Open the AFFILIATIONS details","opened the AFFILIATIONS details", true);
			mouseover(fluxobs.select_contactfields("organization_affiliated_staff_yn"), "Staff Affiliated", true);
			selectByVisibleText(fluxobs.select_contactfields("organization_affiliated_yn"), trstOffrAff, "Trustee/Officer Affiliated", true);
			if(trstOffrAff.toLowerCase().trim().equals("yes"))
				typeAndEnter(fluxobs.text_Area("organization_affiliated_trustee_text"), trstOffrAffDtl, "Trustee/Officer Affiliated Details","Trustee/Officer Affiliated Details", true);
			selectByVisibleText(fluxobs.select_contactfields("organization_affiliated_staff_yn"), stfAff, "Staff Affiliated", true);
			if(stfAff.toLowerCase().trim().equals("yes"))
				typeAndEnter(fluxobs.text_Area("organization_affiliated_staff_text"), stfAffDtl, "Staff Affiliated Details","Staff Affiliated Details", true);
			
			if(granteeType.toLowerCase().equals("individual"))
			{
				click(fluxobs.Comp_grantFields2("ORG - compliance rev","TAX AND FINANCIAL INFORMATION"), "Open the Tax and financial information details","opened the TAX AND FINANCIAL INFORMATION details", true);
				selectByVisibleText(fluxobs.select_contactfields("organization_er_yn"), erFlag, "ER Flag", true);
			}
			if(granteeType.toLowerCase().equals("organization"))
			{
				// filling tax and financial information                                       
				click(fluxobs.Comp_grantFields("ORG - compliance rev","TAX AND FINANCIAL INFORMATION"), "Open the Tax and financial information details","opened the TAX AND FINANCIAL INFORMATION details", true);
				selectByVisibleText(fluxobs.select_contactfields("organization_er_yn"), erFlag, "ER Flag", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_tax_id"), "7", "Organization tax id","Organization tax id", true);
				selectByVisibleText(fluxobs.select_contactfields("organization_tax_status"), taxSts, "Tax Status", true);
				selectByVisibleText(fluxobs.select_contactfields("organization_tax_classification"),taxClf , "Tax Classification", true);
				
				selectByVisibleText(fluxobs.select_contactfields("organization_tax_document_type"), taxDocType, "Tax Document Type", true);
				selectByVisibleText(fluxobs.select_contactfields("organization_tax_geographic_indicator"), taxGeoInd, "Tax Geographic Indicator", true);
	
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_tax_registration_date"), taxEffDate, "Tax Effective Date","Tax Effective Date", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_last_financial_statement_date"), lstFinStmtDate, "Last Financial Statement Date","Last Financial Statement Date", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_irs_advanced_ruling_expireation_date"), irsAdvDt, "IRS Advance Ruling Effective Date","IRS Advance Ruling Effective Date", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_irs_final_ruling_date"), irsFinalDt, "IRS Final Ruling Effective Date","IRS Final Ruling Effective Date", true);
				// tax details END
				
			}
			
			click(fluxobs.btn_ui("Save"), "Click on Save", "Click on Save", true);
			waitForElementToBeClickable(fluxobs.btn_WorkFlow(OrgName),"Organization Name", false);
			waitForElementPresent(fluxobs.btn_WorkFlow(OrgName),"Organization Name", false);
			waitForVisibilityOfElement(fluxobs.btn_WorkFlow(OrgName),"Organization Name", false);
			Thread.sleep(10000);
			JSClick(fluxobs.btn_WorkFlow(OrgName), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.btn_witha("Approve"), "Approve", true);
			click(fluxobs.btn_witha("Approve"), "Approve it","Approve it", true);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.btn_witha("OK"), "OK", false);
			waitForElementToBeClickable(fluxobs.btn_witha("OK"), "OK", false);
			waitForElementPresent(fluxobs.btn_witha("OK"), "OK", false);
			click(fluxobs.btn_witha("OK"), "select OK","Approve it", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("ORG - compliance rev","Legally Verified"), "Legally Verified", false);
			String status = VerifyStatus("ORG - compliance rev", "Legally Verified");
			logout_from_fluxx("Fluxx with Grants Processing Login"); 
			SuccessReport("Review and approve the grantee via GPU", "Successfully approved the grantee");
			SuccessReport("Verify the Status of the Grantee", "Status of the Grantee: <B>"+status+"</B>");

			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in LegallyVerifyStage");
			System.out.println("#####################");
			//failureReport("Review and approve the organization record via GPU", "Unable to approve the organization record");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Compliance Review Stage of 'Grantee Creation')", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}



	}

	

	/**
	 * Method Name: PossibleStage
	 * This method is used to fill in and submit the req by Program Officer
	 * @throws Throwable
	 */
	synchronized public boolean PossibleStage () throws Throwable {
		try
		{

			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String poLogin = GetPOLogin();
			String poPwd = GetPOLogin();
			String commentsToGrantee = treemap.get("Request Card_Comments to Grantee__Application Comments").toString().trim();
			String suppType = treemap.get("Request Card_Support Type_NA_Support Type").toString().trim();
			String startDate = treemap.get("Request Card_GRANT TERMS_NA_Start Date").toString().trim().replace("-", "/");
			String startDateTBD = treemap.get("Request Card_GRANT TERMS_NA_Start Date is TBD").toString().trim();
			String rpoOfc = treemap.get("Request Card_REQUEST OVERVIEW_NA_RPO Office").toString().trim();
			String approvalType = treemap.get("Request Card_REQUEST OVERVIEW_NA_Approval Type").toString().trim();
			String targetApprovalMonth = treemap.get("Request Card_REQUEST OVERVIEW_NA_Target Approval Month").toString().trim();
			String appFiscalYr = treemap.get("Request Card_REQUEST OVERVIEW_NA_Approval Fiscal Year").toString().trim();
			String amtRecmded = treemap.get("Request Card_GRANT TERMS_NA_Amount Recommended").toString().trim();
			String termsMnths = treemap.get("Request Card_GRANT TERMS_NA_Term(Months)").toString().trim();
			String reqType = treemap.get("RequestType").toString().trim();
			String buildType = treemap.get("Request Card_Request Special Category_NA_Build type").toString().trim();
			String impactedCountries = treemap.get("Request Card_IMPACTED COUNTRIES/REGION_NA_Impacted Countries/Region").toString().trim();
			String planningLows = treemap.get("Request Card_FUNDING SOURCE_NA_Planning LOWs").toString().trim();
			String OrgNameUppercase = OrgName.toUpperCase();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			
			//For multiple Support types
			String[] suppTypes= new String[1];
			if(suppType.contains("/"))
				suppTypes = suppType.split("/");
			else
				suppTypes[0] = suppType;
			
			//For multiple Impacted countries/regions
			String[] impactedCountriesList = new String[1];
			if(impactedCountries.contains("/"))
				impactedCountriesList = impactedCountries.split("/");
			else
				impactedCountriesList[0] = impactedCountries;
			
			//For multiple Planning LOWs
			String[] lows=new String[1];
			if(planningLows.contains("/"))
				lows = planningLows.split("/");
			else
				lows[0] = planningLows;
			
			launchFluxxUrl(poLogin , poPwd, "Program Officer");
			selectDashboard("PO Ideas & Planning");
			Thread.sleep(2000);
			
			mouseover(fluxobs.searchTextInCard("possible"), "mouseover",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("possible"),"Organization Name", false);
			waitForElementPresent(fluxobs.searchTextInCard("possible"),"Organization Name", false);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("possible"),"Organization Name", false);
			Thread.sleep(30000);
			typeClear(fluxobs.searchTextInCard("possible"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("possible",OrgName), "Click On Organization Name", false);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("possible",OrgName), "Click on Org Name", "ClickonOrgName", true);
			
			
			waitForElementToBeClickable(fluxobs.txt_withucontainsStrng(OrgNameUppercase),"Organization Name", false);
			waitForElementPresent(fluxobs.txt_withucontainsStrng(OrgNameUppercase),"Organization Name", false);
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", false);
			click(fluxobs.Comp_wfbtn("possible", "Edit"), "Click on Edit", "Click on Edit", true);
			waitForElementPresent(fluxobs.Comp_wfbtn("possible","Save"),"Save Button", false);
			waitForElementToBeClickable(fluxobs.drpdown_field("grant_request_office"),"grant_request_office", false);
			waitForElementPresent(fluxobs.drpdown_field("grant_request_office"),"grant_request_office", false);
			waitForVisibilityOfElement(fluxobs.drpdown_field("grant_request_office"),"grant_request_office", false);
			
			mouseover(fluxobs.drpdown_field("grant_request_office"), "RPO Office", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_office"), rpoOfc, "RPO Office", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_approval_type"), approvalType, "Approval type", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_target_approval_period_type"), targetApprovalMonth, "Target Approval Month", true);
			typeAndEnter(fluxobs.txtbox("grant_request_spending_year"), appFiscalYr, "Approval Fiscal Year","Approval Fiscal Year", true);
			
			//Add Support Type
			mouseover(fluxobs.Comp_grantFields("possible","Support Type"), "Support Type", false);
			if(!(exists(fluxobs.multi_check("grant_request_type_of_support"))))
			{
				click_validate(fluxobs.plusSign("Add Support Type"), "click add type of support", "Click on type of support", true);
				
				waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
				mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
				
				waitForVisibilityOfElement(fluxobs.txt_spanwithcontains(suppTypes[0]), "wait for general", true);
				
				
				for (String supp : suppTypes)
				{
					btn_click(fluxobs.txt_spanwithcontains(supp), "Select Support Type", "Select Support Type", true);
				}
			
			
				Thread.sleep(2000);
				click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				click(fluxobs.btn_witha("Add Support Type"), "add type of support", "add type of support", true);
				// update percentage to 100 and you need to write the code here for this
				Thread.sleep(3000);
				
				if(suppTypes.length==1)
				{
					WebElement slider = driver.findElement(fluxobs.percentage_slider(suppTypes[0],"1"));
					Actions action = new Actions(driver);
					action.dragAndDropBy(slider, 220, 0).build().perform();
				}
				else if(suppTypes.length==2)
				{
					WebElement slider1 = driver.findElement(fluxobs.percentage_slider(suppTypes[0],"1"));
					Actions action1 = new Actions(driver);
					action1.dragAndDropBy(slider1, 98, 0).build().perform();
					
					WebElement slider2 = driver.findElement(fluxobs.percentage_slider(suppTypes[1],"1"));
					Actions action2 = new Actions(driver);
					action2.dragAndDropBy(slider2, 98, 0).build().perform();
				}
				if(suppTypes.length==3)
				{
					WebElement slider1 = driver.findElement(fluxobs.percentage_slider(suppTypes[0],"1"));
					Actions action1 = new Actions(driver);
					action1.dragAndDropBy(slider1, 74, 0).build().perform();
					
					WebElement slider2 = driver.findElement(fluxobs.percentage_slider(suppTypes[1],"1"));
					Actions action2 = new Actions(driver);
					action2.dragAndDropBy(slider2, 61, 0).build().perform();
					
					WebElement slider3 = driver.findElement(fluxobs.percentage_slider(suppTypes[2],"1"));
					Actions action3 = new Actions(driver);
					action3.dragAndDropBy(slider3, 61, 0).build().perform();
				}
				click(fluxobs.btn_witha("Update Percentages"), "Update Percentages", "Update Percentages", true);
			}
			else
			{
				for (String supp : suppTypes)
				{
					supp = supp.trim();
					selectByVisibleText(fluxobs.multi_check("grant_request_type_of_support"),supp,"Support Types",true);
					JSClick(fluxobs.tree_controls("select"), "Click next", true);
				}
			}
			Thread.sleep(2000);
			if(reqType.toLowerCase().equals("build"))
				selectByVisibleText(fluxobs.dropdownbyId("grant_request_build_type"),buildType, "Build Type", true);
			
			for (String country : impactedCountriesList) {
				country = country.trim();
				click(fluxobs.select_combobox("grant_request_organization_overseas_list",country), "Select Impacted Countries", "Select Impacted Countries", true);
				click(fluxobs.select_control("grant_request_organization_overseas_list",">"), "Select Impacted Countries", "Select Impacted Countries", true);
			}
			
			typeAndEnter(fluxobs.txtbox("grant_request_amount_recommended"), amtRecmded, "Amount Recomended","Amount Recomended", true);
			click(fluxobs.startDateTBD(startDateTBD),"select the Start Date", "select the Start Date", true);
			//click(fluxobs.date_selectdrp_byLabel("Start Date","grant_request_grant_begins_at"),"select the organization registation date", "select the organiztion registration date", true);
			Thread.sleep(1000);
			//click(fluxobs.date_selection("24"), "Select date","select the Date", true);
			if(startDateTBD.trim().toLowerCase().equals("no"))
			{
				mouseover(fluxobs.startDate("grant_request_grant_begins_at"), "mouseover on Grant Liasion",false);
				typeAndEnter(fluxobs.startDate("grant_request_grant_begins_at"), startDate, "Grantee Title", "The Grantee title is successfully entered",true);
				typeAndEnter(fluxobs.txtbox("grant_request_duration_in_months"), termsMnths, "Duration in Months","Duration in Months", true);
			}
			

			click(fluxobs.plusSign("Add Planning"), "Add Planning", "Add Planning", true);
			
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Planning"), "wait for Add Planning", true);

			for (String low : lows) {
				low = low.trim();
				String par_low=null;
				String ch_low=null;
				if(low.contains("~"))
				{
					par_low = low.split("[~]")[0].trim();
					ch_low = low.split("[~]")[1].trim();
					typeAndEnter(fluxobs.tree_search(), par_low, "Parent LOW '"+par_low+"'", "Parent LOW",true);
					//click(fluxobs.txt_spanwithcontains(par_low), "Select LOW '"+par_low+"'", "Select LOW", true);
					click(fluxobs.txt_withspanContains_sibling(par_low), "Select LOW '"+par_low+"'", "Select LOW", true);
					Thread.sleep(2000);
					click_validate(fluxobs.txt_spanwithcontains(ch_low), "Select LOW '"+ch_low+"'", "Select LOW", true);
					btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					mouseover(fluxobs.txt_spanwithcontains(low), "mouseover on low",false);
					click_validate(fluxobs.txt_spanwithcontains(low), "Select LOW '"+low+"'", "Select LOW", true);
					btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Planning"), "Add Planning", "Add Planning", true);
			Thread.sleep(3000);
			
			
			
			if(lows.length==1)
			{
				String lowName1 = lows[0].contains("~") ? lows[0].split("[~]")[0].trim() + " " + "/" + " " + lows[0].split("[~]")[1].trim() : lows[0].trim();
				
				WebElement slider = driver.findElement(fluxobs.percentage_slider(lowName1,"1"));
				Actions action = new Actions(driver);
				action.dragAndDropBy(slider, 220, 0).build().perform();
			}
			else if(lows.length==2)
			{
				String lowName1 = lows[0].contains("~") ? lows[0].split("[~]")[0].trim() + " " + "/" + " " + lows[0].split("[~]")[1].trim() : lows[0].trim();
				String lowName2 = lows[1].contains("~") ? lows[1].split("[~]")[0].trim() + " " + "/" + " " + lows[1].split("[~]")[1].trim() : lows[1].trim();
				
				WebElement slider1 = driver.findElement(fluxobs.percentage_slider(lowName1,"1"));
				Actions action1 = new Actions(driver);
				action1.dragAndDropBy(slider1, 98, 0).build().perform();
				
				WebElement slider2 = driver.findElement(fluxobs.percentage_slider(lowName2,"1"));
				Actions action2 = new Actions(driver);
				action2.dragAndDropBy(slider2, 98, 0).build().perform();
			}
			if(lows.length==3)
			{
				String lowName1 = lows[0].contains("~") ? lows[0].split("[~]")[0].trim() + " " + "/" + " " + lows[0].split("[~]")[1].trim() : lows[0].trim();
				String lowName2 = lows[1].contains("~") ? lows[1].split("[~]")[0].trim() + " " + "/" + " " + lows[1].split("[~]")[1].trim() : lows[1].trim();
				String lowName3 = lows[2].contains("~") ? lows[2].split("[~]")[0].trim() + " " + "/" + " " + lows[2].split("[~]")[1].trim() : lows[2].trim();
				
				
				WebElement slider1 = driver.findElement(fluxobs.percentage_slider(lowName1,"1"));
				Actions action1 = new Actions(driver);
				action1.dragAndDropBy(slider1, 74, 0).build().perform();
				
				WebElement slider2 = driver.findElement(fluxobs.percentage_slider(lowName2,"1"));
				Actions action2 = new Actions(driver);
				action2.dragAndDropBy(slider2, 61, 0).build().perform();
				
				WebElement slider3 = driver.findElement(fluxobs.percentage_slider(lowName3,"1"));
				Actions action3 = new Actions(driver);
				action3.dragAndDropBy(slider3, 61, 0).build().perform();
			}
			click(fluxobs.btn_witha("Update Percentages"), "Update Percentages", "Update Percentages", true);
		
			Thread.sleep(3000);
			
			typeAndEnter(fluxobs.text_Area("grant_request_comments_to_grantee"), commentsToGrantee, "Comments To Grantee","Comments To Grantee", true);
			
			
			click(fluxobs.Comp_wfbtn("possible", "Save"), "Save", "Save", true);
			Thread.sleep(12000);
			JSClick(fluxobs.btn_WorkFlow(OrgNameUppercase), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.btn_witha("Request Information"), "Probable", true);
			click(fluxobs.btn_witha("Request Information"), "select Probable","", true);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);
			/*click(fluxobs.editWF_Note("Edit Note"), "Edit Note","Edit Note", true);
			waitForVisibilityOfElement(fluxobs.text_AreaClass("textarea-workflow-note"), "Edit Note", true);
			click(fluxobs.text_AreaClass("textarea-workflow-note"), "Edit Note","Edit Note", true);
			typeAndEnter(fluxobs.text_AreaClass("textarea-workflow-note"), commentsToGrantee, "Comments to Grantee","Comments to Grantee", true);*/			
			click(fluxobs.btn_witha("OK"), "select OK","", true);
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Request for Information"), "Probable", true);
			String status = VerifyStatus("possible", "Request for Information").trim();
			
			logout_from_fluxx("Fluxx with Program Officer Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+status+"</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}
		catch(Exception e)
		{
			System.out.println("#####################");
			System.out.println("Error in Possible Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Possible)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}
	}
	
	
	
	/**
	 * Method Name: InfoReviewStage
	 * This method is used approve the req by Program Officer
	 * @throws Throwable
	 */
	synchronized public boolean InfoReviewStage () throws Throwable {
		try
		{

			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String poLogin = GetPOLogin();
			String poPwd = GetPOLogin();
			String commentsToGrantee = treemap.get("Request Card_Comments to Grantee__Application Comments").toString().trim();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			launchFluxxUrl(poLogin , poPwd, "Program Officer");
			selectDashboard("PO Ideas & Planning");
			Thread.sleep(2000);
			mouseover(fluxobs.searchTextInCard("Information review"), "mouseover on Grant Liasion",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("Information review"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("Information review"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("Information review"),"Organization Name", true);
			Thread.sleep(15000);
			
			typeClear(fluxobs.searchTextInCard("Information review"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("Information review",OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("Information review",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Information Review"), "Information Review", true);
			JSClick(fluxobs.btn_WorkFlowcGAL("Information review"), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("Information review","Send to Probable"), "Information Review", true);
			
			click(fluxobs.Comp_wfbtn("Information review","Send to Probable"), "select Send to Probable","", true);	
			
			
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);
			/*click(fluxobs.editWF_Note("Edit Note"), "Edit Note","Edit Note", true);
			waitForVisibilityOfElement(fluxobs.text_AreaClass("textarea-workflow-note"), "Edit Note", true);
			click(fluxobs.text_AreaClass("textarea-workflow-note"), "Edit Note","Edit Note", true);
			typeAndEnter(fluxobs.text_AreaClass("textarea-workflow-note"), "Successfully Submitted", "Comments","Comments", true);*/			
			click(fluxobs.btn_witha("OK"), "select OK","", true);
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Probable"), "Probable", true);
			
			
			String status = VerifyStatus("Information review", "Probable");
			logout_from_fluxx("Fluxx with Program Officer Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+status+"</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}
		catch(Exception e)
		{
			System.out.println("#####################");
			System.out.println("Error in Possible Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Information Review)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}
	}

	/**
	 * Method Name: ProbableStage
	 * This method is used fill in and submit the req by Program director
	 * @throws Throwable
	 */
	synchronized public boolean ProbableStage () throws Throwable {
		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String fundingAmt = treemap.get("Request Card_FUNDING SOURCE_Funding Sources_Funding amount").toString().trim();
			String fiscalYr = treemap.get("Request Card_FUNDING SOURCE_Funding Sources_Fiscal Year").toString().trim();
			String fundingSrc = treemap.get("Request Card_FUNDING SOURCE_Funding Sources_Funding Source").toString().trim();
			String low = treemap.get("Request Card_FUNDING SOURCE_Funding Sources_Lines of Work").toString().trim();
			String budType = treemap.get("Request Card_FUNDING SOURCE_Funding Sources_Budget Type").toString().trim();
			String reqType=treemap.get("RequestType").toString().trim();
			String rpoOfc = treemap.get("Request Card_REQUEST OVERVIEW_NA_RPO Office").toString().trim();
			String status = "";
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			
			String dircLogin = treemap.get("Login_NA_NA_Program Director_Login").toString().trim();
			String dircPwd = treemap.get("Login_NA_NA_Program Director_Login").toString().trim();
			boolean lockFlag=false;
			launchFluxxUrl(dircLogin , dircPwd, "Program Director");
			selectDashboard("Director Action"); 
			
			mouseover(fluxobs.btn_CardAction("my office - probable"), "mouseover",false);
			waitForElementToBeClickable(fluxobs.btn_CardAction("my office - probable"),"My Office-Probable Card", true);
			waitForElementPresent(fluxobs.btn_CardAction("my office - probable"),"My Office-Probable Card", true);
			waitForVisibilityOfElement(fluxobs.btn_CardAction("my office - probable"),"My Office-Probable Card", true);
			Thread.sleep(15000);
			/*if (isElementPresent(fluxobs.close_contactcard("New Request"), "Closecard")) {

				click(fluxobs.close_contactcard("New Request"), "close contact card", "close contact card", true);

			}*/
			
			waitForVisibilityOfElement(fluxobs.card_present("my office - probable"), "My Office-Probable Card", true);
			click(fluxobs.btn_CardAction("my office - probable"), "My Office-Projected Card", "My Office-Probable Card", true);
			waitForVisibilityOfElement(fluxobs.btn_CardOpnFilter("my office - probable"), "My Office-Probable Card", true);
			click(fluxobs.btn_CardOpnFilter("my office - probable"), "My Office-Projected Card", "My Office-Probable Card", true);
			waitForVisibilityOfElement(fluxobs.btn_FilterCondition("Office"), "My Office-Probable Card", true);
			
			String classname = getAttribute(fluxobs.chk_addnotqual("my office - probable"), "class", "get class name");
			if(classname.contains("label-disabled-looking disabled-looking"))
				lockFlag = true;
			if(lockFlag)
				click(fluxobs.chk_lockFilter("my office - probable"), "My Office-Projected Card", "My Office-Projected Card", true);
			click(fluxobs.btn_FilterCondition("Office"), "My Office-Projected Card", "My Office-Projected Card", true);
			waitForVisibilityOfElement(fluxobs.drpdwn_ChangValFilterCondition(), "My Office-Projected Card", true);
			selectByVisibleText(fluxobs.drpdwn_ChangValFilterCondition(), rpoOfc, "My Office-Projected Card", true);
			click(fluxobs.drpdwn_SaveFilterCondition(), "My Office-Projected Card", "My Office-Projected Card", true);
			click(fluxobs.drpdwn_SaveFilter(), "My Office-Projected Card", "My Office-Projected Card", true);
			Thread.sleep(5000);
			
			
			typeClear(fluxobs.searchTextInCard("my office - probable"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("my office - probable",OrgName), "Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("my office - probable",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", true);
			Thread.sleep(3000);  
			click(fluxobs.Comp_wfbtn("my office - probable","Edit"), "Click on Edit", "Click on Edit", true);
			mouseover(fluxobs.plusSign("Add a Funding Source"), "Add Funding Source", false);
			//waitForElementToBeClickable(fluxobs.plusSign("Add a Funding Source"), "Add Planning", true);
			//waitForElementPresent(fluxobs.plusSign("Add a Funding Source"), "Add Planning", true);
			//waitForVisibilityOfElement(fluxobs.plusSign("Add a Funding Source"), "Add Planning", true);
			Thread.sleep(10000);
			
			click(fluxobs.plusSign("Add a Funding Source"), "Add Planning", "Add Planning", true);
			
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txtbox("request_funding_source_funding_amount"), "wait for modal", false);
			
			typeAndEnter(fluxobs.txtbox("request_funding_source_funding_amount"), fundingAmt, "Funding Amount","Funding Amount", true);
			Thread.sleep(3000);
			selectByVisibleText(fluxobs.drpdown_field("request_funding_source_spending_year_input"), fiscalYr, "Funding Source Year", true);
			Thread.sleep(3000);
			selectByVisibleText(fluxobs.drpdown_field("request_funding_source_program_id_input"), fundingSrc, "Funding Source", true);
			Thread.sleep(3000);
			selectByVisibleText(fluxobs.drpdown_field("request_funding_source_sub_program_id_input"), low, "LOW", true);
			//	selectByVisibleText(fluxobs.drpdown_field("request_funding_source_funding_source_allocation_input"), "Regular", "Select Budget type");
			Thread.sleep(4000);
			selectByPartialText(fluxobs.drpdown_field("request_funding_source_funding_source_allocation_input"), budType+":",false, "Budget type",true);
			click(fluxobs.btn_witha("Create Request Budget Types"), "add type of support", "add type of support", true);

			Thread.sleep(4000);
			click(fluxobs.Comp_Save("my office - probable"), "Click on Save", "Click on Save", true);
			Thread.sleep(10000);

			JSClick(fluxobs.btn_WorkFlowcGAL("my office - probable"), "Select WorkFlow", true);
			
			if(!reqType.toLowerCase().equals("build"))
			{
				waitForVisibilityOfElement(fluxobs.Comp_wfbtn("my office - probable","Projected"), "Projected", true);
				click(fluxobs.Comp_wfbtn("my office - probable","Projected"), "select Projected","Projected", true);
				waitForVisibilityOfElement(fluxobs.btn_witha("OK"), "OK", true);
				click(fluxobs.btn_witha("OK"), "select OK","", true);
				waitForVisibilityOfElement(fluxobs.btn_witha("Projected"), "Projected", true);
				status = VerifyStatus("my office - probable", "Projected");
			}
			else if(reqType.toLowerCase().equals("build"))
			{
				waitForVisibilityOfElement(fluxobs.Comp_wfbtn("my office - probable","Submit"), "Submit", true);
				click(fluxobs.Comp_wfbtn("my office - probable","Submit"), "select Submit","", true);
				waitForVisibilityOfElement(fluxobs.btn_witha("OK"), "OK", true);
				click(fluxobs.btn_witha("OK"), "select OK","", true);
				waitForVisibilityOfElement(fluxobs.btn_witha("Build Director Projection Review"), "Build Director Projection Review", true);
				status = VerifyStatus("my office - probable", "Build Director Projection Review");
			}
			//	Thread.sleep(2000);
			
			logout_from_fluxx("Fluxx with Program Director Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+status+"</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in ProbableStage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Probable)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}

	}
	
	
	/**
	 * Build Director Projection Review Stage
	 * Required only for build requests     
	 * Returns true if this is the final stage else returns false
	 */
	synchronized public boolean BldDirectorPrjStage () throws Throwable {
		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;			
			String reqType=treemap.get("RequestType").toString().trim();			
			String status = "";
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
	
			String bldDircLogin = treemap.get("Login_NA_NA_Build Director_Login").toString().trim();
			String bldDircPwd = treemap.get("Login_NA_NA_Build Director_Login").toString().trim();
			
			boolean lockFlag=false;
			launchFluxxUrl(bldDircLogin , bldDircPwd, "Build Director");
			selectDashboard("BUILD - Director"); 
			
			mouseover(fluxobs.searchTextInCard("Projection Review"), "mouseover on Projection Review",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("Projection Review"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("Projection Review"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("Projection Review"),"Organization Name", true);
			Thread.sleep(10000);
			
			typeClear(fluxobs.searchTextInCard("Projection Review"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("Projection Review",OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("Projection Review",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Build Director Projection Review"), "Projection Review", true);
			JSClick(fluxobs.btn_WorkFlowcGAL("Projection Review"), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("Projection Review","Confirmed"), "Information Review", true);
			
			click(fluxobs.Comp_wfbtn("Projection Review","Confirmed"), "Select Confirmed from the workflow button","", true);	
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);
						
			click(fluxobs.btn_witha("OK"), "Select OK on Workflow Action","Select OK on Workflow Action", true);
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Projected"), "Projected", true);
			
			
			status = VerifyStatus("Projection Review", "Projected");
			logout_from_fluxx("Fluxx with Build Director Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+status+"</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
			
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Build Director Projection Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Build Director Projection)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}

	}
	
	
	
	
	/**
	 * Projected Stage
	 * Grants manager approves and sends the request to grantee  
	 * Returns true if this is the final stage else returns false
	 */

	synchronized public boolean ProjectedStage () throws Throwable {
		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;			
				
			String status = "";
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
	
			String gm_Login = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String gm_pwd = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			
			boolean lockFlag=false;
			launchFluxxUrl(gm_Login , gm_pwd, "Grants Manager");
			selectDashboard("GM Planning"); 
			
			mouseover(fluxobs.searchTextInCard("projected"), "mouseover on projected",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("projected"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("projected"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("projected"),"Organization Name", true);
			Thread.sleep(10000);
			
			typeClear(fluxobs.searchTextInCard("projected"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("projected",OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("projected",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Projected"), "Projection Review", true);
			JSClick(fluxobs.btn_WorkFlowcGAL("projected"), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("projected","Invite"), "Invite", true);
			
			click(fluxobs.Comp_wfbtn("projected","Invite"), "Select Invite from the workflow button","", true);	
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);
						
			click(fluxobs.btn_witha("OK"), "Select OK on Workflow Action","Select OK on Workflow Action", true);
			
			waitForVisibilityOfElement(fluxobs.btn_witha("Invited"), "Invited", true);
			
			
			status = VerifyStatus("projected", "Invited");
			logout_from_fluxx("Fluxx with Grants Manager Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+status+"</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
			
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Projected Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Projected)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}

	}
	
	

	
	
	/**
	 * Method Name: InviteStage
	 * This method is used fill in and submit the req by Grantee
	 * @throws Throwable
	 */
	synchronized public boolean InviteStage() throws Throwable {
		try 
		{
			String grantee_Loginname = treemap.get("Login_NA_NA_Grantee Moderator_Login").toString().trim()+timeNow;
			String  password = treemap.get("Login_NA_NA_Grantee Moderator_Login").toString().trim()+timeNow;
			String  grantee_firstname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_First Name*").toString().trim();
			String  grantee_lastname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Last Name*").toString().trim()+timeNow;
			String  grantee_mailId = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Email*").toString().trim();
			grantee_mailId = grantee_mailId.split("[@]")[0] + timeNow + grantee_mailId.split("[@]")[1];
			String  OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			String confirmStmt = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_I confirm the above information is correct").toString().trim();
			String overseas = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_GRANTEE INFORMATION_NA_*Please check here if your organization is incorporated or has a physical presence in China, Egypt, or Indonesia").toString().trim();
			
			String granteeOpBug = treemap.get("Grantee Portal_ORGANIZATION FINANCIALS_Operating Budget_Grantee Operating Budget (Current Year)").toString().trim();
			//String excgRate = treemap.get("Grantee Portal_ORGANIZATION FINANCIALS_Operating Budget_Exchange rate at time of submission").toString().trim();
			String excgRate = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_ORGANIZATION FINANCIALS_Operating Budget_Exchange rate").toString().trim();
			String revScoresFunder = treemap.get("Grantee Portal_ORGANIZATION FINANCIALS_Operating Budget_*Provide a list of your top revenue sources, including name and amount. Funder").toString().trim();
			String revScoresAmt = treemap.get("Grantee Portal_ORGANIZATION FINANCIALS_Operating Budget_*Provide a list of your top revenue sources, including name and amount. Amount").toString().trim();
			String DEIEfforts = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_Diversity_NA_*DEI EFFORTS").toString().trim();
			String DEIGoals = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_Diversity_NA_*DEI GOALS").toString().trim();
			String NoOfBoardMembers = treemap.get("Grantee Portal_Board of Directors_NA_*Total Number of Board Members").toString().trim();
			String noOfWomen_board = treemap.get("Grantee Portal_Board of Directors_NA_*Women").toString().trim();
			String multiRacial_board = treemap.get("Grantee Portal_Board of Directors_NA_Multiracial or Multi Ethnic").toString().trim();
			String arabOrAmec_board = treemap.get("Grantee Portal_Board of Directors_NA_Arab or Arab American").toString().trim();
			String asianOrAmec_board = treemap.get("Grantee Portal_Board of Directors_NA_Asian or Asian American").toString().trim();
			String blackOrAmec_board = treemap.get("Grantee Portal_Board of Directors_NA_Black or African American").toString().trim();
			String latinAmec_board = treemap.get("Grantee Portal_Board of Directors_NA_Latin American").toString().trim();
			String nativeAmec_board = treemap.get("Grantee Portal_Board of Directors_NA_Native American, American Indian, or Alaska Native").toString().trim();
			String nativeHawaiian_board = treemap.get("Grantee Portal_Board of Directors_NA_Native Hawaiian or Other Pacific Islander").toString().trim();
			String white_board = treemap.get("Grantee Portal_Board of Directors_NA_White").toString().trim();
			String other_board = treemap.get("Grantee Portal_Board of Directors_NA_Other").toString().trim();
			String unknown_board = treemap.get("Grantee Portal_Board of Directors_NA_Unknown or decline to state").toString().trim();
			
			String NoOfExecMembers = treemap.get("Grantee Portal_Executive Leadership_NA_*Total Number of Leadership Members").toString().trim();
			String noOfWomen_exec = treemap.get("Grantee Portal_Executive Leadership_NA_*Women").toString().trim();
			String multiRacial_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Multiracial or Multi Ethnic").toString().trim();
			String arabOrAmec_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Arab or Arab American").toString().trim();
			String asianOrAmec_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Asian or Asian American").toString().trim();
			String blackOrAmec_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Black or African American").toString().trim();
			String latinAmec_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Latin American").toString().trim();
			String nativeAmec_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Native American, American Indian, or Alaska Native").toString().trim();
			String nativeHawaiian_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Native Hawaiian or Other Pacific Islander").toString().trim();
			String white_exec = treemap.get("Grantee Portal_Executive Leadership_NA_White").toString().trim();
			String other_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Other").toString().trim();
			String unknown_exec = treemap.get("Grantee Portal_Executive Leadership_NA_Unknown or decline to state").toString().trim();
			
			String coverLtr = treemap.get("Grantee Portal_Proposal Documents_NA_Cover Letter").toString().trim();
			String orgBudget = treemap.get("Grantee Portal_Proposal Documents_NA_Organizational Budget").toString().trim();
			String auditedFin = treemap.get("Grantee Portal_Proposal Documents_NA_CVs/Bios for Project Personnel").toString().trim();
			String proposedPrjBudget = treemap.get("Grantee Portal_Proposal Documents_NA_Endorsement Letter").toString().trim();
			
			String granteeType = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Grantee Type").toString().trim();
			String suppType = treemap.get("Request Card_Support Type_NA_Support Type").toString().trim();
			String waveAuditedFin = treemap.get("Request Card_Request  Special Category_NA_Wave Audited Financials?").toString().trim();
			String country = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Country").toString().trim();
			
			launchFluxxUrl(grantee_Loginname , password,"Grantee");
			waitForElementPresent(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false);
			waitForVisibilityOfElement(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false);
			Thread.sleep(5000);
			mouseover(fluxobs.btn_Pending(), "Pending Button", true);

			click(fluxobs.btn_Pending(), "Click on New/Pending", "Click on New/Pending", true);		
			waitForElementPresent(fluxobs.txt_withH2contains(OrgName), "Wait for Organization Name", true);
			click(fluxobs.txt_withH2contains(OrgName), "Click on Org Name", "ClickonOrgName", true);
			waitForElementPresent(fluxobs.btn_edit(), "wait for edit button", true);
			click(fluxobs.btn_edit(),"Edit the grantee details", "open the grantee filling form", true);
			waitForVisibilityOfElement(fluxobs.btn_save(), "Edit the grantee details", true);
			Thread.sleep(3000);
			
			mouseover(fluxobs.drpdown_field("grant_request_org_info_confirm_yn"), "Mouse Hover", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_org_info_confirm_yn"), confirmStmt, "Confirm the above information is correct", true);
			Thread.sleep(5000);
			if(granteeType.toLowerCase().equals("individual"))
			{
				selectByVisibleText(fluxobs.drpdown_field("grant_request_organization_overseas_office"), overseas, "Select Yes/No if Overseas Organization",true);
			}
			if(granteeType.toLowerCase().equals("organization"))
			{
				
				
				//click(fluxobs.txt_Fieldsgrantee("ORGANIZATION FINANCIALS"),"Open the ORGANIZATION FINANCIALS details", "open the ORGANIZATION FINANCIALS details", false);
				//click(fluxobs.txt_Fieldsgrantee("Operating Budget"),"Open the Operating Budget details", "open the Operating Budget details", false);
				//Thread.sleep(5000);
				mouseover(fluxobs.txtbox_granteeDetails("*Grantee Operating Budget (Current Year)"), "Mouse Hover", true);
				type_input(fluxobs.txtbox_granteeDetails("*Grantee Operating Budget (Current Year)"), granteeOpBug, "Grantee Operating Budget","Grantee Operating Budget", true);
				if(!country.toLowerCase().equals("united states"))
					type_input(fluxobs.txtbox_granteeDetails("Exchange Rate at time of submission"), excgRate, "Exchange Rate","Exchange Rate", true);
				type_input(fluxobs.txtbox_Funder("Operating Budget"), revScoresFunder, "Funder Name","Funder Name", true);
				type_input(fluxobs.txtbox_Amount("Operating Budget"), revScoresAmt, "Funding Amount","Funding Amount", true);
				
				mouseover(fluxobs.text_Area("grant_request_diversity_efforts"), "Mouse Hover", true);
				type_input(fluxobs.text_Area("grant_request_diversity_efforts"), DEIEfforts, "DEI Efforts", "DEI Efforts", true);
				mouseover(fluxobs.text_Area("grant_request_diversity_goals"), "Mouse Hover", true);
				type_input(fluxobs.text_Area("grant_request_diversity_goals"), DEIGoals, "DEI Goals", "DEI Goals", true);
				
				mouseover(fluxobs.txt_Fieldsgrantee("Board of Directors"), "Mouse Hover", true);
				//click(fluxobs.txt_Fieldsgrantee("Board of Directors"),"Open the Board of Directors details", "open the Board of Directors details", false);
				//mouseover(fluxobs.txtbox("grant_request_diversity_board_women"), "Mouse Hover", true);
				type_input(fluxobs.txtbox("grant_request_diversity_board_total"), NoOfBoardMembers, "Total Number of Board Members","Enter Total Number of Board Members", true);
				type_input(fluxobs.txtbox("grant_request_diversity_board_women"), noOfWomen_board, "Total Number of Board Members Women","Enter Total Number of Board Members Women", true);

				if(country.toLowerCase().equals("united states"))
				{
					mouseover(fluxobs.txtbox("grant_request_diversity_board_race_nat_am"), "Mouse Hover", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_multi"), multiRacial_board, "Multiracial or Multi Ethnic","Multiracial or Multi Ethnic", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_arab"), arabOrAmec_board, "Arab or Arab American","Arab or Arab American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_asian"), asianOrAmec_board, "Asian or Asian American","Asian or Asian American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_black"), blackOrAmec_board, "Black or African American","Black or African American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_latin"), latinAmec_board, "Latin American","Latin American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_nat_am"), nativeAmec_board, "Native American, American Indian, or Alaska Native","Native American, American Indian, or Alaska Native", true);
					
					mouseover(fluxobs.txtbox("grant_request_diversity_board_race_unknown"), "Mouse Hover", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_pac_is"), nativeHawaiian_board, "Native Hawaiian or Other Pacific Islander","Native Hawaiian or Other Pacific Islander", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_white"), white_board, "White Members","White Members", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_other"), other_board, "Other Members","Other Members", true);
					type_input(fluxobs.txtbox("grant_request_diversity_board_race_unknown"), unknown_board, "Unknown or decline to state","Unknown or decline to state", true);

				}
				
				mouseover(fluxobs.txt_Fieldsgrantee("Executive Leadership"), "Mouse Hover", true);
				//click(fluxobs.txt_Fieldsgrantee("Executive Leadership"),"Open the Executive Leadership details", "open the Executive Leadership details", false);
				mouseover(fluxobs.txtbox("grant_request_diversity_exec_women"), "Mouse Hover", true);
				type_input(fluxobs.txtbox("grant_request_diversity_exec_total"), NoOfExecMembers, "Total Number of Leadership Members","Enter Total Number of Leadership Members", true);
				type_input(fluxobs.txtbox("grant_request_diversity_exec_women"), noOfWomen_exec, "Total Number of Leadership Members Women","Enter Total Number of Leadership Members Women", true);
				
				if(country.toLowerCase().equals("united states"))
				{
					mouseover(fluxobs.txtbox("grant_request_diversity_exec_race_nat_am"), "Mouse Hover", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_multi"), multiRacial_exec, "Multiracial or Multi Ethnic","Multiracial or Multi Ethnic", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_arab"), arabOrAmec_exec, "Arab or Arab American","Arab or Arab American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_asian"), asianOrAmec_exec, "Asian or Asian American","Asian or Asian American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_black"), blackOrAmec_exec, "Black or African American","Black or African American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_latin"), latinAmec_exec, "Latin American","Latin American", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_nat_am"), nativeAmec_exec, "Native American, American Indian, or Alaska Native","Native American, American Indian, or Alaska Native", true);
					
					mouseover(fluxobs.txtbox("grant_request_diversity_exec_race_unknown"), "Mouse Hover", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_pac_is"), nativeHawaiian_exec, "Native Hawaiian or Other Pacific Islander","Native Hawaiian or Other Pacific Islander", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_white"), white_exec, "White Members","White Members", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_other"), other_exec, "Other Members","Other Members", true);
					type_input(fluxobs.txtbox("grant_request_diversity_exec_race_unknown"), unknown_exec, "Unknown or decline to state","Unknown or decline to state", true);
				}
				
			}
			
			//Upload Cover Letter
			mouseover(fluxobs.audit_doucments2("Cover Letter"), "Mouse Hover", true);
			JSClick(fluxobs.audit_doucments2("Cover Letter"), "Click add symbol to add documents", true);
			driver.findElement(By.xpath("//div[@id='simplemodal-data']//div[@class='plupload html5']//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload Cover Letter", true);
			waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload Cover Letter", false);
			waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload Cover Letter", false);
			Thread.sleep(3000);
			click(fluxobs.btn_witha("Start upload"), "Upload Cover Letter", "Upload the Cover Letter", true);
			Thread.sleep(5000);
			click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);

			if(granteeType.toLowerCase().equals("organization"))
			{
				//Upload Organizational Budget
				mouseover(fluxobs.audit_doucments2("Organizational Budget"), "Mouse Hover", true);
				JSClick(fluxobs.audit_doucments2("Organizational Budget"), "Click add symbol to add documents", true);
				driver.findElement(By.xpath("//div[@id='simplemodal-data']//div[@class='plupload html5']//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
				Thread.sleep(2000);
				waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload Organizational Budget", true);
				waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload Organizational Budget", false);
				waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload Organizational Budget", false);
				Thread.sleep(3000);
				click(fluxobs.btn_witha("Start upload"), "Upload Organizational Budget", "Upload the Organizational Budget", true);
				Thread.sleep(5000);
				click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);
			}
			
			if(granteeType.toLowerCase().equals("individual"))
			{
				//Upload CVs/Bios for Project Personnel
				mouseover(fluxobs.audit_doucments2("CVs/Bios for Project Personnel"), "Mouse Hover", true);
				JSClick(fluxobs.audit_doucments2("CVs/Bios for Project Personnel"), "Click add symbol to add documents", true);
				driver.findElement(By.xpath("//div[@id='simplemodal-data']//div[@class='plupload html5']//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
				Thread.sleep(2000);
				waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload CVs/Bios for Project Personnel", true);
				waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload CVs/Bios for Project Personnel", false);
				waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload CVs/Bios for Project Personnel", false);
				Thread.sleep(3000);
				click(fluxobs.btn_witha("Start upload"), "Upload CVs/Bios for Project Personnel", "Upload the CVs/Bios for Project Personnel", true);
				Thread.sleep(5000);
				click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);
			}
			
			if(granteeType.toLowerCase().equals("individual"))
			{
				//Upload Endorsement Letter
				mouseover(fluxobs.audit_doucments2("Endorsement Letter"), "Mouse Hover", true);
				JSClick(fluxobs.audit_doucments2("Endorsement Letter"), "Click add symbol to add documents", true);
				driver.findElement(By.xpath("//div[@id='simplemodal-data']//div[@class='plupload html5']//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
				Thread.sleep(2000);
				waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload Endorsement Letter", true);
				waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload Endorsement Letter", false);
				waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload Endorsement Letter", false);
				Thread.sleep(3000);
				click(fluxobs.btn_witha("Start upload"), "Upload Endorsement Letter", "Upload the Endorsement Letter", true);
				Thread.sleep(5000);
				click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);
			}
			
		
			if(waveAuditedFin.toLowerCase().equals("no"))
			{
				//Upload Audited Financials
				mouseover(fluxobs.audit_doucments2("Audited Financials"), "Mouse Hover", true);
				JSClick(fluxobs.audit_doucments2("Audited Financials"), "Click add symbol to add documents", true);
				driver.findElement(By.xpath("//div[@id='simplemodal-data']//div[@class='plupload html5']//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
				Thread.sleep(2000);
				waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload Audited Financials", true);
				waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload Audited Financials", false);
				waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload Audited Financials", false);
				Thread.sleep(3000);
				click(fluxobs.btn_witha("Start upload"), "Upload Audited Financials", "Upload the Audited Financials", true);
				Thread.sleep(5000);
				click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);
			}
			
			if(suppType.toLowerCase().contains("project"))
			{
				//Upload Proposed Project Budget
				mouseover(fluxobs.audit_doucments2("Proposed Project Budget"), "Mouse Hover", true);
				JSClick(fluxobs.audit_doucments2("Proposed Project Budget"), "Click add symbol to add documents", true);
				driver.findElement(By.xpath("//div[@id='simplemodal-data']//div[@class='plupload html5']//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
				Thread.sleep(2000);
				waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload Proposed Project Budget", true);
				waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload Proposed Project Budget", false);
				waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload Proposed Project Budget", false);
				Thread.sleep(3000);
				click(fluxobs.btn_witha("Start upload"), "Upload Proposed Project Budget", "Upload the Proposed Project Budget", true);
				Thread.sleep(5000);
				click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);
			}
			
			if(exists(fluxobs.close_btn()))
			{
				click(fluxobs.close_btn(), "Cancel the edited details","Cancel the edited details", true);
				Thread.sleep(5000);
			}
			click(fluxobs.btn_ui("Save"), "Click Save","Click Save", true);
			waitForElementPresent(fluxobs.btn_edit(), "wait for edit button", true);
			click(fluxobs.btn_submit(), "Submit the grantee deatails","submit the grantee details", true);
			type_input(fluxobs.txtarea_comment(), "I have entered my credentials","comments", "Few comments in comment box", true);
			click(fluxobs.btn_Ok(), "close the Comment box","close the comment box", true);

			logout_fluxgranteeExtportal();
			String status = "Program Review";
			SuccessReport("Fill in all the required details in Grantee Portal via Grantee moderator", "Successfully filled in all the required details in Grantee Portal");
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>Program Review</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
			

		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Invited Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Invited)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}
		

	}

	
	
	/**
	 * Program Review Stage 
	 * Program Officer has to login and fill in the relevant details
	 * @throws Throwable
	 */
	synchronized public boolean ProgramreviewStage_PO () throws Throwable {
		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String poLogin = GetPOLogin();
			String poPwd = GetPOLogin();
			String commentsToGrantee = treemap.get("Request Card_Comments to Grantee__Application Comments").toString().trim();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			String prLow = treemap.get("Request Card_GRANT CODING (PO)_WHY (Grant Strategy)_Program/LOW/Outcome").toString().trim();
			String targetApp = treemap.get("Request Card_GRANT CODING (PO)_HOW (Grantee Approach)_Target Approach").toString().trim();
			String gender = treemap.get("Request Card_GRANT CODING (PO)_WHO (Target Demographics)_Gender").toString().trim();
			String race = treemap.get("Request Card_GRANT CODING (PO)_WHO (Target Demographics)_Race/Ethnicity").toString().trim();
			String other = treemap.get("Request Card_GRANT CODING (PO)_WHO (Target Demographics)_Who (Other)").toString().trim();
			String geoArea = treemap.get("Request Card_GRANT CODING (PO)_WHERE (Target Locations)_Geographic Area").toString().trim();
			String targetSub = treemap.get("Request Card_GRANT CODING (PO)_WHAT (Grant Subject)_Target Subject").toString().trim();
			
			String customCode = treemap.get("Request Card_GRANT CODING (PO)__Custom Code").toString().trim();
			
			String poHasApp = treemap.get("Request Card_STRATEGIC ALIGNMENT (PO)_NA_PO has approved this proposal").toString().trim();
			String buildPOReviewed = treemap.get("Request Card_STRATEGIC ALIGNMENT (PO)_NA_BUILD PO Reviewed").toString().trim();
			String st_recomm = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Strategy_Why are you recommending this organization for funding?").toString().trim();
			String st_cofunded = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Strategy_(Optional) If this grant is co-funded by another LOW or Program, describe the collaboration").toString().trim();
			String org_health_strngh = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Organization Health_What are the strengths and areas of improvement for this organization?").toString().trim();
			String fin_health_assess = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Financial Health_*What is your assessment of the organization's financial health and capacity").toString().trim();
			String div_assess_commit = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Diversity Assessment_How strong are the organization").toString().trim();
			String add_followUp_challng = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Additional Follow Up_*What are the potential challenges associated with this grant?").toString().trim();
			String add_followUp_obs = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_Additional Follow Up_Based on your observations on organization health, financial health, commitment to DEI, and any additional observations you may").toString().trim();
			String pgi_writeUp_assess = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_PGI Write-Up_Program Officer").toString().trim();
			String pgi_writeUp_confirm = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_PGI Write-Up_Please check the following to confirm, based on your review of prior grants. If a new grantee, please also check box below").toString().trim();
			String pgi_writeUp_mgmt = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_PGI Write-Up_*Management of Organization").toString().trim();
			String pgi_writeUp_conclusion = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_PGI Write-Up_*Conclusion").toString().trim();
			String pgi_writeUp_pgiApprv = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_PGI Write-Up_PGI Approved by PO").toString().trim();
			String appSumm_Summary = ReadingExcel.getValueFromPartialKey(treemap,"Request Card_STRATEGIC ALIGNMENT (PO)_APPROVAL SUMMARY_APPROVAL SUMMARY").toString().trim();
			
			
			String reqType=treemap.get("RequestType").toString().trim();
			String erFlag = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_ER Flag").toString().trim();
			
			//For multiple Planning LOWs
			String[] prLows=new String[1];
			if(prLow.contains("/"))
				prLows = prLow.split("/");
			else
				prLows[0] = prLow;
			
			
			//For multiple target approaches
			String[] targetApps=new String[1];
			if(targetApp.contains("/"))
				targetApps = targetApp.split("/");
			else
				targetApps[0] = targetApp;
			
			//For multiple genders
			String[] genders=new String[1];
			if(gender.contains("/"))
				genders = gender.split("/");
			else
				genders[0] = gender;
			
			//For multiple races
			String[] races=new String[1];
			if(race.contains("/"))
				races = race.split("/");
			else
				races[0] = race;
			
			//For multiple Who(Other)
			String[] others=new String[1];
			if(other.contains("/"))
				others = other.split("/");
			else
				others[0] = other;
			
			//For multiple Geo Areas
			String[] geoAreas=new String[1];
			if(geoArea.contains("/"))
				geoAreas = geoArea.split("/");
			else
				geoAreas[0] = geoArea;
			
			//For multiple target Subject
			String[] targetSubs=new String[1];
			if(targetSub.contains("/"))
				targetSubs = targetSub.split("/");
			else
				targetSubs[0] = targetSub;
			
			
			
			
			launchFluxxUrl(poLogin , poPwd, "Program Officer");
			selectDashboard("PO Grant Approval");
			Thread.sleep(2000);
			
			
			mouseover(fluxobs.searchTextInCard("proposal review"), "mouseover on proposal review",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("proposal review"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("proposal review"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("proposal review"),"Organization Name", true);
			Thread.sleep(10000);
			
			typeClear(fluxobs.searchTextInCard("proposal review"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("proposal review",OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("proposal review",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			waitForVisibilityOfElement(fluxobs.btn_status("proposal review","Program Review"), "Program Review", true);

			click(fluxobs.Comp_wfbtn("proposal review","Edit"), "click on Edit", "Click on Edit", true);
			Thread.sleep(4000);

			
			mouseover(fluxobs.Comp_grantFields("proposal review","GRANT CODING (PO)"), "GRANT CODING (PO) section", true);
			click(fluxobs.Comp_grantFields("proposal review","GRANT CODING (PO)"), "Open the GRANT CODING (PO) section","opened the GRANT CODING (PO) section", true);
			
			
			//Add New Program/LOW/Outcome
			click(fluxobs.btn_AddNew_Grant("proposal review","Program/LOW/Outcome"), "New Program/LOW/Outcome", "New Program/LOW/Outcome",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Program/LOW/Outcome"), "wait for Add Program/LOW/Outcome", true);

			for (String low : prLows) {
				low = low.trim();
				String par_low=null;
				String ch_low=null;
				if(low.contains("~"))
				{
					par_low = low.split("[~]")[0].trim();
					ch_low = low.split("[~]")[1].trim();
					if(par_low.contains("'"))
						par_low = par_low.split("'")[0];
					if(ch_low.contains("'"))
						ch_low = ch_low.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_low, "Parent LOW '"+par_low+"'", "Parent LOW",true);
					click(fluxobs.txt_withspanContains_sibling(par_low), "Select LOW '"+par_low+"'", "Select LOW", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_low), "Select LOW '"+ch_low+"'", "Select LOW", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(low.contains("'"))
						low = low.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(low), "mouseover on low",false);
					doubleclick(fluxobs.txt_spanwithcontains(low), "Select LOW '"+low+"'", "Select LOW", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Program/LOW/Outcome"), "Add Program/LOW/Outcome", "Add Program/LOW/Outcome", true);
			Thread.sleep(3000);
			
			
			
			
			
			
			//Add New Target Approach
			click(fluxobs.btn_AddNew_Grant("proposal review","Target Approach"), "New Target Approach", "New Target Approach",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Target Approach"), "wait for Add Target Approach", true);

			for (String target_App : targetApps) {
				target_App = target_App.trim();
				String par_target_App=null;
				String ch_target_App=null;
				if(target_App.contains("~"))
				{
					par_target_App = target_App.split("[~]")[0].trim();
					ch_target_App = target_App.split("[~]")[1].trim();
					
					if(par_target_App.contains("'"))
						par_target_App = par_target_App.split("'")[0];
					if(ch_target_App.contains("'"))
						ch_target_App = ch_target_App.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_target_App, "Parent target App '"+par_target_App+"'", "Parent target App",true);
					click(fluxobs.txt_withspanContains_sibling(par_target_App), "Select target App '"+par_target_App+"'", "Select target App", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_target_App), "Select target App '"+ch_target_App+"'", "Select target App", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(target_App.contains("'"))
						target_App = target_App.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(target_App), "mouseover on target App",false);
					doubleclick(fluxobs.txt_spanwithcontains(target_App), "Select target App '"+target_App+"'", "Select target App", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Target Approach"), "Add Target Approach", "Add Target Approach", true);
			Thread.sleep(3000);
			
			
			
			//Add New Gender
			click(fluxobs.btn_AddNew_Grant("proposal review","Gender"), "New Gender", "New Gender",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Gender"), "wait for Add Gender", true);

			for (String gen : genders) {
				gen = gen.trim();
				String par_gen=null;
				String ch_gen=null;
				if(gen.contains("~"))
				{
					par_gen = gen.split("[~]")[0].trim();
					ch_gen = gen.split("[~]")[1].trim();
					if(par_gen.contains("'"))
						par_gen = par_gen.split("'")[0];
					if(ch_gen.contains("'"))
						ch_gen = ch_gen.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_gen, "Parent Gender '"+par_gen+"'", "Parent Gender",true);
					click(fluxobs.txt_withspanContains_sibling(par_gen), "Select Gender '"+par_gen+"'", "Select Gender", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_gen), "Select Gender '"+ch_gen+"'", "Select Gender", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(gen.contains("'"))
						gen = gen.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(gen), "mouseover on Gender",false);
					doubleclick(fluxobs.txt_spanwithcontains(gen), "Select Gender '"+gen+"'", "Select Gender", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Gender"), "Add Gender", "Add Gender", true);
			Thread.sleep(3000);
			
			
			
			//Add Race/Ethnicity
			click(fluxobs.btn_AddNew_Grant("proposal review","Race/Ethnicity"), "New Race/Ethnicity", "New Race/Ethnicity",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Race/Ethnicity"), "wait for Add Race/Ethnicity", true);

			for (String race_ethn : races) {
				race_ethn = race_ethn.trim();
				String par_race_ethn=null;
				String ch_race_ethn=null;
				if(race_ethn.contains("~"))
				{
					par_race_ethn = race_ethn.split("[~]")[0].trim();
					ch_race_ethn = race_ethn.split("[~]")[1].trim();
					
					if(par_race_ethn.contains("'"))
						par_race_ethn = par_race_ethn.split("'")[0];
					if(ch_race_ethn.contains("'"))
						ch_race_ethn = ch_race_ethn.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_race_ethn, "Parent race/ethnicity '"+par_race_ethn+"'", "Parent race/ethnicity",true);
					click(fluxobs.txt_withspanContains_sibling(par_race_ethn), "Select race/ethnicity '"+par_race_ethn+"'", "Select race/ethnicity", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_race_ethn), "Select race/ethnicity '"+ch_race_ethn+"'", "Select race/ethnicity", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(race_ethn.contains("'"))
						race_ethn = race_ethn.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(race_ethn), "mouseover on race/ethnicity",false);
					doubleclick(fluxobs.txt_spanwithcontains(race_ethn), "Select race/ethnicity '"+race_ethn+"'", "Select race/ethnicity", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Race/Ethnicity"), "Add Race/Ethnicity", "Add Race/Ethnicity", true);
			Thread.sleep(3000);
			
			
			
			//Add Who (Other)
			click(fluxobs.btn_AddNew_Grant("proposal review","Who (Other)"), "New Who (Other)", "New Who (Other)",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Who (Other)"), "wait for Add Who (Other)", true);

			for (String who_oth : others) {
				who_oth = who_oth.trim();
				String par_who_oth=null;
				String ch_who_oth=null;
				if(who_oth.contains("~"))
				{
					par_who_oth = who_oth.split("[~]")[0].trim();
					ch_who_oth = who_oth.split("[~]")[1].trim();
					
					if(par_who_oth.contains("'"))
						par_who_oth = par_who_oth.split("'")[0];
					if(ch_who_oth.contains("'"))
						ch_who_oth = ch_who_oth.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_who_oth, "Parent who(other) '"+par_who_oth+"'", "Parent who(other)",true);
					click(fluxobs.txt_withspanContains_sibling(par_who_oth), "Select who(other) '"+par_who_oth+"'", "Select who(other)", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_who_oth), "Select who(other) '"+ch_who_oth+"'", "Select who(other)", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(who_oth.contains("'"))
						who_oth = who_oth.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(who_oth), "mouseover on who(other)",false);
					doubleclick(fluxobs.txt_spanwithcontains(who_oth), "Select who(other) '"+who_oth+"'", "Select who(other)", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Who (Other)"), "Add Who (Other)", "Add Who (Other)", true);
			Thread.sleep(3000);
			
			
			
			//Add Geographic Area
			click(fluxobs.btn_AddNew_Grant1("proposal review","Geographic Area","2"), "New Geographic Area", "New Geographic Area",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Geographic Area"), "wait for Add Geographic Area", true);

			for (String geo_area : geoAreas) {
				geo_area = geo_area.trim();
				String par_geo_area=null;
				String ch_geo_area=null;
				if(geo_area.contains("~"))
				{
					par_geo_area = geo_area.split("[~]")[0].trim();
					ch_geo_area = geo_area.split("[~]")[1].trim();
					if(par_geo_area.contains("'"))
						par_geo_area = par_geo_area.split("'")[0];
					if(ch_geo_area.contains("'"))
						ch_geo_area = ch_geo_area.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_geo_area, "Parent geo area '"+par_geo_area+"'", "Parent geo area",true);
					click(fluxobs.txt_withspanContains_sibling(par_geo_area), "Select geo area '"+par_geo_area+"'", "Select geo area", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_geo_area), "Select geo area '"+ch_geo_area+"'", "Select geo area", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(geo_area.contains("'"))
						geo_area = geo_area.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(geo_area), "mouseover on geo area",false);
					doubleclick(fluxobs.txt_spanwithcontains(geo_area), "Select geo area '"+geo_area+"'", "Select geo area", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Geographic Focus"), "Add Geographic Focus", "Add Geographic Focus", true);
			Thread.sleep(3000);
			
			
			//Add Target Subject
			click(fluxobs.btn_AddNew_Grant("proposal review","Target Subject"), "New Target Subject", "New Target Subject",true);
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Add Target Subject"), "wait for Add Target Subject", true);

			for (String tar_sub : targetSubs) {
				tar_sub = tar_sub.trim();
				String par_tar_sub=null;
				String ch_tar_sub=null;
				if(tar_sub.contains("~"))
				{
					par_tar_sub = tar_sub.split("[~]")[0].trim();
					ch_tar_sub = tar_sub.split("[~]")[1].trim();
					if(par_tar_sub.contains("'"))
						par_tar_sub = par_tar_sub.split("'")[0];
					if(ch_tar_sub.contains("'"))
						ch_tar_sub = ch_tar_sub.split("'")[0];
					typeAndEnter(fluxobs.tree_search(), par_tar_sub, "Parent target subject '"+par_tar_sub+"'", "Parent target subject",true);
					click(fluxobs.txt_withspanContains_sibling(par_tar_sub), "Select target subject '"+par_tar_sub+"'", "Select target subject", true);
					Thread.sleep(2000);
					doubleclick(fluxobs.txt_spanwithcontains(ch_tar_sub), "Select target subject '"+ch_tar_sub+"'", "Select target subject", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
					driver.findElement(fluxobs.tree_search()).clear(); 
					driver.findElement(fluxobs.tree_search()).sendKeys(Keys.ENTER);
					
				}
				else
				{
					if(tar_sub.contains("'"))
						tar_sub = tar_sub.split("'")[0];
					mouseover(fluxobs.txt_spanwithcontains(tar_sub), "mouseover on target subject",false);
					doubleclick(fluxobs.txt_spanwithcontains(tar_sub), "Select target subject '"+tar_sub+"'", "Select target subject", true);
					//btn_click(fluxobs.btn_nexttree(), "Click next", "click next", true);
				}
			}
			Thread.sleep(2000);
			click(fluxobs.btn_witha("Add Target Subject"), "Add Target Subject", "Add Target Subject", true);
			Thread.sleep(3000);
			
			
			type_input(fluxobs.txtbox_granteeDetails("Custom Code"), customCode, "Custom Code", "Custom Code", true);
			
			
			mouseover(fluxobs.Comp_grantFields("proposal review","STRATEGIC ALIGNMENT (PO)"), "STRATEGIC ALIGNMENT (PO) section", false);
			click(fluxobs.Comp_grantFields("proposal review","STRATEGIC ALIGNMENT (PO)"), "Open the STRATEGIC ALIGNMENT (PO) section","opened the STRATEGIC ALIGNMENT (PO) section", true);
			
			selectByVisibleText(fluxobs.drpdown_field("grant_request_proposal_ready_po"), poHasApp, "PO has approved", true);
			if(reqType.toLowerCase().equals("build") && buildPOReviewed.toLowerCase().trim().equals("yes"))
				click(fluxobs.txtbox_xpath("grant_request_build_po_review","2"), "Build PO Reviewed","Build PO Reviewed", true);
				
			
			mouseover(fluxobs.Comp_grantFields("proposal review","Strategy"), "Strategy section", false);
			click(fluxobs.Comp_grantFields("proposal review","Strategy"), "Open the Strategy section","opened the Strategy section", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","Why are you recommending this organization for funding"), st_recomm, "Strategy Section Question1", "Strategy Section Question1", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","(Optional) If this grant is co-funded by another LOW or Program"), st_cofunded, "Strategy Section Question2", "Strategy Section Question2", true);
			
			
			mouseover(fluxobs.Comp_grantFields("proposal review","Organization Health"), "Organization Health section", false);
			click(fluxobs.Comp_grantFields("proposal review","Organization Health"), "Open the Organization Health section","opened the Organization Health section", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","What are the strengths and areas of improvement for this organization"), org_health_strngh, "Strategy Section Question3", "Strategy Section Question3", true);
			
			
			mouseover(fluxobs.Comp_grantFields("proposal review","Financial Health"), "Financial Health section", false);
			click(fluxobs.Comp_grantFields("proposal review","Financial Health"), "Open the Financial Health section","opened the Financial Health section", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","What is your assessment of the organization"), fin_health_assess, "Strategy Section Question4", "Strategy Section Question4", true);
			
			mouseover(fluxobs.Comp_grantFields("proposal review","Diversity Assessment"), "Diversity Assessment section", false);
			click(fluxobs.Comp_grantFields("proposal review","Diversity Assessment"), "Open the Diversity Assessment section","opened the Diversity Assessment section", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","How strong are the organization"), div_assess_commit, "Strategy Section Question5", "Strategy Section Question5", true);
			
			mouseover(fluxobs.Comp_grantFields("proposal review","Additional Follow Up"), "Additional Follow Up section", false);
			click(fluxobs.Comp_grantFields("proposal review","Additional Follow Up"), "Open the Additional Follow Up section","opened the Additional Follow Up section", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","What are the potential challenges associated with this grant"), add_followUp_challng, "Strategy Section Question6", "Strategy Section Question6", true);
			type_input(fluxobs.txtArea_DetailTxtArea("proposal review","Based on your observations on organization health, financial health, commitment to DEI, and any additional observations"), add_followUp_obs, "Strategy Section Question7", "Strategy Section Question7", true);
			
			if(erFlag.toLowerCase().equals("yes"))
			{
				mouseover(fluxobs.Comp_grantFields("proposal review","PGI Write-Up"), "PGI Write-Up section", false);
				click(fluxobs.Comp_grantFields("proposal review","PGI Write-Up"), "Open the PGI Write-Up section","opened the PGI Write-Up section", true);
				type_input(fluxobs.txtArea_DetailTxtArea("proposal review","Assessment of Experience of Grantee Related to Proposed Project"), pgi_writeUp_assess, "Strategy Section Question8", "Strategy Section Question8", true);
				
				if(pgi_writeUp_confirm.toLowerCase().trim().equals("yes"))
					click(fluxobs.txtbox_xpath("grant_request_pgi_prior_grants_yn","2"), "Strategy Section Question9","Strategy Section Question9", true);
				
				type_input(fluxobs.txtArea_DetailTxtArea("proposal review","Management of Organization"), pgi_writeUp_mgmt, "Strategy Section Question10", "Strategy Section Question10", true);
				type_input(fluxobs.txtArea_DetailTxtArea("proposal review","*Conclusion"), pgi_writeUp_conclusion, "Strategy Section Question11", "Strategy Section Question11", true);
				
				selectByVisibleText(fluxobs.drpdown_field("grant_request_pgi_approved_by_po"), pgi_writeUp_pgiApprv, "PGI Approved by PO", true);
			}	
			
			mouseover(fluxobs.Comp_grantFields("proposal review","Approval Summary"), "Approval Summary section", false);
			click(fluxobs.Comp_grantFields("proposal review","Approval Summary"), "Open the Approval Summary section","opened the Approval Summary section", true);
			click(fluxobs.btn_AddNew_Doc("proposal review","Approval Summary","2"), "Approval Summary", "Approval Summary",true);
			
			waitForVisibilityOfElement(fluxobs.txt_withspanContains("Generate Document"), "Wait for Generate Document", false);
			selectByVisibleText(fluxobs.drpdown_field("model_document_model_document_template_id_input"), "Approval Summary", "Approval Summary", true);
			click(fluxobs.Modal_Button("Generate Document","footer","Save"), "Save Approval Summary", "Save Approval Summary",true);
			waitForVisibilityOfElement(fluxobs.Doc_link("proposal review","Approval Summary","Approval Summary"), "Wait for Generate Document", false);
			
			
			if(erFlag.toLowerCase().equals("no"))
			{
				click(fluxobs.btn_AddNew_Doc("proposal review","Approval Summary","2"), "Approval Summary", "Approval Summary",true);
				waitForVisibilityOfElement(fluxobs.txt_withspanContains("Generate Document"), "Wait for Generate Document", false);
				selectByVisibleText(fluxobs.drpdown_field("model_document_model_document_template_id_input"), "Due Diligence", "Due Diligence", true);
				click(fluxobs.Modal_Button("Generate Document","footer","Save"), "Save Due Diligence", "Save Due Diligence",true);
				waitForVisibilityOfElement(fluxobs.Doc_link("proposal review","Approval Summary","Due Diligence"), "Wait for Generate Document", false);
			}
			
			
			
			if(erFlag.toLowerCase().equals("yes"))
			{
				click(fluxobs.btn_AddNew_Doc("proposal review","Approval Summary","2"), "Approval Summary", "Approval Summary",true);
				waitForVisibilityOfElement(fluxobs.txt_withspanContains("Generate Document"), "Wait for Generate Document", false);
				selectByVisibleText(fluxobs.drpdown_field("model_document_model_document_template_id_input"), "Pre Grant Inquiry", "Pre Grant Inquiry", true);
				click(fluxobs.Modal_Button("Generate Document","footer","Save"), "Save Pre Grant Inquiry", "Save Pre Grant Inquiry",true);
				waitForVisibilityOfElement(fluxobs.Doc_link("proposal review","Approval Summary","Pre Grant Inquiry"), "Wait for Generate Document", false);
			}
						
			click(fluxobs.Comp_Save("proposal review"), "Click on Save", "Click on Save", true);
			
			logout_from_fluxx("Fluxx with Program Officer Login"); 
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Program review Stage_PO");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Program review Stage(PO))", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}
		return true;

	}
	
	
	/**
	 * Method Name: ProgramreviewStage_GM
	 * This method is used fill in and submit the req by Grants Manager
	 * @throws Throwable
	 */
	synchronized public boolean ProgramreviewStage_GM () throws Throwable {
		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String gmLogin = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String gmpwd = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String payType = treemap.get("Request Card_PAYMENTS_NA_Payment Type").toString().trim();
			String grant_bud_entered = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Grant Budget has been entered").toString().trim();
			String grant_coding_spcd = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Grant Coding is specified").toString().trim();
			String geo_area_spcd = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Target Geographic Area(s) are specified").toString().trim();
			String pay_date_spcd = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Initial Payment Date must be specified").toString().trim();
			String pay_prj_spcd = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Payment projections are entered").toString().trim();
			String req_entered = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Reporting Requirements are entered").toString().trim();
			String gal_setup_comp = treemap.get("Request Card_GM FINAL CHECKLIST_NA_GAL Setup Complete").toString().trim();
			String pgiComp = treemap.get("Request Card_GM FINAL CHECKLIST_NA_PGI is complete").toString().trim();
			String dueDilignc_rpt_prd = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Due Diligence Report is produced for the current year").toString().trim();
			String docs_reviewed = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Organization record and documents have been reviewed").toString().trim();
			String supp_type_slct = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Support Type Percentages have been selected").toString().trim();
			String gen_app_summ = treemap.get("Request Card_GM FINAL CHECKLIST_NA_Generated Approval Summary").toString().trim();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			String erFlag = treemap.get("Organization Card_TAX AND FINANCIAL INFORMATION_NA_ER Flag").toString().trim();
			
			launchFluxxUrl(gmLogin , gmpwd, "Grants Manager");
			selectDashboard("GM Grant Approval"); 
			
			mouseover(fluxobs.searchTextInCard("prop PO reviewed"), "mouseover on proposal review",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("prop PO reviewed"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("prop PO reviewed"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("prop PO reviewed"),"Organization Name", true);
			Thread.sleep(10000);
			
			typeClear(fluxobs.searchTextInCard("prop PO reviewed"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("prop PO reviewed",OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("prop PO reviewed",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			waitForVisibilityOfElement(fluxobs.btn_status("prop PO reviewed","Program Review"), "Program Review", true);

			click(fluxobs.Comp_wfbtn("prop PO reviewed","Edit"), "click on Edit", "Click on Edit", true);
			Thread.sleep(4000);
			
			waitForVisibilityOfElement(fluxobs.Comp_Save("prop PO reviewed"), "Save", true);
			
			
			mouseover(fluxobs.Comp_grantFields("prop PO reviewed","PAYMENTS"), "PAYMENTS", true);
			click(fluxobs.Comp_grantFields("prop PO reviewed","PAYMENTS"), "Open the PAYMENTS details","opened the PAYMENTS details", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_payment_projection_type"), payType, "Select Pay Type", true);
			
			mouseover(fluxobs.Comp_grantFields("prop PO reviewed","GM FINAL CHECKLIST"), "GM FINAL CHECKLIST", true);
			click(fluxobs.Comp_grantFields("prop PO reviewed","GM FINAL CHECKLIST"), "Open the GM FINAL CHECKLIST details","opened the GM FINAL CHECKLIST details", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_budget"), grant_bud_entered, "Grant Budget Entered", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_target_pop"), grant_coding_spcd, "Grant Coding Specified", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_target_geo"), geo_area_spcd, "Geo Area Specified", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_initial_payment"), pay_date_spcd, "Pay Type Specified", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_payments"), pay_prj_spcd, "Payment Projections Specified", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_reports"), req_entered, "reporting Requirements are entered", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_gnl"), gal_setup_comp, "GAL setup complete", true);
			
			if(erFlag.toLowerCase().equals("yes"))
				selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_pgi"), pgiComp, "PGI Set up complete", true);
			if(erFlag.toLowerCase().equals("no"))
				selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_due_diligence"), dueDilignc_rpt_prd, "Due Diligence Report produced for current year", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_pgi"), pgiComp, "PGI Set up complete", true);//aaa
			
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_org"), docs_reviewed, "Documents  Reviewed", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_support"), supp_type_slct, "Support Type Percentages Selected", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_gm_check_summary"), gen_app_summ, "Generated Approval Summary", true);
			
           click(fluxobs.Comp_Save("prop PO reviewed"), "Click on Save", "Click on Save", true);
			Thread.sleep(9000);

			click(fluxobs.btn_WorkFlowcGAL("prop PO reviewed"), "Select WorkFlow", "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("prop PO reviewed","Submit"), "Submit", true);

			click(fluxobs.Comp_wfbtn("prop PO reviewed","Submit"), "Click on Submit","Click on Submit", true);
			Thread.sleep(2000);
			click(fluxobs.btn_witha("OK"), "select OK","Select OK", true);	
			waitForVisibilityOfElement(fluxobs.btn_witha("Compliance Review"), "Compliance Review", true);
			
			String status = VerifyStatus("prop PO reviewed", "Compliance Review").trim();
			
			logout_from_fluxx("Fluxx with Grants Manager Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+status+"</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Program review Stage(GM)");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Program Review)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
			
		}
		

	}
	



	
	/**
	 * Method Name: ComplianceReviewStage2
	 * This method is used fill in and submit the req by Grants Processing Unit
	 * @throws Throwable
	 */
	synchronized public void ComplianceReviewStage2 (String OrgName) throws Throwable {
		try {
			launchFluxxUrl("grants_processing2" , "grants_processing2,","");
			selectDashboard("Compliance Action,"); 
			System.out.println("Hey test it,");

			click(fluxobs.Comp_Organization(OrgName), "Click on Org Name", "ClickonOrgName,", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name,", true);
			click(fluxobs.Comp_Edit("req - COMPLIANce rev"), "click on Edit", " Click on Edit,", true);
			mouseover(fluxobs.Comp_grantFields("req - COMPLIANce rev","ORGANIZATION INFORMATION"), "STRATEGIC ALIGNMENT (PO),", true);
			Thread.sleep(2000);           
			mouseover(fluxobs.Comp_grantFields("req - COMPLIANce rev","AFFILIATIONS"), "STRATEGIC ALIGNMENT (PO),", true);

			Thread.sleep(3000);      
			click(fluxobs.Comp_grantFields("req - COMPLIANce rev","AFFILIATIONS"), "Open the AFFILIATIONS","opened the AFFILIATIONS,", true);

			selectByVisibleText(fluxobs.drpdown_field("grant_request_affiliated_trustee_yn"), "No", "Select Afflications,", true);
			selectByVisibleText(fluxobs.drpdown_field("grant_request_affiliated_staff_yn"), "No", "Select Final Checklist,", true);

			click(fluxobs.btn_ui("Save"), "Click on Save", "Click on Save,", true);
			Thread.sleep(12000);  

			click(fluxobs.btn_WorkFlowcGAL("req - COMPLIANce rev"), "Select WorkFlow", "Select WorkFlow,", true);
 
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("req - COMPLIANce rev","Submit"), "Submit,", true);


			click(fluxobs.Comp_wfbtn("req - COMPLIANce rev","Submit"), "select Submit",",", true);

			Thread.sleep(2000);
			click(fluxobs.btn_witha("OK"), "select OK","',", true);		
			waitForVisibilityOfElement(fluxobs.btn_witha("Director Review"), "Director Review,", true);
			logout_fluxwebsite();


		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in ComplianceReviewStage2");
			System.out.println("#####################");
		}


	}
	
	
	/**
	 * Method Name: GetDirectorLogin
	 * This method is used to get the director login to be used
	 * @throws Throwable
	 */
	synchronized public String GetDirectorLogin () throws Throwable 
	{
		try
		{
			String resPORole = treemap.get("Login_NA_NA_Responsible Program Officer Role").toString().trim();
			String approvalType=treemap.get("Request Card_REQUEST OVERVIEW_NA_Approval Type").toString().trim();
			String rpoOfc = treemap.get("Request Card_REQUEST OVERVIEW_NA_RPO Office").toString().trim();
			
			String dirAsRPO = treemap.get("Login_NA_NA_DirectorAsRPO_Login").toString().trim();
			String vpAsDir = treemap.get("Login_NA_NA_VPAsDir_Login").toString().trim();
			String vpAsRPO = treemap.get("Login_NA_NA_VPAsRPO_Login").toString().trim();
			String presAsDir = treemap.get("Login_NA_NA_PresidentAsDir_Login").toString().trim();
			
			//By Default the approver is "Program Director"
			String dirLogin=treemap.get("Login_NA_NA_Program Director_Login").toString().trim();
			
			String role="";
			String dashboard="";
			String card="";
			
			//If Responsible Program Officer is "Director As RPO" and Approval Type is "Presidential"
			if(resPORole.toLowerCase().equals("director as rpo") && (approvalType.equals("Presidential")))
			{
				dirLogin = dirAsRPO;
				role = "Director As RPO";
				dashboard = "Director Action";
				card = "DIR is RPO - PRESIDENTIAL";
			}
			
			//If Responsible Program Officer is "Director As RPO" and Approval Type is "Delegated"
			if(resPORole.toLowerCase().equals("director as rpo") && (approvalType.equals("Delegated")))
			{
				dirLogin = vpAsDir;
				role = "VP as Director";
				dashboard = "Vice President Action";
				card = "delegated - dir is rpo";
			}
			
			//If Responsible Program Officer is "VP As RPO" and Approval Type is "Presidential"
			if(resPORole.toLowerCase().equals("vp as rpo") && (approvalType.equals("Presidential")))
			{
				dirLogin = vpAsRPO;
				role = "VP as RPO";
				dashboard = "Vice President Action";
				card = "dir/vp rev - my reqs";
			}
			
			//If Responsible RPO Office is "Office Of the President" and Approval Type is "Delegated"
			if(rpoOfc.equals("Office of the President") && (approvalType.equals("Delegated")))
			{
				dirLogin = presAsDir;
				role = "President as Director";
				dashboard = "President Action";
				card = "dir rev - delegated";
			}
			
			//If Responsible Program Officer is "VP As RPO" and Approval Type is "Delegated"
			if(resPORole.toLowerCase().equals("vp as rpo") && (approvalType.equals("Delegated")))
			{
				dirLogin = presAsDir;
				role = "President as Director";
				dashboard = "President Action";
				card = "dir rev - delegated";
			}
			
			dirLogin = dirLogin+"~"+role+"~"+dashboard+"~"+card;
			return dirLogin;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		
	
	}
	
	
	
	/**
	 * Method Name: GetVPLogin
	 * This method is used to get the VP login to be used in VP Review Stage
	 * @throws Throwable
	 */
	synchronized public String GetVPLogin () throws Throwable 
	{
		try
		{
			String poRole = treemap.get("Login_NA_NA_Responsible Program Officer Role").toString().trim();
			String apprType=treemap.get("Request Card_REQUEST OVERVIEW_NA_Approval Type").toString().trim();
			String vpRvwLogin="";
			String vpRvwRole = "";
			String vpRvwDashboard = "";
			String vpRvwCardName = "";
			
			
			if(poRole.toLowerCase().equals("vp as rpo") && (apprType.equals("Presidential")))
			{
				vpRvwLogin = treemap.get("Login_NA_NA_VPAsRPO_Login").toString().trim();
				vpRvwRole = "VP as RPO";
			    vpRvwDashboard = "Vice President Action";
				vpRvwCardName = "dir/vp rev - my reqs";
			}
			else
			{
				vpRvwLogin = treemap.get("Login_NA_NA_Vice President_Login").toString().trim();
				vpRvwRole = "Vice President";
				vpRvwDashboard = "Vice President Action";
				vpRvwCardName = "Requests - approvals";
			}
			
			vpRvwLogin = vpRvwLogin+"~"+vpRvwRole+"~"+vpRvwDashboard+"~"+vpRvwCardName;
			return vpRvwLogin;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		
	
	}
	
	
	
	/**
	 * Method Name: GetCardNameForALRStage
	 * This method is used to get the card name to be used for Agreement Letter Review stage 
	 * @throws Throwable
	 */
	synchronized public String GetCardNameForALRStage () throws Throwable 
	{
		try
		{
			String rpoValues = "Andean Region (Bogota), Brazil (Rio de Janeiro), China (Beijing), Eastern Africa (Nairobi), India, Nepal and Sri Lanka (New Delhi), Indonesia (Jakarta), Mexico and Central America (Mexico City), Middle East and North Africa (Cairo), Southern Africa (Johannesburg), West Africa (Lagos)";
			String approvalType=treemap.get("Request Card_REQUEST OVERVIEW_NA_Approval Type").toString().trim();
			String rpoOfc = treemap.get("Request Card_REQUEST OVERVIEW_NA_RPO Office").toString().trim();
			String cardName = "";
			
			if(approvalType.equals("Delegated") && (!rpoValues.contains(rpoOfc)))
				cardName = "NY - delegated - gal rev";
			else if(approvalType.equals("Presidential") && (!rpoValues.contains(rpoOfc)))
				cardName = "NY - Presidential - gal rev";
			
			switch(rpoOfc)
			{
				case "Andean Region (Bogota)":
					cardName = "Andean - gal rev";
					break;
				case "Brazil (Rio de Janeiro)":
					cardName = "brazil - gal review";
					break;
				case "China (Beijing)":
					cardName = "China - GAL Review";
					break;
				case "Eastern Africa (Nairobi)":
					cardName = "E. Africa - GAL Review";
					break;
				case "India, Nepal and Sri Lanka (New Delhi)":
					cardName = "India, Nepal, SL - GAL Rev";
					break;
				case "Indonesia (Jakarta)":
					cardName = "Indonesia - GAL Review";
					break;
				case "Mexico and Central America (Mexico City)":
					cardName = "Mexico & C. Amer - GAL";
					break;
				case "Middle East and North Africa (Cairo)":
					cardName = "MENA - GAL Review";
					break;
				case "Southern Africa (Johannesburg)":
					cardName = "S. Africa - GAL Review";
					break;
				case "West Africa (Lagos)":
					cardName = "W. AFrica - GAL Review";
					break;
			}
			return cardName;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		
	
	}
	
	
	
	
	/**
	 * Method Name: GetPOLogin
	 * This method is used to get the PO login to be used
	 * @throws Throwable
	 */
	synchronized public String GetPOLogin () throws Throwable 
	{
		try
		{
			String resPORole = treemap.get("Login_NA_NA_Responsible Program Officer Role").toString().trim();
			String poLogin=treemap.get("Login_NA_NA_Program Officer_Login").toString().trim();
			String dirAsRPO = treemap.get("Login_NA_NA_DirectorAsRPO_Login").toString().trim();
			String vpAsRPO = treemap.get("Login_NA_NA_VPAsRPO_Login").toString().trim();
			
			
			switch(resPORole)
			{
				case "Director as RPO":
					poLogin = dirAsRPO;
				case "VP as RPO":
					poLogin = vpAsRPO;
			}
			
			return poLogin;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		
	
	}
	
	
	/**
	 * Method Name: ComplianceReviewStage
	 * This method is used fill in and submit the req by Grants Processing Unit
	 * @throws Throwable
	 */
	synchronized public boolean ComplianceReviewStage () throws Throwable {
		try
		{

			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String gpuLogin = treemap.get("Login_NA_NA_Grants Processing_Login").toString().trim();
			String gpupwd = treemap.get("Login_NA_NA_Grants Processing_Login").toString().trim();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			//String[] status= new String[1];
			
			launchFluxxUrl(gpuLogin , gpupwd, "Grants Processing Unit");
			selectDashboard("Compliance Action");
			Thread.sleep(2000);
			mouseover(fluxobs.searchTextInCard("req - COMPLIANce rev"), "mouseover on Grant Liasion",false);
			waitForElementToBeClickable(fluxobs.searchTextInCard("req - COMPLIANce rev"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("req - COMPLIANce rev"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("req - COMPLIANce rev"),"Organization Name", true);
			Thread.sleep(15000);
			
			typeClear(fluxobs.searchTextInCard("req - COMPLIANce rev"),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("req - COMPLIANce rev",OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card("req - COMPLIANce rev",OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			
			
			waitForVisibilityOfElement(fluxobs.btn_witha("req - COMPLIANce rev"), "Compliance Review", true);
			JSClick(fluxobs.btn_WorkFlowcGAL("req - COMPLIANce rev"), "Select WorkFlow", true);
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn("req - COMPLIANce rev","Submit"), "Submit", true);
			
			click(fluxobs.Comp_wfbtn("req - COMPLIANce rev","Submit"), "Submit","", true);	
			
			
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);			
			click(fluxobs.btn_witha("OK"), "select OK","", true);
			
			waitForInVisibilityOfElement(fluxobs.btn_witha("OK"), "Compliance Review", true);
			waitForInVisibilityOfElement(fluxobs.btn_witha("req - COMPLIANce rev"), "Compliance Review", true);
			Thread.sleep(5000);
			
			//waitForVisibilityOfElement(fluxobs.btn_witha("Probable"), "Probable", true);
			
			
			stat = GetStatus("req - COMPLIANce rev");
			logout_from_fluxx("Fluxx with Grants Processing Unit Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+stat+"</B>");
			
			if(stat.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}
		catch(Exception e)
		{
			System.out.println("#####################");
			System.out.println("Error in Compliance Review Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Compliance Review)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}


	}
	
	
	
	
	
	/**
	 * Method Name: Review Stages
	 * This method is used to submit and approve for further request processing
	 * @throws Throwable
	 */
	synchronized public boolean ReviewStage (String stage, String login, String pwd, String role, String dashboard, String cardName, String aprvBtn) throws Throwable {
		try
		{

			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			launchFluxxUrl(login , pwd, role);
			selectDashboard(dashboard);
			Thread.sleep(2000);
			mouseover(fluxobs.first_card(cardName), "mouseover on the card",false);
			waitForElementToBeClickable(fluxobs.first_card(cardName),"Organization Name", true);
			waitForElementPresent(fluxobs.first_card(cardName),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.first_card(cardName),"Organization Name", true);
			
			click(fluxobs.first_card(cardName),"Click on Card: "+cardName,"Click on Card", false);
			waitForElementToBeClickable(fluxobs.searchTextInCard(cardName),"Organization Name", false);
			waitForElementPresent(fluxobs.searchTextInCard(cardName),"Organization Name", false);
			waitForVisibilityOfElement(fluxobs.searchTextInCard(cardName),"Organization Name", false);
			
			Thread.sleep(15000);
			
			typeClear(fluxobs.searchTextInCard(cardName),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card(cardName,OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card(cardName,OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			
			
			
			waitForVisibilityOfElement(fluxobs.btn_WorkFlowcGAL(cardName), "Workflow button", true);
			Thread.sleep(3000);
			String prevStat = GetStatus(cardName);
			
			JSClick(fluxobs.btn_WorkFlowcGAL(cardName), "Select WorkFlow", true);
			Thread.sleep(2000);
			if(!(exists(fluxobs.Comp_wfbtn(cardName,aprvBtn))))
				throw new Exception("Unable to approve the request.~Please provide the correct approver credentials in the template.");
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn(cardName,aprvBtn), "Submit", false);
			
			click(fluxobs.Comp_wfbtn(cardName,aprvBtn), "Submit","Submit", true);	
			
			
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);			
			click(fluxobs.btn_witha("OK"), "Submit","Submit", true);
			
			waitForInVisibilityOfElement(fluxobs.btn_witha("OK"), prevStat, false);
			waitForInVisibilityOfElement(fluxobs.btn_witha(prevStat), prevStat, false);
			Thread.sleep(5000);
			
			
			stat = GetStatus(cardName);
			logout_from_fluxx("Fluxx with "+role+" Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+stat+"</B>");
			
			if(stat.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}
		catch(Exception e)
		{
			System.out.println("#####################");
			System.out.println("Error in "+stage+" Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: "+stage+")", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}


	}
	
	
	
	

	/**
	 * Method Name: ReviewStages
	 * This method is used fill in and approve the req by Grant Approvers
	 * @throws Throwable
	 */
	synchronized public void ReviewStages (String UnPw,String Dashboard,String OrgName,String ReviewStage,String button) throws Throwable {
		try {
			launchFluxxUrl(UnPw , UnPw,"");
			selectDashboard(Dashboard); 
			click(fluxobs.txt_withH2contains(OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", true);
			Thread.sleep(3000);
			click(fluxobs.btn_WorkFlow(OrgNameUppercase), "Select WorkFlow", "Select WorkFlow", true);	
			click(fluxobs.btn_witha(button), "select Submit","", true);
			Thread.sleep(2000);
			click(fluxobs.btn_witha("OK"), "select OK","", true);		
			waitForVisibilityOfElement(fluxobs.btn_witha(ReviewStage), ReviewStage, true);
			logout_fluxwebsite();

		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in ReviewStages");
			System.out.println("#####################");
		}



	}
	
	
	

	/**
	 * Method Name: ReviewStages_default
	 * This method is used fill in and approve the req by Grant Approvers
	 * @throws Throwable
	 */
	synchronized public void ReviewStages_default (String UnPw,String Dashboard,String OrgName,String ReviewStage,String button) throws Throwable {
		try {
			launchFluxxUrl(UnPw , UnPw,"");
			selectDashboard(Dashboard); 
			JSClick(fluxobs.btn_add(), "select add button", true);
			// for time being do this code 
			click(fluxobs.btn_ui("Requests"), "select requests card", "requests card", true);
			JSClick(fluxobs.btn_closemark(), "select close button", true);
			click(fluxobs.txt_withH2contains(OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", true);
			Thread.sleep(3000);
			click(fluxobs.btn_WorkFlow(OrgNameUppercase), "Select WorkFlow", "Select WorkFlow", true);	
			click(fluxobs.btn_witha(button), "select Submit","", true);
			Thread.sleep(2000);
			click(fluxobs.btn_witha("OK"), "select OK","", true);		
			waitForVisibilityOfElement(fluxobs.btn_witha(ReviewStage), ReviewStage, true);
			logout_fluxwebsite();

		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in ReviewStages");
			System.out.println("#####################");
		}



	}
	
	/**
	 * Method Name: ReviewStages2
	 * This method is used fill in and approve the req by Grant Approvers
	 * @throws Throwable
	 */
	synchronized public void ReviewStages2 (String UnPw,String Dashboard,String OrgName,String ReviewStage,String button,String cardName) throws Throwable {
		try {
			launchFluxxUrl(UnPw , UnPw,"");
			selectDashboard(Dashboard); 
			click(fluxobs.txt_withH2contains(OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.btn_WorkFlowcGAL(cardName), "Select WorkFlow", "Select WorkFlow", true);
			Thread.sleep(2000);
			click(fluxobs.btn_SendtoPendingCGal(), "select Submit","", true);
			Thread.sleep(2000);
			click(fluxobs.btn_witha("OK"), "select OK","", true);		
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn(cardName,ReviewStage), ReviewStage, true);
			logout_fluxwebsite();
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in ReviewStages2");
			System.out.println("#####################");
		}




	}

	
	/**
	 * Method Name: ReviewStages2a
	 * This method is used fill in and approve the req by Grant Approvers
	 * @throws Throwable
	 */
	synchronized public void ReviewStages2a (String UnPw,String Dashboard,String OrgName,String ReviewStage,String button,String cardName) throws Throwable {
		try {
			launchFluxxUrl(UnPw , UnPw,"");
			selectDashboard(Dashboard); 
			click(fluxobs.txt_withH2contains(OrgName), "Click on Org Name", "ClickonOrgName", true);
			String OrgNameUppercase = OrgName.toUpperCase();
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.btn_WorkFlowcGAL(cardName), "Select WorkFlow", "Select WorkFlow", true);
            Thread.sleep(2000);
			click(fluxobs.btn_ApproveGAL(), "select Approve GAL","Approve GAL", true);
			Thread.sleep(2000);
		
			click(fluxobs.btn_witha("OK"), "select OK","", true);		
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn(cardName,ReviewStage), ReviewStage, true);
			logout_fluxwebsite();
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in ReviewStages2");
			System.out.println("#####################");
		}




	}

	/**
	 * Method Name: Pending_CGALStage
	 * This method is used fill in and approve the req by Grant Approvers
	 * @throws Throwable
	 *///String login,String Dashboard,String OrgName,String ReviewStage,String button
	synchronized public boolean Pending_CGALStage () throws Throwable {
		try 
		{
			String OrgName = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String OrgNameUppercase = OrgName.toUpperCase();
			String pcLogin = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String pcRole = "Grants Manager";
			String pcDashboard = "GM Grant Approval";
			String pcCardName = "PENDING c-GAL";
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			String aprvBtn = "cGAL Complete";
			String prevStat = "Pending cGAL";

			launchFluxxUrl(pcLogin , pcLogin,"Grants Manager");
			selectDashboard(pcDashboard); 
			
			mouseover(fluxobs.first_card(pcCardName), "mouseover on the card",false);
			waitForElementToBeClickable(fluxobs.first_card(pcCardName),"Organization Name", true);
			waitForElementPresent(fluxobs.first_card(pcCardName),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.first_card(pcCardName),"Organization Name", true);
			
			click(fluxobs.first_card(pcCardName),"Click on Card: "+pcCardName,"Click on Card", false);
			waitForElementToBeClickable(fluxobs.searchTextInCard(pcCardName),"Organization Name", false);
			waitForElementPresent(fluxobs.searchTextInCard(pcCardName),"Organization Name", false);
			waitForVisibilityOfElement(fluxobs.searchTextInCard(pcCardName),"Organization Name", false);
			
			Thread.sleep(15000);
			
			typeClear(fluxobs.searchTextInCard(pcCardName),OrgName,"Organization Name","Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card(pcCardName,OrgName), "Click On Organization Name", true);
			Thread.sleep(2000);
			click(fluxobs.txt_withH2contains_card(pcCardName,OrgName), "Click on Org Name", "ClickonOrgName", true);
			waitForVisibilityOfElement(fluxobs.btn_witha(prevStat), "Status - Pending cGAL", true);
			
			
			
			
			
			
			
			//	click(fluxobs.btn_ui("Edit"), "click on Edit", "Click on Edit", true);
			mouseover(fluxobs.Comp_grantFields("PENDING c-GAL", "GM FINAL CHECKLIST"), "", true);
			Thread.sleep(3000);
			click(fluxobs.Comp_grantFields("PENDING c-GAL","INTERNAL DOCUMENTS"), "Open the INTERNAL DOCUMENTS","opened the INTERNAL DOCUMENTS", true);

			click(fluxobs.cGAL_Document(), "cGAL_Document","", true);
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
			waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload audit Financial Documents", true);
			selectByVisibleText(fluxobs.select_cGAL(), "cGAL", "Select cGAL document", true);

			click(fluxobs.btn_witha("Start upload"), "Upload the docs", "Upload the audit Fincancial Documents", true);

			waitForVisibilityOfElement(fluxobs.StatusUpload(), "wait for 100% Status", true);
			Thread.sleep(1000); 
			click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);

			Thread.sleep(9000);
			
			
			
			
			
			
			JSClick(fluxobs.btn_WorkFlowcGAL(pcCardName), "Select WorkFlow", true);
			Thread.sleep(2000);
			if(!(exists(fluxobs.Comp_wfbtn(pcCardName,aprvBtn))))
				throw new Exception("Unable to approve the request.~Please provide the correct approver credentials in the template.");
			waitForVisibilityOfElement(fluxobs.Comp_wfbtn(pcCardName,aprvBtn), "Submit", false);
			
			click(fluxobs.Comp_wfbtn(pcCardName,aprvBtn), "Submit","Submit", true);	
			
			
			waitForVisibilityOfElement(fluxobs.editWF_Note("Edit Note"), "Edit Note", true);			
			click(fluxobs.btn_witha("OK"), "Submit","Submit", true);
			
			waitForInVisibilityOfElement(fluxobs.btn_witha("OK"), prevStat, false);
			waitForInVisibilityOfElement(fluxobs.btn_witha(prevStat), prevStat, false);
			Thread.sleep(5000);
			
			
			stat = GetStatus(pcCardName);
			logout_from_fluxx("Fluxx with "+pcRole+" Login"); 
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>"+stat+"</B>");
			
			if(stat.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
			
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Pending_CGALStage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Pending cGAL+)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}

	}
	
	
	/**
	 * Method Name: VicePresidentReviewStage
	 * This method is used fill in and approve the req by Grant Approvers
	 * @throws Throwable
	 */
	synchronized public void VicePresidentReviewStage (String OrgName) throws Throwable {

		launchFluxxUrl("director_tester2" , "director_tester2","");
		selectDashboard("Director Action"); 
		click(fluxobs.txt_withH2contains(OrgName), "Click on Org Name", "ClickonOrgName", true);
		String OrgNameUppercase = OrgName.toUpperCase();
		waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng(OrgNameUppercase), "Organization Name", true);

		click(fluxobs.btn_WorkFlow(OrgNameUppercase), "Select WorkFlow", "Select WorkFlow", true);	
		click(fluxobs.btn_witha("Submit"), "select Submit", "Select Submit", true);
		click(fluxobs.btn_witha("OK"), "select OK", "Select OK", true);
		waitForVisibilityOfElement(fluxobs.btn_witha("VP Review"), "VP Review", true);
		logout_fluxwebsite();



	}


	/**
	 * Method Name: CloseCards
	 * This method is used to close all the cards in the dashboard
	 * @throws Throwable
	 */
	synchronized public void CloseCards () throws Throwable {




		if (isElementPresent(fluxobs.firstcard(),"firstcard")) 

		{


			List<WebElement> noofcards = driver.findElements(By.xpath("//div[@id='card-table']//li[*]//a[@class='close-card']"));
			for(int i=0;i<noofcards.size();i++) {

				click(fluxobs.btn_closecard(), "closing the card","close the existing card", true);
				if(isAlertPresent())
				{
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
			}

		}		
		else {

			System.out.println("No existing cards available");
		}



	}



	

	/**
	 * Method Name: Create_Contact_and_Link_the_Organization_with_Super_User
	 * This method is used to create contact
	 * @throws Throwable
	 */
	public void Create_Contact_and_Link_the_Organization_with_Super_User () throws Throwable {
		try {
			String OrgNameUppercase = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim().toUpperCase()+timeNow;
			String organization_name=treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String grantee_firstname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_First Name*").toString().trim();
			String grantee_lastname= treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Last Name*").toString().trim()+timeNow;
			String grantee_Loginname = treemap.get("Contact Card_Login and Access information_NA_Login").toString().trim()+timeNow;
			String password= treemap.get("Contact Card_Login and Access information_NA_Password").toString().trim()+timeNow;
			
			
			launchFluxxUrl_SU("auto_superuser" , "abcd@1234");
			selectDashboard("Default"); 
			closeCards(fluxobs.close_contactcard("New Request"), fluxobs.close_contactcard3("New Request"));
			closeCards(fluxobs.close_contactcard("Requests"), fluxobs.close_contactcard3("Requests"));
			closeCards(fluxobs.close_contactcard_txtContains("Automation Tester"), fluxobs.close_contactcard3_txtContains("Automation Tester"));
			JSClick(fluxobs.btn_add(), "select add button", true);
			click(fluxobs.btn_ui("Requests"), "select requests card", "requests card", true);
			JSClick(fluxobs.btn_closemark(), "select close button", true);
			
			waitForElementToBeClickable(fluxobs.searchTextInCard("Requests"),"Organization Name", true);
			waitForElementPresent(fluxobs.searchTextInCard("Requests"),"Organization Name", true);
			waitForVisibilityOfElement(fluxobs.searchTextInCard("Requests"),"Organization Name", true);
			Thread.sleep(1000);
			
			typeClear(fluxobs.searchTextInCard("Requests"),organization_name,"Type Organization Name","Type Organization Name", false);
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.txt_withH2contains_card("Requests",organization_name), "select org name to link", true);
			Thread.sleep(2000);
			//      type(fluxobs.search(), grantee_firstname+" "+grantee_lastname, "search box", "search box");
			click(fluxobs.txt_withH2contains_card("Requests",organization_name), "select org name to link", "Org name", true);
			waitForVisibilityOfElement(fluxobs.txt_withucontainsStrng_card("Requests",OrgNameUppercase), "Organization Name", true);
			//	waitForVisibilityOfElement(fluxobs.Connectedbutton(OrgNameUppercase), "select connected button");
			click(fluxobs.Connectedbutton(OrgNameUppercase), "select connected button", "connected magnet button", true);
			Thread.sleep(2000);

			mouseover(fluxobs.Connectedbutton(OrgNameUppercase), "Requests", true);
			Thread.sleep(2000);
			click(fluxobs.Contacts_button(OrgNameUppercase), "Select Contacts", "Select contacts", true);
			mouseover(fluxobs.txts_withH2contains(grantee_firstname,grantee_lastname), "select org name link", true);
			click(fluxobs.txts_withH2contains(grantee_firstname,grantee_lastname), "select org name to link", "Org name", true);

			// close all oth ercards excpet the contact card
			Thread.sleep(2000);
			//if (isElementPresent(fluxobs.close_contactcard("Requests"), "Closecard")) {

				//click(fluxobs.close_contactcard("Requests"), "close contact card", "close contact card");

			//}

			mouseover(fluxobs.edit_buttonSuperUser(grantee_firstname,grantee_lastname), "mouseover", true);
			Thread.sleep(4000);
			//    click(fluxobs.superuser_edit("grantee_firstname"), "Click edit","'");
			click(fluxobs.edit_buttonSuperUser(grantee_firstname,grantee_lastname), "Click edit","Edit the form", true);

			Thread.sleep(2000);
			//	click(fluxobs.txt_profilename("Grantee"), "Select grantee in profile section","select the Grantee profile name");
			//	click(fluxobs.btn_arrownext(), "select the next button to move grantee profile","select the next button");


			typeAndEnter(fluxobs.txtboxEq(grantee_firstname+" "+grantee_lastname,"user_login"), grantee_Loginname, "Grantee login name",grantee_Loginname +" in grantee login name field", true);
			typeAndEnter(fluxobs.txtboxEq(grantee_firstname+" "+grantee_lastname,"user_password"), password,"grantee password", password+ " in grantee password field", true);
			typeAndEnter(fluxobs.txtboxEq(grantee_firstname+" "+grantee_lastname,"user_password_confirmation"), password,"grantee Password Confirmation ",password+ " in grantee password confirmation field", true);
			//click(fluxobs.btn_ui("Save"), "save the new grantee contact card","save the new grantee cotact details"); 
			click(fluxobs.Comp_wfbtn(grantee_firstname+" "+grantee_lastname,"Save"), "save the new grantee contact card","save the new grantee cotact details", true);
			waitForVisibilityOfElement(fluxobs.header_card(grantee_firstname,grantee_lastname), grantee_firstname, true);

			/*mouseover(fluxobs.link_linkaddRole() , "link add role");
			Thread.sleep(2000);
			JSClick(fluxobs.link_linkaddRole(),"Add the type of role to the grantee");
			waitForElementPresent(fluxobs.txt_span("add ro le"), "add role");
			selectByVisibleText(fluxobs.select_role(), grantee_role,"Grantee");
			selectByVisibleText(fluxobs.select_program(),grantee_program, ".Test Funding Source");
			Thread.sleep(2000);

			click(fluxobs.btn_createRelationship(), "create the role user","Create the Role user"); */
			Thread.sleep(8000);
			click(fluxobs.chckbox_ismoderator(grantee_firstname, grantee_lastname), "check the moderator check box","check the moderator check box", true);
			Thread.sleep(4000);
			//String status = VerifyStatus("New Request", "Possible");
			logout_from_fluxx("Fluxx with Program Officer_Login");  
			SuccessReport("Link the Contact with the new Request", "Successfully linked the Contact with the new Request");
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Create_Contact_and_Link_the_Organization_with_Super_User Stage");
			System.out.println("#####################");
			//failureReport("Create new Contact via Super User", "Unable to create Contact");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Creating new Contact via Super User)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}



	}

	/**
	 * Method Name: Fill_Grantee_Details_using_Grantee_Portal
	 * This method is used to fill in details by Grantee
	 * @throws Throwable
	 */
	public void Fill_Grantee_Details_using_Grantee_Portal () throws Throwable {
		try 
		{
			String grantee_Loginname = treemap.get("Login_NA_NA_Grantee Moderator_Login").toString().trim()+timeNow;
			String  password = treemap.get("Login_NA_NA_Grantee Moderator_Login").toString().trim()+timeNow;
			String  grantee_firstname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_First Name*").toString().trim();
			String  grantee_lastname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Last Name*").toString().trim()+timeNow;
			String  grantee_mailId = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Email*").toString().trim();
			grantee_mailId = grantee_mailId.split("[@]")[0] + timeNow + grantee_mailId.split("[@]")[1];
			
			String  organization_name = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;

			
			System.out.println("org name: "+organization_name);
			System.out.println("grantee first name"+grantee_firstname);
			System.out.println("grantee last name: "+grantee_lastname);
			System.out.println("grantee login name: "+grantee_Loginname);
			System.out.println("password: "+password);
			System.out.println("grantee mail id :"+ grantee_mailId);
			launchFluxxUrl(grantee_Loginname,password,"Grantee");
			//if(!waitForElementPresent(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false))
			Thread.sleep(1000);
			driver.navigate().refresh();
			waitForElementPresent(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false);
			click(fluxobs.btns_granteeportal("Organizations"), "Open the organization dtails in grantee external portal","open the organization details", true);
			waitForElementPresent(fluxobs.txt_granteeorgsearch(organization_name),organization_name, true);
			click(fluxobs.btn_edit(),"Edit the grantee details", "open the grantee filling form", true);
			waitForElementPresent(fluxobs.txt_granteeorgsearch(organization_name),organization_name, true);

			filling_granteedetails(organization_name,grantee_firstname,grantee_lastname);
			
			waitForElementPresent(fluxobs.btn_submit_grantee("New"),"Submit Button", true);
			waitForElementToBeClickable(fluxobs.btn_submit_grantee("New"),"Submit Button", true);
			waitForVisibilityOfElement(fluxobs.btn_submit_grantee("New"),"Submit Button", true);
			
			Thread.sleep(3000);
			click(fluxobs.btn_submit_grantee("New"), "Submit the grantee deatails","submit the grantee details", true);
			typeAndEnter(fluxobs.txtarea_comment(), "I have entered my credentials","comments", "Few comments in comment box", true);
			click(fluxobs.btn_Ok(), "close the Comment box","close the comment box", true);
			//     click(fluxobs.btn_close(), "Close button");
			Thread.sleep(4000);

			logout_fluxgranteeExtportal();
			SuccessReport("Fill in all the required details in Grantee Portal via Grantee moderator", "Successfully filled in all the required details in Grantee Portal");
			SuccessReport("Verify the Status of the Grantee", "Status of the Grantee: <B>GM Review</B>");

		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Fill_Grantee_Details_using_Grantee_Portal");
			System.out.println("#####################");
			//failureReport("Fill in all the required details in Grantee Portal via Grantee moderator", "Unable to fill in all the required details in Grantee Portal");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Fill Grantee Details using Grantee Portal)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}





	} 
	
	
	/**
	 * Method Name: ReqForInfo_Grantee_Portal
	 * This method is used to fill in details by Grantee
	 * @throws Throwable
	 */
	public boolean ReqForInfo_Grantee_Portal () throws Throwable {
		try 
		{
			String grantee_Loginname = treemap.get("Login_NA_NA_Grantee Moderator_Login").toString().trim()+timeNow;
			String  password = treemap.get("Login_NA_NA_Grantee Moderator_Login").toString().trim()+timeNow;
			String  grantee_firstname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_First Name*").toString().trim();
			String  grantee_lastname = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Last Name*").toString().trim()+timeNow;
			String  grantee_mailId = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Email*").toString().trim();
			grantee_mailId = grantee_mailId.split("[@]")[0] + timeNow + grantee_mailId.split("[@]")[1];
			String  organization_name = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			System.out.println("org name: "+organization_name);
			System.out.println("grantee first name"+grantee_firstname);
			System.out.println("grantee last name: "+grantee_lastname);
			System.out.println("grantee login name: "+grantee_Loginname);
			System.out.println("password: "+password);
			System.out.println("grantee mail id :"+ grantee_mailId);
			launchFluxxUrl(grantee_Loginname,password,"Grantee");
			//if(!waitForElementPresent(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false))
			Thread.sleep(1000);
			driver.navigate().refresh();
			
			waitForElementPresent(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false);
			waitForVisibilityOfElement(fluxobs.btns_granteeportal("Organizations"), "Ford Foundation title", false);
			Thread.sleep(5000);
			mouseover(fluxobs.btns_granteeportal("New/Pending"), "mouseover on Grant Liasion",false);
			click(fluxobs.btns_granteeportal("New/Pending"), "Open the organization dtails in grantee external portal","open the organization details", true);
			waitForElementPresent(fluxobs.txt_granteeReqsearch(organization_name.toUpperCase()),organization_name, true);
			click(fluxobs.btn_edit(),"Edit the grantee details", "open the grantee filling form", true);
			//waitForElementPresent(fluxobs.txt_granteeReqsearch(organization_name.toUpperCase()),organization_name, true);
			waitForVisibilityOfElement(fluxobs.btn_save(), "Edit the grantee details", true);
			Thread.sleep(3000);
			filling_granteeReqdetails(organization_name,grantee_firstname,grantee_lastname);
			
			waitForVisibilityOfElement(fluxobs.btn_submit_grantee("Request for Information"), "Submit the grantee details", true);
			waitForElementPresent(fluxobs.btn_submit_grantee("Request for Information"), "Submit the grantee details", true);
			waitForElementToBeClickable(fluxobs.btn_submit_grantee("Request for Information"), "Submit the grantee details", true);
			
			click(fluxobs.btn_submit_grantee("Request for Information"), "Submit the grantee deatails","submit the grantee details", true);
			typeAndEnter(fluxobs.txtarea_comment(), "I have entered my credentials","comments", "Few comments in comment box", true);
			click(fluxobs.btn_Ok(), "close the Comment box","close the comment box", true);
			//     click(fluxobs.btn_close(), "Close button");
			Thread.sleep(4000);

			logout_fluxgranteeExtportal();
			String status = "GM Review";
			SuccessReport("Fill in all the required details in Grantee Portal via Grantee moderator", "Successfully filled in all the required details in Grantee Portal");
			SuccessReport("Verify the Status of the Grant", "Status of the Grant: <B>GM Review</B>");
			
			if(status.toLowerCase().equals(stopStatus.toLowerCase()))
				return true;
			return false;
		}

		catch(Exception e) {
			System.out.println("#####################");
			System.out.println("Error in Fill_Grantee_Details_using_Grantee_Portal");
			System.out.println("#####################");
			//failureReport("Fill in all the required details in Grantee Portal via Grantee moderator", "Unable to fill in all the required details in Grantee Portal");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Request For Information)", e.getMessage().split("[~]")[1]);
			count = count+1;
			throw e;
		}
	} 
	

	/**
	 * Method Name: createNewGranteeViaRequest
	 * This method is used to create Grantee
	 * @throws Throwable
	 */
	synchronized public void createNewGranteeViaRequest () throws Throwable {
		String typeOfNewReq = "";
		try {


			String OrgNameUppercase = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim().toUpperCase()+timeNow;
			String OrgName=treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String FirstName = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_First Name*").toString().trim();
			String LastName = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Last Name*").toString().trim()+timeNow;
			String MailId = treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Email*").toString().trim();
			MailId = MailId.split("[@]")[0] + timeNow + MailId.split("[@]")[1];
			String gm_Login = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String gm_pwd = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
			String reqType = treemap.get("RequestType").toString().trim();
			String grantReqType = treemap.get("Request Card_NA_NA_Grant -Request Type").toString().trim();
			String granteeType = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Grantee Type").toString().trim();
			
			
			String grant_title=treemap.get("Request Card_REQUEST OVERVIEW_NA_Grant Title").toString().trim();
			String job_title=treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_Job Title").toString().trim();
			String requestLogin=treemap.get("Request Card_REQUEST OVERVIEW_Grant Liaison ( Add new)_*Request Login").toString().trim();
			String approvalType=treemap.get("Request Card_REQUEST OVERVIEW_NA_Approval Type").toString().trim();
			String prFundingSource=treemap.get("Request Card_REQUEST OVERVIEW_NA_Primary Funding Source").toString().trim();
			String progOffcr=treemap.get("Request Card_REQUEST OVERVIEW_NA_Responsible Program Officer").toString().trim();
			String grntsmgr=treemap.get("Request Card_REQUEST OVERVIEW_NA_Grants Manager").toString().trim();
			String just_films = treemap.get("Request Card_Request  Special Category_NA_Just Films").toString().trim();
			String waveAuditedFin = treemap.get("Request Card_Request  Special Category_NA_Wave Audited Financials?").toString().trim();
			String buildType = treemap.get("Request Card_Request Special Category_NA_Build type").toString().trim();
			String renewal = treemap.get("Request Card_Request  Special Category_NA_Renewal").toString().trim();
			String regrant = treemap.get("Request Card_Request  Special Category_NA_Regrant").toString().trim();
			String recoverable = treemap.get("Request Card_Request  Special Category_NA_Recoverable").toString().trim();
			
			String diffMailAddr = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Does this Organization have a Different mailing Address?").toString().trim();
			String mail_street_addr = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_Street Address").toString().trim();
			String mail_street_addr2 = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_Street Address 2").toString().trim();
			String mail_city = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_City").toString().trim();
			String mail_state = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_State/Province").toString().trim();
			String mail_postal = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_Postal Code (Zip)").toString().trim();
			String mail_country = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_Country").toString().trim();
			String mail_pref_lang = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Mailing Address_Preferred Language").toString().trim();
			
			
			
			String otherMailAddr = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Does this Organization have another address?").toString().trim();
			String add_street_addr = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_Street Address").toString().trim();
			String add_street_addr2 = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_Street Address 2").toString().trim();
			String add_city = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_City").toString().trim();
			String add_state = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_State/Province").toString().trim();
			String add_postal = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_Postal Code (Zip)").toString().trim();
			String add_country = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_Country").toString().trim();
			String add_pref_lang = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_Preferred Language").toString().trim();
			String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
			
			
			String buildProgOffr = treemap.get("Request Card_REQUEST OVERVIEW_NA_Build Program Officer").toString().trim();
			
			if(reqType.toLowerCase().equals("non build"))
				typeOfNewReq = "New Request";
			else if(reqType.toLowerCase().equals("build"))
				typeOfNewReq = "New Build";
			
			launchFluxxUrl(gm_Login , gm_pwd,"Grants Manager");
			selectDashboard("GM Planning"); 

			closeCards(fluxobs.close_contactcard("New Request"), fluxobs.close_contactcard3("New Request"));
			closeCards(fluxobs.close_contactcard("New Build"), fluxobs.close_contactcard3("New Build"));
			//CloseCards();
			JSClick(fluxobs.btn_add(), "select add button", true);
			//click(fluxobs.btn_add(), "select add button", "add button", true);
			Thread.sleep(3000);
			click(fluxobs.new_card("Requests"), "select requests card", "requests card", true);
			//click(fluxobs.btn_closemark(), "select close button", "close button", true);
			
			
			//waitForElementToBeClickable(fluxobs.newRequestbutton("possible"), "New Request button from Possible card",true);
			//Thread.sleep(3000);
			//click(fluxobs.newRequestbutton("possible"), "Select New Request card", "Select new request card",true);
			waitForVisibilityOfElement(fluxobs.newRequestOptions(typeOfNewReq), "New request option",true);
			Thread.sleep(3000);
			
			//Checks the card name
			
			
			click(fluxobs.newRequestOptions(typeOfNewReq), "Select New Request Option", "Select New request Option", true);
			JSClick(fluxobs.btn_closemark(), "select close button", true);
			
			waitForElementToBeClickable(fluxobs.txtArea_DetailArea(typeOfNewReq,"Grant Title"), "granttitle_comments",true);
			waitForElementPresent(fluxobs.txtArea_DetailArea(typeOfNewReq,"Grant Title"), "granttitle_comments",true);
			waitForVisibilityOfElement(fluxobs.txtArea_DetailArea(typeOfNewReq,"Grant Title"), "granttitle_comments",true);
			Thread.sleep(1000);
			
		
			
			typeAndEnter(fluxobs.txtArea_DetailArea(typeOfNewReq,"Grant Title"), grant_title, "Grantee Title", "The Grantee title is successfully entered",true);
			selectByVisibleText(fluxobs.granteecreate_DropDown_Card(typeOfNewReq,"grant_request_grant_type"), grantReqType, "Organization", true);
			click(fluxobs.btn_AddNew(typeOfNewReq,"Organization"), "Select Organization", "Select Organization",true);
			
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			
			waitForVisibilityOfElement(fluxobs.txtbox("organization_name"), "Org Name",true);
			
			selectByVisibleText(fluxobs.dropdownbyId("organization_grantee_type"), granteeType, "Grantee Type", true);
			
			typeAndEnter(fluxobs.txtbox("organization_name"), OrgName, "Organization name", "Organization name",true);
			typeAndEnter(fluxobs.txtbox("organization_fka_name"), "testing_Org", "Organization other name", "Organization other name",true);
			typeAndEnter(fluxobs.txtbox("organization_name_foreign_language"), "English", "Language name", "Language name",true);
			
			if(diffMailAddr.trim().toLowerCase().equals("yes"))
			{
				selectByVisibleText(fluxobs.dropdownbyId("organization_mailing_yn"), diffMailAddr, "Different Mailing Address", true);
				Thread.sleep(1000);
				typeAndEnter(fluxobs.startDate("organization_mailing_street_address"), mail_street_addr, "Different Mailing Street Address", "Different Mailing Street Address",true);
				typeAndEnter(fluxobs.startDate("organization_mailing_street_address2"), mail_street_addr2, "Different Mailing Street Address 2", "Different Mailing Street Address 2",true);
				typeAndEnter(fluxobs.startDate("organization_mailing_city"), mail_city, "Different Mailing address city", "Different Mailing address city",true);
				typeAndEnter(fluxobs.startDate("organization_mailing_state_str"), mail_state, "Different Mailing address state", "Different Mailing address state",true);
				typeAndEnter(fluxobs.startDate("organization_mailing_postal_code"), mail_postal, "Different Mailing address postal code", "Different Mailing address postal code",true);
				typeAndEnter(fluxobs.startDate("organization_mailing_country_str"), mail_country, "Different Mailing address country", "Different Mailing address country",true);
				selectByVisibleText(fluxobs.dropdownbyId("organization_mailing_language_type"), mail_pref_lang, "Different Mailing Address Preferred language", false);
			}
			
			if(otherMailAddr.trim().toLowerCase().equals("yes"))
			{
				selectByVisibleText(fluxobs.dropdownbyId("organization_grant_address_yn"), otherMailAddr, "Additional Address", true);
				Thread.sleep(1000);
				typeAndEnter(fluxobs.startDate("organization_grant_address_street_address"), add_street_addr, "Additional Street Address", "Additional Street Address",true);
				typeAndEnter(fluxobs.startDate("organization_grant_address_street_address2"), add_street_addr2, "Additional Street Address 2", "Additional Street Address 2",true);
				typeAndEnter(fluxobs.startDate("organization_grant_address_city"), add_city, "Additional address city", "Additional address city",true);
				typeAndEnter(fluxobs.startDate("organization_grant_address_state_str"), add_state, "Additional address state", "Additional address state",true);
				typeAndEnter(fluxobs.startDate("organization_grant_address_postal_code"), add_postal, "Additional address postal code", "Additional address postal code",true);
				typeAndEnter(fluxobs.startDate("organization_grant_address_country_str"), add_country, "Additional address country", "Additional address country",true);
				//type_input(fluxobs.startDate("organization_grant_address_language_type"), add_pref_lang, "Additional address Preferred language", "Additional Mailing address Preferred language",true);
				selectByVisibleText(fluxobs.dropdownbyId("organization_grant_address_language_type"), add_pref_lang, "Additional address Preferred language", false);
			}
			
			
		
			Thread.sleep(1000);
			click(fluxobs.btn_save(), "click on save button", "Save button",true);
			Thread.sleep(3000); // wait for visibility of element
			//click(fluxobs.Comp_wfbtn("New Request","Save"), "Click Save","Click");



			mouseover(fluxobs.btn_AddNew(typeOfNewReq,"Grant Liaison"), "mouseover on Grant Liasion",false);
			click(fluxobs.btn_AddNew(typeOfNewReq,"Grant Liaison"), "Select grantee Liaison", "grantee Liaison form",true);
			
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			
			Thread.sleep(2000); // wait for visibility of element
			selectByVisibleText(fluxobs.drpdown_field("user_prefix_dropdown"), "Mr.", "Select prefix",true);
			typeAndEnter(fluxobs.txtbox("user_first_name"), FirstName, "FirstName", "Select Grantee Liaison first name", true);
			typeAndEnter(fluxobs.txtbox("user_last_name"), LastName, "FirstName", "Select Grantee Liaison Last name", true);
			typeAndEnter(fluxobs.txtbox( "user_email"), MailId, "email details", "enter Grantee Liaison email details", true);
			typeAndEnter(fluxobs.txtbox("user_personal_mobile"), "1094949384", "Mobile details", "enter Grantee Liaison mobile details", true);
			typeAndEnter(fluxobs.txtbox("user_title"), job_title, "Job title details", "enter Grantee Liaison Job title details", true);
			selectByVisibleText(fluxobs.drpdown_field("user_login_needed"), requestLogin, "Login needed", true);
			click(fluxobs.btn_save(), "click on save button", "Save button", true);
			waitForInVisibilityOfElement(fluxobs.txtbox("user_first_name "), "First Name", true);

			//		waitForVisibilityOfElement(fluxobs.Comp_wfbtn("New Request","Save"), "Edit button : for Sync purpose"); //edit
			//		Thread.sleep(4000);

			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_approval_type"), approvalType, "Approval type", true);
			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_program_id"),prFundingSource, "Primary Funding Source", true);
			//click(fluxobs.drpdown_field2("grant_request_program_lead_id"),"","",false);
			Thread.sleep(5000);
			
			

			
			selectByPartialText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_program_lead_id"),progOffcr, false,"Program Officer", true);
			Thread.sleep(2000);
			selectByPartialText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_secondary_program_lead_id"),grntsmgr, false,"Grants Manager", true);
			Thread.sleep(2000);
			
			if(reqType.toLowerCase().equals("build"))
				selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_build_po_model"),buildProgOffr, "Build Program Officer", true);
			Thread.sleep(2000);
			
			//Add Just Films Flag
			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_just_films_yn"),just_films, "Just Films flag", true);
			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_waive_audited_financials_yn"),waveAuditedFin, "Wave Audited Financials", true);
			
			if(reqType.toLowerCase().equals("build"))
				selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_build_type"),buildType, "Build type", true);
			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_request_is_renewal"),renewal, "grant request renewal", true);
			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_request_is_regrant"),regrant, "grant request regrant", true);
			selectByVisibleText(fluxobs.drpdown_field_card(typeOfNewReq,"grant_request_recoverable_grant"),recoverable, "grant recoverable grant", true);

			
			click(fluxobs.Comp_wfbtn(typeOfNewReq,"Save"), "Click Save","click save", true);

			waitForVisibilityOfElement(fluxobs.btn_paramUi(OrgNameUppercase,"Edit"), "Edit button : for Sync purpose", true);
			String reqId = getText(fluxobs.reqId(typeOfNewReq),"Get request Id");
			System.out.println("Request ID: "+reqId);
			String status = VerifyStatus(typeOfNewReq, "Possible");
			logout_from_fluxx("Fluxx with Grants Manager Login"); 
			SuccessReport("Create new Grantee", "Successfully created grantee: Org Name: "+ OrgName +"/Grant Request Id: "+reqId.split("[|]")[0].trim());
			SuccessReport("Create new Contact", "Successfully created contact: (Contact Name: "+ FirstName + " "+ LastName +")");
			SuccessReport("Verify the Status of the Request", "Status of the Request: <B>"+status+"</B>");
			
			//SuccessReport("Verify the Status of the grantee", "Status of the grantee: "+status);
		}

		catch (Exception e) {
			System.out.println("#####################");
			System.out.println("Error in createNewGranteeViaRequest Stage");
			System.out.println("#####################");
			failureReport(e.getMessage().split("[~]")[0]+"(Stage: Creating new Grantee via Request)", e.getMessage().split("[~]")[1]);
			//failureReport("Create new Grantee via new request", "Unable to create grantee");
			count = count+1;
			throw e;
			
		}

	}
	
	
	/**
	 * Method Name: VerifyStatus
	 * This method is used to assert the status field
	 * @throws Throwable
	 */
	public String VerifyStatus(String cardName, String expectedStatus) throws Throwable  
	{   
		try  
		{  
			Thread.sleep(3000);
			waitForVisibilityOfElement(fluxobs.getCardStatus(cardName), "", true);
			Thread.sleep(6000);
			waitForVisibilityOfElement(fluxobs.getCardStatus(cardName), "", true);
			String status= getTextFromField(fluxobs.getCardStatus(cardName), "Status", true);
			if(!status.equals(expectedStatus))
				throw new Exception("Get the status of the Request~Status of the request created is "+status+"(Status should be '"+expectedStatus+"')");
			return status;
		}  
		catch(Exception e)  
		{  
			throw new Exception("Get text of 'Status' field~Unable to get the text from 'Status' field");  
		}  
	}
	
	
	/**
	 * Method Name: VerifyStatus
	 * This method is used to assert the status field
	 * @throws Throwable
	 */
	public String GetStatus(String cardName) throws Throwable  
	{   
		try  
		{  
			Thread.sleep(3000);
			waitForVisibilityOfElement(fluxobs.getCardStatus(cardName), "", true);
			Thread.sleep(6000);
			waitForVisibilityOfElement(fluxobs.getCardStatus(cardName), "", true);
			String status= getTextFromField(fluxobs.getCardStatus(cardName), "Status", true);
			return status;
		}  
		catch(Exception e)  
		{  
			throw new Exception("Get text of 'Status' field~Unable to get the text from 'Status' field");  
		}  
	}
	

	
	/**
	 * Method Name: exists
	 * This method is used to assert the web element present or not
	 * @throws Throwable
	 */
	public boolean exists(By by)  
	{  
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  
		try  
		{  
			driver.findElement(by);  
			return true;  
		}  
		catch(Exception e)  
		{  
			return false;  
		}  
		finally  
		{  
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
		}  
	}
	

	/***
	 *GetTimeStamp
	 *This method is used to get the current timestamp
	 * @return timeStamp
	 * @throws Throwable
	 */
	synchronized public String GetTimeStamp()  {

		Date todaysDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ssSSS");
		String timeStamp = formatter.format(todaysDate);
		//	String driverBrowser = driver.getWrappedDriver().toString();
		return "_"+timeStamp;


	}

	/**
	 * Method Name: filling_granteedetails
	 * This method is used to fill in all the details by Grantee
	 * @throws Throwable
	 */
	synchronized public void filling_granteedetails (String orgName, String grantee_fName , String grantee_Lname) throws Throwable {

		// Entering all the credentials
		try
		{
			String Grantee_leagalName = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Grantee Legal Name").toString().trim(); 
			String Grantee_Acronym = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Acronym").toString().trim(); 
			String Grantee_organization_street_address = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_Additional Address_Street Address").toString().trim(); 
			String Grantee_organization_city = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_City").toString().trim(); 
			String Grantee_organization_postal_code  = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Postal Code (Zip)").toString().trim(); 
			String Grantee_organization_phone = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Phone").toString().trim(); 
			String Grantee_organization_email = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Organizational Email").toString().trim(); 
			String Grantee_organization_fax = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Fax").toString().trim(); 
			//String Grantee_organization_url = treemap.get("Organization").toString().trim(); 
			String Grantee_organization_year_founded= treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Year Founded").toString().trim(); 
			String Grantee_organization_grantee_description = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Concise Mission Statement").toString().trim(); 
			String Grantee_organization_fy_month = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Fiscal Year End Month (1-12)").toString().trim(); 
			String Grantee_organization_fy_date = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Fiscal Year End Day (1-31)").toString().trim(); 
			String country = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Country").toString().trim();
			String state = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_State/Province").toString().trim();
			String org_diff_mailAddr = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Does this Organization have a Different mailing Address?").toString().trim();
			String org_another_Addr = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Does this Organization have another address?").toString().trim();
			String  organization_name = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Organization Name").toString().trim()+timeNow;
			String dateLastVerified = treemap.get("Grantee Portal_BOARD LIST_NA_Date Last Verified").toString().trim().replaceAll("/", "-");
			
			String preferredLang = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Preferred Language").toString().trim();
			String ssn = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Social Security Number or ITIN").toString().trim();
			String dob = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Date of Birth*").toString().trim();
			String pob = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Place of Birth*").toString().trim();
			String country_citi = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_Country of Citizenship*").toString().trim();
			String usVisa = treemap.get("Grantee Portal_ORGANIZATION INFORMATION_NA_US Visa Type (if applicable)").toString().trim();
			String granteeType = treemap.get("Request Card_REQUEST OVERVIEW_Organization Name (Add New)_Grantee Type").toString().trim();
			
			String rgi_regNo = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_FCRA Registration Number").toString().trim();
			String rgi_bankAccNo = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_FCRA Bank Account Number").toString().trim();
			String rgi_regEffDate = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_FCRA Registration Effective Date").toString().trim();
			String rgi_regExpDate = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_FCRA Registration Expiration Date").toString().trim();
			String rgi_StAddr = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_Street Address").toString().trim();
			String rgi_StAddr2 = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_Street Address2").toString().trim();
			String rgi_city = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_City").toString().trim();
			String rgi_state = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_State").toString().trim();
			String rgi_posCode = treemap.get("Grantee Portal_REGIONAL OFFICE INFORMATION (if applicable)_NA_Postal Code").toString().trim();
			
			String bank_acctNo = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Bank Account Number").toString().trim();
			String bank_dateLast = treemap.get("Grantee Portal_BANKING INFORMATION_Verification By_Date Last Verified").toString().trim();
			String bank_verifiedBy = treemap.get("Grantee Portal_BANKING INFORMATION_Verification By_Verified By").toString().trim()+timeNow;
			String bank_benBankType = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Beneficiary Bank Type").toString().trim();
			String bank_fullNameInBankAcct = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Full Name on Bank Account").toString().trim();
			String bank_corr_acctNo = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Bank Account Number").toString().trim();
			String bank_fullName = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Full Name of Bank/Institution").toString().trim();
			String bank_swift_code = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Swift Code").toString().trim();
			String bank_typeOfAcct = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Type Of Account").toString().trim();
			
			String bank_streetAdd = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Street Address").toString().trim();
			String bank_streetAdd2 = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Street Address 2").toString().trim();
			String bank_city = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_City").toString().trim();
			String bank_country = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Country").toString().trim();
			String bank_state = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_State/Province").toString().trim();
			String bank_zipCode = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Zip Code").toString().trim();
			String bank_phone = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Phone").toString().trim();
			String bank_ABANo = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_ABA Number").toString().trim();
			String bank_corr_BankType = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Correspondent Bank Type").toString().trim();
			String bank_fullName_corrBank = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Full Name of Correspondent Bank").toString().trim();
			String bank_corr_BAN = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Correspondent Bank Account Number").toString().trim();
			String bank_corrABA = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Correspondent ABA Number").toString().trim();
			String bank_corrSwift = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Correspondent Bank SWIFT").toString().trim();
			String bank_corr_Street = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Bank Street Address").toString().trim();
			String bank_corr_Street2 = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Bank Street Address 2").toString().trim();
			String bank_corr_city = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_City").toString().trim();
			String bank_corr_country = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Country").toString().trim();
			String bank_corr_state = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_State").toString().trim();
			String bank_corr_zip = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Zip Code").toString().trim();
			String bank_corr_phone = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent (Intermediary) Bank_Phone").toString().trim();
			String bank_corr2_fullNameOnBank = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_Full Name of Bank").toString().trim();
			String bank_corr2BAN = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_Account Number").toString().trim();
			String bank_corr2ABA = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_ABA Number").toString().trim();
			String bank_corr2City = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_City").toString().trim();
			String bank_corr2State = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_State").toString().trim();
			String bank_corrbank_FullName = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent Bank_Full Name of Correspondent Bank").toString().trim();
			String bank_corrbank_BAN = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent Bank_Correspondent Bank Account Number").toString().trim();
			String bank_corrbank_ABA = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent Bank_ABA Number").toString().trim();
			
			String bank_corrbank_city = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent Bank_City").toString().trim();
			String bank_corrbank_State = treemap.get("Grantee Portal_BANKING INFORMATION_Correspondent Bank_State").toString().trim();
			String bank_corr2Inter_fullName = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_Full Name of Bank").toString().trim();
			String bank_corr2Inter_BAN = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_Account Number").toString().trim();
			String bank_corr2Inter_ABA = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_ABA Number").toString().trim();
			String bank_corr2Inter_city = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_City").toString().trim();
			String bank_corr2Inter_State = treemap.get("Grantee Portal_BANKING INFORMATION_Second Correspondent (Intermediary) Bank_State").toString().trim();
			
			String bank_fullName_China = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Full Name on Bank Account in Local Language (China only)").toString().trim();
			String bank_bankName_China = treemap.get("Grantee Portal_BANKING INFORMATION_Beneficiary (Grantee) Bank or Institution_Bank Name Local Language (China only)").toString().trim();
			String bank_ref_desc = treemap.get("Grantee Portal_BANKING INFORMATION_NA_Reference Description").toString().trim();
			
			if(exists(fluxobs.cookie_msg("Okay")))
				click(fluxobs.cookie_msg("Okay"), "Click Okay", "click Okay", false);
			typeAndEnter(fluxobs.txtbox_granteeDetails("Grantee Legal Name"), Grantee_leagalName,"Grantee legal name", "Avinash in Grantee legal name field", true);
			if(granteeType.toLowerCase().equals("organization"))
				typeAndEnter(fluxobs.txtbox_granteeDetails("Acronym"), Grantee_Acronym, "Acronym","ASA in Acronym field", true);
			Thread.sleep(3000);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_street_address-li"),Grantee_organization_street_address , "Organiztion Street Address","New york in Organization street Adress field", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_city-li"), Grantee_organization_city, "organization_city","New York in organization city field", true);
			selectByVisibleText(fluxobs.dropdownbyId("organization_geo_country_id"),country , "Country", true);
			Thread.sleep(5000);
			selectByPartialText(fluxobs.dropdown_valuesState(),state ,true, "state", true);
			Thread.sleep(2000);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_postal_code"),Grantee_organization_postal_code , "organization_postal_code","10055 in organization_postal_code field", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_phone"),Grantee_organization_phone , "Organization phone number","8790855495 in organization phone number field", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_email"),Grantee_organization_email , "organization email","abc@gmail.com in organization email field", true);
			//type_input(fluxobs.txtbox_granteedetails("organization_fax"),Grantee_organization_url , "organization fax","10055 in organization fax field", true);
			typeAndEnter(fluxobs.txtbox_granteedetails("organization_url"), Grantee_organization_fax, "organization url","www.avinash.com in organization url field", true);
			selectByVisibleText(fluxobs.dropdownbyId("organization_mailing_yn"),org_diff_mailAddr , "Organization different Mailing Address", true);
			selectByVisibleText(fluxobs.dropdownbyId("organization_grant_address_yn"),org_another_Addr , "Organization Another Address", true);
			
			if(granteeType.toLowerCase().equals("organization"))
			{
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_year_founded"),Grantee_organization_year_founded , "organization year founded date","2017 in organization year founded date field", true);
				typeAndEnter(fluxobs.txtarea_granteedetails("organization_grantee_description"),Grantee_organization_grantee_description , "organization grantee description","text 123 in organization grantee description field", true);
			}
			
			
			selectByVisibleText(fluxobs.dropdownbyId("organization_preferred_language"),preferredLang , "Organization Another Address", true);
			//type_input(fluxobs.txtbox("organization_tax_501c4_affiliate"),org_501c , "501(c)(4) affiliate, if applicable","501(c)(4) affiliate, if applicable", true);
			
			if(granteeType.toLowerCase().equals("individual"))
			{
				mouseover(fluxobs.txtbox_granteedetails("organization_individual_visa_type-li"),"US Visa type", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_individual_ssn-li"),ssn , "Social Security Number","Social Security Number", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_individual_dob-li"),dob , "Date Of Birth","Date Of Birth", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_individual_place_of_birth-li"),pob , "Place Of Birth","Place Of Birth", true);
				selectByVisibleText(fluxobs.dropdownbyId("organization_individual_country_of_citizenship"),country_citi , "Country of Citizenship", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_individual_visa_type-li"),usVisa , "US Visa Type","US Visa Type", true);
			}
			
			if(granteeType.toLowerCase().equals("organization"))
			{
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_fy_month"),Grantee_organization_fy_month , "organization month","6 in organization month field", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("organization_fy_date"), Grantee_organization_fy_date, "organization date","20 in organization date field", true);
				int ok_size=driver.findElements(fluxobs.drpdown_field_Overseas_NA("organization_overseas_office_list-li-5")).size();
				
				driver.findElements(fluxobs.drpdown_field_Overseas_NA("organization_overseas_office_list-li-5")).get(ok_size-1).click();
		
			    //click(fluxobs.drpdown_field_Overseas_NA("organization_overseas_office_list-li-5"), "select Overseas Organization","");
				//JSClick(fluxobs.drpdown_field_Overseas_NA("organization_overseas_office_list-li-5"), "select Overseas Organization");
				click(fluxobs.btn_nexttree_OS(), "Click next", "click next", true);
				Thread.sleep(2000 );
			}
	
			// Selecting contact details
		//	selectByVisibleText(fluxobs.drpdown_field("organization_overseas_office_yn"), "No", "select Overseas Organization"); 
			
		//	selectByVisibleText(fluxobs.drpdown_field_Overseas("organization_overseas_office_list-li-5"), "Not Applicable", "select Overseas Organization");
	
	
			
	
			mouseover(fluxobs.txt_Fieldsgrantee("ORGANIZATION DOCUMENTS"),"Open the contact details", true);
			if(granteeType.toLowerCase().equals("organization"))
			{
				click(fluxobs.txt_Fieldsgrantee("CONTACTS"),"Open the contact details", "open the contact details", true);
				Thread.sleep(2000);
				String name =grantee_fName+" "+grantee_Lname; 
				System.out.println(name);
				selectByVisibleText(fluxobs.select_contactfields("organization_secondary_user"),name, "Organization secondary user", true);
			}
			
			// Adding board list deatils
			if(granteeType.toLowerCase().equals("organization"))
			{
				mouseover(fluxobs.txt_Fieldsgrantee("BOARD LIST"),"Open the Board list", false);
				click(fluxobs.txt_Fieldsgrantee("BOARD LIST"), "Open the Board list","open the board list", false);
				selectByVisibleText(fluxobs.select_contactfields("organization_last_confirmed_by"), grantee_fName+" "+grantee_Lname, "Organization last confirmed by", false);
				if(granteeType.toLowerCase().equals("organization"))
					typeAndEnter(fluxobs.startDate("organization_confimed_date"), dateLastVerified, "organization confirmed date", "organization confirmed date",false);
				
				JSClick(fluxobs.btn_addBoardList(), "add board list", false);
				
				waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
				mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
				if(exists(fluxobs.drpdown_field("machine_model_prefix")))
				{
					waitForVisibilityOfElement(fluxobs.drpdown_field("machine_model_prefix"), "wait for modal", false);
					
					selectByVisibleText(fluxobs.drpdown_field("machine_model_prefix"), "Select Board Member Prefix", "select Prefix", false);
					
					
					typeAndEnter(fluxobs.txtbox_granteeDetails("*First Name"), "Alex","Board Member First Name", "select the board member first name", false);
					typeAndEnter(fluxobs.txtbox_granteeDetails("Middle Name"), "John","Board Member Middle Name", "select the board member middle name", false);
					typeAndEnter(fluxobs.txtbox_granteeDetails("*Last Name"), "Keith","Board Member Last Name", "select the board member last name", false);
					typeAndEnter(fluxobs.txtbox_granteeDetails("Suffix"), "AlexJK","Board Member Suffix", "select the board member Suffix", false);
					//selectByVisibleText(fluxobs.drpdown_field("machine_model_country_of_citizenship_select"), "United States", "select country");
					mouseover(fluxobs.txtbox_granteeDetails("*Organization"), "Mouse over on Organization details", false);
					typeAndEnter(fluxobs.txtbox_granteeDetails("*Job Title"), "director","Board Member", "select the board member role", false);
					typeAndEnter(fluxobs.txtbox_granteeDetails("*Organization"), "testorg","Board Member", "select the board member Org", false);
					
					Thread.sleep(3000);
					click(fluxobs.btn_windowSave("Add a Board List"), "Save button", "Click On Save button", false);
					Thread.sleep(5000);
				}
			}
			if(exists(fluxobs.close_btn()))
			{
				click(fluxobs.close_btn(), "Cancel the edited details","Cancel the edited details", true);
				Thread.sleep(5000);
			}
			click(fluxobs.txt_Fieldsgrantee("BANKING INFORMATION"), "Open the Banking Information Section","open the banking information section", true);
			click(fluxobs.btn_addBankingInfo(), "add Banking Info", "add Banking Info", true);
			
			waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			waitForVisibilityOfElement(fluxobs.txtbox_granteedetails("bank_account_confirmed_date-li"), "wait for modal", false);
			
			typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_confirmed_date-li"), bank_dateLast, "Date Last Verified", "Date Last Verified", true);
			selectByVisibleText(fluxobs.drpdown_field("bank_account_confirmed_by-li"), bank_verifiedBy, "Verified By", true);
			mouseover(fluxobs.drpdown_field("bank_account_institution_type-li"), "Mouse over on Beneficiary Bank Type", true);
			selectByVisibleText(fluxobs.drpdown_field("bank_account_institution_type-li"), bank_benBankType, "Beneficiary Bank Type", true);
			
			
			
			
			
			if(bank_benBankType.toLowerCase().equals("us bank"))
			{
				//Beneficiary Bank/Institution fields
				mouseover(fluxobs.txtbox_granteedetails("bank_account_account_name-li"), "Mouse over on Beneficiary Bank Type", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_name-li"), bank_fullName, "Full Name on bank Account", "Full Name on bank Account", true);
				
				if(country.toLowerCase().equals("china"))
				{
					mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name_local_language-li"), "Mouse over on Beneficiary Bank Type", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_name_local_language-li"), bank_fullName_China, "Full Name on bank Account", "Full Name on bank Account", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_bank_name_local_language-li"), bank_bankName_China, "Full Name of bank", "Full Name on bank", false);
				}
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), "Mouse over on Bank Account details", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_number-li"), bank_acctNo, "Bank Acct No", "Bank Acct No", true);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), "Mouse over on Bank Acct Name", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), bank_fullName, "bank_account_bank_name", "bank_account_bank_name", true);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_domestic_wire_aba_routing-li"), "Mouse over on ABA number", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_domestic_wire_aba_routing-li"), bank_ABANo, "ABA No", "ABA No", true);
				mouseover(fluxobs.dropdownbyId("bank_account_geo_state_id"), "Mouse over on State", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_street_address-li"), bank_streetAdd, "Street Address1", "Street Address1", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_street_address2-li"), bank_streetAdd2, "Street Address2", "Street Adress2", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_city-li"), bank_city, "City", "City", true);
				selectByVisibleText(fluxobs.dropdownbyId("bank_account_geo_country_id"), bank_country, "Country",false);
				Thread.sleep(3000);
				selectByPartialText(fluxobs.dropdownbyId("bank_account_geo_state_id"), bank_state, true, "State/Province",false);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_phone-li"), "Mouse over on Phone", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_postal_code-li"), bank_zipCode, "Postal Code", "Postal Code", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_phone-li"), bank_phone, "Phone", "Phone", false);
				
				//Second Correspondent (Intermediary) Bank
				//mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary2_state-li"), "Mouse over on Swift Code", true);
				//typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_bank_name-li"), bank_corr2_fullNameOnBank, "Bank correspndent2 full name on bank", "Bank correspndent fullname on bank", true);
				//typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_account_number-li"), bank_corr2BAN, "Bank correspndent2 Bank acct no", "Bank correspndent2 bank acct no", false);
				//typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_wire_aba_routing-li"), bank_corr2ABA, "Bank correspndent2 ABA", "Bank correspndent2 ABA", true);
				//typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_city-li"), bank_corr2City, "Bank correspndent2 city", "Bank correspndent2 city", true);
				//typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_state-li"), bank_corr2State, "Bank correspndent state", "Bank correspndent state", true);
				
				
				
			}
			else if(bank_benBankType.toLowerCase().equals("non-us bank"))
			{
				//Beneficiary Bank/Institution fields
				mouseover(fluxobs.txtbox_granteedetails("bank_account_account_name-li"), "Mouse over on Beneficiary Bank Type", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_name-li"), bank_fullName, "Full Name on bank Account", "Full Name on bank Account", true);
				
				if(country.toLowerCase().equals("china"))
				{
					mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name_local_language-li"), "Mouse over on Beneficiary Bank Type", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_name_local_language-li"), bank_fullName_China, "Full Name on bank Account", "Full Name on bank Account", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_bank_name_local_language-li"), bank_bankName_China, "Full Name of bank", "Full Name on bank", false);
				}
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), "Mouse over on Bank Account details", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_number-li"), bank_acctNo, "Bank Acct No", "Bank Acct No", true);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), "Mouse over on Bank Acct Name", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), bank_fullName, "bank_account_bank_name", "bank_account_bank_name", true);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_foreign_wire_beneficiary_bank_swift-li"), "Mouse over on Swift Code", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_foreign_wire_beneficiary_bank_swift-li"), bank_swift_code, "Swift Code", "Swift Code", true);
				mouseover(fluxobs.drpdown_field("bank_account_foreign_wire_beneficiary_type_of_account-li"), "Mouse over on Type Of Acct", true);
				selectByVisibleText(fluxobs.drpdown_field("bank_account_foreign_wire_beneficiary_type_of_account-li"), bank_typeOfAcct, "Type Of Account",true);
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_city-li"), "City", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_street_address-li"), bank_streetAdd, "Street Address1", "Street Address1", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_street_address2-li"), bank_streetAdd2, "Street Address2", "Street Adress2", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_city-li"), bank_city, "City", "City", true);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_phone-li"), "Phone", false);
				selectByVisibleText(fluxobs.dropdownbyId("bank_account_geo_country_id"), bank_country, "Country",false);
				Thread.sleep(3000);
				selectByPartialText(fluxobs.dropdownbyId("bank_account_geo_state_id"), bank_state, true, "State/Province",false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_postal_code-li"), bank_zipCode, "Postal Code", "Postal Code", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_phone-li"), bank_phone, "Phone", "Phone", false);
				
				
				
				//Correspondent (Intermediary) Bank
				mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary_street_address-li"), "Mouse over on Swift Code", true);
				selectByVisibleText(fluxobs.drpdown_field("bank_account_intermediary_institution_type-li"), bank_corr_BankType, "select correspndent bank Type",true);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_foreign_wire_intermediary_bank_name-li","2"), bank_fullName_corrBank, "Full Name of Correspondent Bank", "Full Name of Correspondent Bank", true);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_account_number-li","2"), bank_corr_BAN, "Correspondent Bank Account Number", "Correspondent Bank Account Number", false);
				
				if(bank_corr_BankType.toLowerCase().equals("non-us bank"))
					typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_foreign_wire_intermediary_bank_swift-li","1"), bank_corrSwift, "Correspondent Bank Swift", "Correspondent Bank Swift", true);
				else 
					typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_bank_code-li","2"), bank_corrABA, "Correspondent Bank ABA", "Correspondent Bank ABA", true);
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary_postal_code-li"), "Bank correspndent postal code", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary_street_address-li"), bank_corr_Street, "Bank correspndent city Street Address", "Bank correspndent city Street Address", false);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary_phone-li"), "Mouse over on Phone", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary_street_address2-li"), bank_corr_Street2, "Bank correspndent city Street Address 2", "Bank correspndent city Street Address 2", false);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_city-li","2"), bank_corr_city, "Bank correspndent city", "Bank correspndent city", true);
				if(bank_corr_BankType.toLowerCase().equals("non-us bank"))
					selectByVisibleText(fluxobs.drpdown_field("bank_account_intermediary_geo_country_id-li"), bank_corr_country, "correspndent country",true);				
				else
					typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_geo_state_id_input-li","1"), bank_corr_state, "Correspondent Bank State", "Correspondent Bank State", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary_postal_code-li"), bank_corr_zip, "Bank correspndent postal code", "Bank correspndent postal code", false);
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary_phone-li"), "Bank correspndent phone", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary_phone-li"), bank_corr_phone, "Bank correspndent phone number", "Bank correspndent phone number", false);
				
				
				//Second Correspondent (Intermediary) Bank
				if(bank_corr_BankType.toLowerCase().equals("non-us bank"))
				{
					mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary2_account_number-li"), "Mouse over on Acc no", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_bank_name-li"), bank_corr2_fullNameOnBank, "Bank correspndent2 full name on bank", "Bank correspndent fullname on bank", true);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_account_number-li"), bank_corr2BAN, "Bank correspndent2 Bank acct no", "Bank correspndent2 bank acct no", false);
					mouseover(fluxobs.txtbox_granteedetails("bank_account_intermediary2_state-li"), "Mouse over on State", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_wire_aba_routing-li"), bank_corr2ABA, "Bank correspndent2 ABA", "Bank correspndent2 ABA", true);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_city-li"), bank_corr2City, "Bank correspndent2 city", "Bank correspndent2 city", true);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_intermediary2_state-li"), bank_corr2State, "Bank correspndent state", "Bank correspndent state", true);
				}
				
			}

			if(bank_benBankType.toLowerCase().contains("non-bank institution"))
			{
				//Beneficiary Bank/Institution fields
				mouseover(fluxobs.txtbox_granteedetails("bank_account_account_name-li"), "Mouse over on Beneficiary Bank Type", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_name-li"), bank_fullName, "Full Name on bank Account", "Full Name on bank Account", true);
				
				if(country.toLowerCase().equals("china"))
				{
					mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name_local_language-li"), "Mouse over on Beneficiary Bank Type", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_name_local_language-li"), bank_fullName_China, "Full Name on bank Account", "Full Name on bank Account", false);
					typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_bank_name_local_language-li"), bank_bankName_China, "Full Name of bank", "Full Name on bank", false);
				}
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), "Mouse over on Bank Account details", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_account_number-li"), bank_acctNo, "Bank Acct No", "Bank Acct No", true);
				mouseover(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), "Mouse over on Bank Acct Name", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_bank_name-li"), bank_fullName, "bank_account_bank_name", "bank_account_bank_name", true);
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_city-li"), "Mouse over on City", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_street_address-li"), bank_streetAdd, "Street Address1", "Street Address1", true);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_street_address2-li"), bank_streetAdd2, "Street Address2", "Street Adress2", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_city-li"), bank_city, "City", "City", true);
				
				mouseover(fluxobs.txtbox_granteedetails("bank_account_phone-li"), "Mouse over on Phone", true);
				selectByVisibleText(fluxobs.dropdownbyId("bank_account_geo_country_id"), bank_country, "Country",false);
				Thread.sleep(3000);
				selectByPartialText(fluxobs.dropdownbyId("bank_account_geo_state_id"), bank_state, true, "State/Province",false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_postal_code-li"), bank_zipCode, "Postal Code", "Postal Code", false);
				typeAndEnter(fluxobs.txtbox_granteedetails("bank_account_phone-li"), bank_phone, "Phone", "Phone", false);
				
				
				//Correspondent Bank
				mouseover(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_account_number-li","1"), "Mouse over on correspondent bank acct no", false);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_foreign_wire_intermediary_bank_name-li","1"), bank_corrbank_FullName, "Bank correspndent2 full name on bank", "Bank correspndent fullname on bank", true);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_account_number-li","1"), bank_corrbank_BAN, "Bank correspndent2 Bank acct no", "Bank correspndent2 bank acct no", true);
				
				mouseover(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_state-li","1"), "Mouse over on State", false);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_bank_code-li","1"), bank_corrbank_ABA, "Bank correspndent2 ABA", "Bank correspndent2 ABA", true);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_city-li","1"), bank_corrbank_city, "Bank correspndent2 city", "Bank correspndent2 city", true);
				typeAndEnter(fluxobs.txtbox_granteedetailsEnabled("bank_account_intermediary_state-li","1"), bank_corrbank_State, "Bank correspndent state", "Bank correspndent state", true);
				
			}
				
	
			mouseover(fluxobs.text_Area("bank_account_epi_ref_description"), "Mouse over on Reference Description details", true);
			typeAndEnter(fluxobs.text_Area("bank_account_epi_ref_description"), bank_ref_desc, "Bank Reference Description", "bank Reference Description", true);
			click(fluxobs.btn_windowSave("Add a Bank Account"), "Save button", "Click On Save button", true);
	       
			Thread.sleep(2000);
			mouseover(fluxobs.txt_Fieldsgrantee("ORGANIZATION DOCUMENTS"), "ORGANIZATION DOCUMENTS", true);
			//
			click(fluxobs.txt_Fieldsgrantee("ORGANIZATION DOCUMENTS"), "Open the Board list","open the board list", true);
	
	
			JSClick(fluxobs.Org_Document(), "click add symbol to add documents", true);
			
			//waitForVisibilityOfElement(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			//mouseover(fluxobs.div_contains_class("modal new-modal"), "wait for modal", false);
			
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\FordFoundation\\Others\\DummyDocs\\dummy.docx");
			Thread.sleep(2000);
			waitForVisibilityOfElement(fluxobs.btn_witha("Start upload"), "Upload audit Financial Documents", false);
			waitForElementToBeClickable(fluxobs.btn_witha("Start upload"), "Upload audit Financial Documents", false);
			waitForElementPresent(fluxobs.btn_witha("Start upload"), "Upload audit Financial Documents", false);
			Thread.sleep(3000);
			click(fluxobs.btn_witha("Start upload"), "Upload the docs", "Upload the audit Fincancial Documents", true);
			//waitForVisibilityOfElement(fluxobs.file_upload_percent(), "Upload audit Financial Documents", false);
			Thread.sleep(5000);
			click(fluxobs.btn_uploadclose(), "Close button", "Click on Close button", true);
			Thread.sleep(3000);
			if(isAlertPresent()){
				
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Thread.sleep(3000);
			}
	
			click(fluxobs.btn_save(), "Save the contact details","Save the contact details of grantee", true);
			
			//Fill Regional Office Information section if country is India
			if(country.toLowerCase().equals("india"))
			{
				waitForElementPresent(fluxobs.btn_submit(),"Submit Button", false);
				Thread.sleep(3000);
				click(fluxobs.btn_edit(),"Edit the grantee details", "open the grantee filling form", true);
				waitForElementPresent(fluxobs.txt_granteeorgsearch(organization_name),organization_name, true);
				click(fluxobs.txt_Fieldsgrantee("REGIONAL OFFICE INFORMATION (if applicable)"), "Open Regional Office Information section","Open Regional Office Information section", true);
				waitForElementPresent(fluxobs.input_fields("organization_fcra_number"),"organization_fcra_number", true);
				typeAndEnter(fluxobs.input_fields("organization_fcra_number"), rgi_regNo,"Reg Number (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_fcra_bank_account_no"), rgi_bankAccNo,"Bank Acct No (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_fcra_effective_date"), rgi_regEffDate,"Eff Date (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_fcra_expiration_date"), rgi_regExpDate,"Exp Date (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_perm_street_address"), rgi_StAddr,"Street Address (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_perm_street_address2"), rgi_StAddr2,"Street Address 2 (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_perm_city"), rgi_city,"CIty (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_perm_state"), rgi_state,"State (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				typeAndEnter(fluxobs.input_fields("organization_perm_postal_code"), rgi_posCode,"Postal Code (Regional Office Information)", "Reg Number (Regional Office Information)", true);
				click(fluxobs.btn_save(), "Save the contact details","Save the contact details of grantee", true);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}

	}







	/**
	 * Method Name: filling_granteeReqdetails
	 * This method is used to fill in all the details by Grantee
	 * @throws Throwable
	 */
synchronized public void filling_granteeReqdetails (String orgName, String grantee_fName , String grantee_Lname) throws Throwable {

	// Entering all the credentials
	try
	{
		
		String org_stmt = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_*ORGANIZATION STATEMENT").toString().trim();
		String issue_stmt = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Issue Statement").toString().trim();
		String long_term_goal = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_LONG TERM GOAL").toString().trim();
		String med_term_outcomes = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Medium Term Outcomes").toString().trim();
		String nActivities = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Example of Activities").toString().trim();
		String nIndicators = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Indicators").toString().trim();
		String nPriorExp = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Prior Experience").toString().trim();
		String nCollbn = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_(Optional) Collaboration").toString().trim();
		
		String gBgtExpl = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Financials_Grant Budget Explanation").toString().trim();
		String gBgtAmt = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Financials_Grant Budget Amount").toString().trim();
		String suppTypes=treemap.get("Request Card_Support Type_NA_Support Type").toString().trim();
		String reqType=treemap.get("RequestType").toString().trim();
		String activities = ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Activities").toString().trim();
		
		String title_film=treemap.get("Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Title of Film/Media Project").toString().trim();
		String brief_film=treemap.get("Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Brief Synopsis of Film/Media Project").toString().trim();
		String chief_film=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Names and titles of chief personnel involved").toString().trim();
		String summ_film=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Please include a summary of your distribution plan").toString().trim();
		String summ_comp=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Brief summary of successfully completed").toString().trim();
		String reliblty=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Brief statement of reliability and accountability").toString().trim();
		
		String justFilmFlag=treemap.get("Request Card_Request  Special Category_NA_Just Films").toString().trim();
		String buildType = treemap.get("Request Card_Request Special Category_NA_Build type").toString().trim();
		
		String prog_med_term=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Programmatic Medium Term Outcomes").toString().trim();
		String inst_st_now=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_*INSTITUTIONAL STRENGTHENING - WHY NOW?").toString().trim();
		String inst_st_med_term=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Institutional Strengthening Medium-Term Outcomes").toString().trim();
		String prog_act=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Programmatic Activities").toString().trim();
		String prog_ind=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Programmatic Indicators").toString().trim();
		String inst_st_acts=ReadingExcel.getValueFromPartialKey(treemap,"What institutional strengthening activities will you undertake over this grant period?  What deliverables will result from these activities?").toString().trim();
		String ex_inst_st_acts=ReadingExcel.getValueFromPartialKey(treemap,"What are examples of institutional strengthening activities that you will undertake during the grant period to strengthen your institution?").toString().trim();
		String inst_st_ind=ReadingExcel.getValueFromPartialKey(treemap,"Grantee Portal_PROPOSAL INFORMATION_Proposal Narrative_Institutional Strengthening Indicators of Success").toString().trim();
		
		if(buildType.toLowerCase().contains("year"))
			buildType = buildType.split("[ ]")[0].replace("+", "").trim();
		
		
		
		
		if(exists(fluxobs.cookie_msg("Okay")))
			click(fluxobs.cookie_msg("Okay"), "Click Okey", "click Okey", false);
		waitForElementToBeClickable(fluxobs.txt_Fieldsgrantee("PROPOSAL INFORMATION"),"Open the PROPOSAL INFORMATION details", true);
		waitForElementPresent(fluxobs.txt_Fieldsgrantee("PROPOSAL INFORMATION"),"Open the PROPOSAL INFORMATION details", true);
		waitForVisibilityOfElement(fluxobs.txt_Fieldsgrantee("PROPOSAL INFORMATION"),"Open the PROPOSAL INFORMATION details", true);
		click(fluxobs.txt_Fieldsgrantee("PROPOSAL INFORMATION"),"Open the PROPOSAL INFORMATION details", "open the PROPOSAL INFORMATION details", true);
		
		waitForVisibilityOfElement(fluxobs.txt_Fieldsgrantee("Proposal Narrative"),"Open the PROPOSAL INFORMATION details", true);
		click(fluxobs.txt_Fieldsgrantee("Proposal Narrative"),"Open the Proposal Narrative details", "open the Proposal Narrative details", true);
		
		//Xpath is different for build and non-build
		if(!reqType.toLowerCase().equals("build"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"), "mouseover on org stmt",false);
			waitForVisibilityOfElement(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"),"Open the PROPOSAL INFORMATION details", true);
			btn_click(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"),"Open the Proposal Narrative details", "open the Proposal Narrative details", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"), org_stmt,"Organization Statement", true);
		}
		else if(reqType.toLowerCase().equals("build"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"), "mouseover on org stmt",false);
			waitForVisibilityOfElement(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"),"Open the PROPOSAL INFORMATION details", true);
			btn_click(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"),"Open the Proposal Narrative details", "open the Proposal Narrative details", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*ORGANIZATION STATEMENT"), org_stmt,"Organization Statement", true);
		}
		
		
		
		if( ( (!reqType.toLowerCase().equals("build")) && (suppTypes.toLowerCase().contains("project") || suppTypes.toLowerCase().contains("core") || (suppTypes.toLowerCase().contains("general") && suppTypes.toLowerCase().contains("project")) ) ) )
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*ISSUE STATEMENT"), "mouseover on Issue Statement",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*ISSUE STATEMENT"),"Click on Issue Statement", "Click on Issue Statement", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*ISSUE STATEMENT"), issue_stmt,"Issue Statement", true);
		}
		
		
		if(reqType.toLowerCase().equals("build"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING - WHY NOW?"), "mousehover on Institutional strengthing",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING - WHY NOW?"),"click on Institutional strengthing", "open the Proposal Narrative details", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING - WHY NOW?"), inst_st_now,"Institutional strengthing", true);
		}
		
		mouseover(fluxobs.txt_granteeProposalNarr("*LONG TERM GOAL"), "mouseover on long term goal",false);
		btn_click(fluxobs.txt_granteeProposalNarr("*LONG TERM GOAL"),"click on long term goal", "click on long term goal", true);
		typeAction(fluxobs.txt_granteeProposalNarr("*LONG TERM GOAL"), long_term_goal,"Long Term Goal", true);
		
		
		if(!reqType.toLowerCase().equals("build"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*MEDIUM TERM OUTCOMES"), "mouseover on medium term outcomes",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*MEDIUM TERM OUTCOMES"),"Click on Medium Term Outcomes", "Click on Medium Term Outcomes", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*MEDIUM TERM OUTCOMES"), med_term_outcomes,"Medium Term Outcomes", true);
		}
		
		if(reqType.toLowerCase().equals("build"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC MEDIUM TERM OUTCOMES"), "mouseover on Programming Med Term Outcomes",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC MEDIUM TERM OUTCOMES"),"Click on Med Term Outcomes", "Click on Med Term Outcomes", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC MEDIUM TERM OUTCOMES"), prog_med_term,"Med Term Outcomes", true);
			
			if(buildType.equals("4") || buildType.equals("5"))
			{
				mouseover(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING MEDIUM TERM OUTCOMES"), "mouseover on Institutional strengthening medium term outcomes",false);
				btn_click(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING MEDIUM TERM OUTCOMES"),"Click on Institutional strengthening Med Term Outcomes", "Click on Institutional strengthening Med Term Outcomes", true);
				typeAction(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING MEDIUM TERM OUTCOMES"), inst_st_med_term,"Institutional strengthening Med Term Outcomes", true);
			}
			mouseover(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC ACTIVITIES"), "mouseover on Programming Activities",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC ACTIVITIES"),"Click on Programming Activities", "Click on Programming Activities", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC ACTIVITIES"), prog_act,"Programming Activities", true);
			
			mouseover(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC INDICATORS"), "mouseover on Programming Indicators",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC INDICATORS"),"Click on Programming Indicators", "Click on Programming Indicators", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*PROGRAMMATIC INDICATORS"), prog_ind,"Programming Indicators", true);
			
			if(buildType.equals("1"))
			{
				mouseover(fluxobs.txt_granteeProposalNarr2("What institutional strengthening activities will you undertake over this grant period? What deliverables will result from these activities?"), "mouseover on institutional strengthening activities",false);
				btn_click(fluxobs.txt_granteeProposalNarr2("What institutional strengthening activities will you undertake over this grant period? What deliverables will result from these activities?"),"Click on institutional strengthening activities", "Click on institutional strengthening activities", true);
				typeAction(fluxobs.txt_granteeProposalNarr2("What institutional strengthening activities will you undertake over this grant period? What deliverables will result from these activities?"), inst_st_acts,"institutional strengthening activities", true);
			}
			if(buildType.equals("4") || buildType.equals("5"))
			{
				mouseover(fluxobs.txt_granteeProposalNarr2("What are examples of institutional strengthening activities that you will undertake during the grant period to strengthen your institution?"), "mouseover on examples of institutional strengthening activities",false);
				btn_click(fluxobs.txt_granteeProposalNarr2("What are examples of institutional strengthening activities that you will undertake during the grant period to strengthen your institution?"),"Click on examples of institutional strengthening activities", "click on examples of institutional strengthening activities", true);
				typeAction(fluxobs.txt_granteeProposalNarr2("What are examples of institutional strengthening activities that you will undertake during the grant period to strengthen your institution?"), ex_inst_st_acts,"examples of institutional strengthening activities", true);
			}
			if(buildType.equals("4") || buildType.equals("5"))
			{
				mouseover(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING INDICATORS OF SUCCESS"), "mouseover on Institutional strengthening indicators",false);
				btn_click(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING INDICATORS OF SUCCESS"),"Click on Institutional strengthening indicators", "Click on Institutional strengthening indicators", true);
				typeAction(fluxobs.txt_granteeProposalNarr("*INSTITUTIONAL STRENGTHENING INDICATORS OF SUCCESS"), inst_st_ind,"Institutional strengthening indicators", true);
			}
		}
		
		
		
		if((!(reqType.trim().toLowerCase().equals("build"))) && (suppTypes.toLowerCase().contains("general") || suppTypes.toLowerCase().contains("core") || (suppTypes.toLowerCase().contains("general") && suppTypes.toLowerCase().contains("project")) || (suppTypes.toLowerCase().contains("general") && suppTypes.toLowerCase().contains("core")) ))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*EXAMPLE OF ACTIVITIES"), "mouseover on example of activities",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*EXAMPLE OF ACTIVITIES"),"Enter Example of activities", "Enter Example of activities", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*EXAMPLE OF ACTIVITIES"), nActivities,"Example of activities", true);
		}
		if((!(reqType.trim().toLowerCase().equals("build"))) && suppTypes.toLowerCase().contains("project"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*ACTIVITIES"), "mouseover on activities",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*ACTIVITIES"),"Enter Activities", "Enter Activities", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*ACTIVITIES"), activities,"Activities", true);
		}
		if(!reqType.toLowerCase().equals("build"))
		{
			mouseover(fluxobs.txt_granteeProposalNarr("*INDICATORS"), "mouseover on indicators",false);
			btn_click(fluxobs.txt_granteeProposalNarr("*INDICATORS"),"Enter Indicators", "Enter Indicators", true);
			typeAction(fluxobs.txt_granteeProposalNarr("*INDICATORS"), nIndicators,"Indicators", true);
		}
		
		mouseover(fluxobs.txt_granteeProposalNarr("[OPTIONAL] COLLABORATION"), "mouseover on medium term outcomes",false);
		btn_click(fluxobs.txt_granteeProposalNarr("*PRIOR EXPERIENCE"),"Enter Prior Experience", "Enter Prior Experience", true);
		typeAction(fluxobs.txt_granteeProposalNarr("*PRIOR EXPERIENCE"), nPriorExp,"Prior Experience", true);
		btn_click(fluxobs.txt_granteeProposalNarr("[OPTIONAL] COLLABORATION"),"Enter Collaboration", "Enter Collaboration", true);
		typeAction(fluxobs.txt_granteeProposalNarr("[OPTIONAL] COLLABORATION"), nCollbn,"Collaboration", true);
		
		if(justFilmFlag.toLowerCase().equals("yes"))
		{
			btn_click(fluxobs.txt_granteeProposalNarr_FilmsTitle("Title of Film/Media Project"),"Title of Film/Media Project", "Title of Film/Media Project", true);
			typeAction(fluxobs.txt_granteeProposalNarr_FilmsTitle("Title of Film/Media Project"), title_film,"Title of Film/Media Project", true);
			
			btn_click(fluxobs.txt_granteeProposalNarr_justFilms("Brief Synopsis of Film/Media Project"),"Brief Synopsis of Film/Media Project", "Brief Synopsis of Film/Media Project", true);
			typeAction(fluxobs.txt_granteeProposalNarr_justFilms("Brief Synopsis of Film/Media Project"), brief_film,"Brief Synopsis of Film/Media Project", true);
			
			btn_click(fluxobs.txt_granteeProposalNarr_justFilms("Names and titles of chief personnel involved"),"Names and titles of chief personnel involved", "Names and titles of chief personnel involved", true);
			typeAction(fluxobs.txt_granteeProposalNarr_justFilms("Names and titles of chief personnel involved"), chief_film,"Names and titles of chief personnel involved", true);
			
			btn_click(fluxobs.txt_granteeProposalNarr_justFilms("Please include a summary of your distribution plan"),"Please include a summary of your distribution plan", "Please include a summary of your distribution plan", true);
			typeAction(fluxobs.txt_granteeProposalNarr_justFilms("Please include a summary of your distribution plan"), summ_film,"Please include a summary of your distribution plan", true);
			
			btn_click(fluxobs.txt_granteeProposalNarr_justFilms("Brief summary of successfully completed"),"Brief summary of successfully completed", "Brief summary of successfully completed", true);
			typeAction(fluxobs.txt_granteeProposalNarr_justFilms("Brief summary of successfully completed"), summ_comp,"Brief summary of successfully completed", true);
			
			btn_click(fluxobs.txt_granteeProposalNarr_justFilms("Brief statement of reliability and accountability"),"Brief statement of reliability and accountability", "Brief statement of reliability and accountability", true);
			typeAction(fluxobs.txt_granteeProposalNarr_justFilms("Brief statement of reliability and accountability"), reliblty,"Brief statement of reliability and accountability", true);
		}
		
		
		//Needed in invite stage. Hence commented
		/*mouseover(fluxobs.txt_Fieldsgrantee("Proposal Financials"), "mouseover on medium term outcomes",false);
		click(fluxobs.txt_Fieldsgrantee("Proposal Financials"),"Open the Proposal Financials details", "open the Proposal Financials details", true);
		Thread.sleep(3000);
		type_input(fluxobs.txt_granteeProposalFinAmt(), gBgtAmt,"Grant Budget Amount", "Grant Budget Amount", false);
		type_input(fluxobs.txt_granteeProposalFinExpl(), gBgtExpl,"Grant Budget Amount Explanation", "Grant Budget Amount Explanation", true);*/
		
		click(fluxobs.btn_save(), "Save the contact details","Save the contact details of grantee", true);
	}
	catch(Exception e)
	{
		throw e;
	}

}


}
