package com.example.usersapi.service;

import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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
