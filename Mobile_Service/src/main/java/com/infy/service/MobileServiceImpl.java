package com.infy.service;

import java.util.List;
import com.infy.dao.MobileServiceDAO;
import com.infy.dao.MobileServiceDAOImpl;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;

public class MobileServiceImpl implements MobileService{
	
	private MobileServiceDAO dao =  new MobileServiceDAOImpl();

	@Override
	public ServiceRequest registerRequest(ServiceRequest service) throws Exception {
		return null;
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws Exception {
		return null;
	}

	@Override
	public List<ServiceReport> getServices(Status status) throws Exception {
		return null;
	}

}
