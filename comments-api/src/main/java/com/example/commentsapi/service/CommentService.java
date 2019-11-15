package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, int postId, int userId);

    public Iterable<Comment> listCommentsByPostId(int postId);

    public Iterable<Comment> listCommentsByUserId(int userId);

    public String deleteByCommentId(int commentId);

}