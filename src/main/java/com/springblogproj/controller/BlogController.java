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

    @GetMapping("/blogs")
    public String getAllBlogs(Model model) {
        List<BlogViewResponse> blogs = blogService.findAll().stream()
            .map(BlogViewResponse::new)
            .toList();

        model.addAttribute("blogs", blogs);

        return "blogList.html";
    }

    @GetMapping("/blogs/{id}")
    public String getBlogById(@PathVariable("id") Long id, Model model) {
        try {
            BlogViewResponse blog = new BlogViewResponse(blogService.findById(id));

            model.addAttribute("blog", blog);
        } catch (NoSuchElementException e) {
            return "redirect:/error";
        }

        return "blog.html";
    }

    @GetMapping("/new-blog")
    public String createNewBlog(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("blog", new BlogViewResponse());
        }
        else {
            Blog blog = blogService.findById(id);
            model.addAttribute("blog", new BlogViewResponse(blog));
        }

        return "newBlog.html";
    }

    @PostMapping("/blogs")
    @ResponseStatus(value = HttpStatus.CREATED) // 반환 값이 없을 경우 Http Status Code를 Response로 보낸다.
    public void createBlog(@RequestBody BlogRequest req) {
        Blog result = blogService.createBlog(req);
    }

    @PutMapping("/blogs/{id}")
    @ResponseStatus(value = HttpStatus.OK) // 반환 값이 없을 경우 Http Status Code를 Response로 보낸다.
    public void updateBlog(@PathVariable("id") Long id, @RequestBody BlogRequest req) {
        blogService.updateBlog(id, req);
    }

    @DeleteMapping("/blogs/{id}")
    @ResponseStatus(value = HttpStatus.OK) // 반환 값이 없을 경우 Http Status Code를 Response로 보낸다.
    public void deleteBlogById(@PathVariable("id") Long id) {
        blogService.deleteBlogById(id);
    }
}
