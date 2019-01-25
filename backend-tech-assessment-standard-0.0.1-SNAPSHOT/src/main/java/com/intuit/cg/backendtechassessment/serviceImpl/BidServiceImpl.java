package com.intuit.cg.backendtechassessment.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.BidDaoImpl;
import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.controllerImpl.ProspectEmployeeControllerImpl;
import com.intuit.cg.backendtechassessment.service.BidService;
@Service
public class BidServiceImpl implements BidService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BidServiceImpl.class);
	@Autowired
	BidDaoImpl bidDao;
	@Override
	public Bid addBid(Bid bid) {
		
		bidDao.isBidValid(bid);
		bidDao.isBiddingActive();
		if(!bidDao.isNewBidder(bid)) {
			bidDao.bidderBidAlreadyExist(bid);
		}
		bidDao.isTheBidLeastCoated();
		bidDao.placeBid(bid);
		
	
		return null;	

	}
}
