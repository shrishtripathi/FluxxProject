/** Purpose: This class is the main workflow class of Fluxx-Grantee and Grant Approval Workflow. 
* It collects the test scenarios from the test data template and execute them by calling the sub-functions in CommonHelper class 
* Author: Satyadeep Behera
*/

package com.FordFoundation.Fluxx;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.FordFoundation.workFlows.CommonHelper;
import com.jacada.objectRepository.FluxObjects;
import com.jacada.utilities.ReadingExcel;
@SuppressWarnings("all")
public class Fluxx_Grantee_Grant_Approval_Workflow extends CommonHelper {
	
	public static String testCaseColRange = null;
	FluxObjects fluxobs = new FluxObjects();


	//Fluxx Grantee and Grant Approval Workflow
	@Parameters({"view"})
	@Test(priority=1)
	public void Fluxx_Grant_Creation (String view) throws Throwable {

		if(view.equalsIgnoreCase("Web")) {
			int testCaseIndex = 0;
			String testCaseId = "";
			String desc = "";
			
			//Get total number of sheets in the excel test data template
			String filePath = "Resources/TestData/GranteeGrant_TestData/Grantee_Grant Approval Workflow_TestData_Template.xlsx";
			boolean stopFlag = false;
			int totalSheets = ReadingExcel.getRangeOfSheets(filePath);
			
			//Iterate through each sheet and read the test scenarios 
			for(int sheetNum=0;sheetNum<2;sheetNum++){
				
				//Get the test scenarios range in the sheet
				testCaseColRange = ReadingExcel.getRangeOfTestCases(filePath,"8",sheetNum);
				String[] testCaseCols = testCaseColRange.split("[~]");
				
				//Iterate through each test scenario and execute
				for (int i=Integer.parseInt(testCaseCols[0]),j=0;i<=Integer.parseInt(testCaseCols[1]);i++,j++) {
					try{
						//timeNow = LocalDateTime.now().toLocalTime().toString().split("[.]")[0].replaceAll(":", "");
						timeNow = "11";
						logout_fluxx();
						initializeReportValuesEachTestCase();
						testCaseIndex = j;
						
						//Get the fields and their values in a consolidated map data structure
						treemap = ReadingExcel.getFieldValues(filePath,3,i,sheetNum);
						testCaseId = treemap.get("Grant Approval Workflow_ TestData Template").toString();
						String reqType=treemap.get("RequestType").toString().trim();
						desc = treemap.get("Description").toString();
						String stopStatus = treemap.get("Control Flow_NA_NA_Stop_at _Status").toString().trim();
						System.out.println(treemap);
						testCaseSeparatorHeader(testCaseId.toString(),i,"");
					
						
						/*//Create New Request
						createNewGranteeViaRequest();
						
						//Create New Contact and associate the organization
						Create_Contact_and_Link_the_Organization_with_Super_User();
						
						//Fill in the details in Grantee external portal
						Fill_Grantee_Details_using_Grantee_Portal();
						
						//Review and approve by Grants Manager
						GM_reviewStage();
						
						//Review and Approve by Grants Processing Unit
						if(LegallyVerifyStage())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Fill in the relevant fields by Program Officer 
						if(PossibleStage())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Fill in the details in grantee external portal
						if(ReqForInfo_Grantee_Portal())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Review and approve the details by Program Officer
						if(InfoReviewStage())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Fill the details and submit by Program Director
						if(ProbableStage())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Fill the details and submit by Build Director
						if(reqType.toLowerCase().equals("build"))
						{
							if(BldDirectorPrjStage())
							{
								tearDownForEachTestCase();
								createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
								continue;
							}
						}
						
						//Fill the details and submit by Grants Manager
						if(ProjectedStage())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}*/
						
						//Fill the details and submit in external Grantee portal
						if(InviteStage())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Fill the details and submit by Program Officer
						if(ProgramreviewStage_PO())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Fill the details and submit by Grants Manager
						if(ProgramreviewStage_GM())
						{
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//Submit and Approve by Grants Processing unit
						if(ComplianceReviewStage())
						{							
							tearDownForEachTestCase();
							createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
							continue;
						}
						
						//while((stat.toLowerCase().equals(stopStatus)) || (stat.toLowerCase().equals("active")))
						while(!(stat.toLowerCase().equals(stopStatus.toLowerCase())))
						{
							String statLower = stat.toLowerCase().trim();
							switch(statLower)
							{
								case "director review":
									String dirInfo = GetDirectorLogin();
									String dirLogin = dirInfo.split("[~]")[0];
									String dirRole = dirInfo.split("[~]")[1];
									String dirDashboard = dirInfo.split("[~]")[2];
									String dirCardName = dirInfo.split("[~]")[3];
									ReviewStage ("Director Review", dirLogin, dirLogin, dirRole, dirDashboard, dirCardName, "Approve");
									break;
								case "build director review":
									String bldDirLogin = treemap.get("Login_NA_NA_Build Director_Login").toString().trim();
									String bldDirRole = "Build Director";
									String bldDirDashboard = "BUILD - Director";
									String bldDirCardName = "Requests dir Review";
									ReviewStage ("Build Director Review", bldDirLogin, bldDirLogin, bldDirRole, bldDirDashboard, bldDirCardName, "Approve");
									break;
								case "vp review":
									String vpInfo = GetVPLogin();
									String vpRvwLogin = vpInfo.split("[~]")[0];
									String vpRvwRole = vpInfo.split("[~]")[1];
									String vpRvwDashboard = vpInfo.split("[~]")[2];
									String vpRvwCardName = vpInfo.split("[~]")[3];
									ReviewStage ("VP Review", vpRvwLogin, vpRvwLogin, vpRvwRole, vpRvwDashboard, vpRvwCardName, "Approve");
									break;
								case "general counsel review":
									String gConLogin = treemap.get("Login_NA_NA_General Counsel_Login").toString().trim();
									String gConRole = "General Counsel";
									String gConDashboard = "General Counsel";
									String gConCardName = "general counsel rev";
									ReviewStage ("General Counsel Review", gConLogin, gConLogin, gConRole, gConDashboard, gConCardName, "Submit");
									break;
								case "president review":
									String prLogin = treemap.get("Login_NA_NA_President_Login").toString().trim();
									String prRole = "President";
									String prDashboard = "President Action";
									String prCardName = "Requests approval";
									ReviewStage ("President Review", prLogin, prLogin, prRole, prDashboard, prCardName, "Approve");
									break;
								case "pending audit review":
									String paLogin = treemap.get("Login_NA_NA_Grants Processing_Login").toString().trim();
									String paRole = "Grants Processing Unit";
									String paDashboard = "Compliance Action";
									String paCardName = "Pending Audit Review";
									ReviewStage ("Pending Audit Review", paLogin, paLogin, paRole, paDashboard, paCardName, "Approve");
									break;
								case "tag audit review":
									String taLogin = treemap.get("Login_NA_NA_General Counsel_Login").toString().trim();
									String taRole = "General Counsel";
									String taDashboard = "General Counsel";
									String taCardName = "tag audit rev";
									ReviewStage ("Tag Audit Review", taLogin, taLogin, taRole, taDashboard, taCardName, "Approve");
									break;
								case "agreement letter review":
									String agLogin = treemap.get("Login_NA_NA_General Counsel_Login").toString().trim();
									String agRole = "General Counsel";
									String agDashboard = "Legal/OLS ";
									String agCardName = GetCardNameForALRStage();
									ReviewStage ("Agreement Letter Review", agLogin, agLogin, agRole, agDashboard, agCardName, "Approve GAL");
									break;
								case "send agreement review":
									String saLogin = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
									String saRole = "Grants Manager";
									String saDashboard = "GM Grant Approval";
									String saCardName = "gal ready to send";
									ReviewStage ("Send Agreement Review", saLogin, saLogin, saRole, saDashboard, saCardName, "Approve");
									break;
								case "pending cgal":
									String pcLogin = treemap.get("Login_NA_NA_Grants Manager_Login").toString().trim();
									String pcRole = "Grants Manager";
									String pcDashboard = "GM Grant Approval";
									String pcCardName = "PENDING c-GAL";
									//ReviewStage ("PENDING c-GAL", pcLogin, pcLogin, pcRole, pcDashboard, pcCardName, "Approve");
									Pending_CGALStage();
									break;
								default:
									break;
							}
						}
						
						
						
						//Log out from Fluxx 
						logout_fluxx();
						tearDownForEachTestCase();
						
						//Create the summary report for the test scenario
						createHtmlSummaryReportContent(j,treemap.get("Grant Approval Workflow_ TestData Template").toString().trim(),desc);
						
						
						
						
						/*createNewGranteeViaRequest(organization_name, grantee_firstname, grantee_lastname,grantee_mailId);
						Create_Contact_and_Link_the_Organization_with_Super_User(grantee_role, grantee_program, grantee_Loginname,password,grantee_firstname,grantee_lastname,grantee_mailId,organization_name);
						Fill_Grantee_Details_using_Grantee_Portal(grantee_Loginname, password, grantee_firstname, grantee_lastname, grantee_mailId, organization_name);
						GM_reviewStage(organization_name);  
						LegallyVerifyStage(organization_name); 
						PossibleStage(organization_name); 
						ProbableStage(organization_name,"auto_director1"); */
						/*ProjectedStage(organization_name);    
						InviteStage(grantee_Loginname, password, organization_name); 
						ProgramreviewStage_PO(organization_name);                        
						ProgramreviewStage_GM(organization_name); 	
						ComplianceReviewStage2(organization_name);   
						ReviewStages_default("director_tester2", "Default", organization_name, "VP Review","Submit");
						ReviewStages_default("vice_president2", "Default", organization_name, "OLS Review","Submit");
						ReviewStages("general_counsel1", "General Counsel", organization_name, "President Review","Submit");
						ReviewStages("president_tester2", "President Action", organization_name, "Agreement Letter Review","Approve");
						ReviewStages2a("general_counsel1", "General Counsel", organization_name, "Send Agreement Letter ","Approve GAL","agreement ltr rev");
						ReviewStages2("grants_manager3", "GM Grant Approval", organization_name, "Pending cGAL","Send to Pending cGAL","gal ready to send");
						Pending_CGALStage("grants_manager3", "GM Grant Approval", organization_name, "Active","cGAL Complete");*/
					}
					
					catch(Exception e)
					{
						failureReport("Create Fluxx Grant", "Grant creation failed");
						count = count+1;
						logout_fluxx();
						tearDownForEachTestCase();
						createHtmlSummaryReportContent(testCaseIndex,testCaseId,desc);
					}
				}
				
			}
			
		}     

	} 


}
