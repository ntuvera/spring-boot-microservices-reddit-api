package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersApiController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/sign")
    public User signUpUser(){
        return userService.signUp();
    }
    @GetMapping("/list")
    public List<User> listAllUsers(){
        return userService.listAll();
    }
}
