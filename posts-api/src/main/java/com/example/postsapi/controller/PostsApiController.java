package com.example.postsapi.controller;

import com.example.postsapi.bean.CommentBean;
import com.example.postsapi.feign.CommentClient;
import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostsApiController {
    @Autowired
    PostServiceImpl postService;

    @Autowired
    CommentClient commentClient;

    @PostMapping("/")
    public Post createPost(@RequestBody Post newPost, @RequestHeader("userId") int userId) {
        if(newPost.getTitle().trim().length() >0) {
            return postService.createPost(newPost, userId);
        }
        return null;
    }

    @GetMapping("/list")
    public Iterable<Post> getAllPosts() {
        return postService.listPosts();
    }

    @GetMapping("/user/post")
    public Iterable<Post> getPostsByUserId(@RequestHeader("userId") Integer userId) {
        return postService.getPostByUserId(userId);
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return "Post " + postId + " Deleted";
    }

    // Feign Client Comment routing
    @GetMapping("/{postId}/comment")
    public List<CommentBean> addCommentToPost(@PathVariable int postId, @RequestHeader("userId") Integer userId) {
        return commentClient.getCommentsByPostId(postId);
    }

    // Feign Client Get Post By Id
    @GetMapping("identify/{postId}")
    public Optional<Post> findPostById(@PathVariable int postId) {
        return postService.findById(postId);
    }
}
