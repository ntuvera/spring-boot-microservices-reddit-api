package com.example.apigateway.repository;

import com.example.apigateway.bean.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    private UserBean tempUser;
    private UserBean foundUser;

    @InjectMocks
    private UserRepository userRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private RowMapper<UserBean> rowMapper;

    @Before
    public void initUser() {
        tempUser = new UserBean(1,"iam@batman.com", "batman", "bat");
    }

    @Test
    public void getUserByUsername_User_Success() {
        System.out.println(tempUser.getUsername());
        String sql = "SELECT * FROM users WHERE username = ?";
        when(jdbcTemplate.queryForObject(anyString(), any(), any(RowMapper.class))).thenReturn(tempUser);
        System.out.println(foundUser);


        foundUser = userRepository.getUserByUsername(tempUser.getUsername());

        assertEquals(tempUser.getUsername(), foundUser.getUsername());
        assertEquals(tempUser.getEmail(), foundUser.getEmail());
        assertEquals(tempUser.getPassword(), foundUser.getPassword());
    }

    @Test
    public void getUserByUsername_User_Failure() {
        when(jdbcTemplate.queryForObject(anyString(), any(), any(RowMapper.class))).thenReturn(null);

        foundUser = userRepository.getUserByUsername(tempUser.getUsername());

        assertNull(foundUser);
    }
}
