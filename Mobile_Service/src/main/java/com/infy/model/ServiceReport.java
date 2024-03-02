package com.infy.model;

import java.util.List;

public class ServiceReport {
	
//	Instance Variables
	private Integer serviceId;
	private String brand;
	private List<String> issues;
	private float serviceFee;
	
//	Constructor
	public ServiceReport(Integer serviceId, String brand, List<String> issues, float serviceFee) {
		this.serviceId = serviceId;
		this.brand = brand;
		this.issues = issues;
		this.serviceFee = serviceFee;
	}
	
//	Getters and Setters
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public List<String> getIssues() {
		return issues;
	}
	public void setIssues(List<String> issues) {
		this.issues = issues;
	}
	public float getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(float serviceFee) {
		this.serviceFee = serviceFee;
	}
	
}
