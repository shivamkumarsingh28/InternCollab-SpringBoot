package com.interncollab.authapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.interncollab.authapi.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
 
} 
