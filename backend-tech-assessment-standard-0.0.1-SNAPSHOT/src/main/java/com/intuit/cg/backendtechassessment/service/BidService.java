package com.intuit.cg.backendtechassessment.service;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface BidService {
	public Bid addBid(Bid bid) throws UserException;

	public Bid getBid(int projectId, int bidderId, int bidId) throws UserException;
}
