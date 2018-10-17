package com.churracero.eyeMagic.service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;

import com.churracero.eyeMagic.model.EyeMagicProp;

public class EmailServiceTest {

	private EyeMagicProp props;

	@Before
	public void setUp() throws Exception {

		// get Properties
		EyeMagicPropService eyeMagicPropSevice = new EyeMagicPropService();
		props = eyeMagicPropSevice.load();
	}

	@Test
	public void sendMail() {
		Path fileSendPath = FileSystems.getDefault().getPath("/bin/bash");
		
		EmailService emailservice = new EmailService();
		emailservice.send(props.getEmails(), fileSendPath);
	}
}
