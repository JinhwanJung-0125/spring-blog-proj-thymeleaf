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

    // 댓글을 작성하는 Service 메소드
    public Comment writeComment(Comment comment) {
        // 작성할 댓글 내용이 없으면 null을 반환한다.
        if (comment.getBody().isBlank()) {
            return null;
        }

        // 작성한 댓글 내용을 DB에 저장한다.
        return commentRepository.saveAndFlush(comment);
    }

    // CommentId를 통해 특정 포스팅의 특정 댓글 정보를 가져오는 Service 메소드
    public CommentResult getCommentByCommentId(Long articleId, Long commentId) {
        // articleId와 commentId를 통해 특정 포스팅의 특정 댓글 정보를 DB에 조회한다.
        // 결과 Optional 객체에 대한 Null 처리를 포함해 Comment 객체로 변환한다.
        Comment result = commentRepository.findCommentByCommentId(articleId, commentId)
            .orElse(Comment.builder().body("Not Found").build());

        // Comment 객체를 CommentResult 객체로 변환한 뒤 이를 반환한다.
        return CommentResult.convert(result);
    }

    // 특정 포스팅에 대한 모든 댓글 리스트를 가져오는 Service 메소드
    public CommentResponse getCommentsByArticleId(Long articleId) {
        // 먼저 articleId를 통해 특정 포스팅 정보를 DB에 조회한다.
        Optional<Blog> blogResult = blogRepository.findById(articleId);

        // 이 결과 Optional 객체를 Null 처리를 포함해 Blog 객체로 변환한다.
        Blog blog = blogResult.orElse(Blog.builder().title("Not Found").build());

        // 만약 특정 포스팅 정보를 못 찾았다면 댓글 리스트는 null로 처리하고 반환한다.
        if (blog.getTitle().equals("Not Found")) {
            return CommentResponse.convert(blog, null);
        }

        // 특정 포스팅 정보를 찾았다면 그 포스팅에 달린 모든 댓글 리스트를 DB에 조회하고 이 댓글 Comment 객체를 CommentResult 객체로 변환한다.
        List<CommentResult> result = commentRepository.findAllByArticleId(articleId).stream()
            .map(CommentResult::convert)
            .toList();

        // 조회한 포스팅 정보를 담은 Blog 객체와 모든 댓글 리스트 정보를 담은 List<CommentResult> 객체를 CommentResponse 객체로 변환하고 이를 반환한다.
        return CommentResponse.convert(blog, result);
    }
}
