package com.springblogproj.service;

import com.springblogproj.domain.Blog;
import com.springblogproj.domain.Comment;
import com.springblogproj.dto.CommentResponse;
import com.springblogproj.dto.CommentResult;
import com.springblogproj.repository.BlogRepository;
import com.springblogproj.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public Comment writeComment(Comment comment) {
        if (comment.getBody().isBlank()) {
            return null;
        }

        return commentRepository.save(comment);
    }

    public CommentResult getCommentByCommentId(Long articleId, Long commentId) {
        Comment result = commentRepository.findCommentByCommentId(articleId, commentId)
            .orElse(Comment.builder().body("Not Found").build());
        return CommentResult.convert(result);
    }

    public CommentResponse getCommentsByArticleId(Long articleId) {
        Optional<Blog> blogResult = blogRepository.findById(articleId);

        Blog blog = blogResult.orElse(Blog.builder().title("Not Found").build());

        if (blog.getTitle().equals("Not Found")) {
            return CommentResponse.convert(blog, null);
        }

        List<CommentResult> result = commentRepository.findAllByArticleId(articleId).stream()
            .map(CommentResult::convert)
            .toList();

        return CommentResponse.convert(blog, result);
    }
}
