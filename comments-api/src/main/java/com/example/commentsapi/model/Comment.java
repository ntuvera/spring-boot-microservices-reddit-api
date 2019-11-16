package com.example.commentsapi.model;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String text;

    @Column(name="post_id")
    private int postId;

    @Column(name="user_id")
    private int userId;

    @Column(name="user")
    private HashMap<String, String> user;

    public Comment() {
    }

    public Comment(String text, int postId, int userId, HashMap user) {
        this.text = text;
        this.postId = postId;
        this.userId = userId;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public HashMap<String, String> getUser() {
        return user;
    }

    public void setUser(HashMap user) {
        this.user = user;
    }
}
