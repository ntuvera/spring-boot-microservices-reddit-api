package com.example.postsapi.feign;

import com.example.postsapi.bean.CommentBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="comment")
public interface CommentClient {
    @GetMapping(value="/post/2/comment")
    public List<CommentBean> getCommentsByPostId();
}
