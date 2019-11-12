package com.example.usersapi.service;

import com.example.usersapi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User signUpUser(User newUser);
    public User loginUser(User foundUser);
    public Iterable<User> listAll();
}
