package com.example.postsapi.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="user")
public interface UserClient {
//    @GetMapping(value="/user/{userId}")
//    public UserBean getUserByUserId();
}
