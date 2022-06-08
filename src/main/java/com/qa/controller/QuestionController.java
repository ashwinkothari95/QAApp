package com.qa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.QuestionDTO;
import com.qa.service.QuestionService;
import com.qa.utility.ApiResponse;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	private Environment environment;
	
	@PostMapping("/user/{userId}/category/{categoryId}/question")
	public ResponseEntity<QuestionDTO> postQuestion(@Valid @RequestBody QuestionDTO questionDTO,
													@PathVariable Integer userId,
													@PathVariable Integer categoryId)
	{
	QuestionDTO questionDTO2  = this.questionService.addQuestion(questionDTO, userId, categoryId);
	return new ResponseEntity<QuestionDTO>(questionDTO2, HttpStatus.CREATED);	
	}
	
	@GetMapping("/user/{userId}/question")
	public ResponseEntity<List<QuestionDTO>> findAllQuestionByUser(@PathVariable("userId") Integer userId)
	{
		List<QuestionDTO> questionListByUser = this.questionService.findAllQuestionByUser(userId);
		return new ResponseEntity<List<QuestionDTO>>(questionListByUser,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/question")
	public ResponseEntity<List<QuestionDTO>> findAllQuestionByCategory(@PathVariable("categoryId") Integer categoryId)
	{
		List<QuestionDTO> questionListByUser = this.questionService.findAllQuestionByCategory(categoryId);
		return new ResponseEntity<List<QuestionDTO>>(questionListByUser,HttpStatus.OK);
	}
	
	@GetMapping("/question")
	public ResponseEntity<List<QuestionDTO>> findAllQuestion(@RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
															@RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
															@RequestParam(value = "sortBy", defaultValue = "questionId", required = false) String sortBy,
															@RequestParam(value="orderBy", defaultValue = "asc", required = false) String orderBy)
	{
		List<QuestionDTO> questionList = this.questionService.findAllQuestion(pageNumber, pageSize, sortBy, orderBy);
		return new ResponseEntity<List<QuestionDTO>>(questionList,HttpStatus.OK);
	}
	
	@GetMapping("/question/{questionId}")
	public ResponseEntity<QuestionDTO> findQuestionById(@PathVariable("questionId") Integer questionId)
	{
		QuestionDTO question = this.questionService.findQuestionById(questionId);
		return new ResponseEntity<QuestionDTO>(question,HttpStatus.OK);
	}
	
	@PutMapping("/question/{questionId}")
	public ResponseEntity<QuestionDTO> updateQuestion(@Valid @RequestBody QuestionDTO questionDTO,
															@PathVariable("questionId") Integer questionId)
	{
		QuestionDTO question = this.questionService.updateQuestion(questionDTO,questionId);
		return new ResponseEntity<QuestionDTO>(question,HttpStatus.OK);
	}
	
	@DeleteMapping("/question/{questionId}")
	public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable("questionId") Integer questionId)
	{
		this.questionService.deleteQuestionById(questionId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage(environment.getProperty("question.delete.success"));
		apiResponse.setSuccess(true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	
}
