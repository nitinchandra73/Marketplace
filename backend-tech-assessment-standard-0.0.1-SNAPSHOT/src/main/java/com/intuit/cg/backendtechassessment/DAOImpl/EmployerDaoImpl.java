package com.intuit.cg.backendtechassessment.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.cg.backendtechassessment.DAO.EmployerDao;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;

@Repository
@Transactional
public class EmployerDaoImpl implements EmployerDao  {
	@Autowired
	private EntityManager entityManager;
	@Override
	public Employer addEmployer(Employer employer) throws UserException {
		String ein=employer.getEin();
		List<EmployerTable> employers = getEmployerByEin(ein);
		LOGGER.info("requested employer by providing the EIN:"+ein+" for employer:"+employer.toString());
		if(employers.size()>0) {
			LOGGER.error("employer already exist with the EIN number: "+ein+" With error code:"+ErrorCodes.EMPLOYER_ALREADY_EXISTS+". employer Data:"+employer.toString());
			throw new UserException("Employer already exist with the EIN number: "+ein, ErrorCodes.EMPLOYER_ALREADY_EXISTS);
		}
		EmployerTable employerTable = new EmployerTable(employer);
		entityManager.persist(employerTable);
		employer.setId(employerTable.getId());
		LOGGER.info("Persisted employer and associated the employer id to employer: "+employer.toString());
		return employer;
	}
	
	@Override
	public List<EmployerTable> getEmployerByEin(String ein){
		List<EmployerTable> employers = entityManager.createNamedQuery("EmployerTable.listEmployerByEin", EmployerTable.class).setParameter("ein", ein).getResultList();
		LOGGER.info("Queried database for employer with EIN: "+ein);
		return employers;
	}
	@Override
	public List<EmployerTable> getEmployerById(int id){
		List<EmployerTable> employers = entityManager.createNamedQuery("EmployerTable.listEmployerById", EmployerTable.class).setParameter("id", id).getResultList();
		LOGGER.info("Queried database for Bidder with Id: "+id);
		return employers;
	}

}
