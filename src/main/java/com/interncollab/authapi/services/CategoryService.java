package com.interncollab.authapi.services;

import java.util.List;

import com.interncollab.authapi.payloads.CategoryDto;


public interface CategoryService {
    
    // Create
    CategoryDto createCategory(CategoryDto categoryDto);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // delete
    public void deleteCategory(Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    // get All
    List<CategoryDto> getCategories();
}
