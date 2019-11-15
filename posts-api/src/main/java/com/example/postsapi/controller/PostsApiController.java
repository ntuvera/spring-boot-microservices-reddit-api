package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsApiController {
    @Autowired
    PostServiceImpl postService;

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
}
