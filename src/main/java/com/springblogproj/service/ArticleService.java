package com.springblogproj.service;

import com.springblogproj.domain.Article;
import com.springblogproj.dto.ArticleResponse;
import com.springblogproj.repository.ArticleRepository;
import com.springblogproj.repository.ArticleRepositoryDsl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleRepositoryDsl articleRepositoryDsl;

    // 모든 article 조회
    public List<ArticleResponse> getAllArticles() {
        return articleRepository.findAll().stream()
            .map(ArticleResponse::convert)
            .toList();
    }

    // id로 article 조회
    public ArticleResponse getArticleById(Long id) {
        return articleRepository.findById(id)
            .map(ArticleResponse::convert)
            .orElse(ArticleResponse.convert(createDefaultArticle()));
    }

    // article 등록
    public ArticleResponse saveArticle(Article article) {
        Article result = articleRepository.save(article);
        return ArticleResponse.convert(result);
    }

    // Article 수정
    public String updateArticle(Long id, Article article) {
        if (article.getTitle() == null && article.getContent() == null) {
            return "변경점이 없습니다.";
        }

        Article target = null;

        try {
            target = articleRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            return "해당 계시물이 없습니다.";
        }

        int count = 0;

        if (article.getTitle() == null || article.getTitle().contentEquals(target.getTitle())) {
            count++;
        }

        if (article.getContent() == null || article.getContent().contentEquals(target.getContent())) {
            count++;
        }

        if (count == 2) {
            return "변경점이 없습니다.";
        }

        return articleRepositoryDsl.update(id, article).equals(1L) ? "정상적으로 수정되었습니다." : "수정 도중 문제가 발생했습니다.";
    }

//    @Transactional 어노테이션으로 해당 메소드를 Hibernate가 쿼리로 만들어 DB에 보냄
//    @Transactional
//    public Article updateArticle(Article article) {
//        Article target = articleRepository.findById(article.getId()).get();
//        target.update(article.getTitle(), article.getContent());
//        return target;
//    }

    // article 삭제
    public String deleteArticle(Long id) {
        if (articleRepository.findById(id).isEmpty()) {
            return "해당 계시물이 없습니다.";
        }

        articleRepository.deleteById(id);
        return "정상적으로 삭제되었습니다.";
    }

    // 디폴트 ArticleResponse 생성
    private Article createDefaultArticle() {
        return new Article(-1L, "Not Found", "No match ID");
    }
}
