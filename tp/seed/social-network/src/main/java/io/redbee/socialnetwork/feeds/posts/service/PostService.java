package io.redbee.socialnetwork.feeds.posts.service;

import io.redbee.socialnetwork.feeds.posts.Post;
import io.redbee.socialnetwork.feeds.posts.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostDao postDao;

    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<Post> get(){
        return this.postDao.get();
    }

    public Post save(Post post){
        return this.postDao.save(post).orElseThrow();
    }
}
