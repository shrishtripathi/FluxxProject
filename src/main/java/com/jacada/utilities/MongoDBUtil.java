package com.jacada.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jacada.accelerators.TestEngine;

public class MongoDBUtil extends TestEngine{
	
	public String[] verifyPhrasesMongoDB(String accountName, String phrases) {
		StringBuilder sb = new StringBuilder();
		String[] result = new String [2]; 
		try{
		
		String cmd = MongoBinPath+"\\mongo.exe "+MongoBinPath+"\\noAuditForAutomation.js --eval \"var host='"+HostName+":27017'; var tenantName='"+accountName+"'; var phrases=["+phrases+"]\";";
		System.out.println("command is:"+cmd);
		
		Process p = Runtime.getRuntime().
				exec(String.format("cmd /c %s", cmd));

		BufferedReader stdInput = new BufferedReader(new 
				InputStreamReader(p.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
				InputStreamReader(p.getErrorStream()));

		String line = null;
		while ((line = stdInput.readLine()) != null)
		{
			sb.append(line).append("\n");
		}

		while ((line = stdError.readLine()) != null)
		{
			sb.append(line).append("\n");
		}

		int exitValue = p.waitFor();
		
		if (exitValue == 0){
			result[0] = "true";
	    }
	    else{
	    	result[0] = "false";
	    }

		result[1] = sb.toString();
		
		return result;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}

	public String[] dropNavigationCollection(String accountName) {
		StringBuilder sb = new StringBuilder();
		String[] result = new String [2]; 
		try{
		
		String cmd = MongoBinPath+"\\mongo.exe "+MongoBinPath+"\\cleanNavigationCollection.js --eval \"var host='"+HostName+":27017'; var tenantName='"+accountName+"'\";";
		System.out.println("command is:"+cmd);
		
		Process p = Runtime.getRuntime().
				exec(String.format("cmd /c %s", cmd));

		BufferedReader stdInput = new BufferedReader(new 
				InputStreamReader(p.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
				InputStreamReader(p.getErrorStream()));

		String line = null;
		while ((line = stdInput.readLine()) != null)
		{
			sb.append(line).append("\n");
		}

		while ((line = stdError.readLine()) != null)
		{
			sb.append(line).append("\n");
		}

		int exitValue = p.waitFor();
		
		if (exitValue == 0){
			result[0] = "true";
	    }
	    else{
	    	result[0] = "false";
	    }

		result[1] = sb.toString();
		
		return result;
		
		}catch(Exception e){
			e.printStackTrace();
			return result;
		}
		
	}
}
