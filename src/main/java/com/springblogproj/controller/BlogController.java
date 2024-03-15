package com.springblogproj.controller;

import com.springblogproj.domain.Blog;
import com.springblogproj.dto.BlogViewResponse;
import com.springblogproj.dto.BlogRequest;
import com.springblogproj.service.BlogService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Controller
public class BlogController {
    private final BlogService blogService;

    // 모든 포스팅을 조회하는 API
    // 조회한 포스팅 정보는 model 객체를 통해 thymeleaf View Resolver로 렌더링된다.
    @GetMapping("/blogs")
    public String getAllBlogs(Model model) {
        // DB로 조회한 결과를 BlogViewResponse 객체로 모두 변환한다.
        List<BlogViewResponse> blogs = blogService.findAll().stream()
            .map(BlogViewResponse::new)
            .toList();

        // Model 객체에 추가한다.
        model.addAttribute("blogs", blogs);

        return "blogList.html";
    }

    // 특정 포스팅을 조회하는 API
    // id로 특정 포스팅을 조회한다.
    @GetMapping("/blogs/{id}")
    public String getBlogById(@PathVariable("id") Long id, Model model) {
        try {
            // DB에 id를 통해 특정 포스팅을 조회하고 이를 BlogViewResponse 객체로 변환한다.
            BlogViewResponse blog = new BlogViewResponse(blogService.findById(id));

            // 이를 Model 객체에 추가한다.
            model.addAttribute("blog", blog);
        } catch (NoSuchElementException e) {
            // 만약 조회 결과가 없으면 error 페이지로 리다이렉트한다.
            return "redirect:/error";
        }

        return "blog.html";
    }

    // 새로운 포스팅 혹은 기존 포스팅을 수정할 페이지를 가져오는 API
    // 기존 포스팅을 수정할 경우 Query String을 통해 id 값을 가져와 이를 조회한다.
    @GetMapping("/new-blog")
    public String createNewBlog(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id == null) {
            // Query String으로 받은 id가 없으면 새로운 포스팅을 만드는 경우
            // 빈 BlogViewResponse를 Model 객체에 추가한다.
            model.addAttribute("blog", new BlogViewResponse());
        }
        else {
            // id가 있으면 기존의 포스팅을 수정하는 경우
            // id를 통해 기존 포스팅의 정보를 조회한다.
            Blog blog = blogService.findById(id);
            // 이 정보를 BlogViewResponse 객체로 변환한 뒤 Model 객체에 추가한다.
            model.addAttribute("blog", new BlogViewResponse(blog));
        }

        return "newBlog.html";
    }

    // 새로운 포스팅을 만드는 API
    // RequestBody의 내용을 토대로 새로운 포스팅을 DB에 등록한다.
    @PostMapping("/blogs")
    @ResponseStatus(value = HttpStatus.CREATED) // 반환 값이 없을 경우 Http Status Code를 Response로 보낸다.
    public void createBlog(@RequestBody BlogRequest req) {
        Blog result = blogService.createBlog(req);
    }

    // 특정 포스팅을 수정하는 API
    // id를 통해 수정할 특정 포스팅을 조회한다.
    // RequestBody의 내용을 토대로 기존 포스팅 내용을 수정한다.
    @PutMapping("/blogs/{id}")
    @ResponseStatus(value = HttpStatus.OK) // 반환 값이 없을 경우 Http Status Code를 Response로 보낸다.
    public void updateBlog(@PathVariable("id") Long id, @RequestBody BlogRequest req) {
        blogService.updateBlog(id, req);
    }

    // 특정 포스팅을 삭제하는 API
    // id를 통해 삭제할 특정 포스팅을 조회하고 삭제한다.
    @DeleteMapping("/blogs/{id}")
    @ResponseStatus(value = HttpStatus.OK) // 반환 값이 없을 경우 Http Status Code를 Response로 보낸다.
    public void deleteBlogById(@PathVariable("id") Long id) {
        blogService.deleteBlogById(id);
    }
}
