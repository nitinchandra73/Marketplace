package com.intuit.cg.backendtechassessment.controller;

import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;

public interface ProspectEmployeeController {

	public ResponseEntity<Object> newBid(String projectId, String bidderId, Bid bid);
}
