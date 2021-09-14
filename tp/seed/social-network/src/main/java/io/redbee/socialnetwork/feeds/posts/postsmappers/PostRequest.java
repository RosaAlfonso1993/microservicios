package io.redbee.socialnetwork.feeds.posts.postsmappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class PostRequest {
    private String content;

    @JsonCreator
    public PostRequest(String content) {
        this.content = content;
    }
}
