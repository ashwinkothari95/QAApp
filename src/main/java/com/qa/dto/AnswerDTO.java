package com.qa.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.qa.entity.Answer;
import com.qa.entity.User;

public class AnswerDTO {
	
	private int answerId;
	@NotBlank(message = "Answer cannot be blank")
	@Size(max=1000, message = "Answer can be of max 1000 characters")
	private String answer;
	private Date addedDate;
	
	private UserDTO user;
	
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	
	public static Answer prepareAnswerEntity(AnswerDTO answerDTO)
	{
		Answer answer = new Answer();
		answer.setAnswerId(answerDTO.getAnswerId());
		answer.setAnswer(answerDTO.getAnswer());
		answer.setAddedDate(answerDTO.getAddedDate());
		
		return answer;
	}

}
