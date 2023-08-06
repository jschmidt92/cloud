package org.sog.web.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService service;

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody Blog blog
    ) {
        return ResponseEntity.ok(service.save(blog));
    }
    @GetMapping
    public ResponseEntity<List<Blog>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(
            @PathVariable("id") String blogId
    ) {
        return ResponseEntity.ok(service.findById(blogId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") String blogId
    ) {
        service.delete(blogId);
        return ResponseEntity.accepted().build();
    }
}
