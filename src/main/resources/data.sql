create table article (
    id BIGINT primary key auto_increment,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    created_at timestamp,
    updated_at timestamp
);

create table comment (
    id BIGINT primary key auto_increment,
    article_id BIGINT NOT NULL,
    body VARCHAR(255),
    created_at timestamp,
    FOREIGN KEY (article_id) REFERENCES article(id)
);

INSERT INTO article(title, content, created_at, updated_at) VALUES ('블로그 제목 1', '블로그 내용 1', now(), now());
INSERT INTO article(title, content, created_at, updated_at) VALUES ('블로그 제목 2', '블로그 내용 2', now(), now());
INSERT INTO article(title, content, created_at, updated_at) VALUES ('블로그 제목 3', '블로그 내용 3', now(), now());

INSERT INTO comment(article_id, body, created_at) VALUES (1, '블로그 댓글 1', now());
INSERT INTO comment(article_id, body, created_at) VALUES (2, '블로그 댓글 2', now());
INSERT INTO comment(article_id, body, created_at) VALUES (3, '블로그 댓글 3', now());
