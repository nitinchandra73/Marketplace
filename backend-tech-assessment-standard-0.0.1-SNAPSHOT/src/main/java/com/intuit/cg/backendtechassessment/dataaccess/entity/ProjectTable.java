package com.intuit.cg.backendtechassessment.dataaccess.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.intuit.cg.backendtechassessment.controller.entity.Project;

@Entity
@NamedQueries({
	@NamedQuery(name="projectTable.SELECT_PROJECT_BY_ID",query="select pt from ProjectTable pt where pt.id= :id")
})
public class ProjectTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String description;
	public String name;
	public int maximumBudget;
	public Date lastDate;
	public boolean isBidExpired;
	@ManyToOne
	public EmployerTable employer;
	@ManyToOne
	public BidderTable lowestBidder;
	@OneToMany
	public List<BidTable> bids;
	@OneToOne
	public BidTable leastBid;
	public ProjectTable() {
		
	}
	public ProjectTable(Project project,EmployerTable employerTable) {
		this.description=project.getDescription();
		this.isBidExpired=false;
		this.lastDate=project.getLastDate();
		this.maximumBudget=project.getMaximumBudget();
		this.name=project.getProjectName();
		this.employer=employerTable;
	}
	
	public BidTable getLeastBid() {
		return leastBid;
	}
	public void setLeastBid(BidTable leastBid) {
		this.leastBid = leastBid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaximumBudget() {
		return maximumBudget;
	}
	public void setMaximumBudget(int maximumBudget) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EmployerTable getEmployer() {
		return employer;
	}
	public void setEmployer(EmployerTable employer) {
		this.employer = employer;
	}
	public BidderTable getLowestBidder() {
		return lowestBidder;
	}
	public void setLowestBidder(BidderTable lowestBidder) {
		this.lowestBidder = lowestBidder;
	}
	public List<BidTable> getBids() {
		return bids;
	}
	public void setBids(List<BidTable> bids) {
		this.bids = bids;
	}
	
	
	
}
