package com.churracero.eyeMagic.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class EmailService {

	private final static Logger logger       = Logger.getLogger(EmailService.class);
	private final static String eyeMagicMail = "eyemagicadm@gmail.com"; 
	private final static String eyeMagicPass = "eyeMagic1978";
	
	public void send(List<InternetAddress> emails, Path filePath) {

		for (InternetAddress currentEmail : emails) {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.user", "eyemagicadm@gmail.com");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true"); 
			
			// sessions
			Session session = Session.getInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(eyeMagicMail, eyeMagicPass);
				}
			  });
			//session.setDebug(Boolean.TRUE);
			
			// Create a default MimeMessage object.
			MimeMessage msg = new MimeMessage(session);
			try {
				// Set From: header field of the header.
				msg.setFrom(new InternetAddress(eyeMagicMail));
				// Set To: header field of the header.
				msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(currentEmail.toString()));
				// Set Subject: header field
				msg.setSubject("Eye Magic", "UTF-8");
			    // Create the message part
		        BodyPart msgBodyText = new MimeBodyPart();
		       	msgBodyText.setText("Hello !!! Eye Motion has detected movement stranger !!!!");
				// Part two is attachment
		        BodyPart msgBodyFile = new MimeBodyPart();
		        DataSource source = new FileDataSource(filePath.toString());
		        msgBodyFile.setDataHandler(new DataHandler(source));
		        msgBodyFile.setFileName(filePath.toString());
		        // Create a multipart message
		        Multipart multipart = new MimeMultipart();
		        multipart.addBodyPart(msgBodyText);
		        multipart.addBodyPart(msgBodyFile);
		        // Send email
		        msg.setContent(multipart);
				Transport.send(msg);
				logger.info("Send email to "+ currentEmail);
			} catch (MessagingException e) {
				logger.warn("Error send email to "+ currentEmail);
				e.printStackTrace();
			}
		}
	}
}
