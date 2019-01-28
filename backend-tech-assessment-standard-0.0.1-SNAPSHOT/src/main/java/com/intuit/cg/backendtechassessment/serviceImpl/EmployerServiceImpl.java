package com.intuit.cg.backendtechassessment.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intuit.cg.backendtechassessment.DAO.EmployerDao;
import com.intuit.cg.backendtechassessment.controller.entity.Employer;
import com.intuit.cg.backendtechassessment.controller.entity.Status;
import com.intuit.cg.backendtechassessment.dataaccess.entity.EmployerTable;
import com.intuit.cg.backendtechassessment.exception.ErrorCodes;
import com.intuit.cg.backendtechassessment.exception.UserException;
import com.intuit.cg.backendtechassessment.service.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService{
	@Autowired
	EmployerDao employerDao;
	@Override
	public Employer addEmployer(Employer employer) throws UserException {
		boolean isEmployerIdSpecified=employer.getId()!=null;
		if(isEmployerIdSpecified) {
			LOGGER.error("employer id is not specified for "+employer.toString()+" with error code:"+ErrorCodes.EMPLOYER_ID_SHOULDNT_BE_PROVIDED);
			throw new UserException("Employer Id should not be provided for new employer", ErrorCodes.EMPLOYER_ID_SHOULDNT_BE_PROVIDED);
			
		} 
		LOGGER.info("Calling employer service to add new employer "+employer.toString());
		return employerDao.addEmployer(employer);
		
	}
	
	@Override
	public Employer getEmployer(String employerEin,Employer employer) throws UserException {
		List<EmployerTable> employertables=employerDao.getEmployerByEin(employerEin);
		LOGGER.info("Checked employer info for Ein:"+employerEin+" specified. with response "+employertables.toString());
		if(employertables.size()>0) {
			EmployerTable employerTable=employertables.get(0);
			boolean checkEmployerIsValid=  employer.getName().equalsIgnoreCase(employerTable.getName());
			if (checkEmployerIsValid){
				employer.setId((Integer)employerTable.getId());
				LOGGER.info("employer is valid "+ employer.toString());
				return employer;
			}
			LOGGER.error("employer with Ein: "+employerEin+" is a mismatch with provided employer name and Id. with error code: "+ErrorCodes.EIN_NAME_ID_MISMATCH);
			throw new UserException("Employer with Ein: "+employerEin+" is a mismatch with provided Employer name and Id.",ErrorCodes.EIN_NAME_ID_MISMATCH);
		}
		LOGGER.error("employer with Ein: "+employerEin+" doesnt exist. Error code:"+ErrorCodes.EMPLOYER_DOESNT_EXIST);
		throw new UserException("Employer with Ein: "+employerEin+" doesnt exist",ErrorCodes.EMPLOYER_DOESNT_EXIST);	
	}

}
