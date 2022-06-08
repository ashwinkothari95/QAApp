package com.qa.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.qa.customexception.ResourceNotFoundException;
import com.qa.dto.QuestionDTO;
import com.qa.entity.Category;
import com.qa.entity.Question;
import com.qa.entity.User;
import com.qa.repository.CategoryRepository;
import com.qa.repository.QuestionRepository;
import com.qa.repository.UserRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public QuestionDTO addQuestion(QuestionDTO questionDTO, Integer userId, Integer categoryId)
	{
		
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Question question = QuestionDTO.postQuestionEntity(questionDTO);
		question.setAddedDate(new Date());
		question.setUser(user);
		question.setCategory(category);
		
		Question newQuestion = this.questionRepository.save(question);
		
		return Question.prepareQuestionDTO(newQuestion);
	}
	
	public List<QuestionDTO> findAllQuestionByCategory(Integer categoryId)
	{
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Question> questionList = this.questionRepository.findQuestionByCategory(category);
		List<QuestionDTO> questionListByCategory = questionList.stream().map(question->Question.prepareQuestionDTO(question)).collect(Collectors.toList());
		
		return questionListByCategory;
	}
	
	public List<QuestionDTO> findAllQuestionByUser(Integer userId)
	{
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<Question> questionList = this.questionRepository.findQuestionByUser(user);
		List<QuestionDTO> questionListByCategory = questionList.stream().map(question->Question.prepareQuestionDTO(question)).collect(Collectors.toList());
		
		return questionListByCategory;
	}
	
	public List<QuestionDTO> findAllQuestion(Integer pageNumber, Integer pageSize, String sortBy, String orderBy)
	{
		Sort sort = orderBy.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
		Page<Question> questionPage = this.questionRepository.findAll(pageable);
		List<Question> questionList = questionPage.getContent();
		List<QuestionDTO> questionList1 = questionList.stream().map(question->Question.prepareQuestionDTO(question)).collect(Collectors.toList());
		
		return questionList1;
	}
	
	public QuestionDTO findQuestionById(Integer questionId)
	{
		Question question = this.questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "questionId", questionId));
		QuestionDTO questionDTO  = Question.prepareQuestionDTO(question);
		
		return questionDTO;
	}
	
	public void deleteQuestionById(Integer questionId)
	{
		Question question = this.questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "questionId", questionId));
		this.questionRepository.delete(question);
	}
	
	public QuestionDTO updateQuestion(QuestionDTO questionDTO, Integer questionId)
	{
		Question question = this.questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "questionId", questionId));
		Category category = this.categoryRepository.findById(question.getCategory().getCategoryId()).
																		orElseThrow(()->new ResourceNotFoundException("Category", 
																													"categoryId", 
																													question.getCategory().getCategoryId()));
		
		Question updateQuestion = QuestionDTO.postQuestionEntity(questionDTO);
		question.setAddedDate(new Date());
		question.setCategory(category);
		question.setQuestionContent(updateQuestion.getQuestionContent());
		
		Question updatedQuestion = this.questionRepository.save(question);
		
		return Question.prepareQuestionDTO(updatedQuestion);
	}
	
	
}
