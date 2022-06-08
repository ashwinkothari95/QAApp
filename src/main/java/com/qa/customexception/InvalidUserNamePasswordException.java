package com.qa.customexception;

public class InvalidUserNamePasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidUserNamePasswordException(String s) {
		super(s);
	}

}
