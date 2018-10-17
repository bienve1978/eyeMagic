package com.churracero.eyeMagic.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.churracero.eyeMagic.model.EyeMagicConf;

@Path("/eyeMagicService")
public class EyeMagicService {

		private final static Logger logger     = Logger.getLogger(EyeMagicService.class);
		private final static EyeMagicConf conf = new EyeMagicConf();
		private final static MotionSensorService motionSensor = new MotionSensorService();
				
		@GET
		@Path("getConfiguration")
		@Produces(MediaType.APPLICATION_JSON)
		public EyeMagicConf getConfiguration() {
			logger.info("getConfiguration -> "+conf);
			return EyeMagicService.conf;
		}

		@POST
		@Path("setConfiguration")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response setConfiguration(EyeMagicConf conf) {
			logger.info("setConfiguration -> "+conf);
			EyeMagicService.conf.setActive(conf.getActive());
			if (EyeMagicService.conf.getActive())
				motionSensor.start();
			else 
				motionSensor.interrupt();
						
			return Response.status(201).entity(conf.getActive()).build();
			
		}
}
