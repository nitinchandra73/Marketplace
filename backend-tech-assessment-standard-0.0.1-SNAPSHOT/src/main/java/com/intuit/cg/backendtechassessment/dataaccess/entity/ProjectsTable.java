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

@Entity
@NamedQueries({
	@NamedQuery(name="projectTable.SELECT_PROJECT_BY_ID",query="select pt from ProjectsTable pt where pt.id= :id")
})
public class ProjectsTable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	public String description;
	public String name;
	public long maximumBudget;
	public Date lastDate;
	public boolean isBidExpired;
	@ManyToOne
	public EmployerTable employer;
	@ManyToOne
	public BidderTable lowestBidder;
	@OneToMany
	public List<BidsTable> bids;
	@OneToOne
	public BidsTable leastBid;
	
	public BidsTable getLeastBid() {
		return leastBid;
	}
	public void setLeastBid(BidsTable leastBid) {
		this.leastBid = leastBid;
	}
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
	public List<BidsTable> getBids() {
		return bids;
	}
	public void setBids(List<BidsTable> bids) {
		this.bids = bids;
	}
	
	
	
}
