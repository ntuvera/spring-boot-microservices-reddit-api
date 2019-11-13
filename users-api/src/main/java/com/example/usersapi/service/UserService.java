package com.example.usersapi.service;

import com.example.usersapi.model.JwtResponse;
import com.example.usersapi.model.User;

public interface UserService {
    public JwtResponse signUpUser(User newUser);
    public JwtResponse loginUser(User user);
    public Iterable<User> listAll();
}
