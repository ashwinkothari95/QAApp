package com.qa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.qa.dto.AnswerDTO;
import com.qa.dto.QuestionDTO;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerId;
	@Column(length = 1000)
	private String answer;
	private Date addedDate;
	
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private User user;
	
	
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
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public static AnswerDTO prepareAnswerDTO(Answer answer)
	{
		AnswerDTO answerDTO = new AnswerDTO();
		
		answerDTO.setAnswerId(answer.getAnswerId());
		answerDTO.setAnswer(answer.getAnswer());
		answerDTO.setAddedDate(answer.getAddedDate());
		answerDTO.setUser(User.preareUserDTO(answer.getUser()));
		
		return answerDTO;
	}

}
