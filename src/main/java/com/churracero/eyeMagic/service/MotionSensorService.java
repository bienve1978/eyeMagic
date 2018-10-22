package com.churracero.eyeMagic.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;

import org.apache.log4j.Logger;

import com.churracero.eyeMagic.model.EyeMagicProp;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class MotionSensorService extends Thread{
	
	private final static Logger logger= Logger.getLogger(MotionSensorService.class);
    private final GpioController gpio = GpioFactory.getInstance();
       
    private GpioPinDigitalInput motionButton;
    private EmailService emailService;
    private EyeMagicPropService eyeMagicPropService;
    private EyeMagicProp props;
    
   	private class motionButtonListener implements GpioPinListenerDigital{

   		private String[] commandExec = new String[5];
   		private Integer milisec      = props.getCaptSec()*1000;
   		
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			logger.info("-->GPIO PIN STATE CHANGE:" + event.getPin() + " = " + event.getState());
			try{
				Date date = new Date();
				String fileNameCapt = String.format("%s/eyeMotion.h624.%s",props.getCaptPath().toString(),date.toString());
				Path fileCaptPath = FileSystems.getDefault().getPath(fileNameCapt);
				commandExec[0] = "/usr/bin/raspvid";
				commandExec[1] = "-o";
				commandExec[2] = fileNameCapt;
				commandExec[3] = "-t";
				commandExec[4] = milisec.toString();
						
				Runtime.getRuntime().exec(commandExec);
			    emailService.send(props.getEmails(), fileCaptPath);
			  } catch (IOException e) {
					logger.error(e.getMessage());
			 }
		}
   	}
    
   public void run() {
    	try {
    		props = eyeMagicPropService.load();
	 		motionButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);
    		motionButton.setShutdownOptions(Boolean.TRUE);
    		motionButton.addListener(new motionButtonListener());
    		Thread.sleep(100);
    	}catch (InterruptedException e)
    	{
    		logger.info(e.getMessage());
    	}catch (Exception e) {
			logger.error(e.getMessage());
		}
    };
}
