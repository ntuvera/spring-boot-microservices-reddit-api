package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import com.example.postsapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post createPost(Post newPost, @RequestHeader("id") int userId) {
        String postTitle = newPost.getTitle();
        String postDescription = newPost.getDescription();
        postRepository.createPost(postTitle, postDescription, userId);
        return newPost;
        // TODO: Better confirmation of success/failure to post
    }

    @Override
    public String deletePost(int postId) {
       postRepository.deleteById(postId);
       return "post delete triggered";
    }

    @Override
    public Iterable<Post> listPosts() {
        return postRepository.findAll();
    }

    @Override
    public Iterable<Post> getPostByUserId(int userId) {
        return postRepository.findByUserId(userId);
    }
}
