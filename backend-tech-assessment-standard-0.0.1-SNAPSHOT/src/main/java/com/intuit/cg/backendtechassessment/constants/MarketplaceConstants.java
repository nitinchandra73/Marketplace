package com.intuit.cg.backendtechassessment.constants;

public interface MarketplaceConstants {
	public static final String POST_BID_PATH =  "/bid/projectId/{projectid}/bidderId/{bidderid}";
	public static final String GET_BID_PATH = "/bid/projectId/{projectid}/bidderId/{bidderid}/bidId/{bidid}"; 
	public static final String GET_EMPLOYER_PATH = "/employer/ein/{ein}";
	public static final String POST_EMPLOYER_PATH = "/employer";
	public static final String GET_BIDDER_PATH = "/bidder/ein/{ein}";
	public static final String POST_BIDDER_PATH = "/bidder";
	public static final String POST_PROJECT_PATH = "/project/ein/{ein}/employerId/{employerid}";
	public static final String GET_PROJECT_PATH = "/project/employerId/{employerid}/projectId/{projectid}";
	public static final String PROJECT_ID= "projectid";
	public static final String BID_ID="bidid";
	public static final String BIDDER_ID = "bidderid";
	public static final String BIDDER_EIN = "ein";
	public static final String EMPLOYER_ID = "employerid";
	public static final String EMPLOYER_EIN = "ein";
	
}
