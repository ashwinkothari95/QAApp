package com.qa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.customexception.InvalidUserNamePasswordException;
import com.qa.dto.LoginDTO;
import com.qa.jwt.JwtAuthResponse;
import com.qa.jwt.JwtTokenHelper;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody LoginDTO loginDTO) throws Exception
	{
		authenticate(loginDTO.getUserName(),loginDTO.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginDTO.getUserName());
		String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(generatedToken);
		
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}
	
	private void authenticate(String userName, String password) throws InvalidUserNamePasswordException
	{
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
		try {
			manager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new InvalidUserNamePasswordException(environment.getProperty("invalid.username.password"));
		}
		
	}
}
