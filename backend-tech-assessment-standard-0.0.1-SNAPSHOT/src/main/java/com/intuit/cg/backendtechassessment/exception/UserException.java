package com.intuit.cg.backendtechassessment.exception;

public class UserException extends Exception {
	String exception;
	ErrorCodes errorCode;
	
	public UserException(String exception, ErrorCodes errorCode) {
		super();
		this.exception = exception;
		this.errorCode = errorCode;
	}
	public String toString(){
	     return ("User exception: "+ exception+"; error code: "+errorCode.getErrorCode()) ;
	  }
}
