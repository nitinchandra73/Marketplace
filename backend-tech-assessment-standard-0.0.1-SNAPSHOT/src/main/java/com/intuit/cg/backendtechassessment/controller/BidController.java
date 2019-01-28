package com.intuit.cg.backendtechassessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.controllerImpl.BidControllerImpl;

public interface BidController {

	public ResponseEntity<Object> newBid(int projectId, int bidderId, Bid bid);

	public ResponseEntity<Object> getBid(int projectId, int bidderId, int bidId);
	 static final Logger LOGGER = LoggerFactory.getLogger(BidControllerImpl.class);
}
