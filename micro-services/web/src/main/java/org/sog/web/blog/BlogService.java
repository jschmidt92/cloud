package org.sog.web.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public void createBlog(BlogRequest blogRequest) {
        Blog blog;

        if (blogRequest.getId() != null && blogRepository.existsById(blogRequest.getId())) {
            blog = blogRepository.findById(blogRequest.getId())
                    .orElseThrow(() -> new RuntimeException("Blog not found for id: " + blogRequest.getId()));

            blog.setTitle(blogRequest.getTitle());
            blog.setDate(blogRequest.getDate());
            blog.setAuthor(blogRequest.getAuthor());
            blog.setImage(blogRequest.getImage());
            blog.setSummary(blogRequest.getSummary());
            blog.setContent(blogRequest.getContent());
            blog.setTags(blogRequest.getTags());
        } else {
            blog = Blog.builder()
                    .title(blogRequest.getTitle())
                    .date(blogRequest.getDate())
                    .author(blogRequest.getAuthor())
                    .image(blogRequest.getImage())
                    .summary(blogRequest.getSummary())
                    .content(blogRequest.getContent())
                    .tags(blogRequest.getTags())
                    .build();
        }

        blogRepository.save(blog);
    }

    public List<BlogResponse> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();

        return blogs.stream().map(this::mapToBlogResponse).toList();
    }

    private BlogResponse mapToBlogResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .date(blog.getDate())
                .author(blog.getAuthor())
                .image(blog.getImage())
                .summary(blog.getSummary())
                .content(blog.getContent())
                .tags(blog.getTags())
                .build();
    }

    public BlogResponse getBlogById(String id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found for id: " + id));
        return mapToBlogResponse(blog);
    }

    public void deleteBlog(String id) {
        blogRepository.deleteById(id);
    }
}
