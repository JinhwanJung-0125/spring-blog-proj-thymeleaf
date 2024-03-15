package com.springblogproj.dto;

import com.springblogproj.domain.Blog;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Model 객체로 보낼 Blog 데이터만 따로 모아 저장해 전달하는 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public BlogViewResponse(Blog blog) {
        id = blog.getId();
        title = blog.getTitle();
        content = blog.getContent();
        createdAt = blog.getCreatedAt();
    }
}
