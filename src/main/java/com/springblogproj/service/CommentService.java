package com.springblogproj.service;

import com.springblogproj.domain.Comment;
import com.springblogproj.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment writeComment(Comment comment) {
        if (comment.getBody().isBlank()) {
            return null;
        }

        return commentRepository.save(comment);
    }
}
