package com.qa.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.qa.dto.RoleDTO;
import com.qa.dto.UserDTO;

@Entity
public class User implements UserDetails {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userId;
		private String name;
		private String email;
		private String password;
		@Column(length = 600)
		private String about;
		
		@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Question> questionList;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		private  List<Answer> answerList;
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name="user_role",
		joinColumns = @JoinColumn(name="user_userId", referencedColumnName = "userId"),
		inverseJoinColumns = @JoinColumn(name="role_roleId", referencedColumnName = "roleId"))
		private Set<Role> role = new HashSet<>();
		
		public User() {}

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
		
		public Set<Role> getRole() {
			return role;
		}

		public void setRole(Set<Role> role) {
			this.role = role;
		}

		public static UserDTO preareUserDTO(User user)
		{
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setName(user.getName());
			userDTO.setEmail(user.getEmail());
			userDTO.setPassword(user.getPassword());
			userDTO.setAbout(user.getAbout());
			for(Role role : user.getRole())
			{
				userDTO.setRole(Role.prepareRoleDTO(role));
			}
			
			return userDTO;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> userAuthorities = role.stream().map(userRole-> new SimpleGrantedAuthority(userRole.getRoleName())).collect(Collectors.toList());
			return userAuthorities;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return this.email;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		} 
	}

