package com.interncollab.authapi.services;

import java.util.List;

import com.interncollab.authapi.payloads.PostDto;
import com.interncollab.authapi.payloads.PostResponse;

public interface PostService {
    
    // Create
    PostDto createPost(PostDto postDto, Integer authId, Integer categoryId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get single posts
    PostDto getPostById(Integer postId);

    // get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by Auth
    List<PostDto> getPostsbyAuth(Integer authId);

    List<PostDto> searchPosts(String keyword);
}   
