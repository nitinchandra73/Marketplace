package com.intuit.cg.backendtechassessment.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.cg.backendtechassessment.constants.MarketplaceConstants;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;

@RestController
public class EmployerRegistrationControllerImpl implements MarketplaceConstants{

	@Autowired
	EmployerService employerService;
	@RequestMapping(path=POST_EMPLOYER_PATH,method=RequestMethod.POST)
	public ResponseEntity<Object> addNewEmployer(@RequestBody Employer employer) {
		
			try {
				employerService.addEmployer(employer);
			} catch (UserException e) {
				Status status = new Status();
				status.setStatusMessage(e.getMessage());
				return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
			}
		
		return null;	
	}
}
