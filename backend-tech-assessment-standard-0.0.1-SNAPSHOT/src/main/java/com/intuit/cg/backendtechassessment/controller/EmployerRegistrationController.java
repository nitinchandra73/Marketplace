package com.intuit.cg.backendtechassessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Employer;

public interface EmployerRegistrationController {

	public	ResponseEntity<Object> addNewEmployer(Employer employer);

	public	ResponseEntity<Object> getEmployer(String employerEin, Employer employer);
	static final Logger LOGGER = LoggerFactory.getLogger(EmployerRegistrationController.class);

}
