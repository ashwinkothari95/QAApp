package com.qa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.AnswerDTO;
import com.qa.service.AnswerService;
import com.qa.utility.ApiResponse;

@RestController
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private Environment environment;
	
	@PostMapping("/user/{userId}/question/{questionId}/answer")
	public ResponseEntity<AnswerDTO> postAnswer(@Valid @RequestBody AnswerDTO answerDTO,
								@PathVariable("userId") Integer userId,
								@PathVariable("questionId") Integer questionId
								)
	{
		AnswerDTO answerDTO2 = this.answerService.postAnswer(answerDTO, questionId, userId);
		
		return new ResponseEntity<AnswerDTO>(answerDTO2, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/{userId}/answer/{answerId}")
	public ResponseEntity<AnswerDTO> updateAnswer(@Valid @RequestBody AnswerDTO answerDTO,
												@PathVariable("answerId") Integer answerId,
												@PathVariable("userId") Integer userId)
	{
		AnswerDTO answerDTO2 = this.answerService.updateAnswer(answerDTO, answerId, userId);
		return new ResponseEntity<AnswerDTO>(answerDTO2,HttpStatus.OK);
	}
	
	@DeleteMapping("/answer/{answerId}")
	public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable("answerId") Integer answerId)
	{
		this.answerService.deleteAnswer(answerId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage(environment.getProperty("answer.delete.success"));
		apiResponse.setSuccess(true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
}
