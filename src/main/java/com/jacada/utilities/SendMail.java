package com.jacada.utilities;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;

import com.jacada.support.ConfiguratorSupport;
import com.jacada.support.HtmlReportSupport;

public class SendMail extends HtmlReportSupport {


	static ConfiguratorSupport config = new ConfiguratorSupport(
			"config.properties");

	public static void attachReportsToEmail(String zip) throws Exception {
		String strBrowser = config.getProperty("browserType");

		//Zip.zipFolder(zip, zip+"Automation.zip");
		/*String strZipFilePath = "Results\\"
				+ strReportsFolder
				+ "Results/HTML/Screenshots/".split(strReportsFolder)[2].replace(
						"\\", "") + ".Zip";
						String strZipFilePath = "Results\\"
								+ "HTML19_Jun_2015_01_49_06_277"
								+ "Results/HTML/Screenshots/".split(strReportsFolder)[2].replace(
										"\\", "") + ".Zip";
		Zip.zipFolder("Results/HTML/Screenshots/", strZipFilePath);
		Zip.zipFolder("C:\\Users\\ctl-user\\Desktop\\Jun17Workspace\\FKartProject\\Results\\HTML22_Jun_2015_06_17_12_916", "C:\\Users\\ctl-user\\Desktop\\Jun17Workspace\\FKartProject\\Results\\HTML22_Jun_2015_06_17_12_916.zip");*/
		String[] to =  config.getProperty("ToAddresses").split(",");
		//String[] cc =  config.getProperty("CcAddresses").split(",");
		//String[] bcc = config.getProperty("ToAddresses").split(",");

		// This is for google
		SendMail.sendMail(
				config.getProperty("MailUserName"),
				config.getProperty("MailpassWord"),
				"smtp.gmail.com",
				"465",
				"true",
				"true",
				false,
				"javax.net.ssl.SSLSocketFactory",
				"false",
				to,
				"Automation Reports",
				"The attached are the results of current Build",
				zip,"Results.zip");
		//	zip+"Automation.zip","Automation.zip");
		/*strReportsFolder+ "Results/HTML/Screenshots/".split(strReportsFolder)[2]
								.replace("\\", "") + ".Zip");*/

	}

	public static boolean sendMail(String userName, String passWord,
			String host, String port, String starttls, String auth,
			boolean debug, String socketFactoryClass, String fallback,
			String[] to, String subject,
			String text, String attachmentPath, String attachmentName) {

		//		String strReportsFolder = "Firefox";

		Properties props = new Properties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if (!"".equals(port))

			props.put("mail.smtp.port", port);

		if (!"".equals(starttls))

			props.put("mail.smtp.starttls.enable", starttls);

		props.put("mail.smtp.auth", auth);

		if (debug) {

			props.put("mail.smtp.debug", "true");

		} else {

			props.put("mail.smtp.debug", "false");

		}

		if (!"".equals(port))

			props.put("mail.smtp.socketFactory.port", port);

		if (!"".equals(socketFactoryClass))

			props.put("mail.smtp.socketFactory.class", socketFactoryClass);

		if (!"".equals(fallback))

			props.put("mail.smtp.socketFactory.fallback", fallback);

		try

		{

			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setSubject(subject);

			BodyPart messageBodyPart1 = new MimeBodyPart();
			String table3 = FileUtils.readFileToString(new File("Results\\HTML\\SummaryResults.htm"));

			messageBodyPart1.setContent(table3, "text/html");

			Multipart multipart = new MimeMultipart();
			//MimeBodyPart messageBodyPart = new MimeBodyPart();

			if (!"".equals(attachmentPath)){
				System.out.println("Entered into Attachment code");
				DataSource source = new FileDataSource(attachmentPath);
				//messageBodyPart.setDataHandler(new DataHandler(source));
				//messageBodyPart.setFileName(attachmentName);
			}
			//multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(messageBodyPart1);
			msg.setContent(multipart);
			msg.setFrom(new InternetAddress(userName+"@gmail.com"));

			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}

			/*for (int i = 0; i < cc.length; i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			}
			for (int i = 0; i < bcc.length; i++) {
				msg.addRecipient(Message.RecipientType.BCC,	new InternetAddress(bcc[i]));
			}*/

			msg.saveChanges();

			Transport transport = session.getTransport("smtp");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;
		}
		catch (Exception mex)
		{
			mex.printStackTrace();
			return false;
		}
	}
}
