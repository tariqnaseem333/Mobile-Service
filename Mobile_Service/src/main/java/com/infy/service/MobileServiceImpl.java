package com.infy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.infy.dao.MobileServiceDAO;
import com.infy.dao.MobileServiceDAOImpl;
import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;
import com.infy.validator.Validator;

public class MobileServiceImpl implements MobileService{
	
//	Instance Variables
	private MobileServiceDAO dao =  new MobileServiceDAOImpl();
	private Validator validator=new Validator();

//  Methods
	@Override
	public ServiceRequest registerRequest(ServiceRequest service) throws MobileServiceException {
		validator.validate(service);
		float totalCost = this.calculateEstimateCost(service.getIssues());
		if( totalCost <= 0 )
			throw new MobileServiceException("Sorry, we do not provide that service");
		service.setServiceFee(totalCost);
		service.setStatus(Status.ACCEPTED);
		service.setTimeOfRequest(LocalDateTime.now());
		return dao.registerRequest(service);
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws MobileServiceException {
		float serviceFee = 0;
		for( String issue : issues ) {
			if( issue.equalsIgnoreCase("BATTERY") )
				serviceFee = serviceFee + 10;
			else if( issue.equalsIgnoreCase("CAMERA") )
				serviceFee = serviceFee + 5;
			else if( issue.equalsIgnoreCase("SCREEN") )
				serviceFee = serviceFee + 15;
			else 
				serviceFee = serviceFee + 0;
		}
		return serviceFee;
	}

	@Override
	public List<ServiceReport> getServices(Status status) throws MobileServiceException {
		List<ServiceReport> serviceReports = new ArrayList<>();
		dao.getServices().forEach( service -> {
			if( service.getStatus().equals(status) ) {
				ServiceReport serviceReport = new ServiceReport( service.getServiceId(), service.getBrand(),service.getIssues(), service.getServiceFee());
				serviceReports.add(serviceReport);
			}
		});
		if(serviceReports.isEmpty())
			throw new MobileServiceException("Sorry, we did not find any records for your query.");
		return serviceReports;
	}

}
