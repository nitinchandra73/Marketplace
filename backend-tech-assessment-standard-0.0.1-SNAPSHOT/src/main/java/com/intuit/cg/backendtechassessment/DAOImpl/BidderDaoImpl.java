package com.intuit.cg.backendtechassessment.DAOImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.DAO.BidderDao;
import com.intuit.cg.backendtechassessment.controller.entity.Bidder;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.dataaccess.entity.BidderTable;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;

@Repository
@Transactional
public class BidderDaoImpl implements BidderDao{
	@Autowired
	private EntityManager entityManager;
	@Override
	public Bidder addBidder(Bidder bidder) throws UserException {
		String ein=bidder.getEin();
		List<BidderTable> bidders = getBidderByEin(ein);
		LOGGER.info("requested Bidder by providing the EIN:"+ein+" for bidder:"+bidder.toString());
		if(bidders.size()>0) {
			LOGGER.error("Bidder already exist with the EIN number: "+ein+" With error code:"+ErrorCodes.BIDDER_ALREADY_EXISTS+". Bidder Data:"+bidder.toString());
			throw new UserException("Bidder already exist with the EIN number: "+ein, ErrorCodes.BIDDER_ALREADY_EXISTS);
		}
		BidderTable bidderTable = new BidderTable(bidder);
		entityManager.persist(bidderTable);
		bidder.setId(bidderTable.getId());
		LOGGER.info("Persisted bidder and associated the bidder id to bidder: "+bidder.toString());
		return bidder;
	}
	@Override
	public List<BidderTable> getBidderByEin(String ein){
		List<BidderTable> bidders = entityManager.createNamedQuery("BidderTable.listBidderByEin", BidderTable.class).setParameter("ein", ein).getResultList();
		LOGGER.info("Queried database for Bidder with EIN: "+ein);
		return bidders;
	}
}
