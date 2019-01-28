package com.intuit.cg.backendtechassessment.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.EmployerDao;
import com.intuit.cg.backendtechassessment.DAO.ProjectDao;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	EmployerDao employerDao;
	@Autowired
	ProjectDao projectDao;
	@Override
	public Project addProject(int employerId, String ein,Project project) throws UserException {
		EmployerTable employerTable=getEmployer(employerId,ein);
		LOGGER.debug("Adding project for employerId:"+employerId+" ,EIN:"+ein+", project: "+project.toString()+", employerTable: "+employerTable.toString());
		projectDao.addProject(project, employerTable);
		LOGGER.info("Added project with Project:"+project.toString()+" EmployerTable: "+employerTable.toString());
		return project;
	}
	
	EmployerTable getEmployer(int employerId,String ein) throws UserException {
		List<EmployerTable> employers = employerDao.getEmployerById(employerId);
		LOGGER.info("Requested employer for Id:"+employerId);
		EmployerTable employerFromSource;
		boolean isValid;
		if(employers.size()==0) {
			LOGGER.error("Employer doesnt exist with the employerId number: "+employerId+". with erro code: "+ErrorCodes.EMPLOYER_DOESNT_EXIST);
			throw new UserException("Employer doesnt exist with the employerId number: "+employerId, ErrorCodes.EMPLOYER_DOESNT_EXIST);
		}
		employerFromSource= employers.get(0);
		isValid=employerFromSource.getEin().equalsIgnoreCase(ein) ;
		if(!isValid) {
			LOGGER.error("EmployerId "+employerId+" and Employer EIN: "+ein+" dosent match."+" With error code: "+ ErrorCodes.EMPLOYER_ID_AND_EIN_MISMATCH);
			throw new UserException("EmployerId "+employerId+" and Employer EIN: "+ein+" dosent match.", ErrorCodes.EMPLOYER_ID_AND_EIN_MISMATCH);
			
		}
		LOGGER.info("Returning employer for EmployerId:"+ employerId+" ,EIN:"+ein+", employerFromSource:"+employerFromSource.toString());
		return employerFromSource;
	}

	@Override
	public Project getProject(int employerId, Integer projectId) throws UserException {
		LOGGER.info("Requested project for employerId:"+employerId+", ProjectId: "+projectId);
		Project project = projectDao.getProject(employerId, projectId);
		return project;
	}

}
