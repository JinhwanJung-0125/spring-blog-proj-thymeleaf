package com.springblogproj.controller;

import com.springblogproj.domain.Article;
import com.springblogproj.dto.ArticleResponse;
import com.springblogproj.dto.SaveArticleRequest;
import com.springblogproj.service.ArticleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // ResponseEntity 객체를 사용하면 HTTP 헤더 정보도 넣을 수 있다.

    // 모든 article 조회
    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        return ResponseEntity.status(HttpStatus.OK)
            .header("content-type", "application/json")
            .body(articleService.getAllArticles());
    }

    // id로 article 조회
    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .header("content-type", "application/json")
            .body(articleService.getArticleById(id));
    }

    // article 등록
    @PostMapping("/article/post")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody SaveArticleRequest saveArticleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .header("content-type", "application/json")
            .body(articleService.saveArticle(saveArticleRequest.toEntity()));
    }

    // article 수정
    @PutMapping("/article/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.OK)
            .header("content-type", "application/json")
            .body(articleService.updateArticle(id, article));
    }

    // article 삭제
    @DeleteMapping("/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .header("content-type", "application/json")
            .body(articleService.deleteArticle(id));
    }
}
