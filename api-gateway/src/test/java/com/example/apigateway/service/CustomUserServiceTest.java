package com.example.apigateway.service;

import com.example.apigateway.bean.UserBean;
import com.example.apigateway.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserServiceTest {
    private UserBean tempUser;
    private List<GrantedAuthority> tempAuthorities;

    @InjectMocks
    private CustomUserService customUserService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetails userDetails;

    @Mock
    PasswordEncoder bCryptPasswordEncoder;

    @Before
    public void init() {
        tempUser = new UserBean(1,
            "iam@Batman.com",
            "Batman",
            "bat");

        tempAuthorities = new ArrayList<GrantedAuthority>();
    }

    @Test
    public void loadUserByUserName_UserDetails_Success() {
        System.out.println(tempUser);
        when(userRepository.getUserByUsername(anyString())).thenReturn(tempUser);
        when(bCryptPasswordEncoder.encode(tempUser.getPassword())).thenReturn("bat");

        System.out.println(tempUser.getUsername());
        UserDetails returnedUserDetails = customUserService.loadUserByUsername(tempUser.getUsername());

        assertEquals(tempUser.getUsername(), returnedUserDetails.getUsername());
        assertEquals(tempUser.getPassword(), returnedUserDetails.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUserName_UserDetails_Failure() {
        System.out.println(tempUser);
        when(userRepository.getUserByUsername(anyString())).thenReturn(null);

        UserDetails returnedUserDetails = customUserService.loadUserByUsername(tempUser.getUsername());

    }

    @Test
    public void getGrantedAuthorities_UserDetails_Success() {
        List<GrantedAuthority> returnedAuthorities = customUserService.getGrantedAuthorities(tempUser);

        assertEquals(tempAuthorities, returnedAuthorities);
    }
}
