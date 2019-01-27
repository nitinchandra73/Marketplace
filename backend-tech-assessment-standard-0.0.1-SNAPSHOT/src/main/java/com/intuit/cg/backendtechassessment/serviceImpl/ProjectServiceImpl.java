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
		// TODO Auto-generated method stub
		EmployerTable employerTable=getEmployer(employerId,ein);
		projectDao.addProject(project, employerTable);
		return project;
	}
	
	EmployerTable getEmployer(int employerId,String ein) throws UserException {
		List<EmployerTable> employers = employerDao.getEmployerById(employerId);
		EmployerTable employerFromSource;
		boolean isValid;
		if(employers.size()==0) {
			throw new UserException("Employer doesnt exist with the employerId number: "+employerId, ErrorCodes.EMPLOYER_DOESNT_EXIST);
		}
		employerFromSource= employers.get(0);
		isValid=employerFromSource.getEin().equalsIgnoreCase(ein) ;
		if(!isValid) {
			throw new UserException("EmployerId "+employerId+" and Employer EIN: "+ein+" dosent match.", ErrorCodes.EMPLOYER_ID_AND_EIN_MISMATCH);
			
		}
		return employerFromSource;
	}

	@Override
	public Project getProject(int employerId, Integer projectid) throws UserException {
		// TODO Auto-generated method stub
		Project project = projectDao.getProject(employerId, projectid);
		return project;
	}

}
