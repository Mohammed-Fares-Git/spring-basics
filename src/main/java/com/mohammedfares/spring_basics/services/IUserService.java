package com.mohammedfares.spring_basics.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mohammedfares.spring_basics.dto.UserDto;
public interface IUserService {
	UserDto createUser(UserDto user);
	List<UserDto> getAllUsers();
}
