package io.redbee.socialnetwork.feeds.posts.controller;

import io.redbee.socialnetwork.feeds.posts.Post;
import io.redbee.socialnetwork.feeds.posts.PostDao;
import io.redbee.socialnetwork.feeds.posts.postsmappers.PostRequest;
import io.redbee.socialnetwork.feeds.posts.postsmappers.PostResponse;
import io.redbee.socialnetwork.feeds.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPost(){
        return postService.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse postPost(@PathVariable int userId, @RequestBody PostRequest newPost){
        return postService.save(userId,newPost);
    }

    @GetMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getByIdPost(@PathVariable int postId){
        return postService.getById(postId);
    }

    @PutMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@PathVariable int postId, @RequestBody PostRequest postRequest){
        return postService.updateById(postId,postRequest);
    }

    @DeleteMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse deletePost(@PathVariable int postId){
        return postService.deletePost(postId);
    }
}
