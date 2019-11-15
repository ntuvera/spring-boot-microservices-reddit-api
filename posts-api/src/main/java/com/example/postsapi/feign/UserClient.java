package com.example.postsapi.feign;

import com.example.postsapi.bean.CommentBean;
import com.example.postsapi.bean.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="user")
public interface UserClient {
    @GetMapping(value="/user/{userId}")
    public UserBean getUserByUserId();
}
