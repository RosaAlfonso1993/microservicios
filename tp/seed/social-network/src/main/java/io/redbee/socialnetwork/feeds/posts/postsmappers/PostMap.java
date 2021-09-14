package io.redbee.socialnetwork.feeds.posts.postsmappers;

import io.redbee.socialnetwork.feeds.posts.EnumsPost.EnumStatusPost;
import io.redbee.socialnetwork.feeds.posts.Post;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMap {

    public Post postCreateFromMap(int userId , PostRequest postRequest){
        return Post.builder()
                .userId(userId)
                .content(postRequest.getContent())
                .status(EnumStatusPost.CREATED.toString())
                .creationDate(LocalDateTime.now())
                .creationUser("system")
                .modificationDate(LocalDateTime.now())
                .modificationUser("system")
                .build();
    }

    public PostResponse postResponseFromMap(Post post){
        return new PostResponse(post.getId(),post.getUserId(),post.getContent(),post.getStatus());
    }

    public Post postUpdateFromMap(Post post, PostRequest postRequest){
        return post.toBuilder()
                .content(postRequest.getContent())
                .modificationDate(LocalDateTime.now())
                .build();
    }

}
