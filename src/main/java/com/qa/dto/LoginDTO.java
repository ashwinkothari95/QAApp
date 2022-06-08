package com.qa.dto;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
	
	@NotBlank(message = "Username must be required")
	private String userName;
	@NotBlank(message = "Password must be required")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
