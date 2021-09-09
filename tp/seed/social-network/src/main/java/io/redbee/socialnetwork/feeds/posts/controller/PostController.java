package io.redbee.socialnetwork.feeds.posts.controller;

import io.redbee.socialnetwork.feeds.posts.Post;
import io.redbee.socialnetwork.feeds.posts.PostDao;
import io.redbee.socialnetwork.feeds.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.get();
    }

    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody Post newPost){
        return new ResponseEntity<>(postService.save(newPost), HttpStatus.CREATED);
    }
}
