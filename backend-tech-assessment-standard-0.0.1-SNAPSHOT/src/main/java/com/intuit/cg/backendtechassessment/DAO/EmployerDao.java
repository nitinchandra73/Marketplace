package com.intuit.cg.backendtechassessment.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.BidController;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface EmployerDao {

	public	Employer addEmployer(Employer employer) throws UserException;

	public	List<EmployerTable> getEmployerByEin(String ein);

	public	List<EmployerTable> getEmployerById(int id);
	static final Logger LOGGER = LoggerFactory.getLogger(EmployerDao.class);

}