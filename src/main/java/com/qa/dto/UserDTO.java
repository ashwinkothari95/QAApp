package com.qa.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.qa.entity.User;

public class UserDTO {
	
	private int userId;
	@NotBlank(message = "Name must be required")
	private String name;
	@NotBlank(message = "Email must be required")
	@Email(message = "Invalid email format")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be of min 8 characters")
	private String password;
	private String about;
	private RoleDTO role;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	
	public static User prepareUserEntity(UserDTO userDTO)
	{
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		return user;
	}

}
