package com.example.usersapi.service;

import com.example.usersapi.exception.UserNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.repository.UserProfileRepository;
import com.example.usersapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Override
    public UserProfile createProfile(UserProfile userProfile, int userId) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(userId);

        UserProfile newUserProfile = userProfile;
        newUserProfile.setUserId(userId);
            if(foundUser.isPresent()) {
                foundUser.get().setUserProfile(newUserProfile);
            } else {
                throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "User Not Found");
            }

        logger.info(">>>>>>>>>> " + foundUser.get().getUsername() + " just created their profile! New profile: " + newUserProfile);

        return userProfileRepository.save(userProfile);

    }

    @Override
    public UserProfile updateProfile(UserProfile userProfile, int userId) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(userId);

        UserProfile newUserProfile = userProfile;
        newUserProfile.setUserId(userId);
        if(foundUser.isPresent()) {
            foundUser.get().setUserProfile(newUserProfile);
            userRepository.save(foundUser.get());
        } else {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "User Not Found");
        }
        newUserProfile = userProfileRepository.save(userProfile);

        logger.info(">>>>>>>>>> " + foundUser.get().getUsername() + " just added to their profile! New profile: " + newUserProfile);

        return newUserProfile;
    }

    @Override
    public UserProfile getUserProfile(int userId) {
        if(userProfileRepository.getUserProfileByUserId(userId) == null) {
           UserProfile newProfile = new UserProfile("", "", "", userId);
           userProfileRepository.save(newProfile);
           newProfile.setUser(userRepository.findById(userId));
           return newProfile;
        }

        UserProfile foundUserProfile = userProfileRepository.getUserProfileByUserId(userId);
        foundUserProfile.setUser(userRepository.findById(userId));

        return foundUserProfile;
    }
}
