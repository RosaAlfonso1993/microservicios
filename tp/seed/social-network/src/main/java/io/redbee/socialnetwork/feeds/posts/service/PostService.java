package io.redbee.socialnetwork.feeds.posts.service;

import io.redbee.socialnetwork.feeds.posts.EnumsPost.EnumStatusPost;
import io.redbee.socialnetwork.feeds.posts.Post;
import io.redbee.socialnetwork.feeds.posts.PostDao;
import io.redbee.socialnetwork.feeds.posts.postsmappers.PostMap;
import io.redbee.socialnetwork.feeds.posts.postsmappers.PostRequest;
import io.redbee.socialnetwork.feeds.posts.postsmappers.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostDao postDao;
    private final PostMap postMap;

    @Autowired
    public PostService(PostDao postDao, PostMap postMap) {
        this.postDao = postDao;
        this.postMap= postMap;
    }

    public List<PostResponse> get(){
        return this.postDao.get()
                .stream()
                .map(postMap::postResponseFromMap)
                .collect(Collectors.toList());
    }

    public PostResponse save(int userId,PostRequest postRequest){
        return this.postDao
                .save(postMap.postCreateFromMap(userId,postRequest))
                .map(postMap::postResponseFromMap)
                .orElseThrow();
    }

    public PostResponse getById(int postId) {
        return postDao.getById(postId)
                .map(postMap::postResponseFromMap)
                .orElseThrow();
    }

    public PostResponse updateById(int postId, PostRequest postRequest) {
        return postDao.getById(postId)
                .map(post -> postMap.postUpdateFromMap(post, postRequest))
                .map(postDao::update)
                .map(postMap::postResponseFromMap)
                .orElseThrow();
    }

    public PostResponse deletePost(int postId) {
        return postDao.getById(postId)
                .map(post -> postDao.update(post.withStatus(EnumStatusPost.DELETED.toString())))
                .map(postMap::postResponseFromMap)
                .orElseThrow();
    }
}
