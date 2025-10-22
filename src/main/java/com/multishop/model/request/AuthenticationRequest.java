package com.multishop.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

	@NotBlank(message = "Email không được để trống")
	private String email;

	@NotBlank(message = "Password không được để trống")
	private String password;
}
