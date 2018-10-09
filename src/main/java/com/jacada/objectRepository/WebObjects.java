package com.jacada.objectRepository;

import org.openqa.selenium.By;

public class WebObjects implements CommonObjects{

	public By interacton_txtLinkName(String interactionName){
		return By.xpath("//input[@placeholder='Filter items...']");
	}

	public By interaction_lnkName(String interactionName){
		return By.xpath("//li[text()='"+interactionName+"']");
	}

	public By interaction_lnkSelectLanguage(String language){
		return By.xpath("//button/span[text()='"+language+"']");
	}

	public By interaction_lblPageTitle(){
		return By.xpath("//div[@class='jaa-content-panel']/span[@class='jaa-page-header-text']");
	}
	
	public By interaction_lblPageTitle2(String title){
	    String title1 = title;

	    if(title.charAt(0) == '\'') { 
	     title1=title1.substring(1);

	     if(title1.contains("\'"))
	      title1=title1.substring(0, title1.indexOf("\'"));

	     return By.xpath("//div[@class='jaa-content-panel']//span[contains(@class,'jaa-page-header-text')][contains(text(),'"+title1+"')]");
	    }
	    else if(title.contains("\'")){
	     title1=title1.substring(0, title1.indexOf("\'"));
	     return By.xpath("//div[@class='jaa-content-panel']//span[contains(@class,'jaa-page-header-text')][contains(text(),'"+title1+"')]");
	    }
	    else{
	     return By.xpath("//div[@class='jaa-content-panel']//span[contains(@class,'jaa-page-header-text')][text()='"+title1+"']");
	    }
	   }

	public By interaction_lblPageTitleNoAudit(String title){
		return By.xpath("//div[@class='page-title-container']//div[@class='page-title'][text()='"+title+"']"); 
	}

	public By interaction_lblPageTitle(String title){
		String title1 = title;

		if(title.charAt(0) == '\'') { 
			title1=title1.substring(1);

			if(title1.contains("\'"))
				title1=title1.substring(0, title1.indexOf("\'"));

			return By.xpath("//*[contains(@class,'header')][contains(text(),'"+title1+"')]");
		}
		else if(title.contains("\'")){
			title1=title1.substring(0, title1.indexOf("\'"));
			return By.xpath("//*[contains(@class,'header')][contains(text(),'"+title1+"')]");
		}
		else{
			return By.xpath("//*[contains(@class,'header')][text()='"+title1+"']");
		}
	}


	public By interaction_lblPageTitle(String title, String classname){		
		return By.xpath("//div[@class='"+classname+"']/span[contains(text(),'"+title+"')]");
	}
	
	public By interaction_lblPageTitle1(){
		return By.xpath("//div[contains(@class, 'page-title-container')]/div");
	}
	
	public By interaction_lblPageHeader(){
		return By.xpath("//p[@class='jaa-header-footer header']/p");
	}

	public By interaction_lblQuestion(){
		return By.xpath("//p[contains(@class,'jaa-section-label')]");
	}

	public By interaction_Poc1_txtTextArea(){
		return By.xpath("//textarea[@class='jaa-long-text text ui-widget-content ui-corner-all']");
	}

	public By interaction_lblPageFooter(){
		return By.xpath("//p[@class='jaa-header-footer footer']/p");
	}

	public By interaction_txtBreadcrumbName(String breadcrumbName){
		return By.xpath("//div[@class='jaa-breadcrumb']/div/div[text()='"+breadcrumbName+"']");
	}

	public By interaction_lblcontactUs(String contactUsLabel){

		if(contactUsLabel.equalsIgnoreCase("CONTACT US")||contactUsLabel.equalsIgnoreCase("NOUS CONTACTER"))
			contactUsLabel="contact-us";
		return By.xpath("//button[contains(@class,'"+contactUsLabel+"')]");
	}

	public By interaction_lblHelp(String helpLabel){
		if(helpLabel.equalsIgnoreCase("HELP")||helpLabel.equalsIgnoreCase("AIDE"))
			helpLabel="help-button";
		//return By.xpath("//div[contains(@class,'jaa-page-header-right')]//button[contains(@class,'"+helpLabel+"')]");SUSHMA

		return By.xpath("//div[contains(@class,'jaa-page-header')]//button[contains(@class,'"+helpLabel+"')]");
	}


	public By interaction_lblHelpTooltipMsg(String helpMsg){
		if(helpMsg.charAt(0) == '\'')  
			helpMsg=helpMsg.substring(1);
		if(helpMsg.contains("\'"))
			helpMsg=helpMsg.substring(0, helpMsg.indexOf("\'"));
		return By.xpath("//div[@id='powerTip'][contains(text(),'"+helpMsg+"')]");
	}

	//POC2

	public By interaction_txtInputValue(){
		return By.xpath("//input[@type='text']");
	}

	//POC3

	public By interaction_lblErrorHeader(String ErrorHeader){
		//return By.xpath("//span[text()='"+ErrorHeader+"']");SUSHMA
		return By.xpath("//div[contains(@style,'block')]/div/span[contains(text(),'"+ErrorHeader+"')]");
	}

	public By interaction_txtErrorMsg(String ErrorMsg){
		//return By.xpath("//div[@class='jaa-confirm-dialog ui-dialog-content ui-widget-content']/p[text()='"+ErrorMsg+"']");
		return By.xpath("//div[contains(@style,'block')]/div/p[contains(text(),'"+ErrorMsg+"')]");
	}

	public By interaction_btnPopup(String button){		
		//return By.xpath("//div/p[text()='"+ErrorMessage+"']/../following-sibling::div/div/button/span[text()='OK']");//SUSHMA
		return By.xpath("//div[contains(@style,'block')]//button/span[text()='"+button+"']");

	}

	//POC4

	public By interaction_lblAnswerHeader(){
		return By.xpath("//div[@class='jaa-content-panel']/span[text()='Answer']");			
	}

	public By interaction_txtAnswer(){
		return By.xpath("//p[@class='jaa-header-footer header']/p");
	}


	public By interaction_lblInnerContactUsPageTitle(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//h1[@class='ui-title'][text()='"+title+"']");
	}

	//S1T1
	public By interaction_btnNextStatus(String status){
		Boolean flag=true;
		if (status.equalsIgnoreCase("Enabled")){
			flag=false; 
		}
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[1][@aria-disabled='"+flag+"']");
	}

	public By interaction_btnBackStatus(String status){
		Boolean flag=true;
		if (status.equalsIgnoreCase("Enabled")){
			flag=false; 
		}
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[2][@aria-disabled='"+flag+"']");          
	}

	public By interaction_btnDoneStatus(String status){
		Boolean flag=true;
		if (status.equalsIgnoreCase("Enabled")){
			flag=false; 
		}
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div/button[1][@aria-disabled='"+flag+"']");
	}

	public By interaction_btnBack(){
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div//button[2]");
	}	

	public By interaction_btnNext(){
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div//button[1]");
	}

	public By interaction_btnDone(){
		return By.xpath("//div[@class='jaa-content-panel']/following-sibling::div//button[1]");
	}

	//S1T3
	public By interaction_clickChoice(int choice){			
		return By.xpath("//ul[@class='jaa-matrix']/li["+choice+"]//p");
	}

	public By interaction_clickCategeory(int choice){
		return By.xpath("//div[@class='jaa-click-to-continue-category ui-accordion ui-widget ui-helper-reset']//h3[contains(text(),"+choice+")]");
	}

	public By interaction_clickCategeoryChoice(String choice){
		return By.xpath("//span[@class='ui-button-text'][contains(text(),"+choice+")]");
	}

	//S1T5

	public By interaction_lblPageHeader(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//p[@class='jaa-header-footer header']//p[contains(text(),'"+title.trim()+"')]");
	}

	public By interaction_lblPageFooter(String title){
		if(title.contains("-")){
			String[] Title=title.split("-");
			title=Title[1];
			title=title.trim();

		}
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//p[@class='jaa-header-footer footer']//p[contains(text(),'"+title.trim()+"')]");
	}
	//S1T7
	public By interaction_lblInnerPageTitle(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//h1[text()='"+title+"']");
	}

	public By interaction_lblInnerPageHeader(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));

		return By.xpath("//div[contains(@class,'ui-page-active')]//p[contains(text(),'"+title+"')]");
	}

	public By interaction_btnInnerBack(){
		return By.xpath("//span[(@class='ui-button-text')][(text()='Préc.')or (text()='Back')]");
	}

	//S1T9
	public By interaction_ContactUsButtons(String Button){  

		if(Button.equalsIgnoreCase("Mail") || Button.equalsIgnoreCase("Courrier")) {  
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-mailto']/following-sibling::P");
		}
		else if(Button.equalsIgnoreCase("Chat") || Button.equalsIgnoreCase("Clavardage")){
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-chat']/following-sibling::P");
		}
		else if(Button.equalsIgnoreCase("Call") || Button.equalsIgnoreCase("Appeler")){
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-call']/following-sibling::P");
		}
		else if(Button.equalsIgnoreCase("Transfer")|| Button.equalsIgnoreCase("Transférer")){
			return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='jma-contactus-call-button ui-btn ui-btn-icon-right ui-icon-carat-r']//p[text()='"+Button+"']");
		}
		else
			return By.xpath("//div[@class='jaa-contact-us-icon jaa-icon-callback']/following-sibling::P");   

	}

	public By interaction_PopUpPageTitle(String Title){  

		return By.xpath("//div[@class='jaa-popover-title ui-state-active' and text()='"+Title+"']");
	}

	public By interaction_btnChatclose(){
		return By.xpath("//div[@class='jaa-full-mode-chat']/div/div[@class='jaa-popover-closebutton']");
	}

	//S1T11

	//S1T13
	public By interaction_userName(){
		return By.xpath("//input[@id='boxLoginUserName']");
	}
	public By interaction_password(){
		return By.xpath("//input[@id='boxLoginPassword']");

	}

	public By interaction_submit(){
		return By.xpath("//input[@id='btnLogin']");
	}

	//S1T15
	public By interaction_verifyOrderCollectionText(String attribute,String value){
		return By.xpath("//span[contains(text(),'"+attribute+"')]//ancestor::div[@class='jaa-table-wrapper']//div[contains(text(),'"+value+"')]");

	}

	public By Navigation_verifyOrderCollectionText(String attribute,String value) {
		return By.xpath("//span[text()='"+attribute+"']/ancestor::thead/following::tbody//tr/td/div[text()='"+value+"']");
	}
	//S1T17
	public By interaction_btnClose(String ErrorMessage){

		return By.xpath("//div[@class='jaa-simple-error-container']//div[contains(text(),'"+ErrorMessage+"')]");

	}
	//S1T18

	//S2T1
	public By interaction_btn(String button){

		if(button.equalsIgnoreCase("Next")||button.equalsIgnoreCase("Fini")||button.equalsIgnoreCase("Done")){
			button="right";
		}
		else if (button.equalsIgnoreCase("Save")){
			button="save";
		}
		else{
			button="left";}

		return By.xpath("//span[@class='ui-button-text']/ancestor::button[contains(@class,'"+button+"')]");     

	}

	public By interaction_btnBack_old(){

		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[2]");
	}

	public By interaction_btnNext_old(){
		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[1]");
	}

	//S2T6
	public By interaction_continuePageHeader(){
		return By.xpath("//p[@class='jaa-header-footer header']/following-sibling::p/span[contains(text(),'Continue')]");
	}
	public By interaction_continuePage(String value){
		return By.xpath("//table[@class='jaa-clickto-continue-container']//span[text()='"+value+"']");
	}

	public By interaction_ThankYouPage(){
		return By.xpath("//div[@class='jaa-end-page']");
	}

	//S2T18
	public By interaction_lblCallPopup(){
		return By.xpath("//span[@class='ui-dialog-title']");

	}

	public By interaction_btnCallPopup(String button){
		return By.xpath("//span[@class='ui-dialog-title']/../following-sibling::div//span[text()='OK']");

	}

	//S3T1

	public By interaction_question(String question){
		return By.xpath("//span[contains(text(),'"+question+"')]");
	}

	public By interaction_defaultQuestionsAnswer(String question,String answer){
		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::tr//input[contains(text(),'"+answer+"')]");//To modify
	}

	public By interaction_questionsAnswer(String question){
		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::tr//input");
	}
	public By interaction_multiQuestionsAnswer(String question){
		return By.xpath("//label[contains(text(),'"+question+"')]/ancestor::li//textarea");//To modify
	}

	public By interaction_getmonth(){
		return By.xpath("//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']");
	}

	public By interaction_arrowNext(){
		return By.xpath("//div[@id='ui-datepicker-div']//a[contains(@class,'next')]");
	}

	public By interaction_month(String month){
		return By.xpath("//div[@id='ui-datepicker-div']//span[contains(text(),'"+month+"')]");
	}

	public By interaction_year(String year){
		return By.className("ui-datepicker-year");	
	}

	public By interaction_day(String day){
		return By.xpath("//div[@id='ui-datepicker-div']//a[text()='"+day+"']");

	}

	public  By interaction_SelectLanguage(String language){
		return By.xpath("//div[@class]/label[text()='"+language+"']");///only for mobile
	}

	public  By interaction_photoUpload(){
		return null;
	}

	public  By interaction_Upload(){
		return null;
	}

	public  By interaction_PhotoUploadButtons(String Button){
		return null;
	}

	public  By interaction_photoUpload_addbutton(){
		return null;
	}

	//S3T3
	public By interaction_lblPageLabel(String Label){
		return By.xpath("//label[text()="+Label+"]");//need to verify
	}

	public By interaction_lblPlusImage(){
		return By.xpath("//p[@class='jaa-header-footer header']/p/img");
	}		

	public By interaction_lblPageHeaderText(String title){
		return By.xpath("//p[@class='jaa-header-footer header']//p/b[contains(text(),'"+title+"')]");
	}

	public By interaction_lblPhotoUploadLabel(String label){
		return By.xpath("//span[contains(text(),'"+label+"')]");
	}

	public By interaction_lblAnswer(String question, String answer){

		return By.xpath("//p[contains(@class,'jaa-section-label')]/ancestor::div/a/input[contains(value(),'"+answer+"')]");
	}

	public  By interaction_PhotoImages(){
		return By.xpath("//table[@class='jaa-uploadphoto-table']//tr[@class='jaa-uploadphoto-row ui-widget-content']");
	}


	public By interaction_photoCountText(int size){		
		return By.xpath("//table[@class='jaa-uploadphoto-table']//tr[@class='jaa-uploadphoto-row ui-widget-content']/td[contains(text(),'Photo "+size+"')]");

	}

	public By interaction_photoCharacterText(int size){		
		return By.xpath("//div[contains(text(),'"+(char)size+"')]/ancestor::a");

	}//need to verify

	//S4T1
	public By interaction_rbRadio(String button){

		return By.xpath("//div[contains(@class,'jaa-radio')]//label[contains(text(),'"+button+"')]");
	}
	
	public By interaction_rbRadioBtn(String button){

		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(text(),'"+button+"')]");
	}
	
	public By interaction_imglogoJacada(){
		return By.xpath("//div[@class='jaa-logo']");

	}
	public By interaction_lblquestionLabel(String questionLabel){
		return By.xpath("//p[@class='jaa-section-label']/span[contains(text(),'"+questionLabel+"')]");
	}

	public By interaction_breadCrumb(String page){
		if (page.equalsIgnoreCase("Home"))
			return By.xpath("//div[contains(@class,'jaa-breadcrumb-home')]");

		else
			return By.xpath("//div[contains(@class,'jaa-breadcrumb-element')][contains(text(),'"+page+"')]");
	}

	public By interaction_rbStatus(String button, Boolean selected){
		if(selected)
		{
			return By.xpath("//div[contains(@class,'jaa-radio')]//label[contains(text(),'"+button+"')]/preceding-sibling::a[@class='checked']");
		}
		else{
			return By.xpath("//div[contains(@class,'jaa-radio')]//label[contains(text(),'"+button+"')]/preceding-sibling::a[@class='']");
		}
	}

	public By interaction_swtActiveflipswitch(String option){
		return By.xpath("//span[@class='switch-button-label on'][text()='"+option+"']");
	}



	//S5T1	
	public By interaction_lblquestion(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}
		return By.xpath("//*[contains(text(),'"+question+"')]");
	}
	public By interaction_txtdefaultQuestionsAnswer(String question,String answer){
		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::tr//input[contains(text(),'"+answer+"')]");//To modify
	}
	public By interaction_txtquestionsAnswer(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}

		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::*//input");//Modified for S6T4 earlier it was tr
	}

	public By interaction_txtquestionsAnswerInMultiLine(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}

		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::tr//input");
	}

	public By interaction_txtmultiQuestionsAnswer(String question){		

		return(By.xpath("//span[contains(text(),'"+question+"')]/parent::*/following-sibling::*//textarea")); 
		//return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::tr//textarea");
	}
	public By interaction_lblgetmonth(){
		return By.xpath("//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']");
	}
	public By interaction_btnarrowNext(){
		return By.xpath("//div[@id='ui-datepicker-div']//a[contains(@class,'next')]");
	}
	public By interaction_lblmonth(String month){
		return By.xpath("//div[@id='ui-datepicker-div']//span[contains(text(),'"+month+"')]");
	}
	public By interaction_drpyear(String year){
		return By.className("ui-datepicker-year");	
	}
	public By interaction_btnday(String day){
		return By.xpath("//div[@id='ui-datepicker-div']//a[text()='"+day+"']");

	}

	public By interaction_swtflipswitch(String option){
		return By.xpath("//span[text()='"+option+"']");
	}

	public By interaction_lblChoice(String labelName){
		return By.xpath("//div[@class='jaa-choice-title'][contains(text(),'"+labelName+"')]");
	}

	public By interaction_lblflipswitch(String labelName){
		return By.xpath("//span[contains(@class, 'switch-button-label'][text()='"+labelName+"']");
	}

	public By interaction_btnflipswitch(String labelName){
		return null;
	}


	//S5T8

	public By interaction_drpChoice(){
		return By.xpath("//*[@id='undefined_flexselect']");
	}

	//S4T5
	public By interaction_lblHelp_Flat(String helpLabel){
		if(helpLabel.equalsIgnoreCase("HELP")||helpLabel.equalsIgnoreCase("AIDE"))
			helpLabel="help-button";			
		return By.xpath("//div[contains(@class,'jaa-page-header')]//button[contains(@class,'"+helpLabel+"')]");
	}
	//S5T1	

	public By interaction_imgChecker(String imageName){

		return By.xpath("//p/img[contains(@src,'"+imageName+"')]");
	}

	public By interaction_imgHeader(String title){
		return By.xpath("//p[@class='jma-header-footer header']//p[(text(),'"+title+"')]");
	}

	//S6T1
	public By interaction_drpMultipleChoice(String choice){			
		return By.xpath("//div[@id='undefined_flexselect_dropdown'][contains(@style,'block')]//li[contains(text(),'"+choice+"')]");
	}

	public By interaction_drpMultipleChoice(String question,String choice){	
		return By.xpath("//span[contains(text(),'"+question+"')]/parent::*/following-sibling::*//option[text()='"+choice+"']");
	}

	public By interaction_drpMultipleChoices(String question,String choice){	
		return By.xpath("//span[contains(text(),'"+question+"')]/following-sibling::*//option[text()='"+choice+"']");
	}

	public By interaction_drpMultipleChoiceDown(String question,String choice){

		return By.xpath("//span[contains(text(),'"+question+"')]/parent::*//option[text()='"+choice+"']");
	}
	//S6T4
	public By interaction_lblwebquestionAnswer(String question){
		return By.xpath("//span[contains(text(),'"+question+"')]/parent::*/following-sibling::*//input");
		//	return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::div//input");

	}
	public By interaction_txtMultichoice(String choice){
		return By.xpath("//span[contains(text(),'"+choice+"')]/parent::div//input");
		//span[contains(text(),'Autocomplete Multiple Choice Question')]/parent::td/following-sibling::td//input
	}

	//S6T5
	public By interaction_lblHelp_Breadcrumbs(String helpLabel ){
		if(helpLabel.equalsIgnoreCase("HELP")||helpLabel.equalsIgnoreCase("AIDE"))
			helpLabel="help-button";
		return By.xpath("//div[contains(@class,'jaa-page-header-right')]//button[contains(@class,'"+helpLabel+"')]");

	}

	//S6T6
	public By interacton_chkMultiChoice(String choice){
		return null; 
	}

	public  By interaction_imgPhotoImages(){
		return By.xpath("//table[@class='jaa-uploadphoto-table']//tr[@class='jaa-uploadphoto-row ui-widget-content']");
	}

	public By interaction_lblMultiQuestionType(String question){
		/*return By.xpath("//p[contains(@class,'jaa-section-label')]/ancestor::div/a/textarea");*/
		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::div//textarea");
	}

	public By interaction_lblQuestionType(String question){
		/*return By.xpath("//p[contains(@class,'jaa-section-label')]/ancestor::div/a/input");		*/	
		return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::div//input");                
	}

	public By interaction_Poc1_lblQuestion(){
		return By.xpath("//p[contains(@class,'jaa-section-label')]");
	}

	//S6T1

	public By interaction_lblPageHeaderImg(){

		return By.xpath("//p[@class='jaa-header-footer header']//img");
	}
	public By interaction_lblPageFooterImg(){

		return By.xpath("//p[@class='jaa-header-footer footer']//img");
	}

	public By interaction_rbRadioSelected(String button){

		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(@class,'ui-radio-on')][contains(text(),'"+button+"')]");//to be modified
	}

	//S6T7

	public By interaction_rbRadio(int index){

		return By.xpath("//div[contains(@class,'jaa-radio-button')]["+index+"]/label");
	}

	public By interaction_lnkLink(String linkName){

		return By.xpath("//span[text()='"+linkName+"']/ancestor::button[contains(@class,'jaa-clickto-continue_button')]");
	}


	public By interaction_lnkDateLink(int index){

		return By.xpath("//ul[@class='jaa-matrix']/li["+index+"]//p");
	}

	public By interaction_verifyNumCollectionText(int index){
		return By.xpath("//div[@class='jaa-content-section']//li["+index+"]");

	}

	public By interaction_chkMultipleChoiceQuestionsCheckBox(int index){		
		return By.xpath("//div[@class='jaa-collection-dispaly-simple-item']["+index+"]//label");

	}


	//S7T1
	public By interaction_txtAppSearch(){
		return By.xpath("//input[@placeholder='Name or Description']");
	}
	public By interaction_AppSearch(){
		//return By.xpath("//span[@title='Search']");
		return By.xpath("//span[contains(@class,'ui-icon-search')]");
	}
	public By interaction_AppInteractionLoading(){
		return By.xpath("//div[@class='jaa-folders-breadcrumb']//div[contains(text(),'Search Results')]");
	}
	public By interaction_AppInteractions(String interaction){
		return By.xpath("//div[@class='jaa-folders-breadcrumb']/following-sibling::div[@class='jaa-interactions-container']//div[@class='jaa-label'][text()='"+interaction+"']");
	}
	public By interaction_tabInteraction(String interaction){
		return By.xpath("//li[contains(@class,'ui-state-active')]/a[text()='"+interaction+"']");
	}
	//S7T2
	public By interaction_AgentAppUser(String user){
		return By.xpath("//div[@data-dropdown='#user-menu-dropdown'][contains(text(),'"+user+"')]");
	}
	public By interaction_AgentAppUserName(){
		return By.xpath("//div[contains(@style,'block')]//input[@id='jaa-username']");
	}
	public By interaction_AgentAppPassword(){
		return By.xpath("//input[@id='jaa-password']");

	}

	public By interaction_AgentAppSubmit(){
		return By.xpath("//button[@class='jaa-login-form-login']");
	}
	public By interaction_AgentAppSaveInteractionButton(){
		return By.xpath("//span[@class='jaa-saved-title']");
	}
	public By interaction_AgentAppInteractionbutton(String button){
		return By.xpath("//span[@class='ui-button-text'][contains(text(),'"+button+"')]");
	}
	public By interaction_AgentAppSaveInteractionName(){
		return By.xpath("//input[@id='savedname']");
	}
	public By interaction_AgentAppUserActions(String action){
		return By.xpath("//div[@id='user-menu-dropdown']//li[contains(text(),'"+action+"')]");
	}
	public By interaction_AgentAppSavedInteractionPopup(String message){
		return By.xpath("//div[@id='saveInteraction']//div[contains(text(),'"+message+"')]");
	}
	public By interaction_AgentAppRecords(){
		return By.xpath("//div[@class='jaa-folders-breadcrumb']/following-sibling::div[@class='jaa-interactions-container']");
	}

	public By interaction_AgentApp_lblBreadcrumbName(String breadcrumbName){
		return By.xpath("//div[@class='jaa-folders-breadcrumb']/div[text()='"+breadcrumbName+"']");
	}

	public By interaction_AgentAppSavedInteractionPlayButton(String interaction){
		return By.xpath("//div[contains(text(),'"+interaction+"')]/ancestor::tr//div[@class='jaa-play-interaction-icon']");
	}
	public By interaction_AgentAppInteractionRestorePopupTitle(String message){
		return By.xpath("//span[contains(text(),'"+message+"')]");  //S3T1
	}
	public By interaction_AgentAppInteractionRestorePopupMessage(String message){
		return By.xpath("//p[contains(text(),'"+message+"')]");
	}
	public By interaction_AgentAppFooterVariables(String variable){
		return By.xpath("//td[contains(text(),'"+variable+":')]/following-sibling::td");
		//By.xpath("//td[contains(text(),'TxtVar1:')]/following-sibling::td[contains(text(),'c')]")
	}
	public By interaction_AgentAppPageVariables(String variable){
		return By.xpath("//div[@class='jaa-content-section']//li[contains(text(),'"+variable+"')]");
	}
	public By savedInteractionsMessage(){
		return By.xpath("//div[@class='jaa-saved-table']/div");
	}
	//S9T7
	public By interaction_UpgradeSavedInteractionHeader(String header){

		return By.xpath("//table[@class='jaa-saved-interactions-table']//th/span[contains(text(),'"+header+"')]");
	}
	public By interaction_UpgradeSavedInteractions(String values){
		return By.xpath("//table[@class='jaa-saved-interactions-table']//td/div[contains(text(),'"+values+"')]") ;
	}

	//S13T2
	public By interaction_txtContact(String msg){
		return null;
	}
	public By interaction_popupContact(String msg){
		return null;
	}
	public By interaction_contactUSheader(String msg){
		return null;
	}

	public By interaction_TxtInnerPageHeader(){
		return By.xpath("//div[contains(@class,'ui-page-active')]/div/p");
	}
	public By interaction_TxtCollection(int index){
		return null;
	}

	public By interaction_lblPageHeaderWithIndex(String title, int index){		
		return null;
	}

	public By interaction_lblstep(){
		return null;
	}

	public By interaction_lblCallPopupdnis(){
		return null;
	}

	public By interaction_lblCallPopupMessage(String popUpMsg){
		return By.xpath("//span[@class='ui-dialog-title']/../following-sibling::div/p[contains(.,'"+popUpMsg+"')]");

	}

	public By interaction_lblCallPopupMessage(String popUpMsg1,String popUpMsg2){
		return By.xpath("//span[@class='ui-dialog-title']/../following-sibling::div/p[contains(.,'"+popUpMsg1+"')]/following-sibling::p[contains(.,'"+popUpMsg2+"')]");

	}

	public By interaction_Folder(String FolderName){
		return By.xpath("//div[@class='jaa-folders-container']//div[text()='"+FolderName+"']") ;
	}

	//S20T1

	public By interaction_lblCallIntentHeader(){
		return By.xpath("//div[@class='container']/h3[1]");

	}

	public By interaction_lblCallIntentInput(String labelName){
		return By.xpath("//input[@id='"+labelName+"']");

	}

	public By interaction_btnCallIntentButtons(String labelName){
		return By.xpath("//button[text()='"+labelName+"']");

	}

	public By interaction_txtCallIntentSuccess(){
		return By.xpath("//div[@id='output'][contains(text(),'success')]");

	}

	public By interaction_txtGetVarCallIntent(){
		return By.xpath("//div[contains(text(),'callIntentId')]");

	}

	//S8T11

	public By interaction_lblInputFieldInContactUsPage(String question){
		return null;

	}

	public By interaction_btnInnerNext(){
		return null;
	}

	public By interaction_AgentAppInteractionName(String interactionName){
		return By.xpath("//div[@class='jaa-label'][text()='"+interactionName+"']");
	} 

	public By interaction_AgentAppTabName(String tabName){
		return By.xpath("//ul[@role='tablist']//li/a[text()='"+tabName+"']");
	}

	/*public By interaction_AgentAppPageHeader(String headerName){
		return By.xpath("//span[@class='jaa-page-header-text'][text()='"+headerName+"']");
	}*/

	public By interaction_AgentAppPageHeader(String headerName){
		  return By.xpath("//div[@class='jaa-content-panel']//span[text()='"+headerName+"']");
		 }
	
	public By interaction_AgentAppPageTitle(){
		return By.xpath("//h1[@class='jaa-history-title'][text()='Interaction History']");
	}

	//S7T7

	public By interaction_AgentAppCreateYourInteraction(String interactionText){
		return By.xpath("//div[@class='jaa-create-new-interaction'][contains(text(),'"+interactionText+"')]");
	}

	public By interaction_AgentAppNextStepsList(String stepName){
		return By.xpath("//ul[@class='jaa-steps-list']/li/span[text()='"+stepName+"']");
	}

	public By interaction_AgentAppLoginPopUpRightSide(){
		return By.xpath("//div[@id='user-menu-dropdown']/ul[@class='dropdown-menu']/li[@class='jaa-user-login-form']");
	}

	public By interaction_AgentAppLoginPopUpRightSideInput(String field){
		return By.xpath("//div[@id='user-menu-dropdown']/ul[@class='dropdown-menu']/li[@class='jaa-user-login-form']/input[@placeholder='"+field+"']");
	}

	public By interaction_AgentAppLoginPopUpRightSideButtons(String Buttons){
		return By.xpath("//div[@id='user-menu-dropdown']/ul[@class='dropdown-menu']/li[@class='jaa-user-login-form']/div//button[text()='"+Buttons+"']");
	}

	public By interaction_AgentAppLoginPopUpRightSideErrMsg(){
		return By.xpath("//div[@id='user-menu-dropdown']/ul[@class='dropdown-menu']/li[@class='jaa-user-login-form']//div[@class='jaa-login-form-error'][text()='Bad Credentials' or contains(text(),'Mauvaises informations')]");
	}

	public By interaction_AgentAppLoginPopUpRightSideUsername(){
		return By.xpath("//div[@class='jaa-user-menu'][text()]");
	}

	public By interaction_AgentAppInviteNewUserPopup(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']/div");
	}

	public By interaction_AgentAppInviteNewUserPopupTitle1(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']/div[@class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix']/span[text()]");
	}

	public By interaction_AgentAppInviteNewUserPopupTitle2(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//div[@class='jaa-invite-sub-title'][text()]");
	}

	public By interaction_AgentAppInviteNewUserPopupEmail(String field){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//input[@placeholder='"+field+"']");
	}

	public By interaction_AgentAppInviteNewUserPopupTitle3(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//div[@class='jaa-invite-permissions-question'][text()]");
	}

	public By interaction_AgentAppInviteNewUserPopupPermissionOptions(String options){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//div[@class='jaa-invite-checkbox']//label[text()='"+options+"']");
	}

	public By interaction_AgentAppInviteNewUserPopupLink(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//a[@class='jaa-permissions-deatails'][text()]");
	}

	public By interaction_AgentAppInviteNewUserPopupButton(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//div[@class='ui-dialog-buttonset']//span[contains(text(),'Invite')]");
	}

	public By interaction_AgentAppInviteNewUserErrMsg(){
		return By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front jaa-invite-dialog jaa-confirm-dialog ui-dialog-buttons ui-draggable ui-resizable']//div[@class='jaa-invite-error'][text()]");
	}

	public By interaction_AgentAppSuccessMsg(){
		return By.xpath("//div[@class='toast-container'][text()]");
	}

	public By interaction_AgentAppLogoutPopupButtons(String button){
		return By.xpath("//div[@class='ui-dialog-buttonset']/button/span[text()='"+button+"']");
	}

	//S7T9

	public By interaction_lblErrorMessage(){

		return By.xpath("//div[@class='jaa-simple-error']");
	}

	//S7T12
	public By interaction_lnkAgentAppPageHeaderHelp() {		
		return By.xpath("//div[contains(@class,'jaa-help-link')]");
	}


	public By interaction_lnkAgentAppPageHeaderHelpOptions(String helpOptions) {		
		return By.xpath("//a[text()='"+helpOptions+"']");
	}

	//S11T2
	public By interaction_yesNoSlider(String label)
	{
		return null;
	}

	//S23T7
	public By interaction_drpchoiceselect()
	{
		return By.xpath("//div[@class='ui-select']//select");
	}

	//S15T4
	public By interaction_lblPageHeaderWithSpan(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//p[@class='jaa-header-footer header']//p/span[contains(text(),'"+title.trim()+"')]");
	}

	public By interaction_tabTitleName(String tabTitleName){
		return By.xpath("//div[contains(@class,'jwd-side-Pane')][text()='"+tabTitleName+"']");
	}

	public By interaction_tabElementName(String elementName){
		return By.xpath("//a[contains(@class, 'ng-binding')][text()='"+elementName+"']");
	}

	public By interaction_elementLabel1(){
		return By.xpath("//input[contains(@class, 'jwd-search-variable-title')]");
	}

	public By interaction_elementLabelName(String labelName){
		return By.xpath("//span[contains(@class, 'ng-binding')][text()='"+labelName+"']");
	}

	public By interaction_verifySimulator()
	{
		return By.xpath("//div[contains(@class, 'jwd-side-pane-expanded')]/div");
	}
	
	public By interaction_SimulatorInputLabel()
	{
		return By.xpath("//div[contains(@class, 'ui-input-text')]/input");
	}
	
	public By interaction_imgHeader1(String title){
		return By.xpath("//p[@class='jma-header-footer header']//p[(text()='"+title+"')]");
	}

	public By interaction_SimulatorInputLabel1()
	{
		return By.id("1468769618975-469104a0aedd00a4d5574a326ed5155f8d761ba-1f81");
	}
	
	public By interaction_SimulatorLabel(String labelName)
	 {
	  return By.xpath("//li[contains(@class, 'ui-li-static ui-body-inherit ui-first-child ui-last-child')]/label[text()='"+labelName+"']");
	 }

}