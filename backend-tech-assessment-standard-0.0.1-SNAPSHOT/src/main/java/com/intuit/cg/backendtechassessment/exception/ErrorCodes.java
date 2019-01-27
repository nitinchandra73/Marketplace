package com.intuit.cg.backendtechassessment.exception;

public enum ErrorCodes {
	MISSING_PROJECT_FOR_ID	(001),
	MISMATCH_PROJECT_ID_TO_PROJECT_TITLE (002),
	MISSMATCH_EMPLOYER_ID_AND_PROJECT_ID(003), 
	BID_AMOUNT_NOT_LESS_THAN_LEAST(004),
	BIDDING_NOT_ACTIVE(005),
	BIDDER_ID_DOESNT_EXIST(006), BID_FOR_BIDDER_EXIST (007),
	EMPLOYER_ID_SHOULDNT_BE_PROVIDED(13208), EMPLOYER_ALREADY_EXISTS(123), EMPLOYER_DOESNT_EXIST(456), EIN_NAME_ID_MISMATCH(124);
	 private Integer errorCode;
	
	 
	    private ErrorCodes(int errorCode) {
	        this.errorCode = errorCode;
	    }
	 
	    public int getErrorCode() {
	        return errorCode;
	    }
}
