package com.mohammedfares.spring_basics.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohammedfares.spring_basics.comon.Utils;
import com.mohammedfares.spring_basics.dto.UserDto;
import com.mohammedfares.spring_basics.entities.UserEntity;
import com.mohammedfares.spring_basics.repositories.UserRepository;
@Service
public class UserServiceV1 implements IUserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity dejaExist = userRepository.findByEmail(user.getEmail());
		
		if (dejaExist != null) throw new RuntimeException("User Already Exists");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setUserId(utils.generateUserId(25));
		userEntity.setEncryptedPassword("test encrypted password");
		
		UserEntity savedUser = userRepository.save(userEntity);
		
		UserDto feedBack = new UserDto();
		
		BeanUtils.copyProperties(savedUser, feedBack);
		
		return feedBack;
	
	}

	

}
