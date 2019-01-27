package com.intuit.cg.backendtechassessment.controller;

import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;

public interface BidController {

	public ResponseEntity<Object> newBid(int projectId, int bidderId, Bid bid);

	ResponseEntity<Object> getBid(int projectId, int bidderId, int bidId);
}
