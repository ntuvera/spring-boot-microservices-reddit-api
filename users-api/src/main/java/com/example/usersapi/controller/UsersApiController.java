package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersApiController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/sign")
    public User signUpUser(@RequestBody User newUser){
        return userService.signUpUser(newUser);
    }
    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        return userService.loginUser(user);
    }
    @GetMapping("/user/list")
    public Iterable<User> listAllUsers(){
        return userService.listAll();
    }
}
