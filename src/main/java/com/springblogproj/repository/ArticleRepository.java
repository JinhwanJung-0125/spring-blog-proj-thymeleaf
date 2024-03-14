package com.springblogproj.repository;

import com.springblogproj.domain.Article;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Override
    void deleteById(Long id);

    // Java Persistence Query Language 사용
    @Query(value = "update article set title = :title, content = :content where id = :id", nativeQuery = true)
    void updateArticle(Long id, String title, String content);
}
