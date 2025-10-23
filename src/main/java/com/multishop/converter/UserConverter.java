package com.multishop.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.multishop.entity.Role;
import com.multishop.entity.User;
import com.multishop.enums.AuthProvider;
import com.multishop.enums.ERole;
import com.multishop.model.dto.AddressDTO;
import com.multishop.model.request.UserRequest;
import com.multishop.model.response.UserResponse;
import com.multishop.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class UserConverter {

	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder byBCryptPasswordEncoder;
	private final RoleRepository roleRepository;
	private final AddressConverter addressConverter;

	public User covertToEntity(UserRequest userRequest) {
		User user = modelMapper.map(userRequest, User.class);

		user.setPassword(byBCryptPasswordEncoder.encode(userRequest.getPassword()));

		if (!userRequest.getProvider().isEmpty())
			user.setProvider(AuthProvider.valueOf(userRequest.getProvideId()));

		Set<Role> roles = new HashSet<>();
		if (userRequest.getRoleCode() != null && !userRequest.getRoleCode().isEmpty()) {
			Role role = roleRepository.findByCode(ERole.valueOf(userRequest.getRoleCode()))
					.orElseThrow(() -> new RuntimeException("Role not found: " + userRequest.getRoleCode()));
			roles.add(role);
		} else {
			Role roleDefault = roleRepository.findByCode(ERole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Role not found: " + ERole.ROLE_CUSTOMER.name()));
			roles.add(roleDefault);
		}
		user.setRoles(roles);

		return user;
	}

	public UserResponse convertToResponse(User user) {
		UserResponse userResponse = modelMapper.map(user, UserResponse.class);

		userResponse.setProvider(user.getProvider().name());
		userResponse
				.setRoleCode(user.getRoles().stream().map(item -> item.getCode().name()).collect(Collectors.toList()).get(0));

		List<AddressDTO> addresses = new ArrayList<>();
		user.getAddresses().forEach(item -> addresses.add(addressConverter.convertToDTO(item)));
		userResponse.setAddresses(addresses);

		return userResponse;
	}

}
