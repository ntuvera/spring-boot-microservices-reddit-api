package com.example.commentsapi.mq;

import com.example.commentsapi.service.CommentServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class Receiver {
    @Autowired
    private CommentServiceImpl commentService;

    @RabbitHandler
    public void receive(String msg) throws InterruptedException {
        System.out.println("postId received to delete: " + msg);
        commentService.deleteByPostId(Integer.parseInt(msg));
    }

//    @RabbitHandler
//    public void receivePostId(int postId) throws InterruptedException {
//        System.out.println("message received: " + postId);
//    }
}
