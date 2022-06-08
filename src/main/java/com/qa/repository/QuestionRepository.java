package com.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.entity.Category;
import com.qa.entity.Question;
import com.qa.entity.User;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	List<Question> findQuestionByCategory(Category category);
	List<Question> findQuestionByUser(User user);
	

}
