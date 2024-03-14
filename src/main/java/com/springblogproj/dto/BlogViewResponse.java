package com.springblogproj.dto;

import com.springblogproj.domain.Blog;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
