package com.intuit.cg.backendtechassessment.controller.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class Bid {
	int bidId;
	@NotBlank
	int employerId;
	
	int bidderId;
	@NotNull
	String projectTitle; 
	double leastBidAmount;
	@NotNull
	double currentBidAmount;
	int projectId;
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	public int getBidderId() {
		return bidderId;
	}
	public void setBidderId(int employeeId) {
		this.bidderId = employeeId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	
	
	
}
