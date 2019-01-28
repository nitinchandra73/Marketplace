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
import com.intuit.cg.backendtechassessment.controller.ProjectController;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;
import com.intuit.cg.backendtechassessment.service.ProjectService;

@RestController
public class ProjectControllerImpl implements ProjectController,MarketplaceConstants{

	@Autowired
	ProjectService projectService;
	@RequestMapping(path=POST_PROJECT_PATH,method=RequestMethod.POST)
	@Override
	public ResponseEntity<Object> addNewProject(@PathVariable(name=EMPLOYER_ID) int employerId, @PathVariable(name=EMPLOYER_EIN) String ein,@RequestBody Project project ) {
		try {
			LOGGER.debug("Recived request to add new project with data:"+project.toString());
			Project responseProject=projectService.addProject(employerId,ein,project);
			LOGGER.info("Successfuly added project in marketplace. "+responseProject.toString());
			return new ResponseEntity<Object>(responseProject, HttpStatus.OK);
			
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			LOGGER.error("Exception has occured with message: "+e.toString()+", HTTP status:"+ HttpStatus.BAD_REQUEST+" for ein="+ein+", employerId="+employerId+" Project:"+ project.toString() );
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(path=GET_PROJECT_PATH,method=RequestMethod.GET)
	@Override
	public ResponseEntity<Object> getProject(@PathVariable(name=EMPLOYER_ID) int employerId, @PathVariable(name=PROJECT_ID) Integer projectId ) {
		Project responseProject;
		try {
			LOGGER.debug("Recived request to get project with data: employerId="+employerId+" ,projectId="+projectId);
			responseProject = projectService.getProject(employerId,projectId);
			LOGGER.info("Successfuly retrived project in marketplace. "+responseProject.toString());
			return new ResponseEntity<Object>(responseProject, HttpStatus.OK);
		} catch (UserException e) {
			Status status = new Status();
			status.setStatusMessage(e.toString());
			LOGGER.error("Exception has occured with message: "+e.toString()+", HTTP status:"+ HttpStatus.BAD_REQUEST+" for employerId="+employerId+" Projectid:"+ projectId );
			return new ResponseEntity<Object>(status, HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
		
		
}
