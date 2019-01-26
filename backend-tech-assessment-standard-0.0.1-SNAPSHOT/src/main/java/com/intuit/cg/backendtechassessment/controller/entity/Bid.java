package com.intuit.cg.backendtechassessment.controller.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class Bid {
	long bidId;
	@NotBlank
	long employerId;
	
	long bidderId;
	@NotNull
	String projectTitle; 
	double leastBidAmount;
	@NotNull
	double currentBidAmount;
	long projectId;
	boolean bidLesserTillThresholdIfNotLeast;
	
	public boolean isBidLesserTillThresholdIfNotLeast() {
		return bidLesserTillThresholdIfNotLeast;
	}
	public void setBidLesserTillThresholdIfNotLeast(boolean bidLesserTillThresholdIfNotLeast) {
		this.bidLesserTillThresholdIfNotLeast = bidLesserTillThresholdIfNotLeast;
	}
	public double getLeastBidAmount() {
		return leastBidAmount;
	}
	public void setLeastBidAmount(double maxBidAmount) {
		this.leastBidAmount = maxBidAmount;
	}
	public double getCurrentBidAmount() {
		return currentBidAmount;
	}
	public void setCurrentBidAmount(double currentBidAmount) {
		this.currentBidAmount = currentBidAmount;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
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
	public long getBidderId() {
		return bidderId;
	}
	public void setBidderId(long employeeId) {
		this.bidderId = employeeId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	
	
	
}
