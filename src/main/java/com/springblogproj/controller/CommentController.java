package com.springblogproj.controller;

import com.springblogproj.domain.Comment;
import com.springblogproj.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/comments/{articleId}")
    public ResponseEntity<Comment> writeComment(@PathVariable("articleId") Long articleId, @RequestBody
    Comment body) {
        Comment comment = Comment.builder()
            .articleId(articleId)
            .body(body.getBody())
            .build();

        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Content-Type", "application/json")
            .body(commentService.writeComment(comment));
    }
}
