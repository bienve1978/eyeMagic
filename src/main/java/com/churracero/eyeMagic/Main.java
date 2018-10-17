package com.churracero.eyeMagic;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.churracero.eyeMagic.model.EyeMagicProp;
import com.churracero.eyeMagic.service.EyeMagicService;
import com.churracero.eyeMagic.service.EyeMagicPropService;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
	// Private 
	private final static Logger logger = Logger.getLogger(Main.class);
	
	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer(String uri) {

		// create a resource config that scans for JAX-RS resources and
		// providers
		// in com.churracero.eyeMagic package
		ResourceConfig config = new ResourceConfig();
		config.register(EyeMagicService.class);
		config.setApplicationName(EyeMagicService.class.getName());

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), config);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			
			EyeMagicPropService eyeMagicPropSevice = new EyeMagicPropService();
			EyeMagicProp prop = eyeMagicPropSevice.load();
			logger.info(prop);
			
			startServer(prop.getUri());
			System.in.read();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
