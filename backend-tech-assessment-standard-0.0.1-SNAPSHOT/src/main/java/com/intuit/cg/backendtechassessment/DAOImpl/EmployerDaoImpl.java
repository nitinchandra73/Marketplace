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
		// TODO Auto-generated method stub
		String ein=employer.getEin();
		List<EmployerTable> employers = getEmployerByEin(ein);
		if(employers.size()>0) {
			throw new UserException("Employer already exist with the EIN number: "+ein, ErrorCodes.EMPLOYER_ALREADY_EXISTS);
		}
		EmployerTable employerTable = new EmployerTable(employer);
		entityManager.persist(employerTable);
		employer.setId(employerTable.getId());
		//entityManager.createNamedQuery("EmployerTable.insertNewEmployer").setParameter("name", employer.getName()).setParameter("ein", employer.getEin()).get;
		return employer;
	}
	
	@Override
	public List<EmployerTable> getEmployerByEin(String ein){
		List<EmployerTable> employers = entityManager.createNamedQuery("EmployerTable.listEmployerByEin", EmployerTable.class).setParameter("ein", ein).getResultList();
		return employers;
	}
	@Override
	public List<EmployerTable> getEmployerById(int id){
		List<EmployerTable> employers = entityManager.createNamedQuery("EmployerTable.listEmployerById", EmployerTable.class).setParameter("id", id).getResultList();
		return employers;
	}

}
