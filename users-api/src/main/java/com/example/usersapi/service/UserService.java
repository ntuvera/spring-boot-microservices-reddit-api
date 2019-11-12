package com.example.usersapi.service;

import com.example.usersapi.model.User;

public interface UserService {
    public User signUpUser(User newUser);
    public User loginUser(User foundUser);
    public Iterable<User> listAll();
}
