package com.example.usersapi.controller;

import com.example.usersapi.bean.CommentBean;
import com.example.usersapi.bean.PostBean;
import com.example.usersapi.feign.CommentClient;
import com.example.usersapi.feign.PostClient;
import com.example.usersapi.model.User;
import com.example.usersapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersApiController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    PostClient postClient;

    @Autowired
    CommentClient commentClient;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody User newUser){
        return ResponseEntity.ok(userService.signUpUser(newUser));
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        return ResponseEntity.ok(userService.loginUser(user));
    }
    @GetMapping("/list")
    public Iterable<User> listAllUsers(){
        return userService.listAll();
    }

    // Feign Client to post service routes
    @GetMapping("/post")
    public List<PostBean> listPostsByUser(@RequestHeader("userId") Integer userId){
        return postClient.getAllPostsByUser();
    }

    @GetMapping("/comment")
    public List<CommentBean> listCommentsByUser(@RequestHeader("userId") Integer userId) {
        return commentClient.getAllCommentsByUser();
    }
}
