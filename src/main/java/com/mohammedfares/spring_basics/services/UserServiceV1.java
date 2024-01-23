package com.mohammedfares.spring_basics.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	BCryptPasswordEncoder bCryptPassswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity dejaExist = userRepository.findByEmail(user.getEmail());
		
		if (dejaExist != null) throw new RuntimeException("User Already Exists");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setUserId(utils.generateUserId(25));
		userEntity.setEncryptedPassword(bCryptPassswordEncoder.encode(user.getPassword()));
		
		UserEntity savedUser = userRepository.save(userEntity);
		
		UserDto feedBack = new UserDto();
		
		BeanUtils.copyProperties(savedUser, feedBack);
		
		return feedBack;
	
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
		List<UserDto> dtoUsers =users.stream()
				.map(UserDto::convertFromEntity)
				.collect(Collectors.toList());
		return dtoUsers;
	}

	

}
