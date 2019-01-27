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
import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;
import com.intuit.cg.backendtechassessment.service.ProjectService;

@RestController
public class ProjectControllerImpl implements MarketplaceConstants{

	@Autowired
	ProjectService projectService;
	@RequestMapping(path=POST_PROJECT_PATH,method=RequestMethod.POST)
	public ResponseEntity<Object> addNewProject(@PathVariable(name=EMPLOYER_ID) int employerId, @PathVariable(name=EMPLOYER_EIN) String ein,@RequestBody Project project ) {
		try {
			Project responseProject=projectService.addProject(employerId,ein,project);
			return new ResponseEntity<Object>(responseProject, HttpStatus.OK);
			
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(path=GET_PROJECT_PATH,method=RequestMethod.GET)
	public ResponseEntity<Object> getProject(@PathVariable(name=EMPLOYER_ID) int employerId, @PathVariable(name=PROJECT_ID) Integer projectid ) {
		Project responseProject;
		try {
			responseProject = projectService.getProject(employerId,projectid);
			return new ResponseEntity<Object>(responseProject, HttpStatus.OK);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
		
		
}
