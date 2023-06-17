package org.a3sog.hq.service;

import org.a3sog.hq.model.Blog;

import java.util.List;

public interface BlogService {
  boolean saveBlog(Blog blog);

  List<Blog> fetchAllBlog();

  Blog fetchBlogById(Long id);

  boolean deleteBlog(Long id);

  boolean updateBlog(Long id, Blog blog);
}
