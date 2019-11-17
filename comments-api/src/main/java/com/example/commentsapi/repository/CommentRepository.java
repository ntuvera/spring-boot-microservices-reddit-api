package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query(value="SELECT c.*, p.* FROM comments AS c JOIN posts AS p ON p.id = c.post_id WHERE c.post_id = :postId", nativeQuery = true)
    public Iterable<Comment> listCommentsByPostId(int postId);

    @Query(value="SELECT c.*, u.* FROM comments AS c JOIN users AS u ON c.user_id = u.id WHERE c.user_id = :userId", nativeQuery = true)
    public Iterable<Comment> listCommentsByUserId(int userId);

}
