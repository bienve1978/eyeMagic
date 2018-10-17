package com.churracero.eyeMagic.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.churracero.eyeMagic.Main;
import com.churracero.eyeMagic.model.EyeMagicConf;
import com.churracero.eyeMagic.model.EyeMagicProp;

import static org.junit.Assert.assertEquals;

public class EyeMagicServiceTest {

    private EyeMagicProp props;

    @Before
    public void setUp() throws Exception {
    	
    	// get Properties
    	EyeMagicPropService eyeMagicPropSevice = new EyeMagicPropService();
		props = eyeMagicPropSevice.load();
			
        // start the server
        Main.startServer(props.getUri());
    }
      
    @Test
    public void changeStatus() {
    	
    	EyeMagicConf conf = new EyeMagicConf();
    	conf.setActive(Boolean.TRUE);
    	
    	Client c = ClientBuilder.newClient();
      	WebTarget target = c.target(props.getUri()).path("eyeMagicService").path("setConfiguration");
    	
    	Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.post(Entity.entity(conf, MediaType.APPLICATION_JSON));
    	assertEquals(response.getStatus(), 201);
    }
    
    @Test 
    public void getStatus(){
    	
    	Client c = ClientBuilder.newClient();
      	WebTarget target = c.target(props.getUri()).path("eyeMagicService").path("getConfiguration");
      	
      	Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();   	
    	assertEquals(response.getStatus(), 200);
    	
    	EyeMagicConf conf = response.readEntity(EyeMagicConf.class);
    	Assert.assertNotNull(conf);
    }
}

