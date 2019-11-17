package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query("FROM Comment c WHERE c.postId = ?1")
    public Iterable<Comment> listCommentsByPostId(int postId);

    @Query("FROM Comment c WHERE c.userId = ?1")
    public Iterable<Comment> listCommentsByUserId(int userId);

}
