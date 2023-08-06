package org.sog.web.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository repository;

    public String save(Blog blog) {
        return repository.save(blog).getId();
    }
    public Blog findById(String id) {
        return repository.findById(id).orElse(null);
    }
    public List<Blog> findAll() {
        return repository.findAll();
    }
    public void delete(String id) {
        repository.deleteById(id);
    }
}
