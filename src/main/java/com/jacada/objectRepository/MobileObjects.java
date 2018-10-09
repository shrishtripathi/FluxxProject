package com.jacada.objectRepository;

import org.openqa.selenium.By;

public class MobileObjects implements CommonObjects{

	public By interacton_txtLinkName(String interactionName){
		return By.xpath("//input[@placeholder='Filter items...']");
	}

	public By interaction_lnkName(String interactionName){
		return By.linkText(interactionName);
	}

	public By interaction_lnkSelectLanguage(String language){
		return By.linkText(language);
	}

	public By interaction_lblPageTitle(){
		return By.xpath("//h1[@class='ui-title']");

	}
	
	public By interaction_lblPageTitle1(){
		return By.xpath("//div[contains(@class, 'page-title-container')]/div");
	
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


	public By interaction_lblPageTitle(String title){
		String title1 = title;
		if(title.charAt(0) == '\'')  
		{
			title1=title1.substring(1);

			if(title1.contains("\'"))
				title1=title1.substring(0, title1.indexOf("\'"));

			return By.xpath("//*[contains(@class,'title')][contains(text(),'"+title1+"')]");
		} 

		else if(title.contains("\'")){
			title1=title.substring(0, title1.indexOf("\'"));
			return By.xpath("//*[contains(@class,'title')][contains(text(),'"+title1+"')]");
		}
		else{
			return By.xpath("//*[contains(@class,'title')][text()='"+title1+"']");
		}
	}


	public By interaction_lblPageHeader(){
		return By.xpath("//p[@class='jma-header-footer header']/p");
	}

	public By interaction_lblQuestion(){
		return By.tagName("label");
	}

	public By interaction_Poc1_txtTextArea(){
		return By.tagName("textarea");
	}

	public By interaction_lblPageFooter(){
		return By.xpath("//p[@class='jma-header-footer footer']/p");
	}

	public By interaction_txtBreadcrumbName(String breadcrumbName){
		return By.xpath("//div[@class='ui-content']/div/div[text()='"+breadcrumbName+"']");
	}

	public By interaction_lblcontactUs(String contactUsLabel){
		return By.xpath("//span[contains(text(),'"+contactUsLabel+"')]/..");

	}

	public By interaction_lblHelp(String helpLabel){
		return By.xpath("//span[contains(text(),'"+helpLabel+"')]/..");
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
		return By.xpath("//h3[text()='"+ErrorHeader+"']");
	}

	public By interaction_txtErrorMsg(String ErrorMsg){
		if(ErrorMsg.contains("-")){
			String[] eMsg=ErrorMsg.split("-");
			ErrorMsg=eMsg[1];
		}
		return By.xpath("//div[@class='ui-content content'][contains(text(),'"+ErrorMsg+"')]");
	}

	public By interaction_btnPopup(String button){
		return By.xpath("//div[contains(@class,'ui-page-active')]//a[text()='"+button+"']");
	}

	//POC4


	public By interaction_lblAnswerHeader(){
		return By.xpath("//h1[@class='ui-title']");
	}

	public By interaction_txtAnswer(){
		return By.xpath("//p[@class='jma-header-footer header']/p");
	}


	public By interaction_lblInnerContactUsPageTitle(String title){
		return By.xpath("//div[contains(@class,'ui-page-active')]//h1[@class='ui-title'][text()='"+title+"']");
	}

	//S1T1
	public By interaction_btnNextStatus(String status){
		if (status.equalsIgnoreCase("Enabled")){
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='next-btn') or contains(@class,'btn-right')]");
		}
		return By.xpath("//div[contains(@class,'btn-disabled ui-disabled next-btn') or contains(@class,'next-btn-disabled') or contains(@class,'ui-btn-right ui-link disabled')]");
	}

	public By interaction_btnBackStatus(String status){
		if (status.equalsIgnoreCase("Enabled")){
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='back-btn') or contains(@class,'btn-left')]");
		}
		return By.xpath("//div[(@class='btn-disabled ui-disabled back-btn)]");
	}

	public By interaction_btnDoneStatus(String status){
		if (status.equalsIgnoreCase("Enabled")){
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='next-btn') or contains(@class,'btn-right')]");
		}
		return By.xpath("//div[(@class='btn-disabled ui-disabled next-btn')]");
	}

	public By interaction_btnBack(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'back-btn') or contains(@class,'btn-left')]");
	}

	public By interaction_btnNext(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'next-btn') or contains(@class,'btn-right')]");
	}

	public By interaction_btnDone(){
		return By.xpath("//div[contains(@class,'ui-page-active')]/div[(@class='next-btn') or contains(@class,'btn-right')]");
	}

	//S1T3
	public By interaction_clickChoice(int choice){
		return By.xpath("//ul[@class='ui-listview ui-listview-inset ui-corner-all ui-shadow']/li["+choice+"]//p");
	}

	public By interaction_clickCategeory(int choice){
		return By.xpath("//ul[@class='jma-click-to-continue-category ui-listview ui-listview-inset ui-corner-all ui-shadow']/li["+choice+"]//span");
	}

	public By interaction_clickCategeoryChoice(String choice){
		return By.xpath("//div[contains(@class,'ui-page-active')]//ul[contains(@class,'jma-click-to-continue')]//a[contains(text(),'"+choice+"')]");
	}

	//S1T5
	public By interaction_lblPageHeader(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//p[@class='jma-header-footer header']//p[contains(text(),'"+title.trim()+"')]");
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
		return By.xpath("//p[@class='jma-header-footer footer']//p[contains(text(),'"+title.trim()+"')]");	
	}

	//S1T7
	public By interaction_lblInnerPageTitle(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//div[contains(@class,'ui-page-active')]//*[contains(@class,'title')][contains(text(),'"+title+"')]");
	}

	public By interaction_lblInnerPageHeader(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));

		return By.xpath("//div[contains(@class,'ui-page-active')]//p[contains(text(),'"+title+"')]");
	}

	public By interaction_btnInnerBack(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='back-btn') or contains(@class,'btn-left')]");
	}


	//S1T9
	public By interaction_ContactUsButtons(String Button){
		if(Button.equalsIgnoreCase("Mail") || Button.equalsIgnoreCase("Courrier")) {  
			return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='jma-contactus-email-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='"+Button+"']");
		}
		else if(Button.equalsIgnoreCase("Chat") || Button.equalsIgnoreCase("Clavardage")){
			return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='jma-contactus-chat-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='"+Button+"']");
		}
		else if(Button.equalsIgnoreCase("Call") || Button.equalsIgnoreCase("Appeler")){
			return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='jma-contactus-call-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='"+Button+"']");
		}
		else if(Button.equalsIgnoreCase("Transfer")|| Button.equalsIgnoreCase("Transférer")){
			return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='jma-contactus-call-button ui-btn ui-btn-icon-right ui-icon-carat-r']//p[text()='"+Button+"']");
		}
		else
			return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='jma-contactus-callback-button ui-btn ui-btn-icon-right ui-icon-carat-r']/p[text()='"+Button+"']");

	}

	public By interaction_PopUpPageTitle(String Title){  
		return By.xpath("//h1[@class='ui-title'][text()='"+Title+"']");
	}

	public By interaction_btnChatclose(){
		return By.xpath("//div[@class='jma-chat-page jma-side-page ui-page ui-page-theme-a ui-page-header-fixed ui-page-footer-fixed ui-page-active']//a");
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
		return By.xpath("//tr[@class='ui-panel-page-container-a']//b[@class='ui-table-cell-label']/ancestor::td[text()='"+attribute+"']/following::td[text()='"+value+"']");
	}

	public By Navigation_verifyOrderCollectionText(String attribute,String value){
		return By.xpath("//tr[@class='ui-panel-page-container-a']//b[@class='ui-table-cell-label'][text()='"+attribute+"']/ancestor::td[text()='"+value+"']");
	}

	//S1T17
	public By interaction_btnClose(String ErrorMessage){
		return By.xpath("//div[@class='jaa-simple-error-container']//div[contains(text(),'"+ErrorMessage+"')]");

	}
	//S1T18

	//S2T1	 
	public By interaction_btn(String button){
		if(button.equalsIgnoreCase("Back")||button.equalsIgnoreCase("Left"))   
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'back-btn') or contains(@class,'btn-left')]");
		else
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'next-btn') or contains(@class,'btn-right')]");

	}

	public By interaction_btnBack_old(){
		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[2]");
	}
	public By interaction_btnNext_old(){
		return By.xpath("//div[@class='jaa-contact-us-buttons-container']/following-sibling::div/button[1]");
	}

	//S2T6
	public By interaction_continuePageHeader(){
		return By.xpath("//p[@class='jma-header-footer header']/following-sibling::p/span[contains(text(),'Continue?')]");
	}
	public By interaction_continuePage(String value){
		return By.xpath("//ul[@class='jma-click-to-continue ui-listview ui-listview-inset ui-corner-all ui-shadow']//a[text()='"+value+"']");
	}

	public By interaction_ThankYouPage(){
		return By.xpath("//div[@class='jma-end-page']");

	}

	//S2T18
	public By interaction_lblCallPopup(){
		return By.xpath("//div[contains(@class, 'ui-page-active')]//div[@id='popupcontactUsPage']//h3");

	}


	public By interaction_btnCallPopup(String button){
		return By.xpath("//div[contains(@class, 'ui-page-active')]//a[@class='ui-btn ui-btn-inline'][contains(text(),'"+button+"')]");

	}



	//S2T21	
	public By interaction_breadCrumb(String page){
		if (page.equalsIgnoreCase("Home"))
			return By.xpath("//div[@class='jma-breadcrumb-home']");

		else
			return By.xpath("//div[@class='jma-breadcrumb-element'][contains(text(),'"+page+"')]");

	}

	//S3T1	
	public By interaction_lblquestion(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}
		return By.xpath("//div[contains(@class,'active')]//*[contains(text(),'"+question+"')]");
	}
	public By interaction_txtdefaultQuestionsAnswer(String question,String answer){
		return By.xpath("//label[contains(text(),'"+question+"')]/ancestor::li//div/input[contains(text(),'"+answer+"')]");
	}
	public By interaction_txtquestionsAnswer(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}
		return By.xpath("//div[contains(@class,'active')]//label[contains(text(),'"+question+"')]/ancestor::li//div/input");
	}

	public By interaction_txtquestionsAnswerInMultiLine(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}

		return null;
	}

	public By interaction_txtmultiQuestionsAnswer(String question){
		if(question.contains("-")){
			String[] Question=question.split("-");
			if(Question.length>1){
				question=Question[1];
			}
		}
		return By.xpath("//label[contains(text(),'"+question+"')]/ancestor::li//textarea");
	}
	public By interaction_lblgetmonth(){
		return By.xpath("//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']");
	}
	public By interaction_btnarrowNext(){
		return By.xpath("//div[@id='ui-datepicker-div']//a[@title='Next']");
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

	public  By interaction_SelectLanguage(String language){
		return By.xpath("//div[@class]/label[text()='"+language+"']");
	}

	public  By interaction_photoUpload(){
		return By.xpath("//ul[@class='jma-upload-image-list ui-listview ui-listview-inset ui-corner-all ui-shadow']");
	}

	public  By interaction_Upload(){
		return By.xpath("//ul[@class='jma-upload-image-list ui-listview ui-listview-inset ui-corner-all ui-shadow']//a");
	}

	public  By interaction_PhotoUploadButtons(String Button){
		return By.xpath("//div[@class='photo-upload-footer ui-footer ui-bar-inherit ui-footer-fixed slideup']//a[contains(text(),'"+Button+"')]");
	}

	public  By interaction_photoUpload_addbutton(){
		return By.xpath("//ul[@class='jma-upload-image-list ui-listview ui-listview-inset ui-corner-all ui-shadow']//div[@class='jma-add-image-btn']");
	}

	//S3T3
	public By interaction_lblPageLabel(String Label){
		return By.xpath("//label[text()="+Label+"]");
	}

	public By interaction_lblPlusImage(){
		return By.xpath("//p[@class='jma-header-footer header']/p/img");
	}

	public By interaction_lblPageHeaderText(String title){
		return By.xpath("//p[@class='jma-header-footer header']//p/b[contains(text(),'"+title+"')]");
	}

	public By interaction_lblPhotoUploadLabel(String label){
		return By.xpath("//span[contains(text(),'"+label+"')]");
	}

	public By interaction_lblAnswer(String question, String answer){
		return By.xpath("//label[contains(text(),'"+question+"')]/ancestor::li//div/input[contains(text(),'"+answer+"')]");
	}

	public By interaction_photoCountText(int size){		
		return By.xpath("//div[contains(text(),'"+(size)+"')]/ancestor::a");

	}

	public By interaction_photoCharacterText(int size){		
		return By.xpath("//div[contains(text(),'"+(char)size+"')]/ancestor::a");

	}


	public  By interaction_PhotoImages(){
		return By.xpath("//div[@class='jma-photo-upload-label clipped']/ancestor::a");
	}

	//S4T1
	public By interaction_rbRadio(String button){

		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(text(),'"+button+"')]");
	}
	
	public By interaction_rbRadioBtn(String button){

		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(text(),'"+button+"')]");
	}
	
	public By interaction_imglogoJacada(){
		return By.xpath("//div[@class='logo']/img[@class='logo-image']");
	}

	public By interaction_lblquestionLabel(String questionLabel){
		return By.xpath("//p[@class='jma-section-label']/span[contains(text(),'"+questionLabel+"')]");
	}

	public By interaction_swtflipswitch(String option){
		return By.xpath("//*[text()='"+option+"']");
	}

	public By interaction_lblChoice(String labelName){
		return By.xpath("//label[text()='"+labelName+"']");
	}

	public By interaction_lblflipswitch(String labelName){
		return By.xpath("//select[@class='ui-flipswitch-input']/option[text()='"+labelName+"']");
	}

	public By interaction_btnflipswitch(String labelName){
		return By.xpath("//select[@class='ui-flipswitch-input']/option[text()='"+labelName+"']/ancestor::div/a");
	}

	public By interaction_lblHelp_Flat(String helpLabel){
		return By.xpath("//span[contains(text(),'"+helpLabel+"')]/..");
	}

	public By interaction_rbStatus(String button, Boolean selected){

		if(selected)
		{
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(@class,'ui-radio-on')][contains(text(),'"+button+"')]");
		}
		else{
			return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(@class,'ui-radio-off')][contains(text(),'"+button+"')]");
		}
	}

	public By interaction_swtActiveflipswitch(String option){
		return By.xpath("//div[contains(@class,'ui-flipswitch-active')]");
	}


	//S5T1

	public By interaction_imgChecker(String imageName){

		return By.xpath("//p/img[contains(@src,'"+imageName+"')]");
	}

	public By interaction_imgHeader(String title){
		return By.xpath("//p[@class='jma-header-footer header']//p[contains(text(),'"+title+"')]");
	}

	//S5T8

	public By interaction_drpChoice(){
		return By.xpath("//*[@id='undefined_flexselect']");
	}

	//S6T1
	public By interaction_drpMultipleChoice(String choice){
		return By.xpath("//div[contains(@class,'ui-page-active')]//*[contains(text(),'"+choice+"')]");
	}	

	public By interaction_drpMultipleChoice(String question,String choice){
		return null;
	}

	public By interaction_drpMultipleChoices(String question,String choice){	
		return null;
	}

	public By interaction_drpMultipleChoiceDown(String question,String choice){

		return null;
	}
	//S6T4
	public By interaction_lblwebquestionAnswer(String question){
		return null; //This is used only for Web
		//return By.xpath("//span[contains(text(),'"+question+"')]/ancestor::div//input");
	}
	public By interaction_txtMultichoice(String choice){
		return By.xpath("//label[contains(text(),'"+choice+"')]/parent::li//input");//This is used only for Web
		//return By.xpath("//span[contains(text(),'"+choice+"')]/parent::div//input");
	}

	//S6T5
	public By interaction_lblHelp_Breadcrumbs(String helpLabel){
		return null; //This is used only for Web
	}

	//S6T6
	public By interacton_chkMultiChoice(String choice){
		return By.xpath("//td[contains(text(),'"+choice+"')]/ancestor::tr//label[contains(@class,'jma-column-checkbox')]");
	}

	public  By interaction_imgPhotoImages(){
		return By.xpath("//div[@class='jma-photo-upload-label clipped']/ancestor::a");
	}


	public By interaction_lblMultiQuestionType(String question){
		return By.xpath("//label[contains(text(),'"+question+"')]/ancestor::li//textarea");

	}

	public By interaction_lblQuestionType(String question){
		return By.xpath("//label[contains(text(),'"+question+"')]/ancestor::li//div/input");
	}

	public By interaction_Poc1_lblQuestion(){
		return By.tagName("label");
	}

	//S6T1

	public By interaction_lblPageHeaderImg(){

		return By.xpath("//p[@class='jma-header-footer header']//img");
	}
	public By interaction_lblPageFooterImg(){

		return By.xpath("//p[@class='jma-header-footer footer']//img");

	}

	public By interaction_rbRadioSelected(String button){

		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']/label[contains(@class,'ui-radio-on')][contains(text(),'"+button+"')]");//S10T2
	}


	//S6T7

	public By interaction_rbRadio( int index){

		return By.xpath("//div[contains(@class,'ui-page-active')]//div[@class='ui-radio']["+index+"]/label");
	}

	public By interaction_lnkLink(String linkName){

		return By.xpath("//ul[@class='jma-click-to-continue ui-listview ui-listview-inset ui-corner-all ui-shadow']/li/a[text()='"+linkName+"']");
	}

	public By interaction_lnkDateLink(int index){

		return By.xpath("//ul[@class='ui-listview ui-listview-inset ui-corner-all ui-shadow']/li["+index+"]//p");
	}

	public By interaction_verifyNumCollectionText(int index){
		return By.xpath("//ul[@class='ui-listview ui-listview-inset ui-corner-all ui-shadow']/li["+index+"]");

	}
	public By interaction_chkMultipleChoiceQuestionsCheckBox(int index){		
		//return By.xpath("//div[@class='ui-checkbox']["+index+"]/label[(text()='"+Choice+"')]");
		return By.xpath("//div[@class='ui-checkbox']["+index+"]/label");

	}

	//S7T1
	public By interaction_txtAppSearch(){
		return null;
	}
	public By interaction_AppSearch(){
		return null;
	}
	public By interaction_AppInteractionLoading(){
		return null;
	}
	public By interaction_AppInteractions(String interaction){
		return null;
	}
	public By interaction_tabInteraction(String interaction){
		return null;
	}
	//S7T2
	public By interaction_AgentAppUser(String user){
		return null;
	}
	public By interaction_AgentAppUserName(){
		return null;
	}
	public By interaction_AgentAppPassword(){
		return null;

	}
	public By interaction_AgentApp_lblBreadcrumbName(String breadcrumbName){
		return null;
	}

	public By interaction_AgentAppSubmit(){
		return null;
	}
	public By interaction_AgentAppSaveInteractionButton(){
		return null;
	}
	public By interaction_AgentAppInteractionbutton(String button){
		return null;
	}
	public By interaction_AgentAppSaveInteractionName(){
		return null;
	}
	public By interaction_AgentAppUserActions(String action){
		return null;
	}
	public By interaction_AgentAppSavedInteractionPopup(String message){
		return null;
	}
	public By interaction_AgentAppRecords(){
		return null;
	}

	public By interaction_AgentAppSavedInteractionPlayButton(String interaction){
		return null;
	}
	public By interaction_AgentAppInteractionRestorePopupTitle(String message){
		return null;
	}
	public By interaction_AgentAppInteractionRestorePopupMessage(String message){
		return null;
	}
	public By interaction_AgentAppFooterVariables(String variable){
		return null;
	}
	public By interaction_AgentAppPageVariables(String variable){
		return null;
	}

	public By savedInteractionsMessage()
	{
		return null;
	}

	//S9T7
	public By interaction_UpgradeSavedInteractionHeader(String header){

		return null;
	}
	public By interaction_UpgradeSavedInteractions(String values){
		return null;
	}

	//S13T2
	public By interaction_txtContact(String msg){
		return By.xpath("//div[contains(@class,'ui-content')]//*[(@placeholder='"+msg+"')or(@name='"+msg+"')]");
	}
	public By interaction_popupContact(String msg){
		return By.xpath("//div[contains(@class,'ui-loader')]//h1[text()='"+msg+"']");
	}
	public By interaction_contactUSheader(String msg){
		return By.xpath("//div[contains(@class,'ui-content')]//*[contains(text(),'"+msg+"')]");
	}

	public By interaction_TxtInnerPageHeader(){
		return By.xpath("//div[contains(@class,'ui-page-active')]/div/p");
	}

	public By interaction_TxtCollection(int index){
		return By.xpath("//div[@class='jma-content-sections']//tr["+index+"]/td");
	}

	public By interaction_lblPageHeaderWithIndex(String title, int index){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//p[@class='jma-header-footer header']//p[contains(text(),'"+title.trim()+"')]["+index+"]");
	}

	public By interaction_lblstep(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[contains(@class,'jma-content-section jma-has-choices')]//li");
	}

	public By interaction_lblCallPopupdnis(){
		return By.xpath("//div[@id='popupundefined']/h3");

	}

	public By interaction_lblCallPopupMessage(String popUpMsg){
		return null;

	}

	public By interaction_lblCallPopupMessage(String popUpMsg1,String popUpMsg2){
		return By.xpath("//span[@class='ui-dialog-title']/../following-sibling::div/p[contains(.,'"+popUpMsg1+"')]/following-sibling::p[contains(.,'"+popUpMsg2+"')]");

	}

	public By interaction_Folder(String FolderName){
		return null;
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
		return By.xpath("//div[contains(@class,'active')]//label[contains(text(),'"+question+"')]/../div/input");

	}

	public By interaction_btnInnerNext(){
		return By.xpath("//div[contains(@class,'ui-page-active')]//div[(@class='next-btn') or contains(@class,'btn-left')]");
	}


	public By interaction_AgentAppInteractionName(String interactionName){
		return null;
	}

	public By interaction_AgentAppTabName(String tabName){
		return null;
	}

	public By interaction_AgentAppPageHeader(String headerName){
		return null;
	}

	public By interaction_AgentAppPageTitle(){
		return null;

	}

	//S7T7

	public By interaction_AgentAppCreateYourInteraction(String interactionText){
		return null;
	}

	public By interaction_AgentAppNextStepsList(String stepName){
		return null;
	}

	public By interaction_AgentAppLoginPopUpRightSide(){
		return null;
	}

	public By interaction_AgentAppLoginPopUpRightSideInput(String field){
		return null;
	}

	public By interaction_AgentAppLoginPopUpRightSideButtons(String Buttons){
		return null;
	}

	public By interaction_AgentAppLoginPopUpRightSideErrMsg(){
		return null;
	}

	public By interaction_AgentAppLoginPopUpRightSideUsername(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopup(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupTitle1(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupTitle2(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupEmail(String field){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupTitle3(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupPermissionOptions(String options){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupLink(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserPopupButton(){
		return null;
	}

	public By interaction_AgentAppInviteNewUserErrMsg(){
		return null;
	}

	public By interaction_AgentAppSuccessMsg(){
		return null;
	}

	public By interaction_AgentAppLogoutPopupButtons(String button){
		return null;
	}

	//S7T9

	public By interaction_lblErrorMessage(){
		return By.xpath("//div[@class='ui-content content']");
	}

	//S7T12
	public By interaction_lblPageTitle(String title, String classname){		
		return null ;
	}


	public By interaction_lnkAgentAppPageHeaderHelp() {		
		return null;
	}


	public By interaction_lnkAgentAppPageHeaderHelpOptions(String helpOptions) {		
		return null;
	}

	//S11T2
	public By interaction_yesNoSlider(String label)
	{
		return By.xpath("//li/label[text()='"+label+"']/following-sibling::div[contains(@class,'ui-flipswitch-active')]");
	}

	//S23T7
	public By interaction_drpchoiceselect()
	{
		return By.xpath("//div[@class='ui-select']//select");
	}

	public By interaction_lblPageHeaderWithSpan(String title){
		if(title.charAt(0) == '\'')  
			title=title.substring(1);
		if(title.contains("\'"))
			title=title.substring(0, title.indexOf("\'"));
		return By.xpath("//p[@class='jaa-header-footer header']//p/span[contains(text(),'"+title.trim()+"')]");
	}
	//S106T1
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