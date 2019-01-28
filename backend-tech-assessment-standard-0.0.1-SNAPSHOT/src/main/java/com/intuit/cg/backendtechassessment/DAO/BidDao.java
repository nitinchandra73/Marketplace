package com.intuit.cg.backendtechassessment.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.controllerImpl.BidControllerImpl;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.ProjectTable;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface BidDao {

public	Bid getBid(int projectId, int bidderId, int bidId) throws UserException;

public	ProjectTable getProjectById(int projectId) throws UserException;

public	BidderTable getBidderById(int bidderId) throws UserException;

public	Bid placeBid(Bid bid) throws UserException;

public boolean bidderBidAlreadyExist(Bid bid) throws UserException;

public boolean isBidderExist(Bid bid) throws UserException;

public boolean isBidValid(Bid bid) throws UserException;
static final Logger LOGGER = LoggerFactory.getLogger(BidDao.class);
}
