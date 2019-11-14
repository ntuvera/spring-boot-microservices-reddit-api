package com.example.usersapi.repository;

import com.example.usersapi.model.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository <UserProfile, Integer> {
    @Query(value = "FROM user_profiles up WHERE up.user_id =:userId", nativeQuery=true)
    public Iterable<UserProfile> getUserProfileByUserId(int userId);
    // TODO: what the syntax for a single return would look like? or does it have to be translated to a single
}
