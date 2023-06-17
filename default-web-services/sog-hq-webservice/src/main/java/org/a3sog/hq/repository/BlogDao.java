package org.a3sog.hq.repository;

import org.a3sog.hq.model.Blog;

import java.util.List;

public interface BlogDao {
  boolean saveBlog(Blog blog);

  List<Blog> fetchAllBlog();

  Blog fetchBlogById(Long id);

  boolean deleteBlog(Long id);

  boolean updateBlog(Long id, Blog blog);
}
