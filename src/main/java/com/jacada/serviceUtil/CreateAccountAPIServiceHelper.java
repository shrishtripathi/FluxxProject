package com.jacada.serviceUtil;  

import com.jacada.accelerators.TestEngine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;


public class CreateAccountAPIServiceHelper extends TestEngine {

	boolean code=false;
	String[] responseData = new String[3];

	public String[] createAccount(String accountName, String email, String password,String accountType) throws Exception {

		String serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/tenant/trial";

		try{
			File file = new File(configProps.getProperty("TestData")+"CreateAccount.txt");

			String fileContent = null;

			fileContent = FileUtils.readFileToString(file, "utf-8");

			fileContent = updateAttributeValue(fileContent, "company", accountName);
			fileContent = updateAttributeValue(fileContent, "email", email);
			fileContent = updateAttributeValue(fileContent, "password", password);
			fileContent = updateAttributeValue(fileContent, "accountType", accountType);

			System.out.println("File Content: "+fileContent);

			responseData[2] = getResponse("POST", serviceUrl, fileContent, AdminUserId, AdminPassword);
			System.out.println("Response: "+responseData[2]);

			return responseData;

		}catch(Exception e){
			e.printStackTrace();
			return responseData;
		}
	}


	public boolean deleteAccount(String accountName, String userName, String password) throws Exception {

		try{
			String accountId = getAccountId(accountName);
			System.out.println("Account id: "+accountId);

			String serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/tenant/"+accountId;
			getResponse("DELETE", serviceUrl, null, userName, password);
			return code;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public String updateAttributeValue(String inputData, String attributeName, String attributeValue){

		String content = inputData;
		String[] contentArray;
		String updatedAttributeValue;
		contentArray = content.split("\""+attributeName+"\" : ");

		System.out.println("Content Array lenght: "+contentArray.length);

		if(contentArray[1].charAt(0) == '\"'){
			updatedAttributeValue = "\""+attributeName+"\" : \""+attributeValue+"\"";
		}
		else{
			updatedAttributeValue = "\""+attributeName+"\" : "+attributeValue+"";
		}

		System.out.println("updatedAttributeValue: "+updatedAttributeValue);

		return contentArray[0]+updatedAttributeValue+contentArray[1].substring(contentArray[1].indexOf(","));

	}

	public String getResponse(String serviceType, String serviceUrl, String requestBody, String userName, String password) throws Exception {

		URL url = new URL(serviceUrl);
		System.out.println("Accounts url: "+serviceUrl);

		try{
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(serviceType);
			con.setRequestProperty("User-Agent", "Chrome/31.0");
			con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
			con.setRequestProperty("user-Id", new String(Base64.encodeBase64(userName.getBytes())));
			con.setRequestProperty("user-Password", new String(Base64.encodeBase64(password.getBytes())));
			con.setRequestProperty("Tenant-Id", "system");
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			if(serviceType.equalsIgnoreCase("PUT") || serviceType.equalsIgnoreCase("POST")){
				con.setDoOutput(true);

				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(requestBody.getBytes());
				wr.flush();
				wr.close();
			}

			int responseCode = con.getResponseCode();
			System.out.println("Response code:"+responseCode);

			if(responseCode == 301){
				// get redirect url from "location" header field
				String newUrl = con.getHeaderField("Location");
				System.out.println("Redirected to New url:"+newUrl);

				// open the new connnection again
				con = (HttpURLConnection) new URL(newUrl).openConnection();
				con.setRequestMethod(serviceType);
				con.setRequestProperty("User-Agent", "Chrome/31.0");
				con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
				con.setRequestProperty("user-Id", new String(Base64.encodeBase64(AdminUserId.getBytes())));
				con.setRequestProperty("user-Password", new String(Base64.encodeBase64(AdminPassword.getBytes())));
				con.setRequestProperty("Tenant-Id", "system");
				con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

				responseCode = con.getResponseCode();
				System.out.println("New response code:"+responseCode);
			}

			if((responseCode == 200) || (responseCode == 201) || (responseCode == 204)){
				code=true;
			}
			else{
				code=false;
			}

			responseData[0] = Boolean.toString(code);
			responseData[1] = Integer.toString(responseCode);

			BufferedReader in;
			String inputLine;
			StringBuffer response = new StringBuffer();

			try{
				in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					response.append("\n");
				}
			}
			catch(Exception e){
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					response.append("\n");
				}
			}
			in.close();  
			con.disconnect();

			return response.toString(); 
		}
		catch(Exception e){
			return e.getMessage();
		}
	}


	public boolean verifyAccountExist(String accountName) throws Exception {

		String serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/tenant/find/"+accountName;

		try{

			URL url = new URL(serviceUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Chrome/31.0");

			con.setRequestProperty("Tenant-Id", "system");
			con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			int responseCode = con.getResponseCode();
			System.out.println(responseCode);

			/*StringBuffer actualResponse = new StringBuffer();
    		BufferedReader in;
    		String inputLine;
    		try{
    			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    			while ((inputLine = in.readLine()) != null) {
    				actualResponse.append(inputLine);
    			}
    		}
    		catch(Exception e){
    			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
    			while ((inputLine = in.readLine()) != null) {
    				actualResponse.append(inputLine);
    			}
    		}

    		in.close();
            con.disconnect();

            String response = actualResponse.toString();*/
			//if((responseCode == 200) && (response.contains("OK"))){

			con.disconnect();

			if((responseCode == 200)){
				code=true;
			}
			else{
				code=false;
			}
			return code; 
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	public String getAccountId(String accountName) throws Exception{

		String getAccountsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/tenant";
		String content = getResponse("GET", getAccountsServiceUrl, null,AdminUserId, AdminPassword);
		//System.out.println("\"name\" : \""+accountName+"\"");
		content = content.substring(0, content.indexOf("\"organizationId\" : \""+accountName+"\""));
		content = content.substring(content.lastIndexOf("id")-1);

		String[] contentArray = content.split("\"id\" : ");

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
	
	public boolean updateAccountParameter(String accountName,  String attributeName, String attributeValue) {
		try{

		String getAccountsServiceUrl = Protocol+"://"+HostName+":"+Port+"/interact/tenant";

		String accountId = getAccountId(accountName);

		System.out.println("Account id is: "+accountId);

		String getAccountResponse = getResponse("GET",getAccountsServiceUrl+"/"+accountId ,null, AdminUserId, AdminPassword);

		System.out.println("App Response"+getAccountResponse);

		String updatedResponseData = updateAttributeValue(getAccountResponse, attributeName, attributeValue);

		String putResponse = getResponse("PUT",getAccountsServiceUrl ,updatedResponseData, AdminUserId, AdminPassword);

		System.out.println("Put Response: "+putResponse);

		return code;

		}catch(Exception e){
		//e.printStackTrace();
		return false;
		}

		}


}
