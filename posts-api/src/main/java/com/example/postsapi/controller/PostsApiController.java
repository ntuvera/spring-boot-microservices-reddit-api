package com.example.postsapi.controller;

import com.example.postsapi.bean.CommentBean;
import com.example.postsapi.feign.CommentClient;
import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsApiController {
    @Autowired
    PostServiceImpl postService;

    @Autowired
    CommentClient commentClient;

    @PostMapping("/")
    public Post createPost(@RequestBody Post newPost, @RequestHeader("userId") int userId) {
        return postService.createPost(newPost, userId);
    }

    @GetMapping("/list")
    public Iterable<Post> getAllPosts() {
        return postService.listPosts();
    }

    @GetMapping("/user") // TOOD: can we explicitly call this from localhost:8080??? ask Isha
//    public Iterable<Post> getPostsByUserId(@RequestHeader("Authorization") int userId) {
    // TODO: Interservice communication FIRST store userId in Token then Grab from header?
    public Iterable<Post> getPostsByUserId(@RequestHeader("userId") Integer userId) {
        return postService.getPostByUserId(userId);
    }

    @DeleteMapping("/{postId}")
    // TODO: Response Entity with message of success of fail
    public String deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return "Delete route tiggered FIX ME Fool";
    }

    // Feign Client Comment routing
    @GetMapping("/{postId}/comment")
    public List<CommentBean> addCommentToPost(@PathVariable int postId, @RequestHeader("userId") Integer userId) {
        return commentClient.getCommentsByPostId();
    }
}
