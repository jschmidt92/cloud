package org.sog.web.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sog.web.user.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private String id;
    private String title;
    private String date;
    private User author;
    private String image;
    private String summary;
    private String content;
    private List<String> tags;
}
