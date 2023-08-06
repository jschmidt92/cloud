package org.sog.web.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.sog.web.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@Builder
public class Blog {
    @Id
    private String id;
    private String title;
    private String date;
    @DBRef
    private User author;
    private String image;
    private String summary;
    private String content;
    private List<String> tags;
}
