package com.example.postsapi.repository;

import com.example.postsapi.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository <Post, Integer> {
    @Query("FROM posts WHERE user_id=?1")
    public Iterable<Post> findByUserId(int userId);
}
