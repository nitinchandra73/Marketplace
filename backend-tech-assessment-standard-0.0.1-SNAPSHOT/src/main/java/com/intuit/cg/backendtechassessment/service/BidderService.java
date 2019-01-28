package com.intuit.cg.backendtechassessment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface BidderService {
	public Bidder addBidder(Bidder bidder) throws UserException;
	public Bidder getBidder(String bidderEin,Bidder bidder) throws UserException;
	static final Logger LOGGER = LoggerFactory.getLogger(BidderService.class);
}
