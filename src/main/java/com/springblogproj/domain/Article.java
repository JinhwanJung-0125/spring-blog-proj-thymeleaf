package com.springblogproj.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// POJO (Plain Object Java Object)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 ID 정책을 따름 (DB는 ID에 Auto Increment를 적용함)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

//    @Builder
//    public Article(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    public static Article builder() {
        return new Article();
    }

    public Article id(Long id) {
        this.id = id;
        return this;
    }

    public Article title(String title) {
        this.title = title;
        return this;
    }

    public Article content(String content) {
        this.content = content;
        return this;
    }

    public Article build() {
        return this;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
