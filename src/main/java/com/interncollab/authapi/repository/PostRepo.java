package com.interncollab.authapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.interncollab.authapi.entities.Auth;
import com.interncollab.authapi.entities.Category;
import com.interncollab.authapi.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{
    
    List<Post> findByAuth(Auth auth);

    List<Post> findByCategory(Category category);

    List<Post> findByContentContaining(String title);

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key")String title);
    
}
