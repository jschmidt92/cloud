package org.a3sog.hq.controller;

import org.a3sog.hq.model.Blog;
import org.a3sog.hq.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
  private final String API_VERSION = "/api/v0.1";
  private final String BLOG = API_VERSION + "/blog" + "/{id}";
  private final String BLOGS = API_VERSION + "/blog";

  @Autowired
  private BlogService blogService;

  @PostMapping(value = BLOGS)
  public ResponseEntity<String> saveBlog(@RequestBody Blog blog) {
    boolean result = blogService.saveBlog(blog);
    if (result)
      return ResponseEntity.ok("Blog Created Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping(value = BLOGS)
  public ResponseEntity<List<Blog>> fetchAllBlog() {
    List<Blog> blogs;
    blogs = blogService.fetchAllBlog();
    return ResponseEntity.ok(blogs);
  }

  @GetMapping(value = BLOG)
  public ResponseEntity<Blog> fetchBlogById(@PathVariable("id") Long id) {
    Blog blog;
    blog = blogService.fetchBlogById(id);
    return ResponseEntity.ok(blog);
  }

  @DeleteMapping(value = BLOG)
  public ResponseEntity<String> deleteBlog(@PathVariable("id") Long id) {
    boolean result = blogService.deleteBlog(id);
    if (result)
      return ResponseEntity.ok("Blog Deleted Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @PutMapping(value = BLOG)
  public ResponseEntity<String> updateBlog(@PathVariable("id") Long id, @RequestBody Blog blog) {
    boolean result = blogService.updateBlog(id, blog);
    if (result)
      return ResponseEntity.ok("Blog Updated Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
