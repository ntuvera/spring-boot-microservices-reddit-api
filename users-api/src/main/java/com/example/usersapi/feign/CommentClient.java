package com.example.usersapi.feign;

import com.example.usersapi.bean.CommentBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="comment")
public interface CommentClient {
    // TODO: is this really necessary?  depends on UI -- do I need to open a post to see comments?
    @GetMapping(value="/list")
    List<CommentBean> getAllComments();

    @GetMapping(value="/user/comment")
    List<CommentBean> getAllCommentsByUser();
}
