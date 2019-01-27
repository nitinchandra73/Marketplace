package com.intuit.cg.backendtechassessment.service;

import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface EmployerService {

	Employer addEmployer(Employer employer) throws UserException;

	
	Employer getEmployer(String employerEin, Employer employer) throws UserException;

}
