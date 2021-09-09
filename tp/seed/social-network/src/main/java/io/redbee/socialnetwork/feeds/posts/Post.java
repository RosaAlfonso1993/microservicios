package io.redbee.socialnetwork.feeds.posts;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class Post {
    private final Integer id;
    private final Integer userId;
    private final String content;
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;
}
