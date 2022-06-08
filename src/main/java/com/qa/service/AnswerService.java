package com.qa.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.customexception.ResourceNotFoundException;
import com.qa.dto.AnswerDTO;
import com.qa.entity.Answer;
import com.qa.entity.Question;
import com.qa.entity.User;
import com.qa.repository.AnswerRepository;
import com.qa.repository.QuestionRepository;
import com.qa.repository.UserRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public AnswerDTO postAnswer(AnswerDTO answerDTO, Integer questionId, Integer userId)
	{
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId)); 
		Question question = this.questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "questionId", questionId));
		Answer answer = AnswerDTO.prepareAnswerEntity(answerDTO);
		answer.setQuestion(question);
		answer.setAddedDate(new Date());
		answer.setUser(user);
		
		Answer posted = this.answerRepository.save(answer);
		
		return Answer.prepareAnswerDTO(answer); 
	}
	
	public AnswerDTO updateAnswer(AnswerDTO answerDTO, Integer answerId, Integer userId)
	{
		AnswerDTO updatedAnswer=null;
		Answer answer = this.answerRepository.findById(answerId).orElseThrow(()-> new ResourceNotFoundException("Answer", "answerId", answerId));
		if(userId==answer.getUser().getUserId())
		{
			answer.setAddedDate(new Date());
			answer.setAnswer(answerDTO.getAnswer());
			updatedAnswer = Answer.prepareAnswerDTO(this.answerRepository.save(answer));
		}
		else
		{
			updatedAnswer = Answer.prepareAnswerDTO(answer);
		}
		return updatedAnswer;
	}
	
	public void deleteAnswer(Integer answerId)
	{
		Answer answer = this.answerRepository.findById(answerId).orElseThrow(()-> new ResourceNotFoundException("Answer", "answerId", answerId));
		this.answerRepository.delete(answer);
	}
}
