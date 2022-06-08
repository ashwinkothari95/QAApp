package com.qa.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.qa.customexception.ResourceNotFoundException;
import com.qa.dto.UserDTO;
import com.qa.entity.User;

import com.qa.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO getUserById(Integer userId)
	{
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		return User.preareUserDTO(user);
	}

}
