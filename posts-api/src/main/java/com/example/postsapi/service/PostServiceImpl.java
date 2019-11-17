package com.example.postsapi.service;

import com.example.postsapi.bean.UserBean;
import com.example.postsapi.feign.UserClient;
import com.example.postsapi.model.Post;
import com.example.postsapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.function.Consumer;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserClient userClient;

    @Override
    public Post createPost(Post newPost, int userId) {
        newPost.setUser_id(userId);
        return postRepository.save(newPost);
        // TODO: Better confirmation of success/failure to post
    }

    @Override
    public String deletePost(int postId) {
       postRepository.deleteById(postId);
       return "post delete triggered";
    }

    @Override
    public Iterable<Post> listPosts() {
        Iterable<Post> allPosts = new ArrayList<Post>();
        allPosts = postRepository.findAll();
        allPosts.forEach((post) -> {
            UserBean fetchedUser = userClient.getUserById(post.getUser_id());
            if(fetchedUser != null) {
                post.setUser(fetchedUser);
            } else {
//                UserBean missingUser = new UserBean("deleted");
                post.setUser(new UserBean("User has been Deleted"));
            }
        });

        return allPosts;
    }

    @Override
    public Iterable<Post> getPostByUserId(int userId) {
        Iterable<Post> foundUserPosts = postRepository.findAll();
        foundUserPosts.forEach((post) -> {
            UserBean fetchedUser = userClient.getUserById((post.getUser_id()));
            if(fetchedUser !=null) {
                post.setUser(fetchedUser);
            }
        });
        return postRepository.findByUserId(userId);
    }
}
