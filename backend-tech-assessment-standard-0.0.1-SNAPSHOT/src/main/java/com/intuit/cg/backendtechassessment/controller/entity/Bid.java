package com.intuit.cg.backendtechassessment.controller.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class Bid {
	long bidId;
	@NotBlank
	long employerId;
	
	long employeeId;
	@NotNull
	String projectTitle; 
	double maxBid;
	@NotNull
	double currentBid;
	
	public long getBidId() {
		return bidId;
	}
	public void setBidId(long bidId) {
		this.bidId = bidId;
	}
	public long getEmployerId() {
		return employerId;
	}
	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public double getMaxBid() {
		return maxBid;
	}
	public void setMaxBid(double maxBid) {
		this.maxBid = maxBid;
	}
	public double getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}
	
	
	
}
