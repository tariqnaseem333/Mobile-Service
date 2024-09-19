package com.infy.userinterface;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;
import com.infy.service.MobileService;
import com.infy.service.MobileServiceImpl;

public class MobileServiceTester {
	
	private static final Log LOGGER = LogFactory.getLog(MobileServiceTester.class);
	
	public static void main(String[] args) throws ConfigurationException{
		registerRequest();
		getServices();
	}
	
	public static void registerRequest() throws ConfigurationException{
		
	    Configurations configurations = new Configurations();
	    PropertiesConfiguration config = configurations.properties("configuration.properties");
		MobileService service = new MobileServiceImpl();
		
		try {
			ServiceRequest obj = new ServiceRequest("Abc", Arrays.asList("Battery"), 9876543210L, "Chap", 3214567890123456L);
			obj = service.registerRequest(obj);
			LOGGER.info(config.getProperty("Tester.SUCCESS") + ""+ obj.getServiceId() +", status of the request is "+obj.getStatus()+" on "+obj.getTimeOfRequest().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy  hh:mm:ss")));
		} catch (Exception e) {
			LOGGER.info(config.getProperty(e.getMessage()));
		}
		
	}
	
	public static void getServices() throws ConfigurationException{
		
		Configurations configurations = new Configurations();
		PropertiesConfiguration config = configurations.properties("configuration.properties");
		MobileService service = new MobileServiceImpl();
		
		try {
			List<ServiceReport> list = service.getServices(Status.COMPLETED);
			LOGGER.info("=================================");
			LOGGER.info(" ID \t Brand \t\t Fee");
			LOGGER.info("=================================");
			list.stream().forEach(obj->LOGGER.info(obj.getServiceId()+"\t"+obj.getBrand()+"\t"+obj.getServiceFee()+" $"));
			LOGGER.info("=================================");
		} catch (Exception e) {
			LOGGER.info(config.getProperty(e.getMessage()));
		}
	}
	
}
