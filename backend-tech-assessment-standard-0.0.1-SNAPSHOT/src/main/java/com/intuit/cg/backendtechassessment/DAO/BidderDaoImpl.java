package com.intuit.cg.backendtechassessment.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;

@Repository
@Transactional
public class BidderDaoImpl {
	@Autowired
	private EntityManager entityManager;
	public Bidder addBidder(Bidder bidder) throws UserException {
		// TODO Auto-generated method stub
		String ein=bidder.getEin();
		List<BidderTable> bidders = getBidderByEin(ein);
		if(bidders.size()>0) {
			throw new UserException("Bidder already exist with the EIN number: "+ein, ErrorCodes.BIDDER_ALREADY_EXISTS);
		}
		BidderTable bidderTable = new BidderTable(bidder);
		entityManager.persist(bidderTable);
		bidder.setId(bidderTable.getId());
		//entityManager.createNamedQuery("BidderTable.insertNewBidder").setParameter("name", bidder.getName()).setParameter("ein", bidder.getEin()).get;
		return bidder;
	}
	
	public List<BidderTable> getBidderByEin(String ein){
		List<BidderTable> bidders = entityManager.createNamedQuery("BidderTable.listBidderByEin", BidderTable.class).setParameter("ein", ein).getResultList();
		return bidders;
	}
}
