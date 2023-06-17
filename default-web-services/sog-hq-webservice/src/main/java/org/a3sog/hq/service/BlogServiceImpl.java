package org.a3sog.hq.service;

import org.a3sog.hq.model.Blog;
import org.a3sog.hq.repository.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

  @Autowired
  private BlogDao blogDao;

  @Override
  public boolean saveBlog(Blog blog) {
    return blogDao.saveBlog(blog);
  }

  @Override
  public List<Blog> fetchAllBlog() {
    return blogDao.fetchAllBlog();
  }

  @Override
  public Blog fetchBlogById(Long id) {
    return blogDao.fetchBlogById(id);
  }

  @Override
  public boolean deleteBlog(Long id) {
    return blogDao.deleteBlog(id);
  }

  @Override
  public boolean updateBlog(Long id, Blog blog) {
    return blogDao.updateBlog(id, blog);
  }
}
