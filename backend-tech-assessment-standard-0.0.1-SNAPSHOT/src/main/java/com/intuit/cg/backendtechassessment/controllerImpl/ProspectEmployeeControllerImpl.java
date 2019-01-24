package com.intuit.cg.backendtechassessment.controllerImpl;

import javax.servlet.http.HttpServletRequest;

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
import com.intuit.cg.backendtechassessment.util.ControllerUtil;

@RestController
public class ProspectEmployeeControllerImpl implements ProspectEmployeeController,MarketplaceConstants {
	@Autowired
	ControllerUtil controllerUtil;
	@Override
	@RequestMapping(value=NEW_BID_PATH, method=RequestMethod.POST)
	public ResponseEntity<Object> newBid(@PathVariable(PROJECT_ID) String projectId, @PathVariable(BIDDER_ID)String bidderId,  @RequestBody Bid bid) {
		// TODO Auto-generated method stub
		controllerUtil.isValid(bid);
		return null;
	}
	@RequestMapping(value=NEW_BID_PATH, method=RequestMethod.GET)
	public ResponseEntity<Object> getBid(@PathVariable(PROJECT_ID) String projectId, @PathVariable(BIDDER_ID)String bidderId) {
		Bid bid = new Bid();
		bid.setBidId(0);
		bid.setCurrentBid(0);
		bid.setEmployeeId(0);
		bid.setEmployerId(0);
		bid.setMaxBid(0);
		bid.setProjectTitle("f");
		return new ResponseEntity<Object>(bid,HttpStatus.OK);
	}
}
