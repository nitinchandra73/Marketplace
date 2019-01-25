package com.intuit.cg.backendtechassessment.dataaccess.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class BidderTable {
	public String name;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;

	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


}
