package com.intuit.cg.backendtechassessment.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.sql.DataSource;

import org.hibernate.annotations.NamedNativeQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.controller.entity.Bid;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidsTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.ProjectTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
@Repository
public class BidDaoImpl {
	@Autowired
    DataSource dataSource;
	@Autowired
	private EntityManager entityManager;
	public boolean isBidValid(Bid bid) throws UserException {
		long projectId; // check is valid listing
		ProjectTable projectsTable;
		double currentBidAmount,leastBidAmount,currentBidLeastAmount;
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
		leastBidAmount=projectsTable.getLeastBid().getBidAmount();
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
	public boolean isBidderExist(Bid bid) throws UserException {
		long bidderId=bid.getBidderId();
		if(bidderId==0) {
			throw new UserException("Bidder with id "+bidderId+" doesnt exist. If adding new bidder then do not include bidder id", ErrorCodes.BIDDER_ID_DOESNT_EXIST) ;

		}
		List<BidderTable> bidderList=entityManager.createNamedQuery("BidderTable.getBidderById", BidderTable.class).setParameter("id", bidderId).getResultList();
		if(bidderList.size()>0) {
			return true;
		}
		throw new UserException("Bidder with id "+bidderId+" doesnt exist. If adding new bidder then do not include bidder id", ErrorCodes.BIDDER_ID_DOESNT_EXIST) ;
	}
	public boolean bidderBidAlreadyExist(Bid bid) {
		bid.getBidderId(); bid.getProjectId();
		List<BidsTable> bids =entityManager.createNamedQuery("bidsTable.getBidderIdForProjectId", BidsTable.class).setParameter("bidderId", bid.getBidderId()).setParameter("projectId", bid.getProjectId()).getResultList();
		if(bids.size()>0) {
			return true;
		}
		return false;
	}
	public boolean isBiddingActive() {
		
		return false;
	}
	public boolean isTheBidLeastCoated() {
		return false;
	}
	public Bid placeBid(Bid bid) {
		return null;
	}
	
	ProjectTable getProjectById(long projectId) throws UserException  {
//		Connection connection = getNewConnection();
//		Statement statment=connection.createStatement();
		//statment.ex
		List<ProjectTable> projectTableList=entityManager.createNamedQuery("projectTable.SELECT_PROJECT_BY_ID", ProjectTable.class).setParameter("id", projectId).getResultList();
		if( projectTableList.size()==1){
			return projectTableList.get(0);
		}
		throw new UserException("project doesnt exist for the give project id="+projectId,ErrorCodes.MISSING_PROJECT_FOR_ID);
	}
	Connection getNewConnection() throws SQLException{
		try {
			
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("Unable to get new connection from database");
		}
	}
	void closeConnection(Connection connection) throws SQLException{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("Unable to close database connection");
		}
	}
	public void addBidder(Bid bid) {
	//	bid.get
		// TODO Auto-generated method stub
		
	}
}
