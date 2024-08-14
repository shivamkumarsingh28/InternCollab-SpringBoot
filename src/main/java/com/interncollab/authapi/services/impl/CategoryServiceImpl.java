package com.interncollab.authapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interncollab.authapi.entities.Category;
import com.interncollab.authapi.exceptions.ResourceNotFoundException;
import com.interncollab.authapi.payloads.CategoryDto;
import com.interncollab.authapi.repository.CategoryRepo;
import com.interncollab.authapi.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cate = this.modelMapper.map(categoryDto, Category.class);
        Category addCate = this.categoryRepo.save(cate);
        return this.modelMapper.map(addCate, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cate.setCategoryTitle(categoryDto.getCategoryTitle());
        cate.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCate = this.categoryRepo.save(cate);
        return this.modelMapper.map(updateCate, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category deleteCate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
        this.categoryRepo.delete(deleteCate);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category getCate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.modelMapper.map(getCate, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> allCate = this.categoryRepo.findAll();
        List<CategoryDto> cateDto = allCate.stream().map((Cate)-> this.modelMapper.map(Cate, CategoryDto.class)).collect(Collectors.toList());
        return cateDto;
    }
    
}
