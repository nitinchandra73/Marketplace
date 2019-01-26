package com.intuit.cg.backendtechassessment.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.EmployerDaoImpl;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService{
	@Autowired
	EmployerDaoImpl employerDao;
	@Override
	public Employer addEmployer(Employer employer) throws UserException {
		if(!employer.getId().equals(null)) {
			
				throw new UserException("Employer Id should not be provided for new employer", ErrorCodes.EMPLOYER_ID_SHOULDNT_BE_PROVIDED);
			
		}
		
		return employerDao.addEmployer(employer);
		
	}

}
