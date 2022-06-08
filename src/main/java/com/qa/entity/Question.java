package com.qa.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.qa.dto.AnswerDTO;
import com.qa.dto.QuestionDTO;


@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	@Column(length = 500)
	private String questionContent;
	private Date addedDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy ="question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Answer> answerList;
	
	
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Answer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	public static QuestionDTO prepareQuestionDTO(Question question)
	{
		QuestionDTO questionDTO = new QuestionDTO();
		
		questionDTO.setQuestionId(question.getQuestionId());
		questionDTO.setQuestionContent(question.getQuestionContent());
		questionDTO.setAddedDate(question.getAddedDate());
		questionDTO.setUser(User.preareUserDTO(question.getUser()));
		questionDTO.setCategory(Category.prepareCategoryDTO(question.getCategory()));
		List<AnswerDTO> answer = question.getAnswerList().stream().map(ans->Answer.prepareAnswerDTO(ans)).collect(Collectors.toList());
		questionDTO.setAnswer(answer);
		
		return questionDTO;
	}
	
}
