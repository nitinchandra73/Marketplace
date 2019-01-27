package com.intuit.cg.backendtechassessment.dataaccess.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
@Entity
@NamedQueries({
	@NamedQuery(name="BidderTable.getBidderById",query="select b from BidderTable b where id= :id"),  
	@NamedQuery(name="BidderTable.listBidderByEin",query="select b from BidderTable b where b.ein= :ein")
})
public class BidderTable {
	public String name;
	String ein;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	public BidderTable() {
		
	}
	public BidderTable(Bidder bidder) {
		this.name=bidder.getName();
		this.ein=bidder.getEin();
	}
	
	@OneToMany
	List<BidsTable> bids;
	
	public List<BidsTable> getBids() {
		return bids;
	}
	public void setBids(List<BidsTable> bids) {
		this.bids = bids;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
