package com.springblogproj.dto;

import com.springblogproj.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;

    private ArticleResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static ArticleResponse convert(Article article) {
        return new ArticleResponse(article.getTitle(), article.getContent());
    }
}
