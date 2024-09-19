package com.infy.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;

public class Validator {

	public void validate(ServiceRequest service) throws MobileServiceException{	
		String errorMessage = null;
		
		if(!isValidBrand(service.getBrand()))
			errorMessage = "Validator.INVALID_BRAND";
		else if(!isValidIssues(service.getIssues()))
			errorMessage = "Validator.INVALID_NO_OF_ISSUES";
		else if(!isValidIMEINumber(service.getiMEINumber()))
			errorMessage = "Validator.INVALID_IMEI_NUMBER";
		else if(!isValidContactNumber(service.getContactNumber()))
			errorMessage = "Validator.INVALID_CONTACT_NUMBER";
		else if(!isValidCustomerName(service.getCustomerName()))
			errorMessage = "Validator.INVALID_CUSTOMER_NAME";
		
		if(errorMessage != null) {
			MobileServiceException exception = new MobileServiceException(errorMessage);
			LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}
	}	
	
	// validates the brand
	// brand should always start with a upper case alphabet 
	// and can be followed by one or more alphabets (lower case or upper case) 
	public Boolean isValidBrand(String brand){
		return brand.matches("[A-Z][A-Za-z]+");
	}
	
	// validates the list of issues
	// checks if the list is null or empty
	public Boolean isValidIssues(List<String> issues) {
		return !(issues.isEmpty() || issues == null);
	}

	// validates the IMEINumber
	// it should be a 16 digit number 
	public Boolean isValidIMEINumber(Long iMEINumber) {
		return iMEINumber.toString().length() == 16;
	}
	
	// validates the contact number
	// should contain 10 numeric characters and should not contain 10 repetitive characters
	public Boolean isValidContactNumber(Long contactNumber) {
		boolean isValid = false;
		String[] contactNumberArr = contactNumber.toString().split("");
		for(int i = 0; i < contactNumberArr.length-1; i++) {
			if(!contactNumberArr[i].equals(contactNumberArr[i+1])) {
				isValid = true;
				break;
			}
		}
		return (contactNumber.toString().length() == 10 && isValid);
	}
	
	
	// validates the customer name
	// should contain at least one word and each word separated by a single space should contain at least one letter.
	// the first letter of every word should be an upper case character 
	public Boolean isValidCustomerName(String customerName) {
		return customerName.matches("([A-Z][a-z]+)([ ][A-Z][a-z]+)*");
	}
	
}
