package com.jacada.serviceUtil;  

import com.jacada.accelerators.TestEngine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class VarsetServiceHelper extends TestEngine {

	boolean code=false;
	boolean flag=true;
	String[] responseData = new String[3];

	public String getServiceUrl(String account, String historySearchId, String variableSetName, Boolean widerSearch){
		return Protocol+"://"+HostName+":"+Port+"/interact/agent/version/"+Version+"/account/"+account+"/interactiondata/"+historySearchId+"/variableSet/"+variableSetName+"/byCallerAttribute?widerSearch="+widerSearch;
	}

	public String[] getResponse(String account, String historySearchId, String variableSetName, Boolean widerSearch) throws Exception {

		String serviceUrl = getServiceUrl(account, historySearchId, variableSetName, widerSearch);
		System.out.println("VarsetService url: "+serviceUrl);
	
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Chrome/31.0");
		con.setRequestProperty("Tenant-Id", account);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

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
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
				actualResponse.append("\n");
			}

		}
		catch(Exception e){
			e.printStackTrace();
			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
				actualResponse.append("\n");
			}
		}

		in.close();
		con.disconnect();
		
		responseData[2] = actualResponse.toString();

		System.out.println("Varset Response: "+responseData[2]);
		return responseData;

	}
}
