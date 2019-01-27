package com.intuit.cg.backendtechassessment.DAO;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
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

}
