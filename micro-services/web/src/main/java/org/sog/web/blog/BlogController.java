package org.sog.web.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBlog(@RequestBody BlogRequest blogRequest) {
        blogService.createBlog(blogRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogResponse> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BlogResponse findBlogById(
            @PathVariable("id") String id
    ) {
        return blogService.getBlogById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBlog(
            @PathVariable("id") String id
    ) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
