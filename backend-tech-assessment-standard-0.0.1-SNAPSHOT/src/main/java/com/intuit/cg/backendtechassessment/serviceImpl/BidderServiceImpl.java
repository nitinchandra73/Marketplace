package com.intuit.cg.backendtechassessment.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.BidderDao;
import com.intuit.cg.backendtechassessment.DAOImpl.BidderDaoImpl;
import com.intuit.cg.backendtechassessment.DAOImpl.EmployerDaoImpl;
import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.BidderService;
@Service
public class BidderServiceImpl implements BidderService{
	@Autowired
	BidderDao bidderDao;
	@Override
	public Bidder addBidder(Bidder bidder) throws UserException {
		boolean isBidderIdSpecified=bidder.getId()!=null;
		if(isBidderIdSpecified) {
			
				throw new UserException("Bidder Id should not be provided for new Bidder", ErrorCodes.BIDDER_ID_SHOULDNT_BE_PROVIDED);
			
		} 
		
		return bidderDao.addBidder(bidder);
		
	}
	
	@Override
	public Bidder getBidder(String bidderEin,Bidder bidder) throws UserException {
		List<BidderTable> biddertables=bidderDao.getBidderByEin(bidderEin);
		if(biddertables.size()>0) {
			BidderTable bidderTable=biddertables.get(0);
			boolean checkBidderIsValid=  bidder.getName().equalsIgnoreCase(bidderTable.getName());
			if (checkBidderIsValid){
				bidder.setId((Integer)bidderTable.getId());
				return bidder;
			}
			throw new UserException("Bidder with Ein: "+bidderEin+" is a mismatch with provided Bidder name and Id.",ErrorCodes.EIN_NAME_ID_MISMATCH);
		}
		throw new UserException("Bidder with Ein: "+bidderEin+" doesnt exist",ErrorCodes.BIDDER_DOESNT_EXIST);	
	}

}
