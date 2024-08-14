package com.interncollab.authapi.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interncollab.authapi.entities.Comment;
import com.interncollab.authapi.entities.Post;
import com.interncollab.authapi.exceptions.ResourceNotFoundException;
import com.interncollab.authapi.payloads.CommentDto;
import com.interncollab.authapi.repository.CommentRepo;
import com.interncollab.authapi.repository.PostRepo;
import com.interncollab.authapi.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo PostRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        
        Post posts = this.PostRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(posts);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(comment);

    }
    
}
