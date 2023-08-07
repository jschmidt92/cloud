package org.sog.web.blog;

import org.sog.web.blog.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {
}
