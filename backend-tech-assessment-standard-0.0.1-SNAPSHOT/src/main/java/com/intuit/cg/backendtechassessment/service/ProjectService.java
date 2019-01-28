package com.intuit.cg.backendtechassessment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intuit.cg.backendtechassessment.controller.BidController;
import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface ProjectService {
	public Project addProject(int employerId,String ein, Project project) throws UserException;

	
	public Project getProject(int employerId, Integer projectid) throws UserException;
	static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
}
