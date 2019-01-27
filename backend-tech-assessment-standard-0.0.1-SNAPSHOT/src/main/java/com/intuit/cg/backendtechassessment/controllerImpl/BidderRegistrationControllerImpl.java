package com.intuit.cg.backendtechassessment.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.cg.backendtechassessment.constants.MarketplaceConstants;
import com.intuit.cg.backendtechassessment.controller.BidderRegistrationController;
import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;
import com.intuit.cg.backendtechassessment.serviceImpl.BidderServiceImpl;

@RestController
public class BidderRegistrationControllerImpl implements BidderRegistrationController,MarketplaceConstants{

	@Autowired
	BidderServiceImpl bidderService;
	@RequestMapping(path=POST_BIDDER_PATH,method=RequestMethod.POST)
	@Override
	public ResponseEntity<Object> addNewBidder(@RequestBody Bidder bidder) {
		Bidder bidderResponse;
		try {
			 bidderResponse=bidderService.addBidder(bidder);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
	
		return new ResponseEntity<Object>(bidderResponse, HttpStatus.OK);	
		
	}
	
	@RequestMapping(path=GET_BIDDER_PATH,method=RequestMethod.GET)
	@Override
	public ResponseEntity<Object> getBidder(@PathVariable(name=BIDDER_EIN) String bidderEin, @RequestBody Bidder bidder) {
		Bidder bidderResponse;
		try {
			bidderResponse=bidderService.getBidder(bidderEin,bidder);
			return new ResponseEntity<Object>(bidderResponse, HttpStatus.OK);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
	}
}
