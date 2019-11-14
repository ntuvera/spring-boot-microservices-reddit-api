package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.service.UserProfileServiceImpl;
import com.example.usersapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersApiController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserProfileServiceImpl userProfileService;

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
    @GetMapping("/profile")
    public UserProfile getUserProfile(@RequestHeader("userId") String userId){
        return userProfileService.getUserProfile(Integer.parseInt(userId));
    }
    @PostMapping("/profile")
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile, @RequestHeader("userId") int userId){
        return userProfileService.createProfile(userProfile, userId);
    }
    @DeleteMapping("/profile")
    public UserProfile deleteUserProfile(@RequestBody UserProfile userProfile, @RequestHeader("userId") int userId){
        return userProfileService.deleteProfile(userId);
    }
}
