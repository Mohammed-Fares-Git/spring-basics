package com.mohammedfares.spring_basics.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohammedfares.spring_basics.dto.UserDto;
import com.mohammedfares.spring_basics.models.UserRequest;
import com.mohammedfares.spring_basics.models.UserResponse;
import com.mohammedfares.spring_basics.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping
	public UserResponse post (@RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		
		UserResponse userResponse = new UserResponse()
		;
		BeanUtils.copyProperties(createdUser, userResponse);
		
		return userResponse;
		
	}

}
