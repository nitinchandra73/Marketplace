package com.intuit.cg.backendtechassessment.service;

import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface ProjectService {
	public Project addProject(int employerId,String ein, Project project) throws UserException;

	
	public Project getProject(int employerId, Integer projectid) throws UserException;
}
