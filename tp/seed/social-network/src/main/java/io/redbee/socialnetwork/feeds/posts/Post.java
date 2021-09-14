package io.redbee.socialnetwork.feeds.posts;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder(toBuilder = true)
public class Post {
    private final Integer id;
    private final Integer userId;
    private final String content;
    @With
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;
}
