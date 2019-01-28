package com.intuit.cg.backendtechassessment.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.annotations.NamedNativeQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.DAO.BidDao;
import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.ProjectTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
@Repository
@Transactional
public class BidDaoImpl implements BidDao{
	
	@Autowired
	private EntityManager entityManager;
	@Override
	public boolean isBidValid(Bid bid) throws UserException {
		int projectId; 
		ProjectTable projectsTable;
		double currentBidAmount,leastBidAmount=0,currentBidLeastAmount;
		projectId=bid.getProjectId();
			projectsTable = getProjectById(projectId);
			if(!projectsTable.getName().equals(bid.getProjectTitle())) {
				LOGGER.error("Project title doesnt match for the project Id specified" + "with error code "+ ErrorCodes.MISMATCH_PROJECT_ID_TO_PROJECT_TITLE+", with Bid:"+bid.toString());
				throw new UserException("Project title doesnt match for the project Id specified",ErrorCodes.MISMATCH_PROJECT_ID_TO_PROJECT_TITLE);
			}
		
		if(projectsTable.getEmployer().getId()!=bid.getEmployerId()) {
			LOGGER.error("Employer Id dosnt match for the project Id specified. Error code:"+ ErrorCodes.MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID+", with bid: "+bid.toString());
			throw new UserException("Employer Id dosnt match for the project Id specified",ErrorCodes.MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID);
		}
		if(projectsTable.getLastDate().before(new Date())){
			LOGGER.error("Bidding date is passed. last date was "+projectsTable.getLastDate().toString()+", with error code: "+ErrorCodes.BIDDING_NOT_ACTIVE+". Bid: "+bid.toString());
			throw new UserException("Bidding date is passed. last date was "+projectsTable.getLastDate().toString(), ErrorCodes.BIDDING_NOT_ACTIVE);
		}
		currentBidAmount=bid.getCurrentBidAmount();
		currentBidLeastAmount=bid.getLeastBidAmount();
		if(projectsTable.getLeastBid()!=null) {
			leastBidAmount=projectsTable.getLeastBid().getBidAmount();
		}
		
		if(leastBidAmount>0) {
			if(currentBidAmount<leastBidAmount) {
				LOGGER.info("Valid bid with Bid:"+bid.toString());
				return true;
			}
			else {
				if(bid.isBidLesserTillThresholdIfNotLeast()) {
					if((currentBidLeastAmount<(leastBidAmount-1))) {
						bid.setCurrentBidAmount(leastBidAmount-1);
						LOGGER.info("Valid bid with Bid:"+bid.toString());
						return true;
					}
					LOGGER.error("current bid amount is not lesser than the least bid amount: "+leastBidAmount+", with error code: "+ErrorCodes.BID_AMOUNT_NOT_LESS_THAN_LEAST);
					throw new UserException("current bid amount is not lesser than the least bid amount: "+leastBidAmount, ErrorCodes.BID_AMOUNT_NOT_LESS_THAN_LEAST);

				}
				else {
					LOGGER.error("current bid amount is not lesser than the least bid amount: "+leastBidAmount+". Error code: "+ErrorCodes.BID_AMOUNT_NOT_LESS_THAN_LEAST);
					throw new UserException("current bid amount is not lesser than the least bid amount: "+leastBidAmount, ErrorCodes.BID_AMOUNT_NOT_LESS_THAN_LEAST);
				}
			}
		}
		else {
			LOGGER.info("Valid bid with Bid:"+bid.toString());
			return true;
		}
		
	}
	@Override
	public boolean isBidderExist(Bid bid) throws UserException {
		int bidderId=bid.getBidderId();
		if(bidderId==0) {
			LOGGER.error("Bidder with id "+bidderId+" doesnt exist. If adding new bidder then do not include bidder id"+". with error code: "+ErrorCodes.BIDDER_ID_DOESNT_EXIST);
			throw new UserException("Bidder with id "+bidderId+" doesnt exist. If adding new bidder then do not include bidder id", ErrorCodes.BIDDER_ID_DOESNT_EXIST) ;

		}
		BidderTable bidderTable =getBidderById(bidderId);
		LOGGER.info("Bidder exist for Bidder id:"+bidderId+". bid:"+bid.toString());
		return true;
	}
	
	@Override
	public boolean bidderBidAlreadyExist(Bid bid) throws UserException {
		LOGGER.info("Checking if bidder bid already exist for bid: "+bid.toString());
		List<BidTable> bids =entityManager.createNamedQuery("bidTable.getBidderIdForProjectId", BidTable.class).setParameter("bidderId", getBidderById(bid.getBidderId())).setParameter("projectId", getProjectById(bid.getProjectId())).getResultList();
		
		if(bids.size()>0) {
			LOGGER.info("bids exist for bidder. bids:"+bids.get(0).toString() );
			return true;
		}
		LOGGER.info("Bids dont exist for bidder. bid:"+bid.toString());
		return false;
	}
	
	@Override
	public Bid placeBid(Bid bid) throws UserException {
		BidderTable bidderTable=getBidderById(bid.getBidderId());
		ProjectTable projectTable =getProjectById(bid.getProjectId()); 
		BidTable bidTable = new BidTable(bid,bidderTable,projectTable);
		LOGGER.info("persisting BidTable: "+bidTable.toString());
		entityManager.persist(bidTable);
		bid.setBidId(bidTable.getId());
		return bid;
	}
	
	@Override
	public BidderTable getBidderById(int bidderId) throws UserException {
		List<BidderTable> bidderList=entityManager.createNamedQuery("BidderTable.getBidderById", BidderTable.class).setParameter("id", bidderId).getResultList();
		if(bidderList.size()==1) {
			LOGGER.info("retrived bidder by ID for id:"+bidderId+", with response:"+bidderList.get(0).toString());
			return bidderList.get(0);
		}
		LOGGER.error("Bidder with id "+bidderId+" doesnt exist."+" with error code: "+ErrorCodes.BIDDER_ID_DOESNT_EXIST);
		throw new UserException("Bidder with id "+bidderId+" doesnt exist.", ErrorCodes.BIDDER_ID_DOESNT_EXIST) ;
		
	}
	
	@Override
	public ProjectTable getProjectById(int projectId) throws UserException  {
		
		List<ProjectTable> projectTableList=entityManager.createNamedQuery("projectTable.SELECT_PROJECT_BY_ID", ProjectTable.class).setParameter("id", projectId).getResultList();
		
		if( projectTableList.size()==1){
			LOGGER.info("Queried project table by Id:"+projectId+", recived response: "+projectTableList.get(0).toString());
			return projectTableList.get(0);
		}
		LOGGER.error("project doesnt exist for the give project id="+projectId+". with error code: "+ErrorCodes.MISSING_PROJECT_FOR_ID);
		throw new UserException("project doesnt exist for the give project id="+projectId,ErrorCodes.MISSING_PROJECT_FOR_ID);
	}
	
	@Override
	public Bid getBid(int projectId, int bidderId, int bidId) throws UserException {
		List<BidTable>bidTables=entityManager.createNamedQuery("bidTable.getBidForId", BidTable.class).setParameter("id", bidId).getResultList();
		if(bidTables.size()>0) {
			BidTable bidTable= bidTables.get(0);
			if (bidderId!=bidTable.getBidder().getId()) {
				LOGGER.error("Bidder Id doesnt match, BidderId:"+bidderId+" errorCode:"+ErrorCodes.BIDDER_ID_DOSENT_MATCH);
				throw new UserException("Bidder Id doesnt match", ErrorCodes.BIDDER_ID_DOSENT_MATCH);

			}
			if(projectId!=bidTable.getProject().getId()) {
				LOGGER.error("Project Id dosent match, BidderId:"+bidderId+" errorCode:"+ErrorCodes.PROJECT_ID_DOSENT_EXIST);
				throw new UserException("Project Id dosent match.", ErrorCodes.PROJECT_ID_DOSENT_EXIST);

			}
			ProjectTable projectTable=getProjectById(projectId);
			Bid responseBid = new Bid(bidTable, projectTable,projectId,  bidderId);
			LOGGER.info("Returing response bid: "+responseBid.toString());
			return responseBid;
			
		}
		LOGGER.error("Bid for bid Id: "+bidId+" dosent exist. with error code:"+ErrorCodes.BID_ID_DOSENT_EXIST);
		throw new UserException("Bid for bid Id: "+bidId+" dosent exist", ErrorCodes.BID_ID_DOSENT_EXIST);
	}
	
	
}
