
package com.jacada.serviceUtil;  

import com.jacada.accelerators.TestEngine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;


public class AdminConsoleServicesHelper extends TestEngine {
	public String getResponseUpdateAppAttribute="";
	boolean code=false;
	public StringBuffer reportFile = new StringBuffer();
	public int responseCode;

	/***
	 * updateInteractionSecurityType
	 * This is to update the security type (SECURED/OPEN) for interaction 
	 * @param fileName
	 * @param securityType ex: SECURED,OPEN
	 * @return
	 */
	public boolean updateInteractionSecurityType(String account, String fileName, String securityType,String folderName ) {

		try{

			File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String designerId = getAttributeValue(fileContent, "header designerId");

			System.out.println("Value of designerId is: "+designerId);

			String versionId = getAttributeValue(fileContent, "header version id");

			System.out.println("Value of Version Id is: "+versionId);

			String getServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/headers/designer/"+designerId+"/version/"+versionId;

			String getResponse = getServiceResponse(account, "GET", getServiceUrl, null);
			System.out.println("printing response"+getResponse);

			String interactionId = getAttributeValue(getResponse, "id");

			System.out.println("Value of interactionId is: "+interactionId);

			String putServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/"+interactionId+"/security/"+securityType;

			String putResponse = getServiceResponse(account, "PUT", putServiceUrl, getResponse);

			System.out.println("printing response"+putResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}

	/***
	 * createApplication
	 * This is to create new application with specific theme 
	 * @param existingApplicationName
	 * @param newApplicationName
	 * @param themeName
	 * @return
	 */

	public boolean createApplication(String accountName, String existingApplicationName, String newApplicationName, String themeName) {
		try{

			String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";

			String applicationId = getApplicationId(accountName, existingApplicationName);

			System.out.println("Application id is: "+applicationId);

			String getAppResponse = getServiceResponse(accountName, "GET", getApplicationsServiceUrl+"/"+applicationId, null);

			System.out.println("App Response"+getAppResponse);

			String updatedResponseData = removeAttribute(getAppResponse, "id");

			System.out.println("updatedResponseData"+updatedResponseData);

			updatedResponseData = removeAttribute(updatedResponseData, "internalClientId");

			System.out.println("updatedResponseData"+updatedResponseData);

			updatedResponseData = updateAttributeValue(updatedResponseData, "name", newApplicationName);

			System.out.println("updatedResponseData after updating name"+updatedResponseData);

			String themeId = getApplicationThemeId(accountName, themeName);
			updatedResponseData = updateAttributeValue(updatedResponseData, "theme id", themeId);

			System.out.println("updatedResponseData after updating theme id"+updatedResponseData);

			String postResponse = getServiceResponse(accountName, "POST", getApplicationsServiceUrl, updatedResponseData);

			System.out.println("Post Response: "+postResponse);

			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}


	/***
	 * updateChatSettings
	 * This method is to update chat settings at account level
	 * @return
	 */
	/*public boolean updateChatSettings() {

		try{

			File file = new File(configProps.getProperty("TestData")+"ChatSettings.txt");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String chatServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/chat/settings";

			String putResponse = getServiceResponse("PUT", chatServiceUrl, fileContent);

			System.out.println("printing response"+putResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}*/

	/***
	 * updateChatSettings
	 * This method is to update chat settings at account level
	 * @return
	 */
	public boolean updateChatSettings(String account) {

		try{

			File file = new File(configProps.getProperty("TestData")+"ChatSettings.txt");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String chatServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/chat/settings";

			String putResponse = getServiceResponse(account,"PUT", chatServiceUrl, fileContent);

			System.out.println("printing response"+putResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}

	/***
	 * updateApplicationAttribute
	 * This method is to update the attributes of specific application
	 * @param applicationName ex: Mobile Web app , Web  Self Service etc.. 
	 * @param attributeName ex: timeoutOfNavigation 
	 * @param attributeValue
	 * @return
	 */
	/*public boolean updateApplicationAttribute(String applicationName, String attributeName, String attributeValue) {

		try{

			String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";

			String applicationId = getApplicationId(applicationName);

			System.out.println("Application id is: "+applicationId);

			String getAppResponse = getServiceResponse("GET", getApplicationsServiceUrl+"/"+applicationId, null);

			System.out.println("App Response"+getAppResponse);

			String updatedResponseData = updateAttributeValue(getAppResponse, attributeName, attributeValue);

			String putResponse = getServiceResponse("PUT", getApplicationsServiceUrl, updatedResponseData);

			System.out.println("Put Response: "+putResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}*/


	public boolean updateApplicationAttribute(String accountName, String applicationName, String attributeName, String attributeValue) {

		try{

			String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";

			String applicationId = getApplicationId(accountName, applicationName);

			System.out.println("Application id is: "+applicationId);

			String getAppResponse = getServiceResponse(accountName, "GET", getApplicationsServiceUrl+"/"+applicationId, null);

			System.out.println("App Response"+getAppResponse);

			String updatedResponseData = updateAttributeValue(getAppResponse, attributeName, attributeValue);

			System.out.println("Put Response: "+updatedResponseData);

			String putResponse = getServiceResponse(accountName, "PUT", getApplicationsServiceUrl, updatedResponseData);

			System.out.println("Put Response: "+putResponse);

			reportFile.append("applicationId :"+applicationId); 
			reportFile.append("\n\n\n\n--------------------------");
			reportFile.append("\n getResponse: \n"+getAppResponse);
			reportFile.append("\n\n\n\n--------------------------");
			reportFile.append("\n getUpdatedResponse: \n"+updatedResponseData);
			reportFile.append("\n\n\n\n");
			reportFile.append("putResponsecode Status : \n"+responseCode);

			return code;   

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}



	public boolean updateVoiceSettings(String account, String callMethod) {

		try{

			String getVoiceSettingsUrl = Protocol+"://"+HostName+":"+Port+"/interact/dnis/settings";

			String getVoiceResponse = getServiceResponse(account, "GET", getVoiceSettingsUrl, null);

			System.out.println("App Response"+getVoiceResponse);

			String updatedResponseData = updateAttributeValue(getVoiceResponse, "mode", callMethod);

			if(callMethod.equalsIgnoreCase("DNIS")){
				updatedResponseData = updateAttributeValue(updatedResponseData, "timeout", "5");
			}
			else{
				updatedResponseData = updateAttributeValue(updatedResponseData, "timeout", "0");
			}

			String postResponse = getServiceResponse(account, "POST", getVoiceSettingsUrl, updatedResponseData);

			System.out.println("Post Response: "+postResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}


	public boolean addDnisNumber(String account, String phoneNo) {

		try{

			File file = new File(configProps.getProperty("TestData")+"DNISnumber.txt");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			fileContent = updateAttributeValue(fileContent, "phoneNumber", phoneNo);

			String dnisServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/dnis";

			String postResponse = getServiceResponse(account, "POST", dnisServiceUrl, fileContent);

			System.out.println("printing response"+postResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}

	public boolean addDnisNumber(String account, String phoneNo, String groupName) {

		try{

			File file = new File(configProps.getProperty("TestData")+"DNISnumber.txt");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			fileContent = updateAttributeValue(fileContent, "phoneNumber", phoneNo);

			fileContent = updateAttributeValue(fileContent, "groupName", groupName);

			String dnisServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/dnis";

			String postResponse = getServiceResponse(account, "POST", dnisServiceUrl, fileContent);

			System.out.println("printing response"+postResponse);

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}


	public boolean updateAccountLicense(String user,String license) {

		try{

			File file = new File(configProps.getProperty("TestData")+license+".txt");

			String fileContent = null;
			fileContent = FileUtils.readFileToString(file, "utf-8");

			CreateAccountAPIServiceHelper createAccountAPIServiceHelper = new CreateAccountAPIServiceHelper();
			String accessTokenUrl = Protocol+"://"+HostName+":"+Port+"/interact/authenticate/login";

			String postResponse = createAccountAPIServiceHelper.getResponse("POST", accessTokenUrl, "", AdminUserId, AdminPassword);

			System.out.println("printing response"+postResponse);

			String accessToken = getAttributeValue(postResponse, "Access-Token");
			System.out.println("Access token: "+accessToken);

			String accountId = createAccountAPIServiceHelper.getAccountId(user);
			System.out.println("Account Id: "+accountId);

			String accountLicenseServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/tenant/license";

			URL url = new URL(accountLicenseServiceUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Chrome/31.0");
			con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
			con.setRequestProperty("Access-Token", accessToken);
			con.setRequestProperty("Tenant-ClientId", accountId);
			con.setRequestProperty("Tenant-Id", "system");
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(fileContent.getBytes());
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("Response code:"+responseCode);
			if((responseCode == 200) || (responseCode == 201)){
				code=true;
			}
			else{
				code=false;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();  

			con.disconnect();

			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}


	public boolean updateInteractionStatus(String account, String fileName, String status, String folderName) {

		try{

			File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String designerId = getAttributeValue(fileContent, "header designerId");

			System.out.println("Value of designerId is: "+designerId);

			String versionId = getAttributeValue(fileContent, "header version id");

			System.out.println("Value of Version Id is: "+versionId);

			String getServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/headers/designer/"+designerId+"/version/"+versionId;

			String getResponse = getServiceResponse(account, "GET", getServiceUrl, null);
			System.out.println("printing response"+getResponse);

			String interactionId = getAttributeValue(getResponse, "id");

			System.out.println("Value of interactionId is: "+interactionId);

			String putServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/"+interactionId+"/"+status;

			String putResponse = getServiceResponse(account, "PUT", putServiceUrl, "");

			System.out.println("printing response"+putResponse);

			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	public String getAdminModeUrl(String accountName, String fileName,String folderName) {

		try{

			String accessTokenServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/authenticate/login";

			String postResponse = getServiceResponse(accountName, "POST", accessTokenServiceUrl, "");

			System.out.println("printing response"+postResponse);

			String accessToken = getAttributeValue(postResponse, "Access-Token");

			System.out.println("Value of Access Token is: "+accessToken);

			File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String designerId = getAttributeValue(fileContent, "header designerId");

			System.out.println("Value of designerId is: "+designerId);

			String versionId = getAttributeValue(fileContent, "header version id");

			System.out.println("Value of Version Id is: "+versionId);

			String adminModeServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/link/designer/"+designerId+"/versions/"+versionId+"?appKey="+getApplicationAttribute(accountName, "Mobile Web App", "internalClientId");

			String getResponse = getAdminServiceResponse(accountName, adminModeServiceUrl, accessToken);

			System.out.println("printing response"+getResponse);

			String adminUrl = getAttributeValue(getResponse, "link");

			return adminUrl+"&mode=admin";

		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}

	public String getAdminModeUrl(String accountName,String application, String fileName,String folderName) {

		try{

			String accessTokenServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/authenticate/login";

			String postResponse = getServiceResponse(accountName, "POST", accessTokenServiceUrl, "");

			System.out.println("printing response"+postResponse);

			String accessToken = getAttributeValue(postResponse, "Access-Token");

			System.out.println("Value of Access Token is: "+accessToken);

			File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String designerId = getAttributeValue(fileContent, "header designerId");

			System.out.println("Value of designerId is: "+designerId);

			String versionId = getAttributeValue(fileContent, "header version id");

			System.out.println("Value of Version Id is: "+versionId);

			String adminModeServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/link/designer/"+designerId+"/versions/"+versionId+"?appKey="+getApplicationAttribute(accountName, application, "internalClientId");
			if(application.contains("Web Self Service")){
				adminModeServiceUrl=adminModeServiceUrl+"&wssType=FULL_PAGE";
			}
			String getResponse = getAdminServiceResponse(accountName, adminModeServiceUrl, accessToken);

			System.out.println("printing response"+getResponse);

			String adminUrl = getAttributeValue(getResponse, "link");

			return adminUrl+"&mode=admin";

		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}
	/***
	 * getApplicationId
	 * This method gets the application ID 
	 * @param applicationName
	 * @return
	 * @throws Exception
	 */

	/*public String getApplicationId(String applicationName) throws Exception{

		String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";
		String content = getServiceResponse("GET", getApplicationsServiceUrl, null);
		System.out.println("\"name\" : \""+applicationName+"\"");
		content = content.substring(0, content.indexOf("\"name\" : \""+applicationName+"\""));

		return getAttributeValue(content.substring(content.lastIndexOf("id")-1), "id");
	}*/

	/***
	 * getAttributeValue
	 * This method gets value of the specific attribute in the data 
	 * @param inputData
	 * @param attribute ex: timeoutOfNavigation
	 * @return
	 */

	public String getAttributeValue(String inputData, String attribute){

		String content = inputData;
		String[] contentArray;

		String[] attributeContentArray = attribute.split("\\s+");

		System.out.println("Attributes content length"+attributeContentArray.length);

		int i=0;

		while(i<attributeContentArray.length-1){
			contentArray = content.split("\""+attributeContentArray[i]+"\" : ");
			content = contentArray[1];
			i++;
		}

		contentArray = content.split("\""+attributeContentArray[i]+"\" : ");

		try{
			content = contentArray[1].substring(0,contentArray[1].indexOf(","));
		}
		catch(StringIndexOutOfBoundsException e){
			content = contentArray[1].substring(0,contentArray[1].indexOf("}"));
		}

		if(content.contains("\"")){
			return content.replaceAll("\"", "");
		}else{
			return content;
		}

	}


	public String getAttributeValue_Mailgun(String inputData, String attribute){

		String content = inputData;
		String[] contentArray;

		String[] attributeContentArray = attribute.split("\\s+");

		System.out.println("Attributes content length"+attributeContentArray.length);

		int i=0;

		while(i<attributeContentArray.length-1){
			contentArray = content.split("\""+attributeContentArray[i]+"\": ");
			content = contentArray[1];
			i++;
		}

		contentArray = content.split("\""+attributeContentArray[i]+"\": ");

		try{
			content = contentArray[1].substring(0,contentArray[1].indexOf(","));
		}
		catch(StringIndexOutOfBoundsException e){
			content = contentArray[1].substring(0,contentArray[1].indexOf("}"));
		}

		if(content.contains("\"")){
			return content.replaceAll("\"", "");
		}else{
			return content;
		}

	}

	/***
	 * updateAttributeValue
	 * This  method updates the specific attribute value in the given data
	 * @param inputData
	 * @param attributeName ex: timeoutOfNavigation
	 * @param attributeValue ex: 1
	 * @return
	 */

	public String updateAttributeValue(String inputData, String attributeName, String attributeValue){

		String content = inputData;
		String[] contentArray = null;
		String updatedAttributeValue;
		StringBuffer updatedContent = new StringBuffer();

		if(attributeName.contains(" ")){
			String[] attributes = attributeName.split(" ");
			System.out.println("Attributes length: "+attributes.length);
			for(int i=0; i<attributes.length;i++){
				updatedContent.append(content.substring(0, content.indexOf(attributes[i])-1));
				content = content.substring(content.indexOf(attributes[i])-1);
			}

			contentArray = content.split("\""+attributes[attributes.length-1]+"\" : ");

			if(contentArray[1].charAt(0) == '\"'){
				updatedAttributeValue = "\""+attributes[attributes.length-1]+"\" : \""+attributeValue+"\"";
			}
			else{
				updatedAttributeValue = "\""+attributes[attributes.length-1]+"\" : "+attributeValue+"";
			}

			System.out.println("updatedAttributeValue: "+updatedAttributeValue);
			updatedContent.append(updatedAttributeValue);
			updatedContent.append(content.substring(content.indexOf(",")));

			return updatedContent.toString();

		}else{
			updatedContent.append(content.substring(0, content.indexOf(attributeName)-1));
			content = content.substring(content.indexOf(attributeName)-1);
			contentArray = content.split("\""+attributeName+"\" : ");

			if(contentArray[1].charAt(0) == '\"'){
				updatedAttributeValue = "\""+attributeName+"\" : \""+attributeValue+"\"";
			}
			else{
				updatedAttributeValue = "\""+attributeName+"\" : "+attributeValue+"";
			}

			System.out.println("updatedAttributeValue: "+updatedAttributeValue);

			updatedContent.append(updatedAttributeValue);
			updatedContent.append(content.substring(content.indexOf(",")));

			return updatedContent.toString();

		}
	}


	/***
	 * getServiceResponse
	 * This method is to get the response for any type of given service 
	 * @param serviceType ex: GET,PUT,POST
	 * @param serviceUrl  ex: service url of get application etc... 
	 * @param requestBody ex:Json request body 
	 * @return
	 * @throws Exception
	 */

	/*public String getServiceResponse(String serviceType, String serviceUrl, String requestBody) throws Exception {

		System.out.println("serviceUrl: "+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(serviceType);
		con.setRequestProperty("User-Agent", "Chrome/31.0");

		con.setRequestProperty("Tenant-Id", AccountId);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.setRequestProperty("user-Id", new String(Base64.encodeBase64(UserId.getBytes())));
		con.setRequestProperty("user-Password", new String(Base64.encodeBase64(Password.getBytes())));

		if(serviceType.equalsIgnoreCase("PUT") || serviceType.equalsIgnoreCase("POST")){
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(requestBody.getBytes());
			wr.flush();
			wr.close();
		}
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		if((responseCode == 200) || (responseCode == 201) || (responseCode == 409) || (responseCode == 204)){
			code=true;
		}
		else{
			code=false;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		con.disconnect();
		return response.toString();

	}*/


	/***
	 * getServiceResponse
	 * This method is to get the response for any type of given service 
	 * @param serviceType ex: GET,PUT,POST
	 * @param serviceUrl  ex: service url of get application etc... 
	 * @param requestBody ex:Json request body 
	 * @return
	 * @throws Exception
	 */

	public String getServiceResponse(String user,String serviceType, String serviceUrl, String requestBody) throws Exception {

		System.out.println("serviceUrl: "+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(serviceType);
		con.setRequestProperty("User-Agent", "Chrome/31.0");

		con.setRequestProperty("Tenant-Id", user);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.setRequestProperty("user-Id", new String(Base64.encodeBase64(UserId.getBytes())));
		con.setRequestProperty("user-Password", new String(Base64.encodeBase64(Password.getBytes())));

		if(serviceType.equalsIgnoreCase("PUT") || serviceType.equalsIgnoreCase("POST")){
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(requestBody.getBytes());
			wr.flush();
			wr.close();
		}
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		if((responseCode == 200) || (responseCode == 201) || (responseCode == 409) || (responseCode == 204)){
			code=true;
		}
		else{
			code=false;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			response.append("\n");
		}
		in.close();
		con.disconnect();
		return response.toString();

	}


	public String getAdminServiceResponse(String accountName, String serviceUrl, String accessToken) throws Exception {

		System.out.println("serviceUrl: "+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Chrome/31.0");

		con.setRequestProperty("Tenant-Id", accountName);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
		con.setRequestProperty("Access-Token", accessToken);

		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		if((responseCode == 200)){
			code=true;
		}
		else{
			code=false;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		con.disconnect();

		return response.toString();

	}
	public String getDesignerModeUrl(String accountName, String fileName,String folderName) {

		try{

			String accessTokenServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/authenticate/login";

			String postResponse = getServiceResponse(accountName, "POST", accessTokenServiceUrl, "");

			System.out.println("printing response"+postResponse);

			String accessToken = getAttributeValue(postResponse, "Access-Token");

			System.out.println("Value of Access Token is: "+accessToken);

			File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String designerId = getAttributeValue(fileContent, "header designerId");

			System.out.println("Value of designerId is: "+designerId);

			String versionId = getAttributeValue(fileContent, "header version id");

			System.out.println("Value of Version Id is: "+versionId);

			String designerModeServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/link/designer/"+designerId+"/versions/"+versionId+"?appKey="+getApplicationAttribute(accountName, "Mobile Web App", "internalClientId");

			String getResponse = getAdminServiceResponse(accountName, designerModeServiceUrl, accessToken);

			System.out.println("printing response"+getResponse);

			String designerUrl = getAttributeValue(getResponse, "link");

			return designerUrl+"&mode=designer";

		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}

	public String getDesignerModeUrl(String accountName,String application, String fileName,String folderName) {

		try{

			String accessTokenServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/authenticate/login";
			String postResponse = getServiceResponse(accountName, "POST", accessTokenServiceUrl, "");

			System.out.println("printing response"+postResponse);

			String accessToken = getAttributeValue(postResponse, "Access-Token");

			System.out.println("Value of Access Token is: "+accessToken);

			File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			String designerId = getAttributeValue(fileContent, "header designerId");

			System.out.println("Value of designerId is: "+designerId);

			String versionId = getAttributeValue(fileContent, "header version id");

			System.out.println("Value of Version Id is: "+versionId);

			String designerModeServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition/link/designer/"+designerId+"/versions/"+versionId+"?appKey="+getApplicationAttribute(accountName, application, "internalClientId");
			if(application.contains("Web Self Service")){
				designerModeServiceUrl=designerModeServiceUrl+"&wssType=FULL_PAGE";
			}
			String getResponse = getAdminServiceResponse(accountName, designerModeServiceUrl, accessToken);

			System.out.println("printing response"+getResponse);

			String designerUrl = getAttributeValue(getResponse, "link");

			return designerUrl+"&mode=designer";

		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}

	/***
	 * getApplicationAttribute
	 * @param applicationName
	 * @param attributeName
	 * @return
	 */
	/*public String getApplicationAttribute(String applicationName, String attributeName) {

		try{

			String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";

			String applicationId = getApplicationId(applicationName);

			System.out.println("Application id is: "+applicationId);

			String getAppResponse = getServiceResponse("GET", getApplicationsServiceUrl+"/"+applicationId, null);

			return getAttributeValue(getAppResponse, attributeName);

		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}*/
	/***
	 * getApplicationAttribute
	 * @param applicationName
	 * @param attributeName
	 * @return
	 */
	public String getApplicationAttribute(String accountName, String applicationName, String attributeName) {

		try{

			String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";

			String applicationId = getApplicationId(accountName, applicationName);

			System.out.println("Application id is: "+applicationId);

			String getAppResponse = getServiceResponse(accountName, "GET", getApplicationsServiceUrl+"/"+applicationId, null);

			return getAttributeValue(getAppResponse, attributeName);

		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}

	/***
	 * removeAttribute
	 * This method is to remove specific attribute and it's value from the response data provided
	 * @param response
	 * @param attribute
	 * @return
	 * @throws Exception
	 */
	public String removeAttribute(String response, String attribute) throws Exception{

		StringBuffer updatedResponse = new StringBuffer();

		updatedResponse.append(response.substring(0, response.indexOf("\""+attribute+"\"")));

		String response2 = response.substring(response.indexOf("\""+attribute+"\""));
		updatedResponse.append(response2.substring(response2.indexOf(",")+1));

		return updatedResponse.toString();
	}

	/***
	 * getApplicationThemeId
	 * This method gets the application theme
	 * @param themeName
	 * @return
	 * @throws Exception
	 */
	public String getApplicationThemeId(String accountName, String themeName) throws Exception{

		String getThemesServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/resources/themes";
		String content = getServiceResponse(accountName, "GET", getThemesServiceUrl, null);
		System.out.println("\"name\" : \""+themeName+"\"");
		String[] contentArray = content.split("\"name\" : \""+themeName+"\"");
		return getAttributeValue(contentArray[0].substring(contentArray[0].lastIndexOf("id")-1), "id");
	}

	/*public boolean deleteDnisNumber(String phoneNo) {
		try{
			String dnisServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/dnis";
			String getResponse = getServiceResponse("GET", dnisServiceUrl, null);
			System.out.println("printing response"+getResponse);
			getResponse = getResponse.substring(0, getResponse.indexOf("\"phoneNumber\" : \""+phoneNo+"\""));
			System.out.println("getResponse: "+getResponse);
			String phoneId = getAttributeValue(getResponse.substring(getResponse.lastIndexOf("id")-1), "id");
			getServiceResponse("DELETE", dnisServiceUrl+"/"+phoneId, null);
			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}*/


	public boolean createFolderWithInteractions(String account, String folderName, String interactionDesignerIds) {
		try{
			String createFolderServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/folder/interaction";
			StringBuffer requestBody = new StringBuffer();
			requestBody.append("{ \"name\": \""+folderName+"\", \"interactionDesignerIds\": [");

			if(interactionDesignerIds.contains(",")){
				String[] interactionDesignerId = interactionDesignerIds.split(",");
				requestBody.append("\""+interactionDesignerId[0]+"\"");

				for(int i=1; i<interactionDesignerId.length; i++){
					requestBody.append(", \""+interactionDesignerId[i]+"\"");
				}
			}else{
				requestBody.append("\""+interactionDesignerIds+"\"");
			}

			requestBody.append("], \"order\": 0 }");
			System.out.println("Request Body: "+requestBody.toString());

			getAgentAppServiceResponse("POST", createFolderServiceUrl, requestBody.toString(), account);
			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}


	public String getAgentAppServiceResponse(String serviceType, String serviceUrl, String requestBody, String account) throws Exception {
		System.out.println("serviceUrl: "+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(serviceType);
		con.setRequestProperty("User-Agent", "Chrome/31.0");

		con.setRequestProperty("Tenant-Id", account);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.setRequestProperty("user-Id", new String(Base64.encodeBase64(UserId.getBytes())));
		con.setRequestProperty("user-Password", new String(Base64.encodeBase64(Password.getBytes())));
		con.setRequestProperty("Postman-Token", "65563239-465f-44a3-2366-2cb603f1a3db");


		if(serviceType.equalsIgnoreCase("PUT") || serviceType.equalsIgnoreCase("POST")){
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(requestBody.getBytes());
			wr.flush();
			wr.close();
		}
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		if((responseCode == 200) || (responseCode == 201) || (responseCode == 409) || (responseCode == 204)){
			code=true;
		}
		else{
			code=false;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		con.disconnect();

		return response.toString();

	}

	public boolean deleteFolder(String account, String folderName) {
		try{
			removeInteractionsFromFolder(account, folderName, "all");

			String agentAppServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/folder/interaction";
			String getResponse = getAgentAppServiceResponse("GET", agentAppServiceUrl, "", account);
			getResponse = getResponse.substring(0, getResponse.indexOf("\"name\" : \""+folderName+"\""));

			String folderId = getAttributeValue(getResponse.substring(getResponse.lastIndexOf("id")-1), "id");

			String deleteResponse = getAgentAppServiceResponse("DELETE", agentAppServiceUrl+"/"+folderId, "", account);
			System.out.println("Delete Response: "+deleteResponse);

			return code;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	public boolean removeInteractionsFromFolder(String account, String folderName, String interactionDesignerIds) {

		try{

			String agentAppServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/folder/interaction";
			String getResponse = getAgentAppServiceResponse("GET", agentAppServiceUrl, "", account);
			getResponse = getResponse.substring(0, getResponse.indexOf("\"name\" : \""+folderName+"\""));

			String folderId = getAttributeValue(getResponse.substring(getResponse.lastIndexOf("id")-1), "id");
			getResponse = getAgentAppServiceResponse("GET", agentAppServiceUrl+"/"+folderId, "", account);
			System.out.println("Actual Response: "+getResponse);

			if(interactionDesignerIds.equalsIgnoreCase("all")){

				String response1 = getResponse.substring(0, getResponse.indexOf("[")+1);
				String response2 = getResponse.substring(getResponse.indexOf("]"));

				getResponse = response1+response2;
			}
			else{            
				String[] interactionDesignerId = interactionDesignerIds.split(",");

				for(int i=0; i<interactionDesignerId.length; i++){

					if(getResponse.contains(interactionDesignerId[i])){
						String[] responses = getResponse.split("\""+interactionDesignerId[i]+"\"");

						//System.out.println("Response[0]: "+responses[0]);
						//System.out.println("Response[1]: "+responses[1]);

						if(responses[1].indexOf(',') == 0){
							responses[1] = responses[1].substring(1);    
							//System.out.println("Response[1] after removing comma: "+responses[1]);
						}
						else{
							responses[0] = responses[0].substring(0, responses[0].length()-2);    
							//System.out.println("Response[0] after removing comma: "+responses[0]);
						}
						getResponse = responses[0] + responses[1];
					}
				}
			}

			System.out.println("Updated Response: "+getResponse);
			String putResponse = getAgentAppServiceResponse("PUT", agentAppServiceUrl, getResponse, account);

			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public String getApplicationId(String accountName, String applicationName) throws Exception{

		String getApplicationsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/appkeydata";
		String content = getServiceResponse(accountName, "GET", getApplicationsServiceUrl, null);
		System.out.println("\"name\" : \""+applicationName+"\"");
		content = content.substring(0, content.indexOf("\"name\" : \""+applicationName+"\""));

		return getAttributeValue(content.substring(content.lastIndexOf("id")-1), "id");
	}

	public String[] getDashboardResponse(String accountName,String urlPoint) {

		String[] responseData = new String[2];
		String dashboardServiceUrl =null;
		try{
			Date todayDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, 1); //minus number would decrement the days
			Date tomorrowDate = cal.getTime();
			System.out.println("Tomorrows date: "+tomorrowDate);
			long tomorrowEpochTime = tomorrowDate.getTime();

			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -1); //minus number would decrement the days
			Date yesterdayDate = cal.getTime();
			System.out.println("Yesterday date: "+yesterdayDate);
			long yesterdayEpochTime = yesterdayDate.getTime();
			if(urlPoint.contains("usage")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/usage?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440"; }
			else if(urlPoint.contains("multitouch")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/multitouch?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}  
			else if(urlPoint.contains("callresolution")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/callresolution?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}  
			else if(urlPoint.contains("topactive")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/topactive?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}  
			else if(urlPoint.contains("connectivity")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/connectivity?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";} 
			String getResponse = getServiceResponse(accountName,"GET", dashboardServiceUrl, null);


			responseData[0] = Boolean.toString(code);
			responseData[1] = getResponse;

			return responseData;
		}catch(Exception e){
			responseData[0] = Boolean.toString(code);
			responseData[1] = null;
			return responseData;
		}
	}

	public String[] getDashboardResponse(String accountName,String designerID,String urlPoint) {

		String[] responseData = new String[2];
		String dashboardServiceUrl =null;
		try{
			Date todayDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, 1); //minus number would decrement the days
			Date tomorrowDate = cal.getTime();
			System.out.println("Tomorrows date: "+tomorrowDate);
			long tomorrowEpochTime = tomorrowDate.getTime();

			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -1); //minus number would decrement the days
			Date yesterdayDate = cal.getTime();
			System.out.println("Yesterday date: "+yesterdayDate);
			long yesterdayEpochTime = yesterdayDate.getTime();

			if(urlPoint.contains("usage")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/usage?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440"; }
			else if(urlPoint.contains("multitouch")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/multitouch?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}  
			else if(urlPoint.contains("callresolution")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/callresolution?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}
			else if(urlPoint.contains("topactive")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/topactive?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&designerid="+designerID+"&granularity=1440";}  
			String getResponse = getServiceResponse(accountName,"GET", dashboardServiceUrl, null);


			responseData[0] = Boolean.toString(code);
			responseData[1] = getResponse;

			return responseData;
		}catch(Exception e){
			responseData[0] = Boolean.toString(code);
			responseData[1] = null;
			return responseData;
		}
	}

	public String[] getDashboardResponse(String accountName,String designerID,String urlPoint,String accessToken) {

		String[] responseData = new String[2];
		String dashboardServiceUrl =null;
		try{
			Date todayDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, 1); //minus number would decrement the days
			Date tomorrowDate = cal.getTime();
			System.out.println("Tomorrows date: "+tomorrowDate);
			long tomorrowEpochTime = tomorrowDate.getTime();

			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -1); //minus number would decrement the days
			Date yesterdayDate = cal.getTime();
			System.out.println("Yesterday date: "+yesterdayDate);
			long yesterdayEpochTime = yesterdayDate.getTime();
			if(urlPoint.contains("usage")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/usage?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440"; }
			else if(urlPoint.contains("multitouch")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/multitouch?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}  
			else if(urlPoint.contains("concurrency")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/concurrency?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}  
			else if(urlPoint.contains("callresolution")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/callresolution?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&granularity=1440";}
			else if(urlPoint.contains("topactive")){
				dashboardServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/dashboard/topactive?timefrom="+yesterdayEpochTime+"&timeto="+tomorrowEpochTime+"&designerid="+designerID+"&granularity=1440";}  
			String getResponse = getAdminServiceResponse(accountName, dashboardServiceUrl, accessToken);


			responseData[0] = Boolean.toString(code);
			responseData[1] = getResponse;

			return responseData;
		}catch(Exception e){
			responseData[0] = Boolean.toString(code);
			responseData[1] = null;
			return responseData;
		}
	}

	public String[] getAccessToken(String accountName) {
		String [] response=new String[3];
		try{

			String accessTokenServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/authenticate/login";

			String postResponse = getServiceResponse(accountName, "POST", accessTokenServiceUrl, "");
			if (code==true){
				response[0]="200";
			}
			System.out.println("printing response"+postResponse);

			String accessToken = getAttributeValue(postResponse, "Access-Token");

			System.out.println("Value of Access Token is: "+accessToken);
			response[1]=accessToken;
			response[2]=postResponse;


			return response;

		}catch(Exception e){
			//e.printStackTrace();
			return response;
		}

	}

	public boolean executeIpMappingService(String accountName, String parameter, String key, String value) {

		try{

			String postIpMappingServiceUrl = Protocol+"://"+HostName+":"+Port+"/VoiceProxy/version/1/account/"+accountName+"/Interaction/"+parameter+"/PutMapping/"+key+"/Value/"+java.net.URLEncoder.encode(value,"UTF-8");

			String getAppResponse = getServiceResponse(accountName,"POST", postIpMappingServiceUrl, "");
			System.out.println("App Response"+getAppResponse);
			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	public boolean executeIpGetMappingService(String accountName, String parameter,String InteractionUniqueID) {

		try{

			String postIpMappingServiceUrl = Protocol+"://"+HostName+":"+Port+"/VoiceProxy/version/1/account/"+accountName+"/Interaction/"+parameter+"/GetMapping/IUI";

			String getAppResponse = getServiceResponse(accountName,"GET", postIpMappingServiceUrl, "");
			System.out.println("App Response"+getAppResponse);
			InteractionUniqueID=InteractionUniqueID.trim();
			if(InteractionUniqueID.equals(getAppResponse.trim()))
				return code;
			else
				return false;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	public boolean executeIpInitService(String accountName, String parameter) {

		try{

			String postIpInitServiceUrl = Protocol+"://"+HostName+":"+Port+"/VoiceProxy/version/1/account/"+accountName+"/Interaction/"+parameter+"/Init";

			String getAppResponse = getServiceResponse(accountName,"POST", postIpInitServiceUrl, "");
			System.out.println("App Response"+getAppResponse);
			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}


	public String[] executeIpService(String serviceType, String serviceUrl, String account, String appKey, String requestBody) throws Exception {
		String[] responseData = new String[3];
		System.out.println("serviceUrl: "+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(serviceType);
		con.setRequestProperty("User-Agent", "Chrome/31.0");

		con.setRequestProperty("Tenant-Id", account);
		con.setRequestProperty("Application-Key", appKey);
		con.setRequestProperty("user-Id", new String(Base64.encodeBase64(UserId.getBytes())));
		con.setRequestProperty("user-Password", new String(Base64.encodeBase64(Password.getBytes())));
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		if(serviceType.equalsIgnoreCase("PUT") || serviceType.equalsIgnoreCase("POST")){
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(requestBody.getBytes());
			wr.flush();
			wr.close();
		}

		int responseCode = con.getResponseCode();
		responseData[1] = Integer.toString(responseCode);

		System.out.println(responseCode);
		if((responseCode == 200) || (responseCode == 201) || (responseCode == 409) || (responseCode == 204)){
			code=true;
		}
		else{
			code=false;
		}
		responseData[0] = Boolean.toString(code);

		BufferedReader in;
		String inputLine;
		StringBuffer response = new StringBuffer();

		try{
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				response.append("\n");
			}
			in.close();
			con.disconnect();
			responseData[2] = response.toString();
			return responseData;
		}
		catch(Exception e){
			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				response.append("\n");
			}
			in.close();
			con.disconnect();
			responseData[2] = response.toString();
			return responseData;
		}
	}

	public String getCampaignLink(String accountName, String campaignid,String content) {

		try{

			String campaignServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/version/1/account/"+accountName+"/resources/campaign/url/"+campaignid;

			String postResponse = getServiceResponse(accountName, "POST", campaignServiceUrl, content);

			System.out.println("printing response"+postResponse);

			return postResponse;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	/***
	 * runAggregateJob
	 * @param account
	 * @param timeFrom
	 * @param timeTo
	 * @return boolean
	 */
	public boolean runAggregateJob(String account,String timeFrom,String timeTo) {

		try{

		//	String aggregateServiceUrl = Protocol+"://"+HostName+":"+Port+"/mgmt/customreports/?tenantId="+account+"&timeFrom="+timeFrom+"&timeTo="+timeTo;
			String aggregateServiceUrl = Protocol+"://"+HostName+":"+"8090"+"/mgmt/customreports/?tenantId="+account+"&timeFrom="+timeFrom+"&timeTo="+timeTo;

			String response=getServiceResponse(account,"GET", aggregateServiceUrl, null);                                             

			return code;

		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}

	/***
	 * getInteractionReportId
	 * @param account
	 * @param interactionName      
	 * @return String
	 */
	public String getInteractionReportId(String account,String interactionName) {

		try{

			String reportsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/resources/reports";

			String response=getServiceResponse(account,"GET", reportsServiceUrl, "adminconsoleapplicationkey");                                               

			//System.out.println("Get reports Service response :"+response);

			String[] contentArray = response.split("\"name\" : \""+interactionName+"\"");

			return getAttributeValue(contentArray[0].substring(contentArray[0].lastIndexOf("id")-1), "id");                


		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	/***
	 * getInteractionAggregateReport
	 * @param account
	 * @param reportId        
	 * @param timeFrom     
	 * @param timeTo          
	 * @return boolean
	 */
	public String[] getInteractionAggregateReport(String account,String reportId,String timeFrom,String timeTo) {

		try{

			String aggregateServiceUrl = Protocol+"://"+HostName+":"+Port+"/reports/custom/aggregated?reportItemId="+reportId+"&timeFrom="+timeFrom+"&timeTo="+timeTo+" ";

			//String response=getServiceResponse(account,"GET", aggregateServiceUrl, "adminconsoleapplicationkey");                                               

			String[] response=executeIpService("GET", aggregateServiceUrl, account, "adminconsoleapplicationkey", null);

			//System.out.println("Get reports Service response :"+response);

			return response;


		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}

	}


	public String[] getServiceResponse(String serviceUrl, String serviceType, String accountName, String appKey, String requestBody) throws Exception {

		String[] responseData = new String[2];

		System.out.println("::::::::::::Service URL::::::::"+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(serviceType);
		con.setRequestProperty("User-Agent", "Chrome/31.0");
		con.setRequestProperty("Tenant-Id", accountName);
		con.setRequestProperty("Application-Key", appKey);
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		if(serviceType.equalsIgnoreCase("PUT") || serviceType.equalsIgnoreCase("POST")){
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(requestBody.getBytes());
			wr.flush();
			wr.close();
		}

		int responseCode = con.getResponseCode();
		System.out.println(responseCode);

		responseData[0] = Integer.toString(responseCode);

		StringBuffer actualResponse = new StringBuffer();
		BufferedReader in;
		String inputLine;
		try{
			in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
				actualResponse.append("\n");
			}
		}
		catch(Exception e){

			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));

			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
				actualResponse.append("\n");
			}
		}

		in.close();
		con.disconnect();
		responseData[1] = actualResponse.toString();

		return responseData;
	}
}

