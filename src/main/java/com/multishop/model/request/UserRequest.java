package com.multishop.model.request;

import java.time.LocalDateTime;
import java.util.List;

import com.multishop.model.dto.AddressDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

	private Long id;

	@NotBlank(message = "Username không được để trống")
	@Size(min = 3, max = 20, message = "Username phải từ 3-20 ký tự")
	private String userName;

	@Email(message = "Email không hợp lệ")
	private String email;
	
	private String provider;
	private String provideId;
	private String phoneNumber;

	private String password;
	
	@NotBlank(message = "Password không được để trống")
	@Size(min = 6, message = "Password tối thiểu 6 ký tự")
	private String newPassword;

	private Byte gender;
	private String avatarUrl;
	private LocalDateTime dateOfBirth;
	private Boolean isEmailVerified;
	private Boolean isPhoneVerified;
	private String roleCode;
	private Integer status;
	private List<AddressDTO> addresses;

}
