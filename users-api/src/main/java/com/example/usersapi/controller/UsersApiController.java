package com.example.usersapi.controller;

import com.example.usersapi.feign.PostClient;
import com.example.usersapi.model.User;
import com.example.usersapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersApiController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    PostClient postClient;

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
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println(postClient.getAllPosts().size());
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println(postClient.getAllPosts().size());
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println(postClient.getAllPosts().size());
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        System.out.println(postClient.getAllPosts().size());
        System.out.println("HEY HEY HEY HYE HEY HEY HEY HEYH HEY");
        return userService.listAll();
    }
}
