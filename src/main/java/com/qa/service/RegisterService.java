package com.qa.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qa.customexception.ResourceNotFoundException;
import com.qa.customexception.UserAlreadyExistException;
import com.qa.dto.UserDTO;
import com.qa.entity.Role;
import com.qa.entity.User;
import com.qa.repository.RoleRepository;
import com.qa.repository.UserRepository;

@Service
public class RegisterService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment environment;
	
	public UserDTO registerUser(UserDTO userDTO) {
		
		User user = UserDTO.prepareUserEntity(userDTO);
		Optional<User> userExist = this.userRepository.findByEmail(user.getEmail());
		if(userExist.isPresent())
			throw new UserAlreadyExistException(environment.getProperty("user.email.already.exist"),userExist.get().getEmail());
		else
		{
			Role role = this.roleRepository.findById(1).orElseThrow(()-> new ResourceNotFoundException("Role", "roleId", 1));
			Set<Role> userRole = new HashSet<>();
			userRole.add(role);
			user.setRole(userRole);
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			User newUser = this.userRepository.save(user);
			return User.preareUserDTO(newUser);
		}
		
	}

}
