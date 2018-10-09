package com.jacada.serviceUtil;  

import com.jacada.accelerators.TestEngine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FullflowServiceHelper extends TestEngine {

	boolean code=false;
	boolean flag=false;
	String[] responseData = new String[3];

	/*public boolean verifyFullFlowServiceResponse(String searchTerm, String attributeValue) throws Exception {
		String searchTermValue = "\""+searchTerm+"\"";

		try{
			getResponse(AccountId, "Full flow", attributeValue, false);
			flag = responseData[2].contains(searchTermValue);

			if(code && flag){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}

	}*/


	/*public String getServiceUrl(String serviceType, String attributeValue){
		String serviceUrl=null;

		if(serviceType.equals("Full flow")){

			//attribute value should have prefix "id" when we are using Full flow service with interaction id
			if(attributeValue.startsWith("Id")){
				serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+AccountId+"/interactiondata/"+attributeValue.substring(2)+"/fullFlow";
			}
			else{
				serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+AccountId+"/interactiondata/"+attributeValue+"/fullFlow/byCallerAttribute";
			}
		}
		else if(serviceType.equals("Summary")){
			serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+AccountId+"/interactiondata/"+attributeValue+"/summary/byCallerAttribute";
		}
		return serviceUrl;

	}*/


	public String getServiceUrl(String account, String serviceType, String attributeValue, Boolean widerSearch){
		String serviceUrl=null;

		if(serviceType.equals("Full flow")){

			//attribute value should have prefix "id" when we are using Full flow service with interaction id
			if(attributeValue.startsWith("Id")){
				serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+account+"/interactiondata/"+attributeValue.substring(2)+"/fullFlow";
			}
			else{
				serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+account+"/interactiondata/"+attributeValue+"/fullFlow/byCallerAttribute?widerSearch="+widerSearch;
			}
		}
		else if(serviceType.equals("Summary")){
			serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+account+"/interactiondata/"+attributeValue+"/summary/byCallerAttribute?widerSearch="+widerSearch;
		}
		return serviceUrl;

	}

	public String[] getResponse(String account, String serviceType, String attributeValue, Boolean widerSearch) throws Exception {

		String serviceUrl = getServiceUrl(account, serviceType, attributeValue, widerSearch);

		System.out.println("::::::::::::Service URL::::::::"+serviceUrl);
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Chrome/31.0");
		con.setRequestProperty("Tenant-Id", account);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");

		int responseCode = con.getResponseCode();
		responseData[1] = Integer.toString(responseCode);

		System.out.println(responseCode);
		if(responseCode == 200){
			code=true;
		}

		responseData[0] = Boolean.toString(code);

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

		responseData[2] = actualResponse.toString();

		return responseData;

	}



}
