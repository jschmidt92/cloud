package org.sog.web.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Character {
    private String id;
    private String uid;
    private String name;
}
