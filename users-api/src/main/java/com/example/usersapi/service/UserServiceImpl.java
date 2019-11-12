package com.example.usersapi.service;

import com.example.usersapi.model.JwtResponse;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
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
    public JwtResponse signUpUser(User newUser) {
        JwtResponse signupResponse = new JwtResponse();
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        if (userRepository.save(newUser) != null) {
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            signupResponse.setJwt(jwtUtil.generateToken(userDetails));
            signupResponse.setUsername(newUser.getUsername());
            signupResponse.setId(newUser.getId());

            System.out.println(signupResponse);

            return signupResponse;
        }
        return null;
    }

    @Override
    public JwtResponse loginUser(User user) {
        JwtResponse loginResponse = new JwtResponse();
        User foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser != null && bCryptPasswordEncoder
                .matches(user.getPassword(), foundUser.getPassword())) {
            UserDetails userDetails = loadUserByUsername(foundUser.getUsername());
            loginResponse.setJwt(jwtUtil.generateToken(userDetails));
            loginResponse.setUsername(foundUser.getUsername());
            loginResponse.setId(foundUser.getId());
            return loginResponse;
        }
        return null;
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
