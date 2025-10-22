package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.multishop.entity.User;
import com.multishop.enums.AuthProvider;
import com.multishop.model.request.UserRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class UserConverter {

	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder byBCryptPasswordEncoder;
	
	public User covertToEntity(UserRequest userRequest) {
		User user = modelMapper.map(userRequest, User.class);
		
		user.setPassword(byBCryptPasswordEncoder.encode(userRequest.getPassword()));

		if(!userRequest.getProvider().isEmpty()) user.setProvider(AuthProvider.valueOf(userRequest.getProvideId()));
		
		return user;
	}
	
}
