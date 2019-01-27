package com.intuit.cg.backendtechassessment.DAO;

import java.util.List;

import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.UserException;

public interface EmployerDao {

	Employer addEmployer(Employer employer) throws UserException;

	List<EmployerTable> getEmployerByEin(String ein);

	List<EmployerTable> getEmployerById(int id);

}