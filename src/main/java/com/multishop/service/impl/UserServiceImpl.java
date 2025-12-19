package com.multishop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.multishop.converter.UserConverter;
import com.multishop.entity.Cart;
import com.multishop.entity.User;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.UserRequest;
import com.multishop.model.response.UserResponse;
import com.multishop.repository.CartRepository;
import com.multishop.repository.UserRepository;
import com.multishop.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final UserConverter userConverter;

	@Transactional
	@Override
	public void registerAccount(UserRequest userRequest) {
		User newUser = userConverter.covertToEntity(userRequest);
		newUser = this.userRepository.saveAndFlush(newUser);

		Cart newCart = new Cart();
    newCart.setTotalProduct(0);
    newCart.setTotalPrice(BigDecimal.ZERO);
    newCart.setUser(newUser);
    newCart = cartRepository.save(newCart);
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
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + id + " to update !"));
		userRequest.setPassword(existingUser.getPassword());
		User updateUser = userConverter.covertToEntity(userRequest);
		userRepository.saveAndFlush(updateUser);
		return userConverter.convertToResponse(updateUser);
	}

}
