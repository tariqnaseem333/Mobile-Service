package com.infy.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;
import com.infy.service.MobileService;
import com.infy.service.MobileServiceImpl;

public class MobileServiceTest {

    private MobileService mobileService= new MobileServiceImpl();
    
    @Test
	public void registerRequestInvalidBrandTest() {
    	ServiceRequest serviceRequest = new ServiceRequest("abc", List.of("Battery"), 9876765487L, 
    														"John", 1234567890123456L);
		MobileServiceException exception = Assertions.assertThrows(MobileServiceException.class, () -> mobileService.registerRequest(serviceRequest));
		Assertions.assertEquals("Validator.INVALID_BRAND", exception.getMessage());
	}

    @Test
	public void registerRequestInvalidContactNumberTest() {
    	ServiceRequest serviceRequest = new ServiceRequest("Twoplusone", List.of("Battery"), 78757L, 
															"John", 1234567890123456L);
    	MobileServiceException exception = Assertions.assertThrows(MobileServiceException.class, () -> mobileService.registerRequest(serviceRequest));
    	Assertions.assertEquals("Validator.INVALID_CONTACT_NUMBER", exception.getMessage());
	}

    @Test
	public void registerRequestInvalidIssuesTest() {
    	ServiceRequest serviceRequest = new ServiceRequest("Twoplusone", List.of("Broken screen"), 9876765487L, 
															"John", 1234567890123456L);
    	MobileServiceException exception = Assertions.assertThrows(MobileServiceException.class, () -> mobileService.registerRequest(serviceRequest));
    	Assertions.assertEquals("Service.INVALID_ISSUES", exception.getMessage());
	}

}
