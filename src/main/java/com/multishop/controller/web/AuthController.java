package com.multishop.controller.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.request.AuthenticationRequest;
import com.multishop.model.request.UserRequest;
import com.multishop.payload.ApiResponse;
import com.multishop.security.CustomUserDetailsService;
import com.multishop.security.JwtUtil;
import com.multishop.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final CustomUserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST, "Incorrect email or password"));
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, jwt, "Login successfully"));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
		// Kiểm tra email đã tồn tại chưa
		if (userService.checkExistUserByEmail(userRequest.getEmail())) {
			return ResponseEntity.badRequest().body("User is already taken !");
		}

		userService.registerAccount(userRequest);

		return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, null, "Resgister user successfully"));
	}
	
}