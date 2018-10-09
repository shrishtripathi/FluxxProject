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


public class PublishServiceHelper extends TestEngine {

	boolean code=false;
	String[] responseData = new String[3];

	public String[] getResponse(String fileName,String folderName, String accountName) throws Exception {

		String serviceUrl = Protocol+"://"+HostName+":"+Port+"/interact/interaction/definition";
		
		URL url = new URL(serviceUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Chrome/31.0");
		con.setRequestProperty("Tenant-Id", accountName);
		//con.setRequestProperty("Application-Key", DesignerKey);
		con.setRequestProperty("Application-Key", "adminconsoleapplicationkey");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.setRequestProperty("user-Id", new String(Base64.encodeBase64(UserId.getBytes())));
		con.setRequestProperty("user-Password", new String(Base64.encodeBase64(Password.getBytes())));
		con.setDoOutput(true);

		File file = new File(configProps.getProperty("InteractionFilesPath")+folderName+"/"+fileName+".int");  

		String fileContent = null;
		try {
			fileContent = FileUtils.readFileToString(file, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(fileContent.getBytes("UTF-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		responseData[1] = Integer.toString(responseCode);
		
		if(responseCode == 201){
			code=true;
		}else{
			code=false;
		}
		responseData[0] = Boolean.toString(code);
		
		StringBuffer actualResponse = new StringBuffer();
		BufferedReader in;
		String inputLine;

		try
		{		
		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		while ((inputLine = in.readLine()) != null) {
			actualResponse.append(inputLine);
		}
		in.close();  
		con.disconnect();
	}
	catch(Exception e){
			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			
			while ((inputLine = in.readLine()) != null) {
				actualResponse.append(inputLine);
			}
			in.close();  
			con.disconnect();
		}
		
		responseData[2] = actualResponse.toString();
		
		return responseData;
	}
}
