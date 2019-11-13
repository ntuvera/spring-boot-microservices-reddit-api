package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import org.springframework.web.bind.annotation.RequestHeader;

public interface PostService {
    public Post createPost(Post newPost, @RequestHeader("id") int userId);
    public String deletePost(int postId);
    public Iterable<Post> listPosts();
    public Iterable<Post> getPostByUserId(int userId);
}
