package com.intuit.cg.backendtechassessment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.DAO.EmployerDao;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface EmployerService {

	public	Employer addEmployer(Employer employer) throws UserException;

	
	public	Employer getEmployer(String employerEin, Employer employer) throws UserException;
	static final Logger LOGGER = LoggerFactory.getLogger(EmployerService.class);

}
