package com.intuit.cg.backendtechassessment.DAO;

import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
@Repository
public class BidDaoImpl {
	public boolean isBidValid(Bid bid) {
		return false;
	}
	public boolean isNewBidder(Bid bid) {
		return false;
	}
	public boolean bidderBidAlreadyExist(Bid bid) {
		
		return false;
	}
	public boolean isBiddingActive() {
		
		return false;
	}
	public boolean isTheBidLeastCoated() {
		return false;
	}
	public Bid placeBid(Bid bid) {
		return null;
	}
}
