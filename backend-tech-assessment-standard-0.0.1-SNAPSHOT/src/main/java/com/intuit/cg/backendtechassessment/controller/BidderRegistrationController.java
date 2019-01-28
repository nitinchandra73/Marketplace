package com.intuit.cg.backendtechassessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;

public interface BidderRegistrationController {

	public ResponseEntity<Object> addNewBidder(Bidder bidder);

	public ResponseEntity<Object> getBidder(String bidderEin, Bidder bidder);
	static final Logger LOGGER = LoggerFactory.getLogger(BidController.class);

}
