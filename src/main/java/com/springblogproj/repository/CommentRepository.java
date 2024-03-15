package com.springblogproj.repository;

import com.springblogproj.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // commentId와 articleId를 통해 특정 댓글을 조회하는 Query
    @Query(value = "SELECT * FROM comment WHERE id = :commentId AND article_id = :articleId ORDER BY id", nativeQuery = true)
    Optional<Comment> findCommentByCommentId(@Param(value = "articleId") Long articleId,
        @Param(value = "commentId") Long commentId);

    // articleId를 통해 특정 포스팅의 모든 댓글을 조회하는 Query
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId ORDER BY id", nativeQuery = true)
    List<Comment> findAllByArticleId(@Param(value = "articleId") Long articleId);
}
