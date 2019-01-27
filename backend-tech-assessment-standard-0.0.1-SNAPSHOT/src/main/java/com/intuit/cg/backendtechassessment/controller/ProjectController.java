package com.intuit.cg.backendtechassessment.controller;

import org.springframework.http.ResponseEntity;

import com.intuit.cg.backendtechassessment.controller.entity.Project;

public interface ProjectController {

	ResponseEntity<Object> addNewProject(int employerId, String ein, Project project);

	ResponseEntity<Object> getProject(int employerId, Integer projectid);

}
