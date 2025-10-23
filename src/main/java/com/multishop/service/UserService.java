package com.multishop.service;

import java.util.List;

import com.multishop.entity.User;
import com.multishop.model.request.UserRequest;
import com.multishop.model.response.UserResponse;

public interface UserService {

	void registerAccount(UserRequest userRequest);

	User getCurrentUser();

	Boolean checkExistUserByEmail(String email);

	List<UserResponse> findUsersByStatus(Byte status);

	UserResponse updateProfile(UserRequest userRequest);
}
