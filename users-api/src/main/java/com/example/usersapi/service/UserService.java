package com.example.usersapi.service;

import com.example.usersapi.model.JwtResponse;
import com.example.usersapi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public JwtResponse signUpUser(User newUser);
    public JwtResponse loginUser(User user);
    public Iterable<User> listAll();
}
