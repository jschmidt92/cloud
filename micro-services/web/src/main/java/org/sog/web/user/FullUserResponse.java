package org.sog.web.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserResponse {
    private String id;
    private String uid;
    private String username;
    List<Character> characters;
}
