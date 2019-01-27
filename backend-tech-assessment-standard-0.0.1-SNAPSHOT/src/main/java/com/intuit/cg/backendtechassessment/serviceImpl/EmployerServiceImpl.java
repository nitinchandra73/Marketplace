package com.intuit.cg.backendtechassessment.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.EmployerDaoImpl;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService{
	@Autowired
	EmployerDaoImpl employerDao;
	@Override
	public Employer addEmployer(Employer employer) throws UserException {
		boolean isEmployerIdSpecified=employer.getId()!=null;
		if(isEmployerIdSpecified) {
			
				throw new UserException("Employer Id should not be provided for new employer", ErrorCodes.EMPLOYER_ID_SHOULDNT_BE_PROVIDED);
			
		} 
		
		return employerDao.addEmployer(employer);
		
	}
	
	@Override
	public Employer getEmployer(String employerEin,Employer employer) throws UserException {
		List<EmployerTable> employertables=employerDao.getEmployerByEin(employerEin);
		if(employertables.size()>0) {
			EmployerTable employerTable=employertables.get(0);
			boolean checkEmployerIsValid=  employer.getName().equalsIgnoreCase(employerTable.getName());
			if (checkEmployerIsValid){
				employer.setId((Integer)employerTable.getId());
				return employer;
			}
			throw new UserException("Employer with Ein: "+employerEin+" is a mismatch with provided Employer name and Id.",ErrorCodes.EIN_NAME_ID_MISMATCH);
		}
		throw new UserException("Employer with Ein: "+employerEin+"doesnt exist",ErrorCodes.EMPLOYER_DOESNT_EXIST);	
	}

}
