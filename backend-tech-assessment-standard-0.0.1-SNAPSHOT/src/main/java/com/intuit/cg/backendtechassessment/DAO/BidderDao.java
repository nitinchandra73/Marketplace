package com.intuit.cg.backendtechassessment.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.BidderService;

public interface BidderDao {

	public	Bidder addBidder(Bidder bidder) throws UserException;

	public	List<BidderTable> getBidderByEin(String ein);
	static final Logger LOGGER = LoggerFactory.getLogger(BidderDao.class);

}
