package com.example.apigateway.bean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {
    private UserBean newUser;
    private UserBean secondUser;

    @InjectMocks
    UserBean user;

    @Before
    public void initUserBean() {
        newUser = new UserBean(1, "iam@batman.com", "Batman", "bat");
        secondUser = new UserBean();
    }

    @Test
    public void getUsername() {
        assertEquals("Batman", newUser.getUsername());
    }

    @Test
    public void setUsername() {
        newUser.setUsername("Robin");
        assertEquals("Robin", newUser.getUsername());
    }

    @Test
    public void getId() {
        assertEquals(1, newUser.getId());
        assertEquals(0, secondUser.getId());
    }

    @Test
    public void setId() {
        newUser.setId(2);
        assertEquals(2, newUser.getId());
    }

    @Test
    public void getPassword() {
        assertEquals("bat", newUser.getPassword());
        assertEquals(null, secondUser.getPassword());
    }

    @Test
    public void setPassword() {
        newUser.setPassword("tab");
        assertEquals("tab", newUser.getPassword());
    }


    @Test
    public void getEmail() {
        assertEquals("iam@batman.com", newUser.getEmail());
        assertEquals(null, secondUser.getPassword());
    }

    @Test
    public void setEmail() {
        newUser.setEmail("iambatman@gmail.com");
        assertEquals("iambatman@gmail.com", newUser.getEmail());
    }

}
