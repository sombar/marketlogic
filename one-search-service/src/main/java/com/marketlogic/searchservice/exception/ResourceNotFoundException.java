package com.marketlogic.searchservice.exception;

public class ResourceNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String errorMessage;
	
	String errCode;

	public ResourceNotFoundException(String errm,String errCode) {
		errorMessage=errm;
		this.errCode = errCode;
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getErrCode() {
		return errCode;
	}


	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
