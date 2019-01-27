package com.intuit.cg.backendtechassessment.controller.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.intuit.cg.backendtechassessment.dataaccess.entity.ProjectTable;

public class Project {
	Integer id;
	@NotBlank
	Integer employerId;
	@NotBlank
	String projectName;
	@NotBlank
	String description;
	@NotBlank
	Long maximumBudget;
	@NotBlank
	Date lastDate;
	Integer leastBidId;
	boolean isBidActive;
	public Project() {
		
	}
	public Project(ProjectTable projectTable) {
		this.id=projectTable.getId();
		this.employerId=projectTable.getEmployer().getId();
		this.projectName = projectTable.getName();
		this.description = projectTable.getDescription();
		this.maximumBudget = projectTable.getMaximumBudget();
		this.lastDate=projectTable.getLastDate();
		this.isBidActive=(!projectTable.isBidExpired);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployerId() {
		return employerId;
	}
	public void setEmployerId(Integer employerId) {
		this.employerId = employerId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getMaximumBudget() {
		return maximumBudget;
	}
	public void setMaximumBudget(Long maximumBudget) {
		this.maximumBudget = maximumBudget;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public Integer getLeastBidId() {
		return leastBidId;
	}
	public void setLeastBidId(Integer leastBidId) {
		this.leastBidId = leastBidId;
	}
	public boolean isBidActive() {
		return isBidActive;
	}
	public void setBidActive(boolean isBidActive) {
		this.isBidActive = isBidActive;
	}
	
	
}
