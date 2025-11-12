package com.multishop.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.multishop.converter.UserConverter;
import com.multishop.entity.User;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.UserRequest;
import com.multishop.model.response.UserResponse;
import com.multishop.repository.UserRepository;
import com.multishop.service.CartService;
import com.multishop.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final CartService cartService;
	private final UserConverter userConverter;

	@Transactional
	@Override
	public void registerAccount(UserRequest userRequest) {
		User newUser = userConverter.covertToEntity(userRequest);
		newUser = this.userRepository.saveAndFlush(newUser);
		cartService.createCartForUser(newUser);
	}

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
	}

	@Override
	public List<UserResponse> findUsersByStatus(Byte status) {
		List<User> users = userRepository.findByStatus(status);
		if (users != null && !users.isEmpty()) {
			return users.stream()
					.map(userConverter::convertToResponse)
					.toList();
		}
		return null;
	}

	@Override
	public Boolean checkExistUserByEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Transactional
	@Override
	public UserResponse updateUser(Long id, UserRequest userRequest) {
		if (userRepository.findById(id).orElse(null) == null) {
			throw new ResourceNotFoundException("User not found by id: " + id + " to update !");
		}
		User updateUser = userConverter.covertToEntity(userRequest);
		userRepository.saveAndFlush(updateUser);
		return userConverter.convertToResponse(updateUser);
	}

}
