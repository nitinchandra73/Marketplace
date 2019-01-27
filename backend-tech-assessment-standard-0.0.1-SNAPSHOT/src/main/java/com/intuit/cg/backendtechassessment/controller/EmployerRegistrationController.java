package com.intuit.cg.backendtechassessment.controller;

import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Employer;

public interface EmployerRegistrationController {

	ResponseEntity<Object> addNewEmployer(Employer employer);

	ResponseEntity<Object> getEmployer(String employerEin, Employer employer);

}
