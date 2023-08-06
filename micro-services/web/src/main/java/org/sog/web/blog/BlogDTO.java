package org.sog.web.blog;

import org.sog.web.user.User;

import java.util.List;

public class BlogDTO {
    private String id;
    private String title;
    private String date;
    private User author;
    private String image;
    private String summary;
    private String content;
    private List<String> tags;
}
