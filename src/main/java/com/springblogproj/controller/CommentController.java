package com.springblogproj.controller;

import com.springblogproj.domain.Comment;
import com.springblogproj.dto.CommentResponse;
import com.springblogproj.dto.CommentResult;
import com.springblogproj.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 등록 API
    // articleId로 댓글을 달 포스팅을 조회
    // RequestBody 안의 댓글 내용을 Comment 객체로 받는다.
    @PostMapping("/comments/{articleId}")
    public ResponseEntity<Comment> writeComment(@PathVariable("articleId") Long articleId, @RequestBody
    Comment body) {
        // articleId와 RequestBody의 Comment 내용을 통해 최종적으로 저장할 Comment 객체를 만든다.
        Comment comment = Comment.builder()
            .articleId(articleId)
            .body(body.getBody())
            .build();

        // Status Code: 201, Content-Type: Json
        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Content-Type", "application/json")
            .body(commentService.writeComment(comment));
    }

    // 특정 댓글 조회 API
    // articleId로 조회할 댓글이 있는 포스팅 조회
    // commentId로 댓글 조회
    @GetMapping("/comments/{articleId}/{commentId}")
    public ResponseEntity<CommentResult> getCommentByArticleId(@PathVariable("articleId") Long articleId,
        @PathVariable("commentId") Long commentId) {
        // Status Code: 200, Content-Type: Json
        return ResponseEntity.ok()
            .header("Content-Type", "application/json")
            .body(commentService.getCommentByCommentId(articleId, commentId));
    }

    // 특정 포스팅의 전체 댓글 조회
    // articleId로 전체 댓글을 조회할 포스팅 조회
    @GetMapping("/comments/{articleId}")
    public ResponseEntity<CommentResponse> getAllCommentByArticleId(@PathVariable("articleId") Long articleId) {
        // Status Code: 200, Content-Type: Json
        return ResponseEntity.ok()
            .header("Content-Type", "application/json")
            .body(commentService.getCommentsByArticleId(articleId));
    }
}
