package com.intuit.cg.backendtechassessment.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.BidDao;
import com.intuit.cg.backendtechassessment.DAOImpl.BidDaoImpl;
import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.controllerImpl.BidControllerImpl;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.BidService;
@Service
public class BidServiceImpl implements BidService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BidServiceImpl.class);
	@Autowired
	BidDao bidDao;
	@Override
	public Bid addBid(Bid bid) throws UserException {
		boolean isBidderExist= bidDao.isBidderExist(bid);
		if(isBidderExist) {
			boolean bidderBidAlreadyExist=bidDao.bidderBidAlreadyExist(bid);
			if(bidderBidAlreadyExist) {
				throw new UserException("Bid for Bidder exist", ErrorCodes.BID_FOR_BIDDER_EXIST);
			}
		}
		bidDao.isBidValid(bid);
	//	bidDao.isBiddingActive();
		
		//bidDao.isTheBidLeastCoated();
		Bid responseBid=bidDao.placeBid(bid);
		
	
		return responseBid;	

	}
	@Override
	public Bid getBid(int projectId, int bidderId, int bidId) throws UserException {
		// TODO Auto-generated method stub
		Bid responseBid = bidDao.getBid( projectId,  bidderId, bidId);
		//bidTable
		return responseBid;
	}
}
