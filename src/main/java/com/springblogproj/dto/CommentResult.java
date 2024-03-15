package com.springblogproj.dto;

import com.springblogproj.domain.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Query 결과로 나온 Comment 중 id, body, createdAt 정보만 따로 모아 저장해 전달하는 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResult {
    private Long id;
    private String body;
    private LocalDateTime createdAt;

    public static CommentResult convert(Comment comment) {
        return new CommentResult(comment.getId(), comment.getBody(), comment.getCreatedAt());
    }
}
