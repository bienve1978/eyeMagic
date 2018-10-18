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
public class EyeMagic {
	// Private
	private final static Logger logger = Logger.getLogger(EyeMagic.class);
	private static HttpServer server = null;

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer(String uri) {

		ResourceConfig config = new ResourceConfig();
		config.register(EyeMagicService.class);
		config.setApplicationName(EyeMagicService.class.getName());

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

			server = startServer(prop.getUri());
			server.start();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
