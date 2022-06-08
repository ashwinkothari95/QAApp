package com.qa.exceptionhandler;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qa.customexception.InvalidUserNamePasswordException;
import com.qa.customexception.ResourceNotFoundException;
import com.qa.customexception.UserAlreadyExistException;
import com.qa.utility.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
	{
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName= ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage(ex.getMessage());
		apiResponse.setSuccess(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidUserNamePasswordException.class)
	public ResponseEntity<ApiResponse> handleInvalidUserNamePasswordException(InvalidUserNamePasswordException ex)
	{
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage(ex.getMessage());
		apiResponse.setSuccess(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ApiResponse> handleUserAlreadyExistException(UserAlreadyExistException ex)
	{
		ApiResponse apiResponse= new ApiResponse();
		apiResponse.setMessage(ex.getMessage());
		apiResponse.setSuccess(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
}
