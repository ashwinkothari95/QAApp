package com.qa.customexception;

public class ResourceNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	
	
	public ResourceNotFoundException(String resourceName, String resourceField, Integer userId) {
		super(String.format("%s with %s not found : %d",resourceName,resourceField,userId));
	}
	
	public ResourceNotFoundException(String resourceName, String resourceField, String userName) {
		super(String.format("%s with %s not found : %d",resourceName,resourceField,userName));
	}

}
