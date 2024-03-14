package com.springblogproj.repository;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.springblogproj.domain.Article;
import com.springblogproj.domain.QArticle;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ArticleRepositoryDsl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long update(Long id, Article article) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QArticle qArticle = QArticle.article;
        UpdateClause<JPAUpdateClause> build = queryFactory.update(qArticle);

        if (StringUtils.isNotBlank(article.getTitle())) {
            build.set(qArticle.title, article.getTitle());
        }

        if (StringUtils.isNotBlank(article.getContent())) {
            build.set(qArticle.content, article.getContent());
        }

        return build
            .where(qArticle.id.eq(id))
            .execute();
    }
}
