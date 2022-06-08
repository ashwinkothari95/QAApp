package com.qa.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.qa.entity.Question;

public class QuestionDTO {
	
	private int questionId;
	@NotBlank(message = "Question cannot be blank")
	@Size(max = 500, message = "Question can be of max 500 characters")
	private String questionContent;
	private Date addedDate;
	private UserDTO user;
	private CategoryDTO category;
	private List<AnswerDTO> answer;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public List<AnswerDTO> getAnswer() {
		return answer;
	}
	public void setAnswer(List<AnswerDTO> answer) {
		this.answer = answer;
	}
	
	public static Question prepareQuestionEntity(QuestionDTO questionDTO)
	{
		Question question = new Question();
		
		question.setQuestionId(questionDTO.getQuestionId());
		question.setQuestionContent(questionDTO.getQuestionContent());
		question.setAddedDate(questionDTO.getAddedDate());
		question.setUser(UserDTO.prepareUserEntity(questionDTO.getUser()));
		question.setCategory(CategoryDTO.prepareCategoryEntity(questionDTO.getCategory()));
		
		return question;
	}
	
	public static Question postQuestionEntity(QuestionDTO questionDTO)
	{
		Question question = new Question();
		
		question.setQuestionId(questionDTO.getQuestionId());
		question.setQuestionContent(questionDTO.getQuestionContent());
		question.setAddedDate(questionDTO.getAddedDate());
		
		return question;
	}

}
