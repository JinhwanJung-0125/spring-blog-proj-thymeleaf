package com.springblogproj.service;

import com.springblogproj.domain.Blog;
import com.springblogproj.dto.BlogRequest;
import com.springblogproj.repository.BlogRepository;
import com.springblogproj.repository.BlogRepositoryDsl;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogRepositoryDsl blogRepositoryDsl;

    // 모든 포스팅 정보를 조회하는 Service 메소드
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    // 특정 포스팅 정보를 id를 통해 조회하는 Service 메소드
    // 만약 찾지 못했다면 NoSuchElementException을 발생시킨다.
    public Blog findById(Long id) throws NoSuchElementException {
        return blogRepository.findById(id).orElseThrow();
    }

    // 새로운 포스팅을 DB에 등록하는 Service 메소드
    // BlogRequest 객체를 Blog 객체로 변환한 뒤 DB에 저장한다.
    public Blog createBlog(BlogRequest req) {
        return blogRepository.save(Blog.toEntity(req));
    }

    // 특정 포스팅을 수정하는 Service 메소드
    // id를 통해 수정하고자 하는 포스팅을 조회한다.
    public void updateBlog(Long id, BlogRequest req) {
        // 만약 수정하고자 하는 내용이 비어있다면 그대로 메소드를 종료시킨다.
        // (DB 접근을 최소화하기 위함)
        if (req.getTitle() == null && req.getContext() == null) {
            return;
        }

        // BlogRequest 객체의 정보를 바탕으로 특정 포스팅의 내용을 수정한다.
        blogRepositoryDsl.updateBlog(id, req);
    }

    // 특정 포스팅을 삭제하는 Service 메소드
    // id를 통해 삭제할 특정 포스팅을 DB에 조회한다.
    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }
}
