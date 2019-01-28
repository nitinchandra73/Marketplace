package com.intuit.cg.backendtechassessment.controllerImpl;

import java.util.ArrayList;
import java.util.Collection;

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
import com.intuit.cg.backendtechassessment.controller.BidController;
import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.BidService;
import com.intuit.cg.backendtechassessment.util.ControllerUtil;

@RestController
public class BidControllerImpl implements BidController,MarketplaceConstants {
	
	@Autowired
	BidService bidservice;
	@Override
	@RequestMapping(value=POST_BID_PATH, method=RequestMethod.POST)
	public ResponseEntity<Object> newBid(@PathVariable(PROJECT_ID) int projectId, @PathVariable(BIDDER_ID)int bidderId,  @RequestBody Bid bid) {
		Bid responseBid=null;
		try {
			LOGGER.debug("requested to add bid for bidderId:"+bidderId+", projectId:"+projectId+", bid:"+bid.toString());
			responseBid = bidservice.addBid(bid);
			LOGGER.info("successfully added bid for bidderId:"+bidderId+", projectId:"+projectId+", responseBid:"+responseBid.toString());
			return new ResponseEntity<Object>(responseBid, HttpStatus.OK);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			LOGGER.error(e.toString()+" with HTTP status:"+ HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
	}
	@RequestMapping(value=GET_BID_PATH, method=RequestMethod.GET)
	@Override
	public ResponseEntity<Object> getBid(@PathVariable(PROJECT_ID) int projectId, @PathVariable(BIDDER_ID)int bidderId,@PathVariable(name= BID_ID) int bidId ) {
		Bid responseBid;
		try {
			LOGGER.debug("requested to get bid for bidderId:"+bidderId+", projectId:"+projectId);
			responseBid = bidservice.getBid(projectId,bidderId,bidId);
			LOGGER.info("returning bid for bidderId:"+bidderId+", projectId:"+projectId+", responseBid:"+responseBid.toString());
			return new ResponseEntity<Object>(responseBid,HttpStatus.OK);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			LOGGER.error(e.toString()+" with HTTP status:"+ HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
	}
}
