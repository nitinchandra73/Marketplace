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
				LOGGER.error("Bidder id is not specified for "+bidder.toString()+" with error code:"+ErrorCodes.BIDDER_ID_SHOULDNT_BE_PROVIDED);
				throw new UserException("Bidder Id should not be provided for new Bidder", ErrorCodes.BIDDER_ID_SHOULDNT_BE_PROVIDED);
			
		} 
		LOGGER.info("Calling Bidder service to add new bidder "+bidder.toString());
		return bidderDao.addBidder(bidder);
		
		
	}
	
	@Override
	public Bidder getBidder(String bidderEin,Bidder bidder) throws UserException {
		List<BidderTable> biddertables=bidderDao.getBidderByEin(bidderEin);
		LOGGER.info("Checked bidder info for Ein:"+bidderEin+" specified. with response "+biddertables.toString());
		if(biddertables.size()>0) {
			BidderTable bidderTable=biddertables.get(0);
			boolean checkBidderIsValid=  bidder.getName().equalsIgnoreCase(bidderTable.getName());
			if (checkBidderIsValid){
				bidder.setId((Integer)bidderTable.getId());
				LOGGER.info("Bidder is valid "+ bidder.toString());
				return bidder;
			}
			LOGGER.error("Bidder with Ein: "+bidderEin+" is a mismatch with provided Bidder name and Id. with error code: "+ErrorCodes.EIN_NAME_ID_MISMATCH);
			throw new UserException("Bidder with Ein: "+bidderEin+" is a mismatch with provided Bidder name and Id.",ErrorCodes.EIN_NAME_ID_MISMATCH);
		}
		LOGGER.error("Bidder with Ein: "+bidderEin+" doesnt exist. Error code:"+ErrorCodes.BIDDER_DOESNT_EXIST);
		throw new UserException("Bidder with Ein: "+bidderEin+" doesnt exist",ErrorCodes.BIDDER_DOESNT_EXIST);	
	}

}
