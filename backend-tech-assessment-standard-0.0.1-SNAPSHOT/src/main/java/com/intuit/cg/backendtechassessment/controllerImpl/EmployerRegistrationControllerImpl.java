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
import com.intuit.cg.backendtechassessment.controller.EmployerRegistrationController;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;

@RestController
public class EmployerRegistrationControllerImpl implements EmployerRegistrationController, MarketplaceConstants{

	
	@Autowired
	EmployerService employerService;
	@RequestMapping(path=POST_EMPLOYER_PATH,method=RequestMethod.POST)
	@Override
	public ResponseEntity<Object> addNewEmployer(@RequestBody Employer employer) {
		Employer employerResponse;
			try {
				LOGGER.debug("Calling employer service to add new employer with data: "+employer.toString());
				 employerResponse=employerService.addEmployer(employer);
			} catch (UserException e) {
				Status status = new Status();
				status.setStatusMessage(e.toString());
				LOGGER.error( e.toString()+" "+employer.toString() +" "+HttpStatus.BAD_REQUEST);
				return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
			}
			LOGGER.info("Successfuly added new employer: "+employer.toString());
			return new ResponseEntity<Object>(employerResponse, HttpStatus.OK);	
	}
	@RequestMapping(path=GET_EMPLOYER_PATH,method=RequestMethod.GET)
	@Override
	public ResponseEntity<Object> getEmployer(@PathVariable(name=EMPLOYER_EIN) String employerEin, @RequestBody Employer employer) {
		Employer employerResponse;
		try {
			LOGGER.debug("Calling employer service with employer data to get employer: "+employer.toString());
			employerResponse=employerService.getEmployer(employerEin,employer);
			LOGGER.info("Successfuly returning data for employer "+employer.toString());
			return new ResponseEntity<Object>(employerResponse, HttpStatus.OK);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			LOGGER.error( e.toString()+" "+employer.toString() +"HTTP status: "+HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
	}
}
