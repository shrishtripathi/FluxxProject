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


public class SoapServiceHelper extends TestEngine {

	boolean flag=false;
	String[] responseData = new String[2];


	public String[] getResponse(String serviceUrl, String requestXml, String accountName, String appKey) throws Exception {

		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");

		//con.setRequestProperty("User-Agent", "Chrome/31.0");
		con.setRequestProperty("Tenant-Id", accountName);
		con.setRequestProperty("Application-Key", appKey);
		con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		con.setRequestProperty("SOAPAction", "");
		con.setDoOutput(true);
		con.setDoInput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(requestXml.getBytes("UTF-8"));
		wr.flush();
		wr.close();

		//con.connect();
		int responseCode = con.getResponseCode();

		System.out.println(responseCode);

		responseData[0] = responseCode+" "+con.getResponseMessage();

		StringBuffer actualResponse = new StringBuffer();
		BufferedReader in;
		String inputLine;

		try
		{		
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
				actualResponse.append("\n");
			}
			in.close();  
			con.disconnect();
		}
		catch(Exception e){
			e.printStackTrace();
			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
				actualResponse.append("\n");
			}
			in.close();  
			con.disconnect();
		}
		
		responseData[1] = actualResponse.toString();

		return responseData;
	}
}
