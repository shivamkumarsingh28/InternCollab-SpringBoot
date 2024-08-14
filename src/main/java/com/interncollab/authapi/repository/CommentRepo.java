package com.interncollab.authapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interncollab.authapi.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

    
} 
