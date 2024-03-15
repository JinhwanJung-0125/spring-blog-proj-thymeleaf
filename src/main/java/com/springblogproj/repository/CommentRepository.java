package com.springblogproj.repository;

import com.springblogproj.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId ORDER BY id", nativeQuery = true)
    List<Comment> findAllByArticleId(@Param(value = "articleId") Long articleId);
}
