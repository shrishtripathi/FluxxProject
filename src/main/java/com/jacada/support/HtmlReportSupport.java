/** Purpose: This class is used for reporting purposes. 
* It contains all the functions for the summary level and detailed level reporting in the fluxx-Grant Approval Workflow
* Author: Satyadeep Behera
*/

package com.jacada.support;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;





//import com.fordfoundation.support.ReportStampSupport;
import com.jacada.accelerators.TestEngine;
@SuppressWarnings("all")
public class HtmlReportSupport {

	public long iStartTime = 0;
	public long iEndTime = 0;
	public long iExecutionTime = 0;
	public static long iSuiteStartTime = 0;
	public static long iSuiteEndTime = 0;
	public static String iSuiteExecutionTime = null;
	public ArrayList<Double> list = new ArrayList<Double>();
	public long startStepTime = 0;
	public long endStepTime = 0;
	public double stepExecutionTime = 0;
	public String strTestName = "";
	static String startedAt = "";
	public String tc_name = "";
	public String packageName = "";
	static int serialNo = 1;
	public static int passCount = 0;
	public static int failCount = 0;
	public static Map<String, String> map = new LinkedHashMap<String, String>();
	
	public static Map<String, String> Firefoxmap = new LinkedHashMap<String, String>();
	public static Map<String, String> Chromemap = new LinkedHashMap<String, String>();
	public static Map<String, String> IEmap = new LinkedHashMap<String, String>();
	public static Map<String, String> Androidmap = new LinkedHashMap<String, String>();
	public static Map<String, String> Iphonemap = new LinkedHashMap<String, String>();
	public static Map<String, String> Mobilemap = new LinkedHashMap<String, String>();
	public static Map<String, String> Webmap = new LinkedHashMap<String, String>();
	public static Map<String, String> Agentmap = new LinkedHashMap<String, String>();
	public static Map<String, String> Adminmap = new LinkedHashMap<String, String>();
	public static Map<String, String> WebDesignermap = new LinkedHashMap<String, String>();
	
	public static Map<String, String> testdescrption = new LinkedHashMap<String, String>();
	public static Map<String, String> executionTime = new LinkedHashMap<String, String>();
	static ConfiguratorSupport config = new ConfiguratorSupport(
			"config.properties");
	public String currentSuit = "";
	public int pCount = 0;
	public int fCount = 0;
	public static String[] key;
	public String value[];

	static String workingDir = System.getProperty("user.dir").replace(
			File.separator, "/");;
			public static int BFunctionNo = 0;
			public static ConfiguratorSupport configProps = new ConfiguratorSupport(
					"config.properties");

			public void createDetailedReport() throws Exception {

			}

			@SuppressWarnings("rawtypes")
			synchronized public static void createHtmlSummaryReport(String browser,
					String interactionUrl, String interactionView) throws Exception {

				File file = new File("Results/HTML/" + "SummaryResults_" + browser+" "
						+ interactionView + ".htm");// "SummaryReport.htm"
				Map<String, String> treeMap = new TreeMap<String, String>(map);
				Map<String, String> map=new LinkedHashMap<String, String> (treeMap);
				Writer writer = null;

				if (file.exists()) {
					file.delete();
				}
				writer = new FileWriter(file, true);
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
					writer.write("background-color: #6A90B6;");
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
					writer.write("</br>");

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
					writer.write("<img align ='left' src= ./Screenshots/logo.png></img>");
					writer.write("</th>");
					writer.write("<th class = 'Logos' colspan='2' > ");
					writer.write("<img align ='right' src= ./Screenshots/cigniti.png></img>");
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
					writer.write("<th>&nbsp;Date&nbsp;&&nbsp;Time&nbsp;:&nbsp;" + ""
							+ "</th> ");
					// writer.write("<th>&nbsp;:&nbsp;08-Apr-2013&nbsp;06:24:21&nbsp;PM</th> ");
					writer.write("<th> &nbsp;" + ReportStampSupport.dateTime()
							+ "&nbsp;</th> ");
					writer.write("<th>&nbsp;Application&nbsp;:&nbsp;</th> ");
					writer.write("<th>" + "Fluxx - Grant Creation" + "</th> ");
					writer.write("</tr> ");

					writer.write("<tr class='subheading'> ");
					writer.write("<th>&nbsp;Host Name&nbsp;:</th> ");
					writer.write("<th>" + InetAddress.getLocalHost().getHostName()
							+ "</th> ");
					/*writer.write("<th>&nbsp;Suite Executed&nbsp;:&nbsp;</th> ");
			writer.write("<th>Sanity</th> ");*/
					writer.write("<th>&nbsp;Browser&nbsp;:</th> ");
					writer.write("<th>" + browser + "</th> ");
					writer.write("</tr> ");

					/*writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Host Name&nbsp;:</th> ");
			writer.write("<th>" + InetAddress.getLocalHost().getHostName()
					+ "</th> ");
			writer.write("<th>&nbsp;No.&nbsp;Of&nbsp;Threads&nbsp;:&nbsp;</th> ");
			writer.write("<th>" + "NA" + "</th> ");
			writer.write("</tr> ");*/

					writer.write("<tr class='subheading'> ");
					/*writer.write("<th colspan='4'> ");
					writer.write("&nbsp;Environment -  " + interactionUrl + "");
					writer.write("</th> ");*/
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.write("</table> ");
					writer.write("<table id='main'> ");
					writer.write("<colgroup> ");
					writer.write("<col style='width: 5%' /> ");
					writer.write("<col style='width: 35%' /> ");
					writer.write("<col style='width: 42%' /> ");
					writer.write("<col style='width: 10%' /> ");
					writer.write("<col style='width: 8%' /> ");
					writer.write("</colgroup> ");
					writer.write("<thead> ");
					writer.write("<tr class='heading'> ");
					writer.write("<th>S.NO</th> ");
					writer.write("<th>Test Case</th> ");
					writer.write("<th>Description</th> ");
					writer.write("<th>Time</th> ");
					writer.write("<th>Status</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					Iterator<Entry<String, String>> iterator1 = map.entrySet()
							.iterator();
					int serialNo = 1;
					writer.write("<tbody> ");
					System.out.println("MAP SIZE ***:"+map.size()+",   Application: "+"Fluxx - Grant Creation");
					System.out.println("MAP :"+map);
					while (iterator1.hasNext()) {
						Map.Entry mapEntry1 = (Map.Entry) iterator1.next();
						key = mapEntry1.getKey().toString().split(":");
						String value = (String) mapEntry1.getValue();
						String time=executionTime.get(key[1]);
						//if(time !=null && !(time.equals("0")))
						if(time !=null){
							if (key[1].endsWith("Web")) {
								if (key[1].contains(browser)){						
									writer.write("<tr class='content2' > ");
									writer.write("<td class='justified'>" + serialNo + "</td>");
									serialNo = serialNo + 1;				
									
									writer.write("<td class='justified'><a href='" + key[1].replaceAll("_", " ")
												+ "_Results" + ".htm#'"
												+ "' target='about_blank'>"
												+ key[1].substring(0, key[1].indexOf("-"))
												+ "</a></td>");
									
									writer.write("<td class='justified'>"
											+ TestEngine.testDescription.get(key[1]) + "</td>");                  

									writer.write("<td>" + executionTime.get(key[1])
											+ " Seconds</td>");
									if (TestEngine.testResults.get(key[1]).equals("PASS"))
										writer.write("<td class='pass'>Passed</td> ");
									else if(TestEngine.testResults.get(key[1]).equals("FAIL"))
										writer.write("<td class='fail'>Failed</td> ");
									else
										writer.write("<td class='fail'>Exception</td> ");
									
									writer.write("</tr>");
									writer.write("</tbody> ");

								}
							}

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				finally {

					writer.flush();
					writer.close();

				}
			}

			
			
			public void testHeader(String testName, String interactionView) {
				Writer writer = null;
				try {
					//strTestName = testName;
					strTestName = "Fluxx Grant Creation-chrome-Web";
					strTestName=strTestName.replaceAll("_"," ");			
					/*File file = new File("Results/HTML/" + strTestName + "_Results"
							+ ".htm");// "Results.htm"
*/					File file = new File("Results/HTML/" + strTestName + "_Results"
							+ ".htm");
					writer = new FileWriter(file, true);

					writer.write("<!DOCTYPE html> ");
					writer.write("<html>");
					writer.write("<head> ");
					writer.write("<meta charset='UTF-8'> ");
					writer.write("<title>" + strTestName.split("-")[0]
							+ " Execution Results</title> ");

					writer.write("<style type='text/css'> ");
					writer.write("body { ");
					writer.write("background-color: #FFFFFF; ");
					writer.write("font-family: Verdana, Geneva, sans-serif; ");
					writer.write("text-align: center; ");
					writer.write("} ");

					writer.write("small { ");
					writer.write("font-size: 0.7em; ");
					writer.write("} ");

					writer.write("table { ");
					writer.write("box-shadow: 9px 9px 10px 4px #BDBDBD;");
					writer.write("border: 0px solid #4D7C7B; ");
					writer.write("border-collapse: collapse; ");
					writer.write("border-spacing: 0px; ");
					writer.write("width: 1000px; ");
					writer.write("margin-left: auto; ");
					writer.write("margin-right: auto; ");
					writer.write("} ");

					writer.write("tr.heading { ");
					writer.write("background-color: #041944; ");
					writer.write("color: #FFFFFF; ");
					writer.write("font-size: 0.7em; ");
					writer.write("font-weight: bold; ");
					writer.write("background:-o-linear-gradient(bottom, #999999 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #000000) );");
					writer.write("background:-moz-linear-gradient( center top, #999999 5%, #000000 100% );");
					writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#999999, endColorstr=#000000);	background: -o-linear-gradient(top,#999999,000000);");
					writer.write("} ");

					writer.write("tr.subheading { ");
					writer.write("background-color: #FFFFFF; ");
					writer.write("color: #000000; ");
					writer.write("font-weight: bold; ");
					writer.write("font-size: 0.7em; ");
					writer.write("text-align: justify; ");
					writer.write("} ");

					writer.write("tr.section { ");
					writer.write("background-color: #A4A4A4; ");
					writer.write("color: #333300; ");
					writer.write("cursor: pointer; ");
					writer.write("font-weight: bold; ");
					writer.write("font-size: 0.7em; ");
					writer.write("text-align: justify; ");
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
					writer.write("background-color: #E1E1E1; ");
					writer.write("border: 1px solid #4D7C7B;");
					writer.write("color: #000000; ");
					writer.write("font-size: 0.75em; ");
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
					writer.write("max-width:200px;");
					writer.write("overflow:auto;");
					writer.write("} ");

					writer.write("td.pass { ");
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
					writer.write("font-weight: bold;");
					writer.write("color: blue; ");
					writer.write("} ");

					writer.write("td.warning { ");
					writer.write("font-weight: bold; ");
					writer.write("color: orange; ");
					writer.write("} ");
					writer.write("</style> ");

					writer.write("<script> ");
					writer.write("function toggleMenu(objID) { ");
					writer.write("if (!document.getElementById) return; ");
					writer.write("var ob = document.getElementById(objID).style; ");
					writer.write("if(ob.display === 'none') { ");
					writer.write("try { ");
					writer.write("ob.display='table-row-group'; ");
					writer.write("} catch(ex) { ");
					writer.write("ob.display='block'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("else { ");
					writer.write("ob.display='none'; ");
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
					writer.write("ob.style.display='table-row'; ");
					writer.write("} catch(ex) { ");
					writer.write("ob.style.display='block'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("else { ");
					writer.write("ob.style.display='none'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("} ");
					writer.write("</script> ");
					writer.write("</head> ");

					writer.write(" <body> ");
					writer.write("</br>");

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
					writer.write("<img align ='left' src= ./Screenshots/logo.png></img>");
					writer.write("</th>");
					writer.write("<th class = 'Logos' colspan='2' > ");
					writer.write("<img align ='right' src= ./Screenshots/cigniti.png></img>");
					writer.write("</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.write("</table> ");

					writer.write("<table id='header'> ");
					writer.write("<colgroup> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("</colgroup> ");

					writer.write(" <thead> ");

					writer.write("<tr class='heading'> ");
					writer.write("<th colspan='6' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> ");
					writer.write("Fluxx - Grantee & Grant Approval Workflow Execution Results");
					writer.write("</th> ");
					writer.write("</tr> ");
					writer.write("<tr class='subheading'> ");
					writer.write("<th>&nbsp;Date&nbsp;&&nbsp;Time&nbsp;:&nbsp;</th> ");

					writer.write("<th>" + ReportStampSupport.dateTime() + "</th> ");
					writer.write("<th>&nbsp;Application:&nbsp;</th> ");
					writer.write("<th>" + "Fluxx - Grantee & Grant Approval Workflow" + "</th> ");
					writer.write("</tr> ");

					writer.write("<tr class='subheading'> ");
					writer.write("<th>&nbsp;Browser&nbsp;:&nbsp;</th> ");
					writer.write("<th>" + (tc_name.substring(tc_name.indexOf("-") + 1).split("-")[0])
							+ "</th> ");
					writer.write("<th>&nbsp;Executed&nbsp;on&nbsp;:&nbsp;</th> ");
					writer.write("<th>" + InetAddress.getLocalHost().getHostName()
							+ "</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.write("</table> ");

					writer.close();
					map.put(packageName + ":" + tc_name, "status");
				} catch (Exception e) {

				} finally {
					try {
						writer.flush();
						writer.close();
					} catch (Exception e) {

					}
				}
			}

			public void testHeaderF(String testName, String browser) {
				Writer writer = null;
				try {
					strTestName = testName;
					strTestName=strTestName.replaceAll("_", " ");
					File file = new File("Results/HTML/" + strTestName + "_Results"
							+ ".htm");// "Results.htm"
					writer = new FileWriter(file, true);

					writer.write("<!DOCTYPE html> ");
					writer.write("<html>");
					writer.write("<head> ");
					writer.write("<meta charset='UTF-8'> ");
					writer.write("<title>" + strTestName.split("-")[0]
							+ " Execution Results</title> ");

					writer.write("<style type='text/css'> ");
					writer.write("body { ");
					writer.write("background-color: #FFFFFF; ");
					writer.write("font-family: Verdana, Geneva, sans-serif; ");
					writer.write("text-align: center; ");
					writer.write("} ");

					writer.write("small { ");
					writer.write("font-size: 0.7em; ");
					writer.write("} ");

					writer.write("table { ");
					writer.write("box-shadow: 9px 9px 10px 4px #BDBDBD;");
					writer.write("border: 0px solid #4D7C7B; ");
					writer.write("border-collapse: collapse; ");
					writer.write("border-spacing: 0px; ");
					writer.write("width: 1000px; ");
					writer.write("margin-left: auto; ");
					writer.write("margin-right: auto; ");
					writer.write("} ");

					writer.write("tr.heading { ");
					writer.write("background-color: #041944; ");
					writer.write("color: #FFFFFF; ");
					writer.write("font-size: 0.7em; ");
					writer.write("font-weight: bold; ");
					writer.write("background:-o-linear-gradient(bottom, #999999 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #000000) );");
					writer.write("background:-moz-linear-gradient( center top, #999999 5%, #000000 100% );");
					writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#999999, endColorstr=#000000);	background: -o-linear-gradient(top,#999999,000000);");
					writer.write("} ");

					writer.write("tr.subheading { ");
					writer.write("background-color: #FFFFFF; ");
					writer.write("color: #000000; ");
					writer.write("font-weight: bold; ");
					writer.write("font-size: 0.7em; ");
					writer.write("text-align: justify; ");
					writer.write("} ");

					writer.write("tr.section { ");
					writer.write("background-color: #A4A4A4; ");
					writer.write("color: #333300; ");
					writer.write("cursor: pointer; ");
					writer.write("font-weight: bold; ");
					writer.write("font-size: 0.7em; ");
					writer.write("text-align: justify; ");
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
					writer.write("background-color: #E1E1E1; ");
					writer.write("border: 1px solid #4D7C7B;");
					writer.write("color: #000000; ");
					writer.write("font-size: 0.75em; ");
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

					writer.write("td.pass { ");
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
					writer.write("font-weight: bold;");
					writer.write("color: blue; ");
					writer.write("} ");

					writer.write("td.warning { ");
					writer.write("font-weight: bold; ");
					writer.write("color: orange; ");
					writer.write("} ");
					writer.write("</style> ");

					writer.write("<script> ");
					writer.write("function toggleMenu(objID) { ");
					writer.write("if (!document.getElementById) return; ");
					writer.write("var ob = document.getElementById(objID).style; ");
					writer.write("if(ob.display === 'none') { ");
					writer.write("try { ");
					writer.write("ob.display='table-row-group'; ");
					writer.write("} catch(ex) { ");
					writer.write("ob.display='block'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("else { ");
					writer.write("ob.display='none'; ");
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
					writer.write("ob.style.display='table-row'; ");
					writer.write("} catch(ex) { ");
					writer.write("ob.style.display='block'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("else { ");
					writer.write("ob.style.display='none'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("} ");
					writer.write("</script> ");
					writer.write("</head> ");

					writer.write(" <body> ");
					writer.write("</br>");

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
					writer.write("<img align ='left' src= ./Screenshots//"
							+ config.getProperty("logo") + ".png></img>");
					writer.write("</th>");
					writer.write("<th class = 'Logos' colspan='2' > ");
					writer.write("<img align ='right' src= .//Screenshots//logo.png></img>");
					writer.write("</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.write("</table> ");

					writer.write("<table id='header'> ");
					writer.write("<colgroup> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("<col style='width: 25%' /> ");
					writer.write("</colgroup> ");

					writer.write(" <thead> ");

					writer.write("<tr class='heading'> ");
					writer.write("<th colspan='4' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> ");
					writer.write("" + strTestName.split("-")[0] + " Execution Results");
					writer.write("</th> ");
					writer.write("</tr> ");
					writer.write("<tr class='subheading'> ");
					writer.write("<th>&nbsp;Date&nbsp;&&nbsp;Time&nbsp;:&nbsp;</th> ");

					writer.write("<th>" + ReportStampSupport.dateTime() + "</th> ");
					writer.write("<th>&nbsp;Iteration&nbsp;Mode&nbsp;:&nbsp;</th> ");
					writer.write("<th>RunAllIterations</th> ");
					writer.write("</tr> ");

					writer.write("<tr class='subheading'> ");
					writer.write("<th>&nbsp;Browser&nbsp;:&nbsp;</th> ");
					writer.write("<th>" + browser.split("-")[0] + "</th> ");
					writer.write("<th>&nbsp;Executed&nbsp;on&nbsp;:&nbsp;</th> ");
					writer.write("<th>" + InetAddress.getLocalHost().getHostName()
							+ "</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.write("</table> ");

					writer.write("<table id='main'> ");
					writer.write("<colgroup> ");
					writer.write("<col style='width: 5%' /> ");
					writer.write("<col style='width: 26%' /> ");
					writer.write("<col style='width: 51%' /> ");
					writer.write("<col style='width: 8%' /> ");
					writer.write("<col style='width: 10%' /> ");
					writer.write("</colgroup> ");
					writer.write("<thead> ");
					writer.write("<tr class='heading'> ");
					writer.write("<th>S.NO</th> ");
					writer.write("<th>Steps</th> ");
					writer.write("<th>Details</th> ");
					writer.write("<th>Status</th> ");
					writer.write("<th>Time</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.close();
					map.put(packageName + ":" + tc_name, "status");
				} catch (Exception e) {

				} finally {
					try {
						writer.flush();
						writer.close();
					} catch (Exception e) {

					}
				}
			}

			public static void copyLogos() {
				File srcFolder = new File("Logos");
				File destFolder = new File("Results/HTML/Screenshots");
				// make sure source exists
				if (!srcFolder.exists()) {
					System.out.println("Directory does not exist.");
				} else {

					try {
						copyFolder(srcFolder, destFolder);
					} catch (IOException e) {

					}
				}
			}

			public static void copyFolder(File src, File dest) throws IOException {

				if (src.isDirectory()) {

					// if directory not exists, create it
					if (!dest.exists()) {
						dest.mkdir();
						System.out.println("Directory copied from " + src + "  to "
								+ dest);
					}

					// list all the directory contents
					String files[] = src.list();

					for (String file : files) {
						// construct the src and dest file structure
						File srcFile = new File(src, file);
						File destFile = new File(dest, file);
						// recursive copy
						copyFolder(srcFile, destFile);
					}

				} else {
					// if file, then copy it
					// Use bytes stream to support all file types
					InputStream in = new FileInputStream(src);
					OutputStream out = new FileOutputStream(dest);

					byte[] buffer = new byte[1024];

					int length;
					// copy the file content in bytes
					while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
					}
					in.close();
					out.close();
					System.out.println("File copied from " + src + " to " + dest);
				}
			}
			
			
			
			public void testCaseSeparatorHeader(String testName,int testCaseIndex, String tcId) {
				Writer writer = null;
				try {
					//strTestName = ScenarioName;
					strTestName=strTestName.replaceAll("_"," ");			
					File file = new File("Results/HTML/" + strTestName + "_Results"
							+ ".htm");// "Results.htm"
					writer = new FileWriter(file, true);

					writer.write("<!DOCTYPE html> ");
					writer.write("<html>");
					writer.write("<head> ");
					writer.write("<meta charset='UTF-8'> ");
					writer.write("<title>" + strTestName.split("-")[0]
							+ " Execution Results</title> ");

					writer.write("<style type='text/css'> ");
					writer.write("body { ");
					writer.write("background-color: #FFFFFF; ");
					writer.write("font-family: Verdana, Geneva, sans-serif; ");
					writer.write("text-align: center; ");
					writer.write("} ");

					writer.write("small { ");
					writer.write("font-size: 0.7em; ");
					writer.write("} ");

					writer.write("table { ");
					writer.write("box-shadow: 9px 9px 10px 4px #BDBDBD;");
					writer.write("border: 0px solid #4D7C7B; ");
					writer.write("border-collapse: collapse; ");
					writer.write("border-spacing: 0px; ");
					writer.write("width: 1200px; ");
					writer.write("margin-left: auto; ");
					writer.write("margin-right: auto; ");
					writer.write("} ");

					writer.write("tr.heading { ");
					writer.write("background-color: #041944; ");
					writer.write("color: #FFFFFF; ");
					writer.write("font-size: 0.7em; ");
					writer.write("font-weight: bold; ");
					writer.write("background:-o-linear-gradient(bottom, #999999 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #000000) );");
					writer.write("background:-moz-linear-gradient( center top, #999999 5%, #000000 100% );");
					writer.write("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#999999, endColorstr=#000000);	background: -o-linear-gradient(top,#999999,000000);");
					writer.write("} ");

					writer.write("tr.subheading { ");
					writer.write("background-color: #FFFFFF; ");
					writer.write("color: #000000; ");
					writer.write("font-weight: bold; ");
					writer.write("font-size: 0.7em; ");
					writer.write("text-align: justify; ");
					writer.write("} ");

					writer.write("tr.section { ");
					writer.write("background-color: #A4A4A4; ");
					writer.write("color: #333300; ");
					writer.write("cursor: pointer; ");
					writer.write("font-weight: bold; ");
					writer.write("font-size: 0.7em; ");
					writer.write("text-align: justify; ");
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
					writer.write("background-color: #E1E1E1; ");
					writer.write("border: 1px solid #4D7C7B;");
					writer.write("color: #000000; ");
					writer.write("font-size: 0.75em; ");
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
					writer.write("max-width:200px;");
					writer.write("overflow:auto;");
					writer.write("} ");

					writer.write("td.pass { ");
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
					writer.write("font-weight: bold;");
					writer.write("color: blue; ");
					writer.write("} ");

					writer.write("td.warning { ");
					writer.write("font-weight: bold; ");
					writer.write("color: orange; ");
					writer.write("} ");
					writer.write("</style> ");

					writer.write("<script> ");
					writer.write("function toggleMenu(objID) { ");
					writer.write("if (!document.getElementById) return; ");
					writer.write("var ob = document.getElementById(objID).style; ");
					writer.write("if(ob.display === 'none') { ");
					writer.write("try { ");
					writer.write("ob.display='table-row-group'; ");
					writer.write("} catch(ex) { ");
					writer.write("ob.display='block'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("else { ");
					writer.write("ob.display='none'; ");
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
					writer.write("ob.style.display='table-row'; ");
					writer.write("} catch(ex) { ");
					writer.write("ob.style.display='block'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("else { ");
					writer.write("ob.style.display='none'; ");
					writer.write("} ");
					writer.write("} ");
					writer.write("} ");
					writer.write("</script> ");
					writer.write("</head> ");

					writer.write(" <body> ");
					writer.write("</br>");
					
					
					
					
					
					
					writer.write("<table id='main"+testCaseIndex+"'> ");
					writer.write("<colgroup> ");
					writer.write("<col style='width: 5%' /> ");
					writer.write("<col style='width: 29%' /> ");
					writer.write("<col style='width: 41%' /> ");
					writer.write("<col style='width: 12%' /> ");
					writer.write("<col style='width: 13%' /> ");
				
					writer.write("</colgroup> ");
					writer.write("<thead> ");
					writer.write("<tr class='heading'> ");
					writer.write("<th colspan='7' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> ");
					//writer.write(testName +" (Testcase Id: "+tcId+")");
					writer.write(testName);
					writer.write("</th> ");
					writer.write("</tr> ");
					writer.write("<tr style='background-color: #999999;font-family:Copperplate Gothic Bold; font-size:0.8em;'> ");
					writer.write("<th>S.NO</th> ");					
					writer.write("<th>Step</th> ");
					writer.write("<th>Message</th> ");
					
					writer.write("<th>Status</th> ");
					writer.write("<th>Time</th> ");
					writer.write("</tr> ");
					
					
					writer.close();
					//map.put(packageName + ":" + tc_name, "status");
				} catch (Exception e) {

				} finally {
					try {
						writer.flush();
						writer.close();
					} catch (Exception e) {

					}
				}
			}
			
			
			synchronized public static void createHtmlSummaryReportHeader() throws Exception {

				File file = new File("Results/HTML/" + "SummaryResults_" + "Fluxx" + ".htm");// "SummaryReport.htm"
				Map<String, String> treeMap = new TreeMap<String, String>(map);
				Map<String, String> map=new LinkedHashMap<String, String> (treeMap);
				Writer writer = null;
				
				
				if (file.exists()) {
					file.delete();
				}
				writer = new FileWriter(file, true);
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
					writer.write("background-color: #6A90B6;");
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
					writer.write("</br>");

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
					writer.write("<img align ='left' src= ./Screenshots/logo.png></img>");
					writer.write("</th>");
					writer.write("<th class = 'Logos' colspan='2' > ");
					writer.write("<img align ='right' src= ./Screenshots/cigniti.png></img>");
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
					writer.write("<th>&nbsp;Date&nbsp;&&nbsp;Time&nbsp;:&nbsp;" + ""
							+ "</th> ");
					// writer.write("<th>&nbsp;:&nbsp;08-Apr-2013&nbsp;06:24:21&nbsp;PM</th> ");
					writer.write("<th> &nbsp;" + ReportStampSupport.dateTime()
							+ "&nbsp;</th> ");
					writer.write("<th>&nbsp;Application&nbsp;:&nbsp;</th> ");
					writer.write("<th>" + "Fluxx Grantee & Grant Approval Workflow" + "</th> ");
					writer.write("</tr> ");

					writer.write("<tr class='subheading'> ");
					writer.write("<th>&nbsp;Host Name&nbsp;:</th> ");
					writer.write("<th>" + InetAddress.getLocalHost().getHostName()
							+ "</th> ");
					/*writer.write("<th>&nbsp;Suite Executed&nbsp;:&nbsp;</th> ");
			writer.write("<th>Sanity</th> ");*/
					writer.write("<th>&nbsp;Browser&nbsp;:</th> ");
					//writer.write("<th>" + browser.split("-")[0] + "</th> ");
					writer.write("<th>"+"Chrome"+"</th> ");
					writer.write("</tr> ");

					/*writer.write("<tr class='subheading'> ");
			writer.write("<th>&nbsp;Host Name&nbsp;:</th> ");
			writer.write("<th>" + InetAddress.getLocalHost().getHostName()
					+ "</th> ");
			writer.write("<th>&nbsp;No.&nbsp;Of&nbsp;Threads&nbsp;:&nbsp;</th> ");
			writer.write("<th>" + "NA" + "</th> ");
			writer.write("</tr> ");*/

					writer.write("<tr class='subheading'> ");
					/*writer.write("<th colspan='4'> ");
					writer.write("&nbsp;Environment -  " + interactionUrl + "");
					writer.write("</th> ");*/
					writer.write("</tr> ");
					writer.write("</thead> ");
					writer.write("</table> ");
					writer.write("<table id='main'> ");
					writer.write("<colgroup> ");
					writer.write("<col style='width: 5%' /> ");
					writer.write("<col style='width: 15%' /> ");
					
					writer.write("<col style='width: 62%' /> ");
					writer.write("<col style='width: 10%' /> ");
					writer.write("<col style='width: 8%' /> ");
					writer.write("</colgroup> ");
					writer.write("<thead> ");
					writer.write("<tr class='heading'> ");
					writer.write("<th>Sl.No</th> ");
					writer.write("<th>Test Case ID</th> ");
					
					writer.write("<th>Scenario</th> ");
					writer.write("<th>Time</th> ");
					writer.write("<th>Status</th> ");
					writer.write("</tr> ");
					writer.write("</thead> ");
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				finally {

					writer.flush();
					writer.close();

				}
			}
			

			
			synchronized public static void createHtmlSummaryReportContent(int testCaseIndex, String tcId, String description) throws Exception {

				File file = new File("Results/HTML/" + "SummaryResults_" + "Fluxx" + ".htm");// "SummaryReport.htm"
				Map<String, String> treeMap = new TreeMap<String, String>(map);
				Map<String, String> map=new LinkedHashMap<String, String> (treeMap);
				Writer writer = null;
				String description1=null;
				String description2=null;
				String detailRptFileName="Fluxx Grant Creation-chrome-Web";
				String testCaseName = "Grant Creation";
				
				
				writer = new FileWriter(file, true);
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
					writer.write("background-color: #6A90B6;");
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
					Iterator<Entry<String, String>> iterator1 = map.entrySet()
							.iterator();
					
					writer.write("<tbody> ");
					System.out.println("MAP SIZE ***:"+map.size()+",   Application: "+"Fluxx - Grantee & Grant Approval Workflow");
					System.out.println("MAP :"+map);
					//while (iterator1.hasNext()) {
						Map.Entry mapEntry1 = (Map.Entry) iterator1.next();
						key = mapEntry1.getKey().toString().split(":");
						String value = (String) mapEntry1.getValue();
						String time=executionTime.get(key[1]);
						time ="null";
						if(time =="null"){					
									writer.write("<tr class='content2' > ");
									writer.write("<td class='justified'>" + serialNo + "</td>");
									serialNo = serialNo + 1;	
									writer.write("<td class='justified'><a href='" + detailRptFileName
												+ "_Results" + ".htm#main"+testCaseIndex+"'"
												+ "' target='about_blank'>"
												+ tcId
												+ "</a></td>");
									writer.write("<td class='justified'>" + description + "</td>");  
									

									writer.write("<td>" + executionTime.get(key[1])
											+ " Seconds</td>");
									if (TestEngine.testResults.get(key[1]).equals("PASS"))
									{
										writer.write("<td class='pass'>Passed</td> ");
										passCount++;
									}
									else if(TestEngine.testResults.get(key[1]).equals("FAIL"))
									{
										writer.write("<td class='fail'>Failed</td> ");
										failCount++;
									}
									else
										writer.write("<td class='fail'>Exception</td> ");
									
									writer.write("</tr>");
									writer.write("</tbody> ");

								//}
							//}

						}

					//}

				} catch (Exception e) {
					e.printStackTrace();
				}

				finally {

					writer.flush();
					writer.close();

				}
			}
}
