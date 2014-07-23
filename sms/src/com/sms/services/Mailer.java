package com.sms.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Component;

import com.sms.utils.Settings;

@Component
public class Mailer {
	private static final String SMTP_HOST_NAME = System.getenv("SENDGRID_SMTP_HOST");
    private static final String SMTP_AUTH_USER = System.getenv("SENDGRID_USERNAME");
    private static final String SMTP_AUTH_PWD  = System.getenv("SENDGRID_PASSWORD");
    
	public boolean sendEmail(String toEmails, String ccEmails, String bccEmails, String subject, String message, ArrayList<String> files, String filesLocation){
		try{
			Properties properties = new Properties();	        

			properties.put("mail.smtp.port", 587);
			properties.put("mail.smtp.host", SMTP_HOST_NAME);
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.startssl.enable","true");
			properties.put("mail.smtp.auth", "true");
	
			Authenticator auth = new MyAuthenticator(SMTP_AUTH_USER, SMTP_AUTH_PWD);
			Session session = Session.getDefaultInstance(properties,auth);
			MimeMessage mimeMessage = new MimeMessage(session);
			Multipart body = new MimeMultipart();
			MimeBodyPart messagePart = new MimeBodyPart();

			if(files != null){
				for(int f=0; f < files.size(); f++) {
					MimeBodyPart part = new MimeBodyPart();

					FileDataSource ds = new FileDataSource(filesLocation + File.separator + files.get(f)) {
						public String getContentType() {
							return "application/octet-stream";
						}
					};

					part.setDataHandler(new DataHandler(ds));
					part.setFileName(files.get(f));
					body.addBodyPart(part);
				}
			}

			messagePart.setContent(MimeUtility.encodeText(message),"text/html");

			body.addBodyPart(messagePart);

			mimeMessage.setFrom(new InternetAddress(Settings.APP_ADMIN_MAILID, Settings.APP_ADMIN_MAILBOX_TITLE));

			if(isNotBlank(toEmails)){
				mimeMessage.addRecipients(Message.RecipientType.TO, getInternetAddressList(toEmails));

			}

			if(isNotBlank(ccEmails)){
				mimeMessage.addRecipients(Message.RecipientType.CC, getInternetAddressList(ccEmails));

			}

			if(isNotBlank(bccEmails)){
				mimeMessage.addRecipients(Message.RecipientType.BCC, getInternetAddressList(bccEmails));
			}

			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			mimeMessage.setSentDate(new Date());
			mimeMessage.setContent(body);
			mimeMessage.saveChanges();

			Transport.send(mimeMessage);
			System.out.println("Message SEND ==> SUCCESS");
			return true;
		}catch (Exception e) {
			System.out.println("Message NOT SEND ==>  ERROR"+e.getStackTrace());
			return true;
		}
	}

	public InternetAddress[] getInternetAddressList(String address) throws Exception{
		StringTokenizer addressTokenizer = new StringTokenizer(address,"\\|");
		InternetAddress[] addressList = new InternetAddress[addressTokenizer.countTokens()];

		int index = 0;
		while (addressTokenizer.hasMoreTokens()) {
			try {
				addressList[index++] = new InternetAddress(addressTokenizer.nextToken());
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}

		return addressList;
	}

	public boolean isNotBlank(String address){
		if((address == null) || address.trim().equals("") || address.trim().equalsIgnoreCase("null")){
			return false;
		}
		return true;
	}
}

class MyAuthenticator extends Authenticator {
	String email;    
	String password;

	MyAuthenticator(String email, String password){
		this.email = email;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {  
		return new PasswordAuthentication(email, password);  
	}  
}