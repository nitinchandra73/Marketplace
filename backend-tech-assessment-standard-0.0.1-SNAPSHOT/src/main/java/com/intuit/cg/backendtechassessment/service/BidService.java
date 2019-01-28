package com.intuit.cg.backendtechassessment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.serviceImpl.BidServiceImpl;

public interface BidService {
	public Bid addBid(Bid bid) throws UserException;

	public Bid getBid(int projectId, int bidderId, int bidId) throws UserException;
	static final Logger LOGGER = LoggerFactory.getLogger(BidService.class);
	
}
