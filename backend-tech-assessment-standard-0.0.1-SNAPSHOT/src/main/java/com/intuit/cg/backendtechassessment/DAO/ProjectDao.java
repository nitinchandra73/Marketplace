package com.intuit.cg.backendtechassessment.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.BidController;
import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface ProjectDao {

	public	Project addProject(Project project, EmployerTable employerTable);

	public	Project getProject(int employerId, Integer projectid) throws UserException;
	static final Logger LOGGER = LoggerFactory.getLogger(ProjectDao.class);

}