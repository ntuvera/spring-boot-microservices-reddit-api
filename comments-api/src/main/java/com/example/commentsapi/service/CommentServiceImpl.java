package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment, int postId, int userId, String username) {
        HashMap<String, String> usernameMap = new HashMap<>();
        usernameMap.put("username", username);

        comment.setPostId(postId);
        comment.setUserId(userId);

        Comment newComment = commentRepository.save(comment);

        return newComment;
    }

    @Override
    public Iterable<Comment> listCommentsByPostId(int postId) {
        return commentRepository.listCommentsByPostId(postId);
    }

    @Override
    public Iterable<Comment> listCommentsByUserId(int userId) {
        return commentRepository.listCommentsByUserId(userId);
    }

    @Override
    public String deleteByCommentId(int commentId) {
        commentRepository.deleteById(commentId);
        Optional<Comment> foundComment = commentRepository.findById(commentId);
        if (foundComment.isPresent()) {
            return "Delete comment failed";
        }
        return "Delete comment succeeded";
    }
}
