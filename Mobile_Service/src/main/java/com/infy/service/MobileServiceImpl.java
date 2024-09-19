package com.infy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;

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
		try {
			validator.validate(service);
			Float estimateCost = calculateEstimateCost(service.getIssues());
			if(estimateCost <= 0F)
				throw new MobileServiceException("Service.INVALID_ISSUES");
			service.setServiceFee(estimateCost);
			service.setStatus(Status.ACCEPTED);
			service.setTimeOfRequest(LocalDateTime.now());
			return dao.registerRequest(service);
		} catch (MobileServiceException exception) {
			if(exception.getMessage().contains("Service"))
				LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws MobileServiceException {
		float estimateCost = 0F;
		for(String issue : issues) {
			  if(issue.equalsIgnoreCase("BATTERY"))
				  estimateCost = estimateCost + 10;
			  else if(issue.equalsIgnoreCase("CAMERA"))
				  estimateCost = estimateCost + 5;
			  else if(issue.equalsIgnoreCase("SCREEN"))
				  estimateCost = estimateCost + 15;
		}
		return estimateCost;
	}

	@Override
	public List<ServiceReport> getServices(Status status) throws MobileServiceException {
		try {
			List<ServiceReport> servicesReport = new ArrayList<>();
			dao.getServices()
			   .stream()
			   .filter(serviceRequest -> serviceRequest.getStatus().equals(status))
			   .forEach(serviceRequest -> {
				   ServiceReport serviceReport = new ServiceReport(serviceRequest.getServiceId(),
						   										   serviceRequest.getBrand(),
						   										   serviceRequest.getIssues(),
						   										   serviceRequest.getServiceFee());
				   servicesReport.add(serviceReport);
			   });
			if(servicesReport.isEmpty() || servicesReport == null)
				throw new MobileServiceException("Service.NO_RECORDS_FOUND");
			return servicesReport;
		} catch (MobileServiceException exception) {
			if(exception.getMessage().contains("Service"))
				LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}
		
	}

}
