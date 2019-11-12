package com.example.usersapi.service;

import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import com.example.usersapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    private User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User null");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                bCryptPasswordEncoder.encode(user.getPassword()), true, true, true, true,
                new ArrayList<>());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }
}
