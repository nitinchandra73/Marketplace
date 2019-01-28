package com.intuit.cg.backendtechassessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Project;

public interface ProjectController {

	public	ResponseEntity<Object> addNewProject(int employerId, String ein, Project project);

	public	ResponseEntity<Object> getProject(int employerId, Integer projectid);
	static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

}
