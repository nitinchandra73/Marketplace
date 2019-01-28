package com.intuit.cg.backendtechassessment.dataaccess.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
@NamedQueries({
	@NamedQuery(name="bidTable.getBidderIdForProjectId",query="select b from BidTable b where b.bidder= :bidderId and b.project= :projectId"),
	@NamedQuery(name="bidTable.getBidForId",query="select b from BidTable b where b.id= :id ")
})
@Entity
public class BidTable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public double bidAmount;
	public double minimumBidAmout;
	@ManyToOne
	public ProjectTable project;
	@ManyToOne
	public BidderTable bidder;
	public BidTable() {
		
	}
	public BidTable(Bid bid, BidderTable bidderTable, ProjectTable projectTable) {
		this.bidAmount=bid.getCurrentBidAmount();
		this.minimumBidAmout=bid.getLeastBidAmount();
		this.bidder=bidderTable;
		this.project=projectTable;
		
	}
	

	public BidderTable getBidder() {
		return bidder;
	}
	public void setBidder(BidderTable bidder) {
		this.bidder = bidder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public double getMinimumBidAmout() {
		return minimumBidAmout;
	}
	public void setMinimumBidAmout(double maxBidAmout) {
		this.minimumBidAmout = maxBidAmout;
	}
	public ProjectTable getProject() {
		return project;
	}
	public void setProject(ProjectTable project) {
		this.project = project;
	}
	
}
