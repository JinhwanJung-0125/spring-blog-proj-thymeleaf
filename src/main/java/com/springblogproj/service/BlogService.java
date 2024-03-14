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

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog findById(Long id) throws NoSuchElementException {
        return blogRepository.findById(id).orElseThrow();
    }

    public Blog createBlog(BlogRequest req) {
        return blogRepository.save(Blog.toEntity(req));
    }

    public void updateBlog(Long id, BlogRequest req) {
        if (req.getTitle() == null && req.getContext() == null) {
            return;
        }

        blogRepositoryDsl.updateBlog(id, req);
    }

    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }
}
