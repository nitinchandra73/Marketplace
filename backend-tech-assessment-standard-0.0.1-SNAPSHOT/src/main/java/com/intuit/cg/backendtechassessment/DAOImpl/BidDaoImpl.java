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
		int projectId; // check is valid listing
		ProjectTable projectsTable;
		double currentBidAmount,leastBidAmount=0,currentBidLeastAmount;
		projectId=bid.getProjectId();
			projectsTable = getProjectById(projectId);
			if(!projectsTable.getName().equals(bid.getProjectTitle())) {
				throw new UserException("Project title doesnt match for the project Id specified",ErrorCodes.MISMATCH_PROJECT_ID_TO_PROJECT_TITLE);
			}
		
		if(projectsTable.getEmployer().getId()!=bid.getEmployerId()) {
			throw new UserException("Employer Id dosnt match for the project Id specified",ErrorCodes.MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID);
		}
		if(projectsTable.getLastDate().before(new Date())){
			throw new UserException("Bidding date is passed. last date was "+projectsTable.getLastDate().toString(), ErrorCodes.BIDDING_NOT_ACTIVE);
		}
		currentBidAmount=bid.getCurrentBidAmount();
		currentBidLeastAmount=bid.getLeastBidAmount();
		if(projectsTable.getLeastBid()!=null) {
			leastBidAmount=projectsTable.getLeastBid().getBidAmount();
		}
		
		if(leastBidAmount>0) {
			if(currentBidAmount<leastBidAmount) {
				return true;
			}
			else {
				if(bid.isBidLesserTillThresholdIfNotLeast()) {
					if((currentBidLeastAmount<(leastBidAmount-1))) {
						bid.setCurrentBidAmount(leastBidAmount-1);
						return true;
					}
					throw new UserException("current bid amount is not lesser than the least bid amount: "+leastBidAmount, ErrorCodes.BID_AMOUNT_NOT_LESS_THAN_LEAST);

				}
				else {
					throw new UserException("current bid amount is not lesser than the least bid amount: "+leastBidAmount, ErrorCodes.BID_AMOUNT_NOT_LESS_THAN_LEAST);
				}
			}
		}
		else {
			return true;
		}
		
		
		
		
	}
	@Override
	public boolean isBidderExist(Bid bid) throws UserException {
		int bidderId=bid.getBidderId();
		if(bidderId==0) {
			throw new UserException("Bidder with id "+bidderId+" doesnt exist. If adding new bidder then do not include bidder id", ErrorCodes.BIDDER_ID_DOESNT_EXIST) ;

		}
		BidderTable bidderTable =getBidderById(bidderId);
		System.out.println("im before true");
		return true;
	}
	
	@Override
	public boolean bidderBidAlreadyExist(Bid bid) throws UserException {
		
		List<BidTable> bids =entityManager.createNamedQuery("bidTable.getBidderIdForProjectId", BidTable.class).setParameter("bidderId", getBidderById(bid.getBidderId())).setParameter("projectId", getProjectById(bid.getProjectId())).getResultList();
		if(bids.size()>0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Bid placeBid(Bid bid) throws UserException {
		BidderTable bidderTable=getBidderById(bid.getBidderId());
		ProjectTable projectTable =getProjectById(bid.getProjectId()); 
		BidTable bidTable = new BidTable(bid,bidderTable,projectTable);
		entityManager.persist(bidTable);
		bid.setBidId(bidTable.getId());
		return bid;
	}
	
	@Override
	public BidderTable getBidderById(int bidderId) throws UserException {
		List<BidderTable> bidderList=entityManager.createNamedQuery("BidderTable.getBidderById", BidderTable.class).setParameter("id", bidderId).getResultList();
		if(bidderList.size()==1) {
			return bidderList.get(0);
		}
		throw new UserException("Bidder with id "+bidderId+" doesnt exist.", ErrorCodes.BIDDER_ID_DOESNT_EXIST) ;
		
	}
	
	@Override
	public ProjectTable getProjectById(int projectId) throws UserException  {

		List<ProjectTable> projectTableList=entityManager.createNamedQuery("projectTable.SELECT_PROJECT_BY_ID", ProjectTable.class).setParameter("id", projectId).getResultList();
		if( projectTableList.size()==1){
			return projectTableList.get(0);
		}
		throw new UserException("project doesnt exist for the give project id="+projectId,ErrorCodes.MISSING_PROJECT_FOR_ID);
	}
	
	@Override
	public Bid getBid(int projectId, int bidderId, int bidId) throws UserException {
		// TODO Auto-generated method stub
		List<BidTable>bidTables=entityManager.createNamedQuery("bidTable.getBidForId", BidTable.class).setParameter("id", bidId).getResultList();
		if(bidTables.size()>0) {
			BidTable bidTable= bidTables.get(0);
			if (bidderId!=bidTable.getBidder().getId()) {
				throw new UserException("Bidder Id doesnt match", ErrorCodes.BIDDER_ID_DOSENT_MATCH);

			}
			if(projectId!=bidTable.getProject().getId()) {
				throw new UserException("Project Id dosent match.", ErrorCodes.PROJECT_ID_DOSENT_EXIST);

			}
			ProjectTable projectTable=getProjectById(projectId);
			Bid responseBid = new Bid(bidTable, projectTable,projectId,  bidderId);
			return responseBid;
			
		}
		throw new UserException("Bid for bid Id: "+bidId+" dosent exist", ErrorCodes.BID_ID_DOSENT_EXIST);
	}
	
	
}
