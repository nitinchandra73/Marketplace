package com.intuit.cg.backendtechassessment.DAO;

import java.util.List;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface BidderDao {

	public	Bidder addBidder(Bidder bidder) throws UserException;

	public	List<BidderTable> getBidderByEin(String ein);

}
