package com.intuit.cg.backendtechassessment.dataaccess.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class BidsTable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	@Column(nullable=true)
	public double bidAmount;
	public double maxBidAmout;
	@ManyToOne
	public ProjectsTable project;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public double getMaxBidAmout() {
		return maxBidAmout;
	}
	public void setMaxBidAmout(double maxBidAmout) {
		this.maxBidAmout = maxBidAmout;
	}
	public ProjectsTable getProject() {
		return project;
	}
	public void setProject(ProjectsTable project) {
		this.project = project;
	}
	
}
