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

    @Transactional
    public Long updateBlog(Long id, BlogRequest req) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QBlog qBlog = QBlog.blog;
        UpdateClause<JPAUpdateClause> builder = queryFactory.update(qBlog);

        if (req.getTitle() != null && !req.getTitle().isBlank()) {
            builder.set(qBlog.title, req.getTitle());
        }

        if (req.getContext() != null) {
            builder.set(qBlog.content, req.getContext());
        }

        // builder.set(qBlog.updatedAt, LocalDateTime.now()); // 직접 수정 날짜 입력

        return builder
            .where(qBlog.id.eq(id))
            .execute();
    }
}
