package com.example.commentsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class CommentsApiApplication {

	@GetMapping("/")
    public String home(){
		return "GET /comment/";
	}
// TODO: Remove after testing ^

	public static void main(String[] args) {
		SpringApplication.run(CommentsApiApplication.class, args);
	}

}
