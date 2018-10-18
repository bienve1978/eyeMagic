package com.churracero.eyeMagic.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import com.churracero.eyeMagic.model.EyeMagicProp;

public class EyeMagicPropService {

	private final static Logger logger = Logger.getLogger(EyeMagicPropService.class);
	private static final String FILE_NAME_PROPS = "/opt/eyeMagic/etc/eyeMagic.properties";
	private static final EyeMagicProp eyeMagicProp = new EyeMagicProp(); 

	public EyeMagicProp load() throws Exception {
		try {
			InputStream eyeMagicPropFile = new FileInputStream(FILE_NAME_PROPS);

			Properties prop = new Properties();
			prop.load(eyeMagicPropFile);
			
			// EMAILS
			String allEmails = prop.getProperty("emails");
			if(allEmails == null || allEmails.isEmpty())
			{
				throw new Exception("No found emails in "+FILE_NAME_PROPS);
			}else 
			{
				List<InternetAddress> emails = new ArrayList<InternetAddress>();
				String[] emailsText = allEmails.split(";");
				for(String emailText: emailsText)
				{
					try {
					InternetAddress currentEmail = new InternetAddress(emailText);
					currentEmail.validate();
					emails.add(currentEmail);
					}catch(AddressException e){
						logger.warn(e.getMessage());
					}
				}
				eyeMagicProp.setEmails(emails);
			}
			
			// PORT
			String host = prop.getProperty("host");
			if (host == null || host.isEmpty())
				throw new Exception("No found host in " + FILE_NAME_PROPS);
			else
				eyeMagicProp.setHost(host);

			// PORT
			String port = prop.getProperty("port");
			if (port == null || port.isEmpty())
				throw new Exception("No found port in " + FILE_NAME_PROPS);
			else
				eyeMagicProp.setPort(port);
			
			// URI
			String uri = String.format("http://%s:%s/", eyeMagicProp.getHost(), eyeMagicProp.getPort());
			eyeMagicProp.setUri(uri);
			
			// CAPTDIR
			String captDir = prop.getProperty("captDir");
			if(captDir == null || captDir.isEmpty())
				throw new Exception("No found capture directory in "+FILE_NAME_PROPS);
			else 	
				eyeMagicProp.setCaptDir(captDir);
			
			// CAPTSEC
			String captSec = prop.getProperty("captSec");
			if(captSec == null || captSec.isEmpty())
				throw new Exception("No found captude seconds in "+FILE_NAME_PROPS);
			else 
				eyeMagicProp.setCaptSec(Integer.parseInt(captSec));
			
			return eyeMagicProp;
		} catch (FileNotFoundException e) {
			throw new Exception("No existe el fichero " + FILE_NAME_PROPS);
		} catch (NumberFormatException e){
			throw new Exception("Clave captsec no es un entero !!!");
		}
	}
}
