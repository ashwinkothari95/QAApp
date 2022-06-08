package com.qa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.CategoryDTO;
import com.qa.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
	{
		CategoryDTO newCategoryDTO = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(newCategoryDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId") Integer categoryId){
		CategoryDTO categoryDTO = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO,HttpStatus.OK);
	}

}
