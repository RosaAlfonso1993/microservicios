package io.redbee.socialnetwork.feeds.posts.postsmappers;

import lombok.Value;

@Value
public class PostResponse {
    Integer id;
    Integer userId;
    String content;
    String status;
}
