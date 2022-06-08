package com.qa.customexception;

public class UserAlreadyExistException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException(String msg, String propertyValue) {
		super(msg+" : " +propertyValue);
	}

}
