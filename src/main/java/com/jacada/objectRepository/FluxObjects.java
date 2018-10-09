/** Purpose: This class is the repository of all the objects(Web Elements) in Fluxx- Grantee and Grant Approval Workflow. 
* It contains all the xpaths of respective webelements in the complete Fluxx Grant Approval Workflow. 
* Author: Satyadeep Behera
*/

package com.jacada.objectRepository;

import org.openqa.selenium.By;

public class FluxObjects {
	
	// Title related xpaths

	public By titleWlcm_fordfoundation() {

		return By.xpath("//h1[contains(text(),'WELCOME TO THE FORD FOUNDATION')]");
	}
	
	public By titlegrant_Fordfoundation() {

		return By.xpath("//div[contains(@class,'form-element')]//img");
	}
	
	public By avantPlus() {

		return By.xpath(".//*[@id='dock_drag']");
	}
	
	public By fluxx_logo() {

		return By.xpath(".//*[@id='toggle_drawer']");
	}
	
	public By fluxx_settings() {

		return By.xpath(".//li[@data-balloon='Select Dashboard' and @class='selected stack']");
	}
	
	public By fluxx_legacy() {

		return By.xpath(".//a[@id='standard-dashboard' and text()='Legacy']/parent::li");
	}

	// Text related xpaths

	public By txt_username() {

		return By.xpath("//input[@id='user_session_login']");
	}

	public By txt_password() {

		return By.xpath("//input[@id='user_session_password']");
	}
	
	public By txt_NewOrgName (String neworgName) {

		return By.xpath("//u[text()='"+neworgName+"']");
	}
	
	public By txt_cardname (String cardname) {

		return By.xpath("//div[@class='titlebar']//span[text()='"+cardname+"']");
	}

	public By firstcard () {

		return By.xpath("//div[@id='card-table']//li[1]//a[@class='close-card']");
	}

	
	public By btn_Signin () {

		return By.xpath("//input[@type='submit']");
	}

	public By btn_addcard () {

		return By.xpath("//a[@class='link noop'][@title='Add Card']");
	}
	
	public By btn_dashboardline () {

		return By.xpath("//span[@class='lines']");
	}
	
	public By dashboard_arrow () {

		return By.xpath("//span[@class='opener']");
	}
	
	public By btn_newOrg () {

		return By.xpath("//a[contains(@class,'listing-toolbar')]//img");
	}
	
	public By logo_flux () {

		return By.xpath("//div[@id='logo']");
	}

	public By btn_new (String btn) {

		return By.xpath("//div[@id='quicklinks']//a[@title='"+btn+"']");
	}
	
	
	public By btn_closecard () {

		return By.xpath("//div[@id='card-table']//li[1]//a[@class='close-card']");
	}
	
	public By fluxx_logo_load() {

		return By.xpath("//a[@class='nav-logo']");
	}
	
	public By fluxxGrantee_logo_load() {

		return By.xpath("//div[@class='fluxx-logo']");
	}

	
	public By lnk (String lnkname) {

		return By.xpath("//a[@class='new-listing indent'][text()='"+lnkname+"']");
	}

	
	public By txt_profilename (String profile_name) {

		return By.xpath("//div[@class='select-transfer']//option[text()='"+profile_name+"']");
	}
	
	public By txtbox (String name) {

		return By.xpath("//input[contains(@id,'"+name+"')]");

	}
	
	public By txtbox_xpath (String name, String xpath) {

		return By.xpath("(//input[contains(@id,'"+name+"')])["+xpath+"]");

	}
	
	public By txtboxEq (String cardName,String name) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section//input[@id='"+name+"']");

	}
	
	public By txtbox_email () {

		return By.xpath("//input[contains(@id,'user_email')]");

	}
	
	public By selectLoginNeeded () {

		return By.xpath(".//*[@name='user[login_needed]']");

	}
	
	
	public By txtbox_field (String fieldname) {

		return By.xpath("//label[@class='label'][text()='"+fieldname+"']");

	}
	
	public By btn_AddNew (String cardName, String text) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//label[text()='"+text+"']/..//div/a");

	}
	
	public By btn_AddNew_Grant (String cardName, String text) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//h3[contains(text(),'"+text+"')]//a");

	}
	
	public By btn_AddNew_Grant1 (String cardName, String text, String num) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//h3[contains(text(),'"+text+"')]//a["+num+"]");

	}
	
	public By btn_AddNew_Doc (String cardName, String text, String num) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[contains(@class,'detail area')]//th[contains(text(),'"+text+"')]//a["+num+"]");

	}
	
	
	public By txt_AddNew () {

		return By.xpath("//div[@class='header']/span[contains(text(),'Add New')]");

	}
	
	
	public By btn_witha(String text) {

		return By.xpath("//a[text()='"+text+"']");

	}
	
	public By btn_status (String cardName, String status) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[contains(@class,'detail area')]//a[text()='"+status+"']");

	}
	
	public By fluxx_Status() {

		return By.xpath(".//div[text()='Status']/following-sibling::div//a[@title='View Workflow']");

	}
	
	public By probableClose() {

		return By.xpath("//h4[text()='probable']/../following-sibling::aside//span[2]//i[2]");

	}
	
	public By pending_CGalClose() {

		return By.xpath("//h4[text()='probable']/../following-sibling::aside//span[2]//i[2]");

	}
	
	
	
	public By btn_save () {

		return By.xpath("//li[@class='submit']//a[text()='Save']");

	}
	
	public By btn_windowSave (String text) {

		return By.xpath("//span[text()='"+text+"']/../following-sibling::div//a[text()='Save']");

	}
	
	public By btn_popupsave () {

		return By.xpath("//div[contains(@class,'modal')]//li[@class='submit']//a[text()='Save']");

	}
	
	public By btn_gm_verify () {

		return By.xpath("//a[@class='as-put with-note'][text()='GM Verify']");

	}
	
	public By txtboxname (String fieldvalue ) {

		return By.xpath("//input[@id='"+fieldvalue+"']");

	}
	
	public By dropdown_valuesCountry ()  {

		return By.xpath("//select[@id='organization_geo_country_id']");

	}
	
	
	public By header_card (String fname, String lName) {

		return By.xpath("//h1[contains(text(),'"+fname+"') and contains(text(),'"+lName+"')]");

	}

	public By arrow_down () {

		return By.xpath("//a[@id='user-tools']/following-sibling::div[@class='triangle-down']");

	}

	public By btn_Logout () {

		return By.xpath("//a[text()='Logout']");
	}
	
	public By btn_arrownext () {

		return By.xpath("//input[@class='select'][@type='button']");
	}
	
	public By txt_span (String txt) {

		return By.xpath("//span[text()='"+txt+"']");
	}
	
	public By txt_spanwithcontains (String txt) {

		return By.xpath("//span[contains(text(),'"+txt+"')]");
	}
	
	public By dashboard_select (String dashboard_name) {

		return By.xpath("//a[@class='dash_selected']//span[text()='"+dashboard_name+"']");
	}
	
	public By box_organizationsearch () {

		return By.xpath("//input[contains(@id,'organization_lookup')][contains(@class,'organization_lookup_select')]");
	}
	public By link_org () {

		return By.xpath("//span[@class='actions']//a[text()='link an org']");
	}
	
	public By txt_organizationsearchname (String name) {

		return By.xpath("//a[contains(@class,'ui-corner')][text()='"+name+"']");
	}
	
	public By btn_createRelationship () {

		return By.xpath("//li[@class='submit']/a");
	} 
	
	public By btn_requests (String name) {

		return By.xpath("//a[@class='new-detail'][@data-insert='after'][text()='"+name+"']");
	} 
	
	
	public By txtfield_orgsearch () {

		return By.xpath("//input[@id='q_q'][@autosave='OrganizationsController']");
	}
	
	public By txtfield_contactsearch () {

		return By.xpath("//input[@id='q_q'][@autosave='UsersController']");
	}
	
	public By txt_grantsorgsearch (String text) {

		return By.xpath("//a[@class='to-detail']//h2/strong[text()='"+text+"']");
		
	}
	
	public By txt_grantcontactsearch (String text) {

		return By.xpath("//a[@class='to-detail']//h2[contains(text(),'"+text+"')]");
		
	}
	
	public By link_linkaddRole () {

		return By.xpath("//h4[contains(text(),'Program')]//span[@class='actions']//a[@class='to-modal'][text()='add role']");
	}
	
	public By title_relationship () {

		return By.xpath("//div[@class='relationship-title']");
	}
	
	public By select_role () {

		return By.xpath("//select[@id='role_user_role_id']");
	}
	
	public By select_program () {

		return By.xpath("//select[@id='role_user_roleable_id']");
	}
	
	public By chckbox_ismoderator (String fName, String lName) {

		return By.xpath("//h1[contains(text(),'"+fName+"') and contains(text(),'"+lName+"')]/ancestor::article//input[@id='user_organization_can_edit']");
	}
	// External grantee portal related xpaths
	
	public By btns_granteeportal (String btnName) {

		return By.xpath("//span[@class='label'][text()='"+btnName+"']");
	}
	
	public By txt_contactinformation () {

		return By.xpath("//h3[contains(text(),'Contact Information')]");
	}
	
	public By txt_granteeorgsearch (String text) {

		return By.xpath("//strong//u[text()='"+text+"']");
		
	}
	
	public By txt_granteeReqsearch (String text) {

		return By.xpath("//strong//u[contains(text(),'"+text+"')]");
		
	}
	
	public By txt_granteeProposalNarr1 (String text) {

		return By.xpath("//label[text()='"+text+"']/following-sibling::textarea");
		
	}
	public By txt_granteeProposalNarr (String text) {

		return By.xpath("//label[text()='"+text+"']/following-sibling::div[contains(@class,'redactor-box')]//p");
		
	}
	
	public By txt_granteeProposalNarr2 (String text) {

		return By.xpath("//div[contains(text(),'"+text+"')]/parent::label/following-sibling::div[contains(@class,'redactor-box')]//p");
		
	}
	
	
	public By txt_granteeProposalNarr_justFilms (String text) {

		return By.xpath("//label[contains(text(),'"+text+"')]/following-sibling::div//p");
		
	}
	
	public By txt_granteeProposalNarr_FilmsTitle (String text) {

		return By.xpath("//label[contains(text(),'"+text+"')]/following-sibling::input");
		
	}
	
	public By txt_granteeProposalFinAmt () {

		return By.xpath("//td[text()='Year 1']/following-sibling::td/div[@class='amount']//input");
		
	}
	
	public By txt_granteeProposalFinExpl () {

		return By.xpath("//label[text()='Explanation']/following-sibling::textarea");
		
	}
	
	public By btn_edit () {

		return By.xpath("//ul[@class='buttons']//li//a[@class='to-self'][text()='Edit']");
		
	}
	
	public By txtbox_granteeDetails (String fieldName) {

		return By.xpath("//label[contains(text(),'"+fieldName+"')]/following-sibling::input");
		
	}
	
	public By txtbox_granteeDetailsEnabled (String fieldName) {

		return By.xpath("//label[contains(text(),'"+fieldName+"')]/following-sibling::input[@disabled='']");
		
	}
	
	public By phone_bank () {

		return By.xpath("//label[@for='bank_account_phone-5']/following-sibling::input");
		
	}
	
	public By input_fields (String txt) {

		return By.xpath("//label[@for='"+txt+"']/following-sibling::input");
		
	}
	
	public By txtbox_granteedetails (String idName) {

		return By.xpath("//li[contains(@id,'"+idName+"')]//input");
	}
	
	public By txtbox_granteedetailsEnabled (String idName, String xpathNo) {

		return By.xpath("(//li[contains(@id,'"+idName+"')]//input)["+xpathNo+"]");
	}
	
	public By multi_check (String id) {

		return By.xpath("//h4[text()='possible']/ancestor::header/following-sibling::section[@class='detail area']//select[@id='"+id+"']/following-sibling::div//select[@class='unselected']");
	}
			
	public By startDate_GTerms () {

		return By.xpath("//label[text()='Start Date']/following-sibling::input");
	}
	
	public By txtarea_granteedetails (String idName) {

		return By.xpath("//li[contains(@id,'"+idName+"')]//textarea");
	}
	
	public By txtarea_gpu () {

		return By.xpath(".//div[text()='Note for Approve']/following-sibling::form//textarea");
	}
	
	public By radiobutton (String button) {

		return By.xpath("//label[text()='"+button+"']//input");
	}
	
	
	public By dropdown_valuesState ()  {

		return By.xpath("//select[contains(@id,'organization_geo_state_id')]"); 
 
	}
	
	public By txt_Fieldsgrantee (String FieldName)  {

		return By.xpath("//h3[text()='"+FieldName+"']"); 

	}
	
	public By cookie_msg (String txt)  {

		return By.xpath("//div[@id='cookie-consent']//a[text()='"+txt+"']"); 

	}
	
	public By startDate (String txt)  {

		return By.xpath("//li[@id='"+txt+"']//input"); 

	}
	
	public By select_contactfields (String idName)  {

		return By.xpath("//li[@id='"+idName+"']/select"); 

	}
	
	public By date_selectdrp (String id)  {

		return By.xpath("//input[contains(@id,'"+id+"')]"); 

	}
	
	public By select_combobox (String id,String txt)  {

		return By.xpath("//select[@id='"+id+"']/following-sibling::div//option[contains(text(),'"+txt+"')]"); 

	}
	
	public By select_control (String id,String val)  {

		return By.xpath("//*[@id='"+id+"']//input[@value='"+val+"']"); 

	}
	
	////*[@id='grant_request_organization_overseas_list']//input[@value='>']
	
	public By date_selectdrp_byLabel (String lbltxt,String id)  {

		return By.xpath("//label[text()='"+lbltxt+"']/following-sibling::input[contains(@id,'"+id+"')]"); 

	}
	////
	
	public By date_selection (String date)  {

		return By.xpath("//a[contains(@class,'ui-state-default')][text()='"+date+"']"); 

	}
	
	public By btn_settings ()  {

		return By.xpath("//div[@class='settings']"); 

	}
	
	public By btn_close ()  {

		return By.xpath("//a[contains(@class,'modalClose')]"); 

	}
	
	public By btn_logoutextPortal ()  {

		return By.xpath("//div[@class='logout']"); 

	}
	
	public By btn_submit ()  {

		return By.xpath("//li//a[contains(text(),'Submit')]"); 

	}
	
	public By btn_submit_grantee (String state)  {

		return By.xpath("//ul[@data-state='"+state+"']//a[contains(text(),'Submit')]"); 

	}
	
	public By txtarea_comment ()  {

		return By.xpath("//textarea[@class='prompt-input']"); 

	}
	
	public By btn_Ok ()  {

		return By.xpath("//li//a[@class='ok-button']"); 

	}
	
	// Grantee creation new Xpahts...
	
	public By newRequestbutton (String cardName)  {

		return By.xpath("//h4[text()='"+cardName+"']/../preceding-sibling::span"); 

	}
	
	public By startDateTBD (String option)  {

		return By.xpath("//input[contains(@id,'grant_request_grant_begins_at')]/parent::label[text()='"+option+"']"); 

	}
	
	public By newRequestOptions (String OptionName)  {

		return By.xpath("//li//a[text()='"+OptionName+"']"); 

	}
	
	public By granttitle_comments ()  {

		return By.xpath("//label[text()='Grant Title']/following-sibling::textarea"); 

	}
	
	public By granteecreate_DropDown (String id)  {

		return By.xpath("//label[@for='"+id+"']/..//select[contains(@name,'grant_request')]"); 

	}
	
	public By granteecreate_DropDown_Card (String cardName,String id)  {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//label[@for='"+id+"']/..//select[contains(@name,'grant_request')]"); 

	}
	/*
	public By btn_AddNews () {

		return By.xpath("//a[contains(@class,'listing-toolbar')]//img");
	}*/
	   
	public By btn_ui (String buttonName) {

		return By.xpath(".//a[text()='"+buttonName+"']");

	}
	
	public By footer_btn (String cardName, String btn) {

		return By.xpath(".//h4[text()='"+cardName+"']/ancestor::header/parent::li//footer//a[text()='"+btn+"']");

	}
	
	public By close_btn () {

		return By.xpath("//div[contains(@class,'modal new-modal')]//ul[@class='controls']//a[@aria-label='Close']");

	}
	
	public By new_card (String buttonName) {

		return By.xpath(".//a[text()='"+buttonName+"']/preceding-sibling::span//a");

	}
	
	public By superuser_edit (String fName) {

		return By.xpath("//h1[contains(text(),'"+fName+"')]/ancestor::article/following-sibling::footer//a[text()='Edit']");

	}
	
	
	
	public By btn_paramUi (String Orgname, String button) {

		return By.xpath("//strong//u[contains(text(),'"+Orgname+"')]/ancestor::article/following-sibling::footer//a[text()='"+button+"']");

	}
	
	public By drpdown_field (String id) {

		return By.xpath("//li[contains(@id,'"+id+"')]/select");

	}
	
	public By reqId (String cardName) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//strong//u");

	}
	
	public By drpdown_field_card (String cardName, String id) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//select[contains(@id,'"+id+"')]");

	}
	
	public By drpdown_field_Overseas_NA (String id) {

		return By.xpath(".//*[@id='"+id+"']//option[contains(text(),'Not Applicable')]");

	}
	
	
	
	public By drpdown_field_Overseas (String id) {

		return By.xpath(".//*[@id='"+id+"']//select");

	}
	
	
	
	public By drpdown_field2 (String id) {

		return By.xpath("//*[contains(@id,'"+id+"')]");

	}
	
	public By dropdownbyId (String id) {

		return By.xpath("//select[contains(@id,'"+id+"')]");

	}
	
	
	public By linkByTitle (String title) {
		return By.xpath("//a[contains(@title,'"+title+"')]//span//i");

	}
	
	public By fluxxSignOutLink (String title) {

		return By.xpath("//a[contains(@title,'"+title+"')]//a[@class='sign-out-link']//i");

	}
	
	
	
	
	
	public By btn_add () {

		return By.xpath("//ul[@id='dock_drag']//li[contains(@class,'plus')]//section//i");

	}
	

	public By btn_addBoardList () {

		return By.xpath("//h3[contains(text(),'Board List')]//img");

	}
	
	public By btn_addBankingInfo () {

		return By.xpath("//h3[contains(text(),'Bank Accounts')]//img");

	}
	
	public By btn_closemark () {

		return By.xpath("//ul[@id='dock_drag']//span[@class='new-card-button']//i");

	}
	
	public By search () {

		return By.xpath("//input[@class='search-list']");

	}
	
 	public By txt_withH2contains (String text) {

		return By.xpath("//h2[contains(text(),'"+text+"')]");

	}
 	
 	public By txt_withH2contains_card (String cardName, String txt) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[contains(@class,'listing area')]//h2[contains(text(),'"+txt+"')]");

	}
 	
 	public By txts_withH2contains (String text1, String text2) {

		return By.xpath("//h2[contains(text(),'"+text1+"') and contains(text(),'"+text2+"')]");

	}
 	
 	public By file_upload_percent () {

		return By.xpath("//div[contains(@class,'file_status') and contains(text(),'100%')]");

	}
 	
 	public By txt_withH4contains (String text) {

		return By.xpath("//h4[contains(text(),'"+text+"')]/ancestor::header//i[contains(@class,'card-icon')]");

	}
 	
 	public By txt_withH2containsStrng (String text) {

		return By.xpath("//h2//strong[contains(text(),'"+text+"')]");

	}
 	
 	public By txt_withucontainsStrng (String text) {

		return By.xpath("//strong//u[contains(text(),'"+text+"')]");

	}
 	
 	public By txt_withucontainsStrng_card (String card,String text) {

		return By.xpath("//h4[text()='"+card+"']/ancestor::header/following-sibling::section[contains(@class,'detail area')]//strong//u[contains(text(),'"+text+"')]");

	}
	
	public By Connectedbutton (String OrgName) {

		//return By.xpath("//strong//u[contains(text(),'"+OrgName+"')]/ancestor::section/preceding-sibling::header//span[contains(@class,'cdata')]//i[2]");
		return By.xpath("//strong//u[contains(text(),'"+OrgName+"')]/ancestor::section/preceding-sibling::header//span[contains(@class,'cdata')]");

	}
	
	public By tree_search () {
		return By.xpath("//input[@class='tree-search']");
		
	}
	
	public By Contacts_button (String OrgName) {

		return By.xpath("//strong//u[contains(text(),'"+OrgName+"')]/ancestor::section/following-sibling::aside//ul//li[1]");

	}
	
	public By txt_withspanContains (String text) {

		return By.xpath("//span[contains(text(),'"+text+"')]");

	}
	
	public By Modal_Button (String headerName, String SectionName, String ButtonName) {

		return By.xpath("//span[contains(text(),'"+headerName+"')]/ancestor::div[contains(@class,'modal new-modal area')]//div[@class='"+SectionName+"']//a[text()='"+ButtonName+"']");

	}
	
	public By Doc_link (String cardName, String secName, String docName) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[contains(@class,'detail area')]//h3[text()='"+secName+"']/following-sibling::div//a[text()='"+docName+"']");

	}
	
	public By txt_withspanContains_sibling (String text) {

		return By.xpath("//span[contains(text(),'"+text+"')]/preceding-sibling::span[@class='selector']");

	}
	
	public By select_Dashboard (String dashBoard) {

		return By.xpath("//aside[@class='dash_back']//span[text()='"+dashBoard+"']");

	}
	
	public By select_frm_Dashboard (String txt) {

		return By.xpath("//li[text()='"+txt+"']");

	}
	
	public By select_Template () {

		return By.xpath("//li[contains(text(),'Templates')]");

	}
	
	public By select_DashboardfrmTemp (String dashboard) {

		return By.xpath("//a[contains(text(),'"+dashboard+"')]");

	}
	
	public By linkText_card (String cardName, String text) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[contains(@class,'detail area')]//a[contains(text(),'"+text+"')]");

	}
	
	public By searchTextInCard (String searchTxt) {

		return By.xpath("//h4[text()='"+searchTxt+"']/ancestor::header/ancestor::li//input[@type='search']");

	}
	
	/*public By select_Dashboard () {

		return By.xpath("//aside[@class='dash_back']//span[text()='Default']");

	}*/
	public By select_stack () {

		return By.xpath("//li[@class='selected stack']//span[1]");

	}
	
	
	public By close_contactcard (String cardName) {

		return By.xpath("//h4[text()='"+cardName+"']/../following-sibling::aside//span[3]//i[1]");

	}
	
	public By close_contactcard_txtContains (String cardName) {

		return By.xpath("//h4[contains(text(),'"+cardName+"')]/../following-sibling::aside//span[3]//i[1]");

	}
	
	public By close_contactcard3_txtContains (String cardName) {

		return By.xpath("//h4[contains(text(),'"+cardName+"')]/../following-sibling::aside//span[3]//i[1]");

	}
	
	public By close_contactcard3 (String cardName) {

		return By.xpath("//h4[text()='"+cardName+"']/../following-sibling::aside//span[3]//i[1]");

	}
	public By close_contactcard2 (String cardName) {

		return By.xpath("//h4[text()='req - PROGRAM REV']/../following-sibling::aside//span[@data-balloon='Hide Details']//i[1]");

	}
	
	public By btn_WorkFlow (String OrgName) {

		return By.xpath("//strong//u[contains(text(),'"+OrgName+"')]/ancestor::article/following-sibling::footer//nav[@class='workflow-button']");

	}
	
	public By div_contains_class (String className) {

		return By.xpath("//div[contains(@class,'"+className+"')]");

	}
	
	public By btn_WorkFlowcGAL (String text) {

		return By.xpath("//h4[text()='"+text+"']/ancestor::header/following-sibling::section[@class='detail area']//nav[@class='workflow-button']");

	}
	
	public By txtArea_DetailArea (String cardName, String text) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//label[text()='"+text+"']/following-sibling::textarea");

	}
	
	public By txtArea_DetailTxtArea (String cardName, String text) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[contains(@class,'detail area')]//label[contains(text(),'"+text+"')]/following-sibling::textarea");

	}
	
	
	public By btn_CardAction (String cardname) {

		return By.xpath("//h4[text()='"+cardname+"']/ancestor::li//section[contains(@class,'listing area')]//span[@class='stack open-actions']");

	}
	
	public By chk_addnotqual (String cardname) {

		return By.xpath("//h4[text()='"+cardname+"']/ancestor::li//label[contains(text(),'Qualifier')]");

	}
	
	public By chk_lockFilter (String cardname) {

		return By.xpath("//h4[text()='"+cardname+"']/ancestor::li//input[@id='lock-card']");

	}
	
	public By btn_CardOpnFilter (String cardname) {

		return By.xpath("//h4[text()='"+cardname+"']/ancestor::li//section[contains(@class,'listing area')]//li[@class='open-filters']");

	}
	
	public By card_present (String cardname) {

		return By.xpath("//h4[text()='"+cardname+"']");

	}
	
	public By btn_FilterCondition (String con) {

		return By.xpath("//div[@class='filters area']//a[text()='"+con+"']");

	}
	
	public By multi_select (String cardName, String id, String text) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section[@class='detail area']//select[@id='"+id+"']//option[contains(text(),'"+text+"')]");

	}
	
	public By drpdwn_ChangValFilterCondition () {

		return By.xpath("//form[@id='new_form_element']//p[text()='Value:']/following-sibling::select");

	}
	
	public By drpdwn_SaveFilterCondition () {

		return By.xpath("//span[text()='Edit Condition']/parent::div[@class='header']/parent::div//div[@class='footer']//a[text()='Save Condition']");
	}
	
	public By drpdwn_SaveFilter () {

		return By.xpath("//div[@class='filters area']//div[@class='footer']//a[text()='Save']");
	}
	
	public By first_card (String text) {

		return By.xpath("//h4[@class='card_name title'][contains(text(),'"+ text +"')]/ancestor::li");

	}
	public By card_Name (String text) {

		return By.xpath("//h4[@class='card_name title'][contains(text(),'"+ text +"')]");

	}
	
	public By editWF_Note (String text) {

		return By.xpath("//a[@class='edit-workflow-note' and text()='"+text+"']");

	}
	
	public By btn_SendtoPendingCGal () {

		return By.xpath("//h4[text()='gal ready to send']/ancestor::header/following-sibling::section//a[text()='Send to Pending cGAL']");

	}
	
	public By btn_ApproveGAL () {

		return By.xpath("//h4[text()='agreement ltr rev']/ancestor::header/following-sibling::section//a[text()='Approve GAL']");

	}
	
	public By edit_button (String OrgName) {

		return By.xpath("//strong//u[contains(text(),'"+OrgName+"')]/ancestor::article/following-sibling::footer//a[text()='Edit']");

	}
	
	public By edit_buttonSuperUser (String FirstName, String lastName) {

		return By.xpath("//h1[contains(text(),'"+FirstName+"') and contains(text(),'"+lastName+"')]/ancestor::article/following-sibling::footer//a[text()='Edit']");

	}
	
	public By plusSign (String text) {

		return By.xpath("//a[@title='"+text+"']//i[2]");

	}
	
	public By btn_nexttree () {

		return By.xpath("//div[@class='tree-select']");

	}
	
	public By tree_controls (String controlName) {

		return By.xpath("//div[@class='controls']//input[@class='"+controlName+"']");

	}
	
	public By percentage_slider (String option, String i) {

		return By.xpath("//div[@class='slider-partial']//div[text()='"+option+"']/following-sibling::div[contains(@class,'slider ui-slider ui-slider-horizontal')]["+i+"]//a");

	}
	
	public By btn_nexttree_OS () {

		return By.xpath("//div[@class='controls']//input[@type='button'][1]");

	}
	
	public By txtbox_Funder (String sectionName) {

		return By.xpath("//h3[text()='"+sectionName+"']/following-sibling::div//th[text()='Funder']/ancestor::thead/following-sibling::tbody//tr[1]//td[2]//input");

	}
	
	public By txtbox_Amount (String sectionName) {

		return By.xpath("//h3[text()='"+sectionName+"']/following-sibling::div//th[text()='Funder']/ancestor::thead/following-sibling::tbody//tr[1]//td[3]//input");

	}
	
	public By audit_doucments (String text) {

		return By.xpath("//h3[text()='"+text+"']/..//th[contains(text(),'Documents')]//img");

	}
	
	
	public By audit_doucments2 (String document) {

		return By.xpath("//li[contains(text(),'"+document+"')]//a//img");

	}
	
	public By cGAL_Document () {

		return By.xpath("//th[contains(text(),'Internal Documents')]/..//a[@title='Add Document']");

	}
	
	public By Org_Document () {

		return By.xpath("//th[contains(text(),'Documents')]/..//a[@title='Add Document']");

	}
	public By Add_File () {

		return By.xpath("//input[@type='file']");

	}
	
	public By text_Area (String id) {

		return By.xpath("//textarea[contains(@id,'"+id+"')]");

	}
	
	public By text_AreaClass (String className) {

		return By.xpath("//textarea[@class='"+className+"']");

	}
	
	public By text_Area2 (String id) {

		return By.xpath("//textarea[contains(@id,'"+id+"')]/preceding-sibling::div");

	}
	
	public By grant_Budgetamount () {

		return By.xpath("//strong[contains(text(),'Grant Budget')]/../following-sibling::div[1]//div[@class='amount']//input");

	}
	
	public By btn_Pending () {

		return By.xpath("//*[@id='requests']/li[2]/a/span[1]");

	}
	
	public By btn_uploadclose () {

		return By.xpath("//span[@class='simplemodal-close']");

	}
	public By select_cGAL () {

		return By.xpath("//select[@id='doc_type_select']");

	}
	// for compliance action stage
	
	public By Comp_Organization (String OrgName) {
      
		return By.xpath("//h4[text()='req - COMPLIANce rev']/ancestor::header/following-sibling::section//h2[contains(text(),'"+OrgName+"')]");

	}
	
	public By Comp_Edit (String cardname) {
   
		return By.xpath("//h4[text()='"+cardname+"']/ancestor::header/following-sibling::section//a[text()='Edit']");

	}  
	
	public By Comp_Save (String cardname) {
		   
		return By.xpath("//h4[text()='"+cardname+"']/ancestor::header/following-sibling::section//a[text()='Save']");

	}  
	
	public By Comp_h4_Save (String cardname) {
		   
		return By.xpath("//h4[text()='"+cardname+"']/ancestor::header/following-sibling::section//a[text()='Save']");

	}
	public By Comp_wfbtn (String cardname, String button) {
      
		return By.xpath("//h4[text()='"+cardname+"']/ancestor::header/following-sibling::section//a[text()='"+button+"']");

	}
	
	public By getCardStatus (String cardname) {
	      
		return By.xpath("//h4[text()='"+cardname+"']/ancestor::header/following-sibling::section//div[text()='Status']/following-sibling::div//a");

	}
	
	public By getStatus (String cardname, String button) {
	      
		return By.xpath("//h4[text()='"+cardname+"']/ancestor::header/following-sibling::section//a");

	}
	
	public By generate_Save () {

		return By.xpath("//span[text()='Generate Document']/../following-sibling::div//a[text()='Save']");

	}
	public By Comp_grantFields (String cardName,String fieldName) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section//h3[text()='"+fieldName+"']");

	}
	
	public By Comp_grantFields2 (String cardName,String fieldName) {

		return By.xpath("(//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section//h3[text()='"+fieldName+"'])[2]");

	}
	
	public By OrgName_withCard (String cardName) {

		return By.xpath("//h4[text()='"+cardName+"']/ancestor::header/following-sibling::section//strong//u");

	}
	
	public By StatusUpload () {

		return By.xpath("//div[@class='plupload_file_status'][text()='100%']");

	}
	
	public By txtwithh3 (String text) {

		return By.xpath("//h3[text()='"+text+"']");

	}
}
