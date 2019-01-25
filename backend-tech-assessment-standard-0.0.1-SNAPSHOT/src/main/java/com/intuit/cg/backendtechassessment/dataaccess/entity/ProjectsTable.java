package com.intuit.cg.backendtechassessment.dataaccess.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProjectsTable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	public String description;
	public long maximumBudget;
	public Date lastDate;
	public boolean isBidExpired;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getMaximumBudget() {
		return maximumBudget;
	}
	public void setMaximumBudget(long maximumBudget) {
		this.maximumBudget = maximumBudget;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public boolean isBidExpired() {
		return isBidExpired;
	}
	public void setBidExpired(boolean isBidExpired) {
		this.isBidExpired = isBidExpired;
	}
	
	
	
}
