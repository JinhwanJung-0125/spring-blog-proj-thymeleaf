package com.springblogproj.repository;

import com.springblogproj.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIRepository extends JpaRepository<Blog, Long> {

}
