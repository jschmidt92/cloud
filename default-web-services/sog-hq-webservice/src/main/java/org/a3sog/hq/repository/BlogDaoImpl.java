package org.a3sog.hq.repository;

import org.a3sog.hq.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogDaoImpl implements BlogDao {

  @Autowired
  private RedisTemplate redisTemplate;

  private static final String KEY = "BLOG";

  @Override
  public boolean saveBlog(Blog blog) {
    try {
      redisTemplate.opsForHash().put(KEY, blog.getId().toString(), blog);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Blog> fetchAllBlog() {
    List<Blog> blogs;
    blogs = redisTemplate.opsForHash().values(KEY);
    return blogs;
  }

  @Override
  public Blog fetchBlogById(Long id) {
    Blog blog;
    blog = (Blog) redisTemplate.opsForHash().get(KEY, id.toString());
    return blog;
  }

  @Override
  public boolean deleteBlog(Long id) {
    try {
      redisTemplate.opsForHash().delete(KEY, id.toString());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateBlog(Long id, Blog blog) {
    try {
      redisTemplate.opsForHash().put(KEY, id, blog);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
