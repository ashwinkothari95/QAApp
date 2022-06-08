package com.qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.customexception.ResourceNotFoundException;
import com.qa.dto.CategoryDTO;
import com.qa.entity.Category;
import com.qa.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO createCategory(CategoryDTO categoryDTO)
	{
		Category newCategory = this.categoryRepository.save(CategoryDTO.prepareCategoryEntity(categoryDTO));
		return Category.prepareCategoryDTO(newCategory);
	}
	
	public CategoryDTO getCategoryById(Integer categoryId)
	{
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		return Category.prepareCategoryDTO(category);
	}
}
