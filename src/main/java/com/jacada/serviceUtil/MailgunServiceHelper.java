package com.jacada.serviceUtil;  

import com.jacada.accelerators.TestEngine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;


public class MailgunServiceHelper extends TestEngine {

	boolean code=false;
	String[] responseData = new String[3];

	public String[] getResponse(String serviceUrl) throws Exception {

		URL url = new URL(serviceUrl);
		System.out.println("Mailgun service url: " + url);
		String userName="api";
		String password="key-3df778c6efdc7432d6230e5dff3777bf";
		
		String userPassword = userName + ":" + password;
		String basicAuth = "Basic " + new String(new Base64().encode(userPassword.getBytes()));

	
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", "Chrome/31.0");
			con.setRequestProperty("Authorization", basicAuth);
			
			int responseCode = con.getResponseCode();
			
			System.out.println("Response code:" + responseCode);
			
			if ((responseCode == 200)) {
				code = true;
			} else {
				code = false;
			}

			responseData[0] = Boolean.toString(code);
			responseData[1] = Integer.toString(responseCode);
			System.out.println(responseData[0]);
			System.out.println(responseData[1]);

			BufferedReader in;
			String inputLine;
			StringBuffer response = new StringBuffer();

			try {
				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					response.append("\n");
				}
				System.out.println("Response: "+response.toString());
			} catch (Exception e) {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					response.append("\n");
				}
				System.out.println("Response: "+response.toString());
			}
			in.close();
			con.disconnect();
			responseData[2] = response.toString();

			return responseData;
		} catch (Exception e) {
			responseData[2] =  e.getMessage();
			return responseData;
		}
	}
}
