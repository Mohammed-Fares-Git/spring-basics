package com.mohammedfares.spring_basics.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohammedfares.spring_basics.models.UserRequest;
import com.mohammedfares.spring_basics.models.UserResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	@PostMapping
	public UserResponse post (@RequestBody UserRequest userRequest) {
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(userRequest, userResponse);
		
		return userResponse;
		
	}

}
