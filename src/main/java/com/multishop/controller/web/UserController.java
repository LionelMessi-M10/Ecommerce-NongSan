package com.multishop.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.converter.UserConverter;
import com.multishop.entity.User;
import com.multishop.model.request.UserRequest;
import com.multishop.model.response.UserResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/web")
public class UserController {
  
  private final UserService userService;
  private final UserConverter userConverter;

  @GetMapping("/users/profile")
  public ResponseEntity<?> getProfile() {
    User user = userService.getCurrentUser();
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "User's profile", userConverter.convertToResponse(user)));
  }
  
  @PostMapping("/users/profile/{id}")
  public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody(required = false) UserRequest userRequest) {
      UserResponse userResponse = userService.updateUser(id, userRequest);
      return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Update user's profile sucessfully !", userResponse));
  }
  

}
