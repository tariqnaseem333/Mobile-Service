package com.infy.validator;

import java.util.ArrayList;
import java.util.List;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;

public class Validator {

	public void validate(ServiceRequest service) throws MobileServiceException {	
		if( !this.isValidBrand( service.getBrand() ) ) {
			throw new MobileServiceException("Sorry! we do not provide service for this brand");
		} else if( !this.isValidIssues( service.getIssues() ) ) {
			throw new MobileServiceException("Please provide the device only if there are issues.");
		} else if( !this.isValidIMEINumber( service.getiMEINumber() ) ) {
			throw new MobileServiceException("Sorry! we’re not able to detect the IMEI number for this device.");
		} else if( !this.isValidContactNumber( service.getContactNumber() ) ) {
			throw new MobileServiceException("Please provide a valid contact number.");
		} else if( !this.isValidCustomerName( service.getCustomerName() ) ) {
			throw new MobileServiceException("Please provide a valid customer name.");
		}
	}	
	
//	validates the brand, brand should always start with a upper case alphabet 
//	and can be followed by one or more alphabets (lower case or upper case) 
	public Boolean isValidBrand(String brand){
		return brand.matches("([A-Z][A-Za-z]+)");
	}
	
//	validates the list of issues checks if the list is null or empty
	public Boolean isValidIssues(List<String> issues) {
		return !issues.isEmpty();
	}

//	validates the IMEINumber it should be a 16 digit number 
	public Boolean isValidIMEINumber(Long iMEINumber) {
		return (iMEINumber.toString().length() == 16);
	}
	
//	validates the contact number should contain 10 numeric characters 
//	and should not contain 10 repetitive characters
	public Boolean isValidContactNumber(Long contactNumber) {
		boolean isAllDigitsSame = true;
		List<Integer> contactNumberList = new ArrayList<>();
		long N = contactNumber;
		int rem = 0;
		while( N > 0 ) {
			rem = (int)(N % 10);
			contactNumberList.add(rem);
			N = N / 10;
		}
		for( int i = 0; i < contactNumberList.size(); i++ ) {
			if( !contactNumberList.get(i).equals(contactNumberList.get(i+1)) ) {
				isAllDigitsSame = false;
				break;
			}
		}
		return (contactNumber.toString().length() == 10) && !isAllDigitsSame;
	}
	
	
//	validates the customer name should contain at least one word and each word separated by a single space
//	should contain at least one letter. The first letter of every word should be an upper case character 
	public Boolean isValidCustomerName(String customerName) {
		return customerName.matches("([A-Z][a-z]+)( [A-Z][a-z])*");
	}
}
