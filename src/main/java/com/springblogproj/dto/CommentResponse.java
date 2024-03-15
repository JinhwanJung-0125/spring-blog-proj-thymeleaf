package com.springblogproj.dto;

import com.springblogproj.domain.Blog;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 댓글 전체 조회 시 포스팅 정보와 전체 댓글 리스트를 저장해 전달하는 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResult> comments;

    public static CommentResponse convert(Blog blog, List<CommentResult> comments) {
        return new CommentResponse(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt(),
            blog.getUpdatedAt(), comments);
    }
}
