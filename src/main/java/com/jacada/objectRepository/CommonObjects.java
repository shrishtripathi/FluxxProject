package com.jacada.objectRepository;

import org.openqa.selenium.By;

public interface CommonObjects {

	public By interacton_txtLinkName(String interactionName);

	public By interaction_lnkName(String interactionName);

	public By interaction_lnkSelectLanguage(String language);

	public By interaction_lblPageTitle();
	
	public By interaction_lblPageTitleNoAudit(String title);

	public By interaction_lblPageTitle(String title);

	public By interaction_lblPageHeader();

	public By interaction_lblQuestion();

	public By interaction_Poc1_txtTextArea();

	public By interaction_lblPageFooter();

	public By interaction_txtBreadcrumbName(String breadcrumbName);

	public By interaction_lblcontactUs(String contactUsLabel);

	public By interaction_lblHelp(String helpLabel);

	public By interaction_lblHelpTooltipMsg(String helpMsg);

	public By interaction_Poc1_lblQuestion();

	//POC2	

	public By interaction_txtInputValue();	

	//POC3

	public By interaction_lblErrorHeader(String ErrorHeader);

	public By interaction_txtErrorMsg(String ErrorMsg);

	public By interaction_btnPopup(String button);

	//POC4

	public By interaction_lblAnswerHeader();

	public By interaction_txtAnswer();

	//S1T1
	public By interaction_btnNextStatus(String status);

	public By interaction_btnBackStatus(String status);

	public By interaction_btnDoneStatus(String status);

	public By interaction_btnBack();	

	public By interaction_btnNext();

	public By interaction_btnDone();

	//S1T3
	public By interaction_clickChoice(int choice);

	public By interaction_clickCategeory(int choice);

	public By interaction_clickCategeoryChoice(String choice);

	//S1T5
	public By interaction_lblPageHeader(String title);

	public By interaction_lblPageFooter(String title);

	//S1T7
	public By interaction_lblInnerPageTitle(String Title);

	public By interaction_lblInnerPageHeader(String title);

	public By interaction_btnInnerBack();

	public By interaction_lblInnerContactUsPageTitle(String Title);

	//S1T9
	public By interaction_ContactUsButtons(String Button);

	public By interaction_PopUpPageTitle(String Title);

	public By interaction_btnChatclose();
 
	//S1T11
	//S1T13
	public By interaction_userName();

	public By interaction_password();

	public By interaction_submit();


	//S1T15
	public By interaction_verifyOrderCollectionText(String attribute,String value);

	public By Navigation_verifyOrderCollectionText(String attribute,String value);

	//S1T17
	public By interaction_btnClose(String ErrorMessage);

	//S1T18

	//S2T1
	public By interaction_btn(String button);

	public By interaction_btnNext_old();

	public By interaction_btnBack_old();

	//S2T6
	public By interaction_continuePageHeader();

	public By interaction_continuePage(String value);	 

	public By interaction_ThankYouPage();

	//S2T18
	public By interaction_lblCallPopup();

	public By interaction_btnCallPopup(String button);

	//S2T21
	public  By interaction_breadCrumb(String page);

	//S3T1

	public  By interaction_SelectLanguage(String language);

	public  By interaction_photoUpload();

	public  By interaction_Upload();

	public  By interaction_PhotoUploadButtons(String Button);


	public By interaction_lblquestion(String question);

	public By interaction_txtdefaultQuestionsAnswer(String question,String answer);

	public By interaction_txtquestionsAnswer(String question);

	public By interaction_txtquestionsAnswerInMultiLine(String question);

	public By interaction_txtmultiQuestionsAnswer(String question);

	public By interaction_btnarrowNext();

	public By interaction_lblgetmonth();

	public By interaction_lblmonth(String month);

	public By interaction_drpyear(String year);

	public By interaction_btnday(String day);

	public  By interaction_imgPhotoImages();

	public  By interaction_photoUpload_addbutton();

	//S3T3
	public By interaction_lblPageLabel(String Label);

	public By interaction_lblPlusImage();		

	public By interaction_lblPageHeaderText(String title);

	public By interaction_lblPhotoUploadLabel(String contactUsLabel);

	public By interaction_lblAnswer(String question, String answer);

	public  By interaction_PhotoImages();

	public By interaction_photoCountText(int size);	

	public By interaction_photoCharacterText(int size);

	//S3T10

	public By interaction_lblMultiQuestionType(String question);

	public By interaction_lblQuestionType(String question);

	//S4T1
	public By interaction_rbRadio(String button);

	public By interaction_imglogoJacada();

	public By interaction_lblquestionLabel(String questionLabel);

	public By interaction_swtflipswitch(String option);

	public By interaction_lblChoice(String labelName);

	public By interaction_lblflipswitch(String labelName);

	public By interaction_btnflipswitch(String labelName);

	public By interaction_lblHelp_Flat(String helpLabel);

	public By interaction_rbStatus(String button, Boolean selected);

	public By interaction_swtActiveflipswitch(String option);


	//S5T1

	public By interaction_imgChecker(String imageName);

	public By interaction_imgHeader(String imageName);
	
	// S5T7
	
	public By interaction_rbRadioBtn(String button);

	//S5T8
	public By interaction_drpChoice();

	//S6T1
	public By interaction_drpMultipleChoice(String choice);//drop down displayed adjacently but select is not highlighted

	public By interaction_drpMultipleChoice(String question,String choice);//drop down displayed adjacently

	public By interaction_drpMultipleChoices(String question,String choice);//Question and option in the same parent 

	public By interaction_drpMultipleChoiceDown(String question,String choice);//drop down displayed below

	//S6T4
	public By interaction_lblwebquestionAnswer(String question);

	public By interaction_txtMultichoice(String choice);

	//S6T5
	public By interaction_lblHelp_Breadcrumbs(String helpLabel);

	//S6T6
	public By interacton_chkMultiChoice(String choice);

	//S5T7

	//S6T1

	public By interaction_lblPageHeaderImg();

	public By interaction_lblPageFooterImg();

	public By interaction_rbRadioSelected(String button);

	//S6T7	

	public By interaction_rbRadio(int index);

	public By interaction_lnkLink(String linkName);

	public By interaction_lnkDateLink(int index);

	public By interaction_verifyNumCollectionText(int index);

	public By interaction_chkMultipleChoiceQuestionsCheckBox(int index);

	//S7T1
	public By interaction_txtAppSearch();

	public By interaction_AppSearch();

	public By interaction_AppInteractionLoading();

	public By interaction_AppInteractions(String interaction);

	public By interaction_tabInteraction(String interaction);

	//S7T2

	public By interaction_AgentAppUser(String user);

	public By interaction_AgentAppUserName();

	public By interaction_AgentAppPassword();

	public By interaction_AgentAppSubmit();

	public By interaction_AgentAppSaveInteractionButton();

	public By interaction_AgentAppSavedInteractionPopup(String message);

	public By interaction_AgentApp_lblBreadcrumbName(String breadcrumbName);

	public By interaction_AgentAppInteractionbutton(String button);

	public By interaction_AgentAppSaveInteractionName();

	public By interaction_AgentAppRecords();	

	public By interaction_AgentAppSavedInteractionPlayButton(String interaction);

	public By interaction_AgentAppInteractionRestorePopupTitle(String message);

	public By interaction_AgentAppInteractionRestorePopupMessage(String message);

	public By interaction_AgentAppFooterVariables(String variable);

	public By interaction_AgentAppPageVariables(String variable);


	public By interaction_AgentAppUserActions(String action);

	public By savedInteractionsMessage();

	//S9T7
	public By interaction_UpgradeSavedInteractionHeader(String header);

	public By interaction_UpgradeSavedInteractions(String values);

	//S13T2
	public By interaction_txtContact(String msg);

	public By interaction_contactUSheader(String msg);

	public By interaction_popupContact(String msg);

	public By interaction_TxtInnerPageHeader();

	public By interaction_lblPageHeaderWithIndex(String title, int index);

	public By interaction_TxtCollection(int index);

	public By interaction_lblstep();

	public By interaction_lblCallPopupdnis();

	public By interaction_lblCallPopupMessage(String popUpMsg);
	public By interaction_lblCallPopupMessage(String popUpMsg1,String popUpMsg2);

	public By interaction_Folder(String FolderName);

	//S20T1

	public By interaction_lblCallIntentHeader();

	public By interaction_lblCallIntentInput(String labelName);

	public By interaction_btnCallIntentButtons(String labelName);

	public By interaction_txtCallIntentSuccess();

	public By interaction_txtGetVarCallIntent();
	//S8T11

	public By interaction_lblInputFieldInContactUsPage(String question);

	public By interaction_btnInnerNext();

	public By interaction_AgentAppInteractionName(String interactionName);

	public By interaction_AgentAppTabName(String tabName);

	public By interaction_AgentAppPageHeader(String headerName);

	public By interaction_AgentAppPageTitle();

	//S7T7

	public By interaction_AgentAppCreateYourInteraction(String interactionText);

	public By interaction_AgentAppNextStepsList(String stepName);

	public By interaction_AgentAppLoginPopUpRightSide();

	public By interaction_AgentAppLoginPopUpRightSideInput(String field);

	public By interaction_AgentAppLoginPopUpRightSideButtons(String Buttons);

	public By interaction_AgentAppLoginPopUpRightSideErrMsg();

	public By interaction_AgentAppLoginPopUpRightSideUsername();

	public By interaction_AgentAppInviteNewUserPopup();

	public By interaction_AgentAppInviteNewUserPopupTitle1();

	public By interaction_AgentAppInviteNewUserPopupTitle2();

	public By interaction_AgentAppInviteNewUserPopupEmail(String field);

	public By interaction_AgentAppInviteNewUserPopupTitle3();

	public By interaction_AgentAppInviteNewUserPopupPermissionOptions(String options);

	public By interaction_AgentAppInviteNewUserPopupLink();

	public By interaction_AgentAppInviteNewUserPopupButton();

	public By interaction_AgentAppInviteNewUserErrMsg();

	public By interaction_AgentAppSuccessMsg();

	public By interaction_AgentAppLogoutPopupButtons(String button);

	//S7T9

	public By interaction_lblErrorMessage();

	//S7T12
	public By interaction_lblPageTitle(String title, String classname);

	public By interaction_lnkAgentAppPageHeaderHelp() ;

	public By interaction_lnkAgentAppPageHeaderHelpOptions(String helpOptions);

	//S11T2
	public By interaction_yesNoSlider(String label);

	//S23T7
	public By interaction_drpchoiceselect();

	public By interaction_lblPageHeaderWithSpan(String title);

	//S108T1
	public By interaction_tabTitleName(String tabTitleName);

	public By interaction_tabElementName(String elementName);

	public By interaction_elementLabel1();

	public By interaction_elementLabelName(String labelName);

	public By interaction_verifySimulator();

	public By interaction_lblPageTitle1();
	
	public By interaction_lblPageTitle2(String title);
	
	public By interaction_SimulatorInputLabel();
	
	public By interaction_imgHeader1(String title);
	
	public By interaction_SimulatorInputLabel1();
	
	public By interaction_SimulatorLabel(String labelName);
}
