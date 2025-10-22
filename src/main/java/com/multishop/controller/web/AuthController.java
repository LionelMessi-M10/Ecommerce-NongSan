package com.multishop.controller.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.request.AuthenticationRequest;
import com.multishop.model.request.UserRequest;
import com.multishop.payload.ApiResponse;
import com.multishop.security.CustomUserDetailsImpl;
import com.multishop.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailsImpl customUserDetailsImpl;
  private final JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    final UserDetails userDetails = customUserDetailsImpl.loadUserByUsername(request.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Login successfully !", jwt));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
      
      return null;
  }
  

}