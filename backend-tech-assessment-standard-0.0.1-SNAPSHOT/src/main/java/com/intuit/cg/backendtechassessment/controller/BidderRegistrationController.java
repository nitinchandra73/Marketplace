package com.intuit.cg.backendtechassessment.controller;

import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;

public interface BidderRegistrationController {

	ResponseEntity<Object> addNewBidder(Bidder bidder);

	ResponseEntity<Object> getBidder(String bidderEin, Bidder bidder);

}
