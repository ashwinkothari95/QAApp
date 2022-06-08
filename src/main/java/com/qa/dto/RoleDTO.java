package com.qa.dto;

import javax.validation.constraints.NotBlank;

import com.qa.entity.Role;

public class RoleDTO {

	private int roleId;
	@NotBlank(message = "Role must be required")
	private String roleName;
	
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public static Role prepareRoleEntity(RoleDTO roleDTO)
	{
		Role role = new Role();
		role.setRoleId(roleDTO.getRoleId());
		role.setRoleName(roleDTO.getRoleName());
		
		return role;
	}
}
