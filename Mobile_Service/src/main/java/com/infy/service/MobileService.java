package com.infy.service;

import java.util.List;

import com.infy.model.ServiceRequest;
import com.infy.model.ServiceReport;
import com.infy.model.Status;

public interface MobileService {
	
	public ServiceRequest registerRequest(ServiceRequest service) throws Exception;
	public Float calculateEstimateCost(List<String> issues) throws Exception;
	public List<ServiceReport> getServices(Status status) throws Exception;
	
}
