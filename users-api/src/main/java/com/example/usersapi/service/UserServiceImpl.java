package com.example.usersapi.service;

import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User signUpUser(User newUser) {
        return userRepository.save(newUser);
    }

    // TODO: finish after auth checks
    @Override
    public User loginUser(User foundUser) {
        return userRepository.findByUsername(foundUser.getUsername());
    }

    @Override
    public Iterable<User> listAll() {
        return userRepository.findAll();
    }
}
