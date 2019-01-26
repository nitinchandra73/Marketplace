package com.intuit.cg.backendtechassessment.exception;

public enum ErrorCodes {
	MISSING_PROJECT_FOR_ID	(001),
	MISMATCH_PROJECT_ID_TO_PROJECT_TITLE (002),
	MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID(003), 
	BID_AMOUNT_NOT_LESS_THAN_LEAST(004);
	 private int errorCode;
	
	 
	    private ErrorCodes(int errorCode) {
	        this.errorCode = errorCode;
	    }
	 
	    public int getErrorCode() {
	        return errorCode;
	    }
}
