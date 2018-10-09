/** Purpose: This class is the group of Selenium Annotations. 
* It contains all the Annotations functions for every event in the fluxx-Grant Approval Workflow (e.g. @BeforeClass, @BeforeMethod etc)
* Author: Satyadeep Behera
*/

package com.jacada.accelerators;       

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;






import com.jacada.support.ConfiguratorSupport;
import com.jacada.support.HtmlReportSupport;
import com.jacada.support.MyListener;
import com.jacada.support.ReportStampSupport;
import com.jacada.utilities.SendMail;
import com.jacada.serviceUtil.AdminConsoleServicesHelper;
import com.jacada.serviceUtil.CreateAccountAPIServiceHelper;

import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("all")
public class TestEngine extends SendMail {

	//protected ObjectFactory objectFactory =new ObjectFactory();
	public static Logger logger = Logger.getLogger(TestEngine.class.getName());

	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");

	public static ConfiguratorSupport counterProp = new ConfiguratorSupport(
			configProps.getProperty("counterPath"));

	CreateAccountAPIServiceHelper createAccountService;
	AdminConsoleServicesHelper adminConsoleServicesHelper;
	
	public String currentSuite = "";
	public String method = "";
	public boolean flag = false;
	public WebDriver webDriver = null;
	public EventFiringWebDriver driver=null;
	public DesiredCapabilities capabilities;
	public  int stepNum = 0;
	public  int PassNum =0;
	public  int FailNum =0;
	public  static int count =0;
	public  static int passCounter =0;
	public  static int failCounter =0;
	//public   failCounter =0;
	public String testName = "";
	public String testCaseExecutionTime = "";
	public StringBuffer x=new StringBuffer();
	public String finalTime = "";
	public boolean isSuiteRunning=false;
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	
	
	public int countcompare = 0;

	public static String browser = null;
	static int len = 0;
	static int i = 0;
	public static ITestContext itc;
	public static String groupNames =null;
	public static final String USERNAME = "sushma28";
	public static final String AUTOMATE_KEY = "fzypF6bwJbkn895jzgaz";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	
	public  String interactionView;

	public static final String Version=configProps.getProperty("interactionVersion");
	public static final String MongoBinPath=configProps.getProperty("MongoBinPath");
	public static String UserId;
	public static String Password;
	
	public static String UserId_ProgramOfficer;
	public static String Password_ProgramOfficer;


	public static String AdminUserId;
	public static String AdminPassword;

	public static String webDesignerUrl;


	public static String Protocol;
	public static String HostName;
	public static String Port;
	
	public static int firefoxFail=0;
	public static int firefoxPass=0;
	public static int chromeFail=0;
	public static int chromePass=0;
	public static int ieFail=0;
	public static int iePass=0;
	public static int iphoneFail=0;
	public static int iphonePass=0;
	public static int androidFail=0;
	public static int androidPass=0;

	public static int mfirefoxFail=0;
	public static int mfirefoxPass=0;
	public static int mchromeFail=0;
	public static int mchromePass=0;
	public static int mieFail=0;
	public static int miePass=0;
	public static int miphoneFail=0;
	public static int miphonePass=0;
	public static int mandroidFail=0;
	public static int mandroidPass=0;	


	public static int afirefoxFail=0;
	public static int afirefoxPass=0;
	public static int achromeFail=0;
	public static int achromePass=0;
	public static int aieFail=0;
	public static int aiePass=0;


	public static int adfirefoxFail=0;
	public static int adfirefoxPass=0;
	public static int adchromeFail=0;
	public static int adchromePass=0;
	public static int adieFail=0;
	public static int adiePass=0;


	public static int wdfirefoxFail=0;
	public static int wdfirefoxPass=0;
	public static int wdchromeFail=0;
	public static int wdchromePass=0;
	public static int wdieFail=0;
	public static int wdiePass=0;


	public String testCaseName="";

	public static boolean deleteAccounts = false;
	public static String reportsFolder;

	public static String suiteStartDateTime;
	public static String suiteEndDateTime;
	/**
	 * Initializing browser requirements, Test Results file path and Database
	 * requirements from the configuration file
	 * 
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */

	@BeforeSuite(alwaysRun=true)
	public void first(ITestContext ctx) throws Throwable{
		itc=ctx;

		TestEngine.cleanUP();

		ReportStampSupport.calculateSuiteStartTime();
		suiteStartDateTime = ReportStampSupport.dateTime();


		if (System.getProperty("InteractHost") != null){

			Protocol=System.getProperty("Protocol");
			HostName=System.getProperty("InteractHost");
			Port=System.getProperty("InteractPort");
			configProps.setProperty("Protocol", Protocol);
			configProps.setProperty("InteractHost", HostName);
			configProps.setProperty("InteractPort", Port);

			//AccountId = System.getProperty("AccountId");
			UserId = System.getProperty("EmailId");
			Password = System.getProperty("Password");
		
			//configProps.setProperty("accountId", AccountId);
			configProps.setProperty("UserId", UserId);
			configProps.setProperty("Password", Password);
			
			//AccountId = System.getProperty("AccountId");
			UserId_ProgramOfficer = System.getProperty("UserId_ProgramOfficer");
			Password_ProgramOfficer = System.getProperty("Password_ProgramOfficer");
			
			//configProps.setProperty("accountId", AccountId);
			configProps.setProperty("UserId_ProgramOfficer", UserId_ProgramOfficer);
			configProps.setProperty("Password_ProgramOfficer", Password_ProgramOfficer);

			AdminUserId = System.getProperty("SystemAdminUsername");
			AdminPassword = System.getProperty("SystemAdminPassword");
			configProps.setProperty("AdminUserId", AdminUserId);
			configProps.setProperty("AdminPassword", AdminPassword);

			webDesignerUrl = System.getProperty("webDesignerUrl");
			configProps.setProperty("webDesignerUrl", webDesignerUrl);

			
			if(System.getProperty("ExecuteLocally").equalsIgnoreCase("False")){
				configProps.setProperty("ExecuteInBrowserstack", "True");
			}
			else{
				configProps.setProperty("ExecuteInBrowserstack", "False");
			}

			if(System.getProperty("DeleteAccountsAfterTestsCompletion").equalsIgnoreCase("True")){
				deleteAccounts = true;
			}
                System.out.println("EmailRecipients");
			if (System.getProperty("EmailRecipients") != null){
				configProps.setProperty("ToAddresses", System.getProperty("EmailRecipients"));
			}

		}
		else{

			Protocol=configProps.getProperty("Protocol");
			HostName=configProps.getProperty("InteractHost");
			Port=configProps.getProperty("InteractPort");

			//AccountId = configProps.getProperty("accountId");
			UserId = configProps.getProperty("UserId");
			Password = configProps.getProperty("Password");

			AdminUserId=configProps.getProperty("AdminUserId");
			AdminPassword=configProps.getProperty("AdminPassword");

			webDesignerUrl = configProps.getProperty("webDesignerUrl");
		}


		System.out.println("Protocol: "+Protocol);
		System.out.println("HostName: "+HostName);
		System.out.println("Port: "+Port);
		//System.out.println("AccountId: "+AccountId);
		System.out.println("UserId: "+UserId);
		System.out.println("Password: "+Password);
		
	}

	@Parameters({"view"})
	@BeforeTest(alwaysRun=true)
	public void first(ITestContext ctx, String view) throws Throwable{
		createHtmlSummaryReportHeader();
	}


	@Parameters({"browser","browser_version","os","os_version","view"})
	//@BeforeClass(alwaysRun=true)
	public void first(ITestContext ctx, String browser, String browser_version, String os, String os_version, String view) throws Throwable{
		itc=ctx;

		System.out.println("View is: "+view);
		if (view.equalsIgnoreCase("Mobile")){
			interactionView = "Mobile Web App";

		}
		else if(view.equalsIgnoreCase("web")){
			interactionView = "Web Self Service";

		}
		else if(view.equalsIgnoreCase("Agent")){
			interactionView = "Agent";

		}

		else if(view.equalsIgnoreCase("Admin")){
			interactionView = "Admin";	

		}
		else if(view.equalsIgnoreCase("webDesigner")){
			interactionView = "webDesigner";		

		}
	}


	@Parameters({"browser","view"})
	@AfterTest(alwaysRun=true)
	public void first1(ITestContext ctx, String browser,String view) throws Throwable{
		itc=ctx;			

		createRxLogFile();
	}
	
	public void createRxLogFile() throws Throwable{
			
			List<String> lines = Arrays.asList("Selenium");
			Path file = Paths.get("C:\\FordFoundation\\FordFoundation\\Binaries\\SeleniumFlag.rxlog.data");
			Files.write(file, lines, Charset.forName("UTF-8"));
		}

	@Parameters({"browser"})
	//@AfterSuite(alwaysRun = true)
	public void tearDownFirefox(ITestContext ctx, String browser) throws Throwable {

		suiteEndDateTime = ReportStampSupport.dateTime();

		if(System.getProperty("InteractHost") != null){
			Date todaysDate = new Date();
			SimpleDateFormat formatter1 = new SimpleDateFormat("MMddyyyy");
			SimpleDateFormat formatter2 = new SimpleDateFormat("mmss");
			reportsFolder = "\\\\BUILD.jacada.com\\Reports\\"+formatter1.format(todaysDate)+"\\"+formatter2.format(todaysDate);


			FileUtils.copyDirectory(new File("Results/HTML"), new File(reportsFolder));
			if(System.getProperty("EmailRecipients") != null){
				if (!System.getProperty("EmailRecipients").isEmpty())
					attachReportsToEmail("");
			}

		}

		else{
			//createFinalSummaryReport();
		}

	}

	public static void cleanUP() throws IOException {

		FileUtils.deleteDirectory(new File("Results/HTML"));
		new File("Results/HTML/Screenshots").mkdirs();
		new File("Results/HTML/ResponseFiles").mkdirs();
		HtmlReportSupport.copyLogos();

	}


	@Parameters({"browser","browser_version","os","os_version","view"})
	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method, ITestContext ctx, String browser,String browser_version, String os, String os_version, String view) throws Throwable {
		itc = ctx;

		testCaseName=method.getName().toString();
		System.out.println("Test case name:"+testCaseName);
		
			if(configProps.getProperty("ExecuteInBrowserstack").equals("True"))
			{
				DesiredCapabilities caps = new DesiredCapabilities();

				if(browser.equalsIgnoreCase("iPad") || browser.equalsIgnoreCase("iPhone") || browser.equalsIgnoreCase("Android"))
				{
					caps.setCapability("browser", browser);
					caps.setPlatform(Platform.MAC);
					caps.setCapability("device", os);
					caps.setCapability("browserstack.debug", "true");
				}
				else{
					caps.setCapability("browser", browser);
					caps.setCapability("browser_version", browser_version);
					caps.setCapability("os", os);
					caps.setCapability("os_version", os_version);
					caps.setCapability("browserstack.debug", "true");

					if(browser.equalsIgnoreCase("IE")){
						caps.setCapability("acceptSslCerts", "true");
						caps.setCapability("browserstack.ie.enablePopups", "true");
					}
				}

				caps.setCapability("browserstack.local", "true");

				webDriver = new RemoteWebDriver(new URL(URL), caps);
				i=i+1;
			}
			else{
				if(browser.equalsIgnoreCase("firefox"))
				{
					webDriver = new FirefoxDriver();
					i=i+1;
				}
				else if(browser.equalsIgnoreCase("ie"))
				{
					File file = new File("Drivers/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					webDriver= new InternetExplorerDriver();
					i=i+1;
				}
				else if(browser.equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");				
					webDriver=new ChromeDriver();
					i=i+1;
 
					
					
				}

			}

			driver = new EventFiringWebDriver(webDriver);
			//System.out.println("url opened"+url);
			MyListener myListener = new MyListener();
			driver.register(myListener);

			try {

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Chrome"))
				{
					driver.manage().window().setPosition(new Point(0,0));
					if(view.equalsIgnoreCase("Agent") || view.equalsIgnoreCase("Web")){
						driver.manage().window().setSize(new Dimension(1280,720));
					}
					else if(view.equalsIgnoreCase("Admin")){
						driver.manage().window().setSize(new Dimension(1024,768));
					}
//					else if(view.equalsIgnoreCase("webDesigner")){
//						driver.manage().window().setSize(new Dimension(1280,800));
//					}
					else{
						driver.manage().window().maximize();
					}
				}


				currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
			} catch (Exception e1) {
				System.out.println(e1);
			}

			if (view.equalsIgnoreCase("Mobile")){
				interactionView = "Mobile Web App";
			//	cObjs = objectFactory.getObject("Mobile");
				//url = mobileUrl;

			}
			else if(view.equalsIgnoreCase("Web")){
				interactionView = "Web Self Service";
		//		cObjs =objectFactory.getObject("WEB");
				//url = webUrl;

			}

			else if(view.equalsIgnoreCase("Agent")){
				interactionView = "Agent";
		//		cObjs =objectFactory.getObject("AGENT");
				//url = agentUrl;

			}

			else if(view.equalsIgnoreCase("Admin")){
				interactionView = "Admin";			
				//url = adminUrl;

			}
			else if(view.equalsIgnoreCase("webDesigner")){
				interactionView = "webDesigner";			
				//url = adminUrl;

			}

			calculateTestCaseStartTime();
			System.out.println("Test Script started:"+testCaseName);
			System.out.println("Test Script Started at:"+currentTime());
			flag = false;

			tc_name = method.getName().toString() + "-" + browser+"-" + view;
			String[] ts_Name = this.getClass().getName().toString().split("\\.");
			packageName = ts_Name[0] + "." + ts_Name[1] + "."+ ts_Name[2];

			testHeader(tc_name, view);

			stepNum = 0;
			PassNum = 0;
			FailNum = 0;
			testName = method.getName();
			String[] tmp=testName.split("_");
			String desc = testName.replaceAll("_", " ")+" Script";
			testDescription.put(testName+ "-" + browser+"-"+view, desc);
		
	}
	
	
	
	public void reportHeader1(Method method,  String browser,String browser_version, String os, String os_version) throws Throwable {
		

		testCaseName=method.getName().toString();
		System.out.println("Test case name:"+testCaseName);
		
			if(configProps.getProperty("ExecuteInBrowserstack").equals("True"))
			{
				DesiredCapabilities caps = new DesiredCapabilities();

				if(browser.equalsIgnoreCase("iPad") || browser.equalsIgnoreCase("iPhone") || browser.equalsIgnoreCase("Android"))
				{
					caps.setCapability("browser", browser);
					caps.setPlatform(Platform.MAC);
					caps.setCapability("device", os);
					caps.setCapability("browserstack.debug", "true");
				}
				else{
					caps.setCapability("browser", browser);
					caps.setCapability("browser_version", browser_version);
					caps.setCapability("os", os);
					caps.setCapability("os_version", os_version);
					caps.setCapability("browserstack.debug", "true");

					if(browser.equalsIgnoreCase("IE")){
						caps.setCapability("acceptSslCerts", "true");
						caps.setCapability("browserstack.ie.enablePopups", "true");
					}
				}

				caps.setCapability("browserstack.local", "true");

				webDriver = new RemoteWebDriver(new URL(URL), caps);
				i=i+1;
			}
			else{
				if(browser.equalsIgnoreCase("firefox"))
				{
					webDriver = new FirefoxDriver();
					i=i+1;
				}
				else if(browser.equalsIgnoreCase("ie"))
				{
					File file = new File("Drivers/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					webDriver= new InternetExplorerDriver();
					i=i+1;
				}
				else if(browser.equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");				
					webDriver=new ChromeDriver();
					i=i+1;
 
					
					
				}

			}

			driver = new EventFiringWebDriver(webDriver);
			//System.out.println("url opened"+url);
			MyListener myListener = new MyListener();
			driver.register(myListener);

			

			try {

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Chrome"))
				{
					driver.manage().window().setPosition(new Point(0,0));
					
					driver.manage().window().maximize();
				}


				//currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
			} catch (Exception e1) {
				System.out.println(e1);
			}

			
			calculateTestCaseStartTime();
			System.out.println("Test Script started:"+testCaseName);
			System.out.println("Test Script Started at:"+currentTime());
			flag = false;

			tc_name = method.getName().toString() + "-" + browser+"-" + "Web";
			String[] ts_Name = this.getClass().getName().toString().split("\\.");
			packageName = ts_Name[0] + "." + ts_Name[1] + "."+ ts_Name[2];

			testHeader(tc_name, "Web");

			stepNum = 0;
			PassNum = 0;
			FailNum = 0;
			testName = method.getName();
			String[] tmp=testName.split("_");
			String desc = testName.replaceAll("_", " ")+" Script";
			testDescription.put(testName+ "-" + browser+"-"+"Web", desc);
		
	}

	@Parameters({"view"})
	@AfterMethod(alwaysRun = true)
	public void tearDown(String view) throws Exception
	{
		
		
		//if (WebAppKey != null){
		String [] testcase = testCaseName.split("_");

			calculateTestCaseExecutionTime(); 
			closeDetailedReport(browser);
			System.out.println("browser :"+strTestName);
			System.out.println("Test Script Ended:"+testCaseName);
			System.out.println("Test Script Ended at:"+currentTime());
			System.out.println("Time Taken to Execute Script in seconds:"+TimeUnit.MILLISECONDS.toSeconds(iExecutionTime));
			String[] test=strTestName.split("-");
			int stringlength=test.length;
			String currentBrwoser=test[1];           
			if(FailNum!=0)
			{
				testResults.put(tc_name, "FAIL");
				System.out.println("current Browser:"+currentBrwoser);
				if(view.equalsIgnoreCase("web")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :firefoxFail+=1;break;
					case "chrome"        :chromeFail+=1;break;
					case "ie"            :ieFail+=1;break;
					case "android"       :androidFail+=1;break;
					case "iphone"		 :iphoneFail+=1;break;
					}
					//failCounter=failCounter+1;
				}
				else if(view.equalsIgnoreCase("mobile")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :mfirefoxFail+=1;break;
					case "chrome"		 :mchromeFail+=1;break;
					case "ie"            :mieFail+=1;break;
					case "android"       :mandroidFail+=1;break;
					case "iphone"		 :miphoneFail+=1;break;
					}      
				}
				else if(view.equalsIgnoreCase("agent")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :afirefoxFail+=1;break;
					case "chrome" 	     :achromeFail+=1;break;
					case "ie"            :aieFail+=1;break;                     
					}      
				}
				else if(view.equalsIgnoreCase("admin")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :adfirefoxFail+=1;break;
					case "chrome" 	     :adchromeFail+=1;break;
					case "ie"            :adieFail+=1;break;                     
					}      
				}
				else if(view.equalsIgnoreCase("webDesigner")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :wdfirefoxFail+=1;break;
					case "chrome" 	     :wdchromeFail+=1;break;
					case "ie"            :wdieFail+=1;break;                     
					}      
				}
				//sushma - throw new Exception();
			}
			else if(PassNum!=0)
			{
				testResults.put(tc_name, "PASS");
				System.out.println("current Browser:"+currentBrwoser);
				if(view.equalsIgnoreCase("web")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :firefoxPass+=1;break;
					case "chrome" 		 :chromePass+=1;break;
					case "ie"            :iePass+=1;break;
					case "android"       :androidPass+=1;break;
					case "iphone" 		 :iphonePass+=1;break;
					}
				}      
				else  if(view.equalsIgnoreCase("mobile")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :mfirefoxPass+=1;break;
					case "chrome" 		 :mchromePass+=1;break;
					case "ie"            :miePass+=1;break;
					case "android"       :mandroidPass+=1;break;
					case "iphone"		 :miphonePass+=1;break;
					}      
				}
				else  if(view.equalsIgnoreCase("agent")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :afirefoxPass+=1;break;
					case "chrome" 	     :achromePass+=1;break;
					case "ie"            :aiePass+=1;break;                      
					}      
				}
				else  if(view.equalsIgnoreCase("admin")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :adfirefoxPass+=1;break;
					case "chrome"   	 :adchromePass+=1;break;
					case "ie"            :adiePass+=1;break;                      
					}      
				}
				else  if(view.equalsIgnoreCase("webDesigner")){
					switch(currentBrwoser.toLowerCase()){
					case "firefox"       :wdfirefoxPass+=1;break;
					case "chrome"   	 :wdchromePass+=1;break;
					case "ie"            :wdiePass+=1;break;                      
					}      
				}//passCounter=passCounter+1;
			}else{
				testResults.put(tc_name, "");
				}

			try{
				//driver.quit();

			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
	}


	@Parameters({"browser"})
	@AfterClass(alwaysRun=true)
	public void close(String browser){

	}


	public void calculateTestCaseStartTime(){			
		iStartTime = System.currentTimeMillis();
	}

	public String currentTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/***
	 * 		This method is supposed to be used in the @AfterMethod to calculate the total test case execution time 
	 * to show in Reports by taking the start time from the calculateTestCaseStartTime method.
	 */
	public void calculateTestCaseExecutionTime(){	
		iEndTime = System.currentTimeMillis();
		iExecutionTime=(iEndTime-iStartTime);
		TimeUnit.MILLISECONDS.toSeconds(iExecutionTime);
		HtmlReportSupport.executionTime.put(tc_name,String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
	}
	
	
	public void initializeReportValuesEachTestCase()
	{
		calculateTestCaseStartTime();
		stepNum = 0;
		PassNum = 0;
		FailNum = 0;
		
	}
	
	public void tearDownForEachTestCase()
	{
		//if (WebAppKey != null){
				String [] testcase = testCaseName.split("_");

					calculateTestCaseExecutionTime(); 
					//closeDetailedReport(browser);
					System.out.println("browser :"+strTestName);
					System.out.println("Test Script Ended:"+testCaseName);
					System.out.println("Test Script Ended at:"+currentTime());
					System.out.println("Time Taken to Execute Script in seconds:"+TimeUnit.MILLISECONDS.toSeconds(iExecutionTime));
					String[] test=strTestName.split("-");
					int stringlength=test.length;
					String currentBrwoser=test[1];           
					if(FailNum!=0)
					{
						testResults.put(tc_name, "FAIL");
						System.out.println("current Browser:"+currentBrwoser);
						
						switch(currentBrwoser.toLowerCase()){
						case "firefox"       :firefoxFail+=1;break;
						case "chrome"        :chromeFail+=1;break;
						case "ie"            :ieFail+=1;break;
						case "android"       :androidFail+=1;break;
						case "iphone"		 :iphoneFail+=1;break;
						}
					}
					else if(PassNum!=0)
					{
						testResults.put(tc_name, "PASS");
						System.out.println("current Browser:"+currentBrwoser);
						
						switch(currentBrwoser.toLowerCase()){
						case "firefox"       :firefoxPass+=1;break;
						case "chrome" 		 :chromePass+=1;break;
						case "ie"            :iePass+=1;break;
						case "android"       :androidPass+=1;break;
						case "iphone" 		 :iphonePass+=1;break;
						}
						  
					}
					else
					{
						testResults.put(tc_name, "");
					}
					/*try{
						driver.quit();

					} catch (Exception e) {
						e.printStackTrace();
					}*/
				
	}


	public void onSuccess(String strStepName, String strStepDes) {


		//String fileName= strTestName;
		strTestName = "Fluxx Grant Creation-chrome-Web";
		strTestName=strTestName.replaceAll("_", " ");	
		File file = new File("Results/HTML/" + strTestName+"_Results"
				+ ".htm");// "SummaryReport.htm"
		Writer writer = null;
		stepNum = stepNum + 1;

		try {
			//testdescrption.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x));
			if (!map.get(packageName + ":" + tc_name).equals("FAIL")) {
				map.put(packageName + ":" + tc_name, "PASS");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>"+ strStepName + "</td>");
			writer.write("<td class='justified'><div style='word-wrap: break-word; word-break: break-all;'>"  + strStepDes + "</div></td> ");
			
			writer.write("<td class='Pass' align='center'><a  href='"+"./Screenshots"+"/"
				     + strStepDes.replaceAll("[^\\w]", "_") +".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img  src='./Screenshots/passed.ico' width='18' height='18'/></a></td>");
			
			//writer.write("<td class='Pass' align='center'><img  src='./Screenshots/passed.ico' width='18' height='18'/></td> ");
			PassNum  =PassNum + 1;
			String strPassTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strPassTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void onWarning(String strStepName, String strStepDes) {
		Writer writer = null;
		try {
			//String fileName= strTestName;
			strTestName=strTestName.replaceAll("_", " ");	

			File file = new File("Results/HTML/" + strTestName+"_Results"
					+ ".htm");// "SummaryReport.htm"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'><div style='word-wrap: break-word; word-break: break-all;'>" + strStepDes + "</div></td> ");
			FailNum = FailNum + 1;


			writer.write("<td class='Fail'  align='center'><a  href='"+"./Screenshots"+"/"
					+ strStepDes.replaceAll("[^\\w]", "_")
					+ ".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img src='./Screenshots/warning.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		} catch (Exception e) {

		}

	}


	/*
	 * 
	 * 
	 */
	public void onFailure(String strStepName, String strStepDes, String stepExecTime) {
		Writer writer = null;
		try {
			strTestName = "Fluxx Grant Creation-chrome-Web";
			strTestName=strTestName.replaceAll("_", " ");			
			File file = new File("Results/HTML/" + strTestName+"_Results"
					+ ".htm");// "SummaryReport.htm"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'><div style='word-wrap: break-word; word-break: break-all;'>" + strStepDes + "</div></td> ");
			FailNum = FailNum + 1;


			writer.write("<td class='Fail' align='center'><a  href='"+"./Screenshots"+"/"
					+ strStepDes.replaceAll("[^\\w]", "_")
					+ stepExecTime +".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img  src='./Screenshots/failed.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			if (!map.get(packageName + ":" + tc_name).equals("PASS")) {
				map.put(packageName + ":" + tc_name, "FAIL");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
		} catch (Exception e) {

		}

	}

	public void onFailure(String strStepName, String strStepDes, String stepExecTime, String expectedText, String actualText) {
		Writer writer = null;
		try {
			//String fileName= strTestName;
			strTestName=strTestName.replaceAll("_", " ");                 
			File file = new File("Results/HTML/" + strTestName+"_Results"
					+ ".htm");// "SummaryReport.htm"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'><div style='word-wrap: break-word; word-break: break-all;'>" + strStepDes + "<br>"+expectedText+"<br>"+actualText+"</div></td> ");
			FailNum = FailNum + 1;


			writer.write("<td class='Fail' align='center'><a  href='"+"./Screenshots"+"/"
					+ strStepDes.replaceAll("[^\\w]", "_")
					+ stepExecTime +".jpeg'"+" alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img  src='./Screenshots/failed.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			if (!map.get(packageName + ":" + tc_name).equals("PASS")) {
				map.put(packageName + ":" + tc_name, "FAIL");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
		} catch (Exception e) {

		}

	}


	public void onSuccess(String strStepName, String strStepDes, String expectedText, String actualText) {


		//String fileName= strTestName;
		strTestName=strTestName.replaceAll("_", " ");   
		File file = new File("Results/HTML/" + strTestName+"_Results"
				+ ".htm");// "SummaryReport.htm"
		Writer writer = null;
		stepNum = stepNum + 1;

		try {
			//testdescrption.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x));
			if (!map.get(packageName + ":" + tc_name).equals("FAIL")) {
				map.put(packageName + ":" + tc_name, "PASS");
				//map.put(TestTitleDetails.x.toString(), TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'><div style='word-wrap: break-word; word-break: break-all;'>" + strStepDes + "<br>"+expectedText+"<br>"+actualText+"</div></td> ");
			writer.write("<td class='Pass' align='center'><img  src='./Screenshots/passed.ico' width='18' height='18'/></td> ");
			PassNum  =PassNum + 1;
			String strPassTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strPassTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		}catch (Exception e) {
			e.printStackTrace();
		}

	} 


	public void closeDetailedReport(String browser) {

		//String fileName= strTestName;
		strTestName=strTestName.replaceAll("_", " ");	
		File file = new File("Results/HTML/" + strTestName+"_Results"+".htm");// "SummaryReport.htm"
		Writer writer = null;

		try {
			writer = new FileWriter(file, true);
			writer.write("</table>");
			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup>");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4'>Execution Time In Seconds (Includes Report Creation Time) : "
					+ executionTime.get(tc_name)+ "&nbsp;</th> ");
			writer.write("</tr> ");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Steps Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + PassNum
					+ "</td>");
			writer.write("<td class='fail'>&nbsp;Steps Failed&nbsp;: </td>");
			writer.write("<td class='fail'>" + FailNum
					+ "</td>");
			writer.write("</tr>");
			writer.close();			

		} catch (Exception e) {

		}
	}

	public void closeSummaryReport(String browser,String view) {	
		

		File file = new File("Results/HTML/" + "SummaryResults_"+browser+" "+view
				+ ".htm");// "SummaryReport.htm"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' /> ");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'>");
			writer.write("<th colspan='4'>Total Duration In Minutes (Including Report Creation) : "
					+ (iSuiteExecutionTime) + "</th>");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + passCount
					+ "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + failCount
					+ "</td> ");
			writer.write("</tr>");
			writer.write("</tfoot>");
			writer.write("</table> ");

			writer.close();
		} catch (Exception e) {

		}
	}


	synchronized public static void createFinalSummaryReport() throws Exception {

		Map<String, String> treeMap = new TreeMap<String, String>(map);
		Map<String, String> map=new LinkedHashMap<String, String> (treeMap);

		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		System.out.println("MAP SIZE Total:"+map.size());
		System.out.println("MAP :"+map);
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			key = mapEntry.getKey().toString().split(":");
			String value = (String) mapEntry.getValue();
			String time=executionTime.get(key[1]);
			if(time !=null && !(value.equals("status"))){  
				if(key[1].contains("Firefox")){
					Firefoxmap.put(key[1], value);
				}
				else if(key[1].contains("Chrome")){
					Chromemap.put(key[1], value);
				}
				else if(key[1].contains("IE")){
					IEmap.put(key[1], value);
				}
				else if(key[1].contains("android")){
					Androidmap.put(key[1], value);
				}
				else if(key[1].contains("iPhone")){
					Iphonemap.put(key[1], value);
				}
			}
		}

		createFinalSummaryReportHeader();

		// Code for creating Final Summary Report Body for each browser
		if(Firefoxmap.size()>0){

			iterator = Firefoxmap.entrySet().iterator();
			System.out.println("Firefox MAP size:"+Firefoxmap.size());
			System.out.println("Firefox MAP:"+Firefoxmap);

			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();

				if(key.contains("Mobile")){
					Mobilemap.put(key, value);
				}
				else if(key.contains("Web")){
					Webmap.put(key, value);
				}
				else if(key.contains("Agent")){
					Agentmap.put(key, value);
				}else if(key.contains("Admin")){
					Adminmap.put(key, value);
				}else{
					WebDesignermap.put(key, value);
				}
			}
			closeFinalSummaryReport("Firefox");
		}

		if(Chromemap.size()>0){

			iterator = Chromemap.entrySet().iterator();
			System.out.println("Chrome MAP size:"+Chromemap.size());
			System.out.println("Chrome MAP:"+Chromemap);

			Mobilemap.clear();
			Webmap.clear();
			Agentmap.clear();
			Adminmap.clear();

			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();

				if(key.contains("Mobile")){
					Mobilemap.put(key, value);
				}
				else if(key.contains("Web")){
					Webmap.put(key, value);
				}
				else if(key.contains("Agent")){
					Agentmap.put(key, value);
				}
				else if(key.contains("Admin")){
					Adminmap.put(key, value);
				}else{
					WebDesignermap.put(key, value);
				}
			}
			closeFinalSummaryReport("Chrome");
		}

		if(IEmap.size()>0){

			iterator = IEmap.entrySet().iterator();
			System.out.println("IE MAP size:"+IEmap.size());
			System.out.println("IE MAP:"+IEmap);

			Mobilemap.clear();
			Webmap.clear();
			Agentmap.clear();
			Adminmap.clear();

			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String key = mapEntry.getKey().toString();
				String value = (String) mapEntry.getValue();

				if(key.contains("Mobile")){
					Mobilemap.put(key, value);
				}
				else if(key.contains("Web")){
					Webmap.put(key, value);
				}
				else if(key.contains("Agent")){
					Agentmap.put(key, value);
				}
				else if(key.contains("Admin")){
					Adminmap.put(key, value);
				}else{
					WebDesignermap.put(key, value);
				}
			}
			closeFinalSummaryReport("IE");
		}

		if(Androidmap.size()>0){
			System.out.println("Android MAP size:"+Androidmap.size());
			System.out.println("Android MAP:"+Androidmap);
			closeFinalSummaryReport("Android");
		}

		if(Iphonemap.size()>0){
			System.out.println("Iphone MAP size:"+Iphonemap.size());
			System.out.println("Iphone MAP:"+Iphonemap);
			closeFinalSummaryReport("Iphone");
		}
	}


	public static void createFinalSummaryReportHeader() throws IOException{
		File file = new File("Results/HTML/" + "SummaryResults.htm");// "SummaryReport.htm"
		Writer writer = null;

		writer = new FileWriter(file);
		try {						
			writer.write("<!DOCTYPE html>");
			writer.write("<html> ");
			writer.write("<head> ");
			writer.write("<meta charset='UTF-8'> ");
			writer.write("<title>Fluxx - Automation Execution Results Summary</title>");

			writer.write("<style type='text/css'>");
			writer.write("body {");
			writer.write("background-color: #FFFFFF; ");
			writer.write("font-family: Verdana, Geneva, sans-serif; ");
			writer.write("text-align: center; ");
			writer.write("} ");

			writer.write("small { ");
			writer.write("font-size: 0.7em; ");
			writer.write("} ");

			writer.write("table { ");
			writer.write("box-shadow: 9px 9px 10px 4px #BDBDBD;");
			writer.write("border: 0px solid #4D7C7B;");
			writer.write("border-collapse: collapse; ");
			writer.write("border-spacing: 0px; ");
			writer.write("width: 1000px; ");
			writer.write("margin-left: auto; ");
			writer.write("margin-right: auto; ");
			writer.write("} ");

			writer.write("tr.heading { ");
			writer.write("background-color: #041944;");
			writer.write("color: #FFFFFF; ");
			writer.write("font-size: 0.7em; ");
			writer.write("font-weight: bold; ");
			writer.write("background:-o-linear-gradient(bottom, #999999 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #000000) );");
			writer.write("background:-moz-linear-gradient( center top, #999999 5%, #000000 100% );");
			writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#999999, endColorstr=#000000);	background: -o-linear-gradient(top,#999999,000000);");
			writer.write("} ");

			writer.write("tr.subheading { ");
			writer.write("color: #000000; ");
			writer.write("font-weight: bold; ");
			writer.write("font-size: 0.7em; ");
			writer.write("text-align: justify; ");
			writer.write("} ");

			writer.write("tr.section { ");
			writer.write("background-color: #A4A4A4; ");
			writer.write("color: #333300; ");
			writer.write("cursor: pointer; ");
			writer.write("font-weight: bold;");
			writer.write("font-size: 0.8em; ");
			writer.write("text-align: justify;");
			writer.write("background:-o-linear-gradient(bottom, #56aaff 5%, #e5e5e5 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #56aaff), color-stop(1, #e5e5e5) );");
			writer.write("background:-moz-linear-gradient( center top, #56aaff 5%, #e5e5e5 100% );");
			writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#56aaff, endColorstr=#e5e5e5);	background: -o-linear-gradient(top,#56aaff,e5e5e5);");

			writer.write("} ");

			writer.write("tr.subsection { ");
			writer.write("cursor: pointer; ");
			writer.write("} ");

			writer.write("tr.content { ");
			writer.write("background-color: #FFFFFF; ");
			writer.write("color: #000000; ");
			writer.write("font-size: 0.7em; ");
			writer.write("display: table-row; ");
			writer.write("} ");

			writer.write("tr.content2 { ");
			writer.write("background-color:#;E1E1E1");
			writer.write("border: 1px solid #4D7C7B;");
			writer.write("color: #000000; ");
			writer.write("font-size: 0.7em; ");
			writer.write("display: table-row; ");
			writer.write("} ");

			writer.write("td, th { ");
			writer.write("padding: 5px; ");
			writer.write("border: 1px solid #4D7C7B; ");
			writer.write("text-align: inherit; ");
			writer.write("} ");

			writer.write("th.Logos { ");
			writer.write("padding: 5px; ");
			writer.write("border: 0px solid #4D7C7B; ");
			writer.write("text-align: inherit /;");
			writer.write("} ");

			writer.write("td.justified { ");
			writer.write("text-align: justify; ");
			writer.write("} ");

			writer.write("td.pass {");
			writer.write("font-weight: bold; ");
			writer.write("color: green; ");
			writer.write("} ");

			writer.write("td.fail { ");
			writer.write("font-weight: bold; ");
			writer.write("color: red; ");
			writer.write("} ");

			writer.write("td.done, td.screenshot { ");
			writer.write("font-weight: bold; ");
			writer.write("color: black; ");
			writer.write("} ");

			writer.write("td.debug { ");
			writer.write("font-weight: bold; ");
			writer.write("color: blue; ");
			writer.write("} ");

			writer.write("td.warning { ");
			writer.write("font-weight: bold; ");
			writer.write("color: orange; ");
			writer.write("} ");
			writer.write("</style> ");

			writer.write("<script> ");
			writer.write("function toggleMenu(objID) { ");
			writer.write(" if (!document.getElementById) return;");
			writer.write(" var ob = document.getElementById(objID).style; ");
			writer.write("if(ob.display === 'none') { ");
			writer.write(" try { ");
			writer.write(" ob.display='table-row-group';");
			writer.write("} catch(ex) { ");
			writer.write("	 ob.display='block'; ");
			writer.write("} ");
			writer.write("} ");
			writer.write("else { ");
			writer.write(" ob.display='none'; ");
			writer.write("} ");
			writer.write("} ");
			writer.write("function toggleSubMenu(objId) { ");
			writer.write("for(i=1; i<10000; i++) { ");
			writer.write("var ob = document.getElementById(objId.concat(i)); ");
			writer.write("if(ob === null) { ");
			writer.write("break; ");
			writer.write("} ");
			writer.write("if(ob.style.display === 'none') { ");
			writer.write("try { ");
			writer.write(" ob.style.display='table-row'; ");
			writer.write("} catch(ex) { ");
			writer.write("ob.style.display='block'; ");
			writer.write("} ");
			writer.write(" } ");
			writer.write("else { ");
			writer.write("ob.style.display='none'; ");
			writer.write("} ");
			writer.write(" } ");
			writer.write("} ");
			writer.write("</script> ");
			writer.write("</head> ");

			writer.write("<body> ");
			writer.write("<br> ");
			writer.write("<br> ");
			writer.write("<br> ");
			writer.write("<table id='Logos'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup> ");
			writer.write("<thead> ");

			writer.write("<tr class='content'>");
			writer.write("<th class ='Logos' colspan='2' >");

			if (System.getProperty("InteractHost") != null){
				writer.write("<img align ='left' src= "+reportsFolder+"\\Screenshots\\logo.png></img>");
				writer.write("</th>");
				writer.write("<th class = 'Logos' colspan='2' > ");
				writer.write("<img align ='right' src= "+reportsFolder+"\\Screenshots\\cigniti.png></img>");
			}
			else{
				writer.write("<img align ='left' src= ./Screenshots/logo.png></img>");
				writer.write("</th>");
				writer.write("<th class = 'Logos' colspan='2' > ");
				writer.write("<img align ='right' src= ./Screenshots/cigniti.png></img>");
			}

			writer.write("</th> ");
			writer.write("</tr> ");
			writer.write("</thead> ");
			writer.write("</table> ");


			writer.write("<table id='header'> ");
			writer.write("<colgroup> ");
			writer.write("<col style='width: 25%' /> ");
			writer.write("<col style='width: 25%' /> ");
			writer.write("<col style='width: 25%' /> ");
			writer.write(" <col style='width: 25%' /> ");
			writer.write("</colgroup> ");

			writer.write("<thead> ");
			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> ");
			writer.write("Fluxx -  Automation Execution Result Summary ");
			writer.write("</th> ");
			writer.write("</tr> ");

			writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Suite Start Time&nbsp;:&nbsp;</th>");
			writer.write("<th> &nbsp;" + suiteStartDateTime + "&nbsp;</th> ");
			writer.write("<th>&nbsp;Host Name&nbsp;:</th> ");
			writer.write("<th>" + InetAddress.getLocalHost().getHostName()+ "</th> ");
			writer.write("</tr> ");

			writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Suite End Time&nbsp;:&nbsp;</th> ");
			writer.write("<th> &nbsp;" +suiteEndDateTime+ "&nbsp;</th> ");
			writer.write("<th>&nbsp;Duration&nbsp;:</th> ");
			writer.write("<th>" + getSuiteExecutionTime() + "</th> ");
			writer.write("</tr> ");

			writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Groups executed&nbsp;:</th> ");
			writer.write("<th style='word-break: break-word; text-align: left' colspan='3'>" + System.getProperty("Groups").replaceAll(",",", ") + "</th> ");
			writer.write("</tr> ");

			writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Environment&nbsp;:</th> ");
			writer.write("<th>&nbsp;" + HostName + "&nbsp;</th> ");
			writer.write("<th>&nbsp;Build No&nbsp;:</th> ");
			writer.write("<th>&nbsp;" + System.getProperty("BuildNo") + "&nbsp;</th> ");
			writer.write("</tr> ");
			writer.write("</thead> ");

			writer.write("</table> ");

		} catch (Exception e) {

		}

		finally {
			writer.flush();
			writer.close();
		}
	}




	public static void closeFinalSummaryReport(String browser) {	
		int[] resultCounter = new int[10];

		switch(browser.toLowerCase()){
		case "firefox":
			resultCounter[0] = firefoxPass;
			resultCounter[1] = mfirefoxPass;
			resultCounter[2] = afirefoxPass;
			resultCounter[3] = adfirefoxPass;
			resultCounter[4] = wdfirefoxPass;
			resultCounter[5] = firefoxFail;
			resultCounter[6] = mfirefoxFail;
			resultCounter[7] = afirefoxFail;
			resultCounter[8] = adfirefoxFail;
			resultCounter[9] = wdfirefoxFail;
			break;

		case "chrome":
			resultCounter[0] = chromePass;
			resultCounter[1] = mchromePass;
			resultCounter[2] = achromePass;
			resultCounter[3] = adchromePass;
			resultCounter[4] = wdchromePass;
			resultCounter[5] = chromeFail;
			resultCounter[6] = mchromeFail;
			resultCounter[7] = achromeFail;
			resultCounter[8] = adchromeFail;
			resultCounter[9] = wdchromeFail;
			break;

		case "ie":
			resultCounter[0] = iePass;
			resultCounter[1] = miePass;
			resultCounter[2] = aiePass;
			resultCounter[3] = adiePass;
			resultCounter[4] = wdiePass;
			resultCounter[5] = ieFail;
			resultCounter[6] = mieFail;
			resultCounter[7] = aieFail;
			resultCounter[8] = adieFail;
			resultCounter[9] = wdieFail;
			break;

		case "android":
			resultCounter[1] = mandroidPass;
			resultCounter[6] = mandroidFail;
			break;

		case "iphone":
			resultCounter[1] = miphonePass;
			resultCounter[6] = miphoneFail;
			break;
		}

		File file = new File("Results/HTML/" + "SummaryResults.htm");// "SummaryReport.htm"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%'/>");
			writer.write("<col style='width: 12%'/>");
			writer.write("<col style='width: 12%'/>");
			writer.write("<col style='width: 12%'/>");
			writer.write("<col style='width: 12%'/>");
			writer.write("<col style='width: 12%'/>");
			writer.write("<col style='width: 15%'/>");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr style='font-size: 0.9em; background-color: #43C6DB;'>");
			writer.write("<th colspan='7'>"+browser+" Results</th>");
			writer.write("</tr>");

			writer.write("<tr class='content2'>");
			writer.write("<td><b>&nbsp;View&nbsp;</b></td>");

			if (System.getProperty("InteractHost") != null){
				writer.write("<td><b>&nbsp;<a href='file:"+reportsFolder+"\\SummaryResults_"+browser+" Web.htm'>Web</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='file:"+reportsFolder+"\\SummaryResults_"+browser+" Mobile.htm'>Mobile</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='file:"+reportsFolder+"\\SummaryResults_"+browser+" Agent.htm'>Agent</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='file:"+reportsFolder+"\\SummaryResults_"+browser+" Admin.htm'>Admin</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='file:"+reportsFolder+"\\SummaryResults_"+browser+" webDesigner.htm'>WebDesigner</a>&nbsp;</b></td>");
			}
			else{
				writer.write("<td><b>&nbsp;<a href='SummaryResults_"+browser+" Web.htm'>Web</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='SummaryResults_"+browser+" Mobile.htm'>Mobile</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='SummaryResults_"+browser+" Agent.htm'>Agent</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='SummaryResults_"+browser+" Admin.htm'>Admin</a>&nbsp;</b></td>");
				writer.write("<td><b>&nbsp;<a href='SummaryResults_"+browser+" webDesigner.htm'>WebDesigner</a>&nbsp;</b></td>");
			}
			writer.write("<td><b>&nbsp;Total&nbsp;</b></td>");
			writer.write("</tr>");

			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + resultCounter[0]+ "</td> ");
			writer.write("<td class='pass'> " + resultCounter[1]+ "</td> ");
			writer.write("<td class='pass'> " + resultCounter[2]+ "</td> ");
			writer.write("<td class='pass'> " + resultCounter[3]+ "</td> ");
			writer.write("<td class='pass'> " + resultCounter[4]+ "</td> ");
			writer.write("<td class='pass'> " + (resultCounter[0]+resultCounter[1]+resultCounter[2]+resultCounter[3]+resultCounter[4])+ "</td> ");
			writer.write("</tr>");

			writer.write("<tr class='content'>");
			writer.write("<td class='fail'>&nbsp;Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + resultCounter[5]+ "</td> ");
			writer.write("<td class='fail'> " + resultCounter[6]+ "</td> ");
			writer.write("<td class='fail'> " + resultCounter[7]+ "</td> ");
			writer.write("<td class='fail'> " + resultCounter[8]+ "</td> ");
			writer.write("<td class='fail'> " + resultCounter[9]+ "</td> ");
			writer.write("<td class='fail'> " +(resultCounter[5]+resultCounter[6]+resultCounter[7]+resultCounter[8]+resultCounter[9])+ "</td> ");
			writer.write("</tr>");

			writer.write("</tfoot>");
			writer.write("</table> ");

			writer.flush();
			writer.close();
		} catch (Exception e) {

		}
	}

	public static String getSuiteExecutionTime(){	

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		Date d1 = null;
		Date d2 = null;

		long diffSeconds=0;
		long diffMinutes=0;
		long diffHours=0;

		String execTime;

		try {
			d1 = formatter.parse(suiteStartDateTime);
			d2 = formatter.parse(suiteEndDateTime);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			diffHours = diff / (60 * 60 * 1000) % 24;

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(diffHours!=0){
			execTime = diffHours+" hrs "+diffMinutes+" Mins "+diffSeconds+" Secs";
		}else{
			if(diffMinutes!=0){
				execTime = +diffMinutes+" Mins "+diffSeconds+" Secs";
			}else{
				execTime = diffSeconds+" Secs";
			}
		}

		return execTime;
	}

}
