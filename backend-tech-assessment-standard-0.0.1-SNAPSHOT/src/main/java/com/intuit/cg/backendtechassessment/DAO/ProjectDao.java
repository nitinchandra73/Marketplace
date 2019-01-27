package com.intuit.cg.backendtechassessment.DAO;

import com.intuit.cg.backendtechassessment.controller.entity.Project;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface ProjectDao {

	Project addProject(Project project, EmployerTable employerTable);

	Project getProject(int employerId, Integer projectid) throws UserException;

}