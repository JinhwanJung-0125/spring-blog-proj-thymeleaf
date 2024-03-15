package com.springblogproj.repository;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.springblogproj.domain.QBlog;
import com.springblogproj.dto.BlogRequest;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class BlogRepositoryDsl {
    private final EntityManager entityManager;

    // 특정 포스팅을 수정하기 위한 QueryDsl
    // Transactional 어노테이션을 통해 이 메소드의 결과를 DB에 반영한다.
    @Transactional
    public Long updateBlog(Long id, BlogRequest req) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QBlog qBlog = QBlog.blog; // Article 테이블과 매핑된 Blog 객체
        UpdateClause<JPAUpdateClause> builder = queryFactory.update(qBlog); // Update Query로 builder를 만든다.

        // 수정할 title 내용이 있으면 set에 반영한다.
        if (req.getTitle() != null && !req.getTitle().isBlank()) {
            builder.set(qBlog.title, req.getTitle());
        }

        // 수정할 content 내용이 있으면 set에 반영한다.
        if (req.getContext() != null) {
            builder.set(qBlog.content, req.getContext());
        }

        builder.set(qBlog.updatedAt, LocalDateTime.now()); // 직접 수정 날짜 입력

        // 이 결과를 id 값과 일치하는 포스팅에 대해 반영한다.
        return builder
            .where(qBlog.id.eq(id))
            .execute();
    }
}
