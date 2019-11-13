package com.example.postsapi.service;

import com.example.postsapi.model.Post;

public interface PostService {
    public Post createPost(Post newPost);
    public String deletePost(int postId);
    public Iterable<Post> listPosts();
    public Iterable<Post> getPostByUserId(int userId);
}
