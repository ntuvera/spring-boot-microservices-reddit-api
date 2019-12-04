package com.example.usersapi.controller;

import com.example.usersapi.bean.CommentBean;
import com.example.usersapi.bean.PostBean;
import com.example.usersapi.exception.InvalidArgumentException;
import com.example.usersapi.exception.NoMatchingUserFoundException;
import com.example.usersapi.exception.UserAlreadyExistsException;
import com.example.usersapi.exception.UserNotFoundException;
import com.example.usersapi.feign.CommentClient;
import com.example.usersapi.feign.PostClient;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.service.UserProfileServiceImpl;
import com.example.usersapi.service.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@Api(value = "Users' endpoints")
public class UsersApiController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserProfileServiceImpl userProfileService;

    @Autowired
    PostClient postClient;

    @Autowired
    CommentClient commentClient;


    @ApiOperation(value = "Post with User Object to create New User",response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully signed up a user"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@ApiParam(example = "{\t\"email\" : \"wonderwoman@superhero.com\",\n" +
            "\t\"password\" : \"wonder\",\n" +
            "\t\"username\" : \"wonderwoman\",\n" +
            "\t\"userRole\": {\n" +
            "\t\t\"name\": \"ROLE_ADMIN\"\n" +
            "\t}}") @Valid @RequestBody User newUser) throws UserAlreadyExistsException, UserNotFoundException, InvalidArgumentException {
        return ResponseEntity.ok(userService.signUpUser(newUser));
    }

    @ApiOperation(value = "Post to Log in with an existing User", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in"),
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException, InvalidArgumentException {
        if(userService.loginUser(user) != null) {
            return ResponseEntity.ok(userService.loginUser(user));
        }


        Map<String,String> response = new HashMap<String, String>();
        response.put("httpStatus", "BAD_REQUEST");
        response.put("message", "Username or Password is incorrect");
        response.put("timestamp", String.valueOf(new Date().getTime()));
        return ResponseEntity.badRequest().body(response);
    }

    @ApiOperation(value = "Retrieves a list of all Users available", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Users"),
            @ApiResponse(code = 401, message = "You are unauthorized to view this list -- Admin level access needed"),
    })
    @GetMapping("/list")
    public Iterable<User> listAllUsers(){
        return userService.listAll();
    }

    // Feign Client to post service routes
    @ApiOperation(value = "Retrieves a list of a User's Posts", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Posts"),
            @ApiResponse(code = 401, message = "You are unauthorized to view this list"),
    })
    @GetMapping("/post")
    public List<PostBean> listPostsByUser(@ApiParam(value="userId", hidden=true, required=false) @RequestHeader("userId") Integer userId){
        return postClient.getAllPostsByUser();
    }

    @ApiOperation(value = "Retrieves a list of a User's Comments", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of Comments"),
            @ApiResponse(code = 401, message = "You are unauthorized to view this list"),
    })
    @GetMapping("/comment")
    public List<CommentBean> listCommentsByUser(@ApiParam(value="userId", hidden=true, required=false) @RequestHeader("userId") Integer userId) {
        return commentClient.getAllCommentsByUser();
    }

    @ApiOperation(value = "Retrieves User's Profile", response = UserProfile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved your profile"),
            @ApiResponse(code = 401, message = "You are unauthorized to view a profile, please log in"),
    })
    @GetMapping("/profile")
    public UserProfile getUserProfile(@ApiParam(value="userId", hidden=true, required=false) @RequestHeader("userId") String userId){
        return userProfileService.getUserProfile(Integer.parseInt(userId));
    }

    @ApiOperation(value = "Create or Update your profile", response = UserProfile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created/updated your profile"),
            @ApiResponse(code = 401, message = "You are unauthorized to edit a profile, please log in"),
    })
    @PostMapping("/profile")
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile, @ApiParam(value="userId", hidden=true, required=false) @RequestHeader("userId") int userId) throws UserNotFoundException {
        return userProfileService.createProfile(userProfile, userId);
    }

    // FeignClient Routes for inter-service communication
    @ApiOperation(value = "Route for Feign call and retrieving a user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created/updated your profile"),
            @ApiResponse(code = 401, message = "You are unauthorized to edit a profile, please log in"),
    })
    @GetMapping("/identify/{userId}")
    public Optional<User> findUserById(@PathVariable int userId) throws NoMatchingUserFoundException {
        return userService.findById(userId);
    }
}
