package com.intuit.cg.backendtechassessment.service;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface BidderService {
	public Bidder addBidder(Bidder bidder) throws UserException;
	public Bidder getBidder(String bidderEin,Bidder bidder) throws UserException;
}
