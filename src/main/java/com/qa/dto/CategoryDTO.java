package com.qa.dto;

import javax.validation.constraints.NotBlank;

import com.qa.entity.Category;

public class CategoryDTO {
	
	private int categoryId;
	@NotBlank(message = "Category name is required")
	private String categoryName;
	private String categoryDesc;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	public static Category prepareCategoryEntity(CategoryDTO categoryDTO)
	{
		Category category = new Category();
		
		category.setCategoryId(categoryDTO.getCategoryId());
		category.setCategoryName(categoryDTO.getCategoryName());
		category.setCategoryDesc(categoryDTO.getCategoryDesc());
		
		return category;
	}

}
