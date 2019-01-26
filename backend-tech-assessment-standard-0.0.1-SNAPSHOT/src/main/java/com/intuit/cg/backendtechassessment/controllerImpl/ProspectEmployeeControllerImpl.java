package com.intuit.cg.backendtechassessment.controllerImpl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.cg.backendtechassessment.constants.MarketplaceConstants;
import com.intuit.cg.backendtechassessment.controller.ProspectEmployeeController;
import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.BidService;
import com.intuit.cg.backendtechassessment.util.ControllerUtil;

@RestController
public class ProspectEmployeeControllerImpl implements ProspectEmployeeController,MarketplaceConstants {
	@Autowired
	ControllerUtil controllerUtil;
	@Autowired
	BidService bidservice;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProspectEmployeeControllerImpl.class);
	@Override
	@RequestMapping(value=NEW_BID_PATH, method=RequestMethod.POST)
	public ResponseEntity<Object> newBid(@PathVariable(PROJECT_ID) String projectId, @PathVariable(BIDDER_ID)String bidderId,  @RequestBody Bid bid) {
		// TODO Auto-generated method stub
		Bid responseBid=null;
		try {
			responseBid = bidservice.addBid(bid);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controllerUtil.isValid(bid);
		LOGGER.error("hi nitin");
		return new ResponseEntity<Object>(responseBid,HttpStatus.OK);
	}
	@RequestMapping(value=NEW_BID_PATH, method=RequestMethod.GET)
	public ResponseEntity<Object> getBid(@PathVariable(PROJECT_ID) String projectId, @PathVariable(BIDDER_ID)String bidderId) {
		Bid bid = new Bid();
		bid.setBidId(0);
		bid.setCurrentBidAmount(0);
		bid.setBidderId(0);
		bid.setEmployerId(0);
		bid.setLeastBidAmount(0);
		bid.setProjectTitle("f");
		return new ResponseEntity<Object>(bid,HttpStatus.OK);
	}
}
