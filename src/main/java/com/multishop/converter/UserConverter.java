package com.multishop.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.multishop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.multishop.entity.Address;
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
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final RoleRepository roleRepository;
    private final UserRepository userRepository;
	private final AddressConverter addressConverter;

	public User covertToEntity(UserRequest userRequest) {

        User existingUser = null;
        if (userRequest.getId() != null) existingUser = userRepository.findById(userRequest.getId()).orElse(null);

		User user = modelMapper.map(userRequest, User.class);

		if (userRequest.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
            if (existingUser != null) {
                user.setId(existingUser.getId());
            }
        } else {
            if (existingUser != null) {
                user.setId(existingUser.getId());
                user.setPassword(existingUser.getPassword());
            }
        }

		if (userRequest.getProvider() != null) user.setProvider(AuthProvider.valueOf(userRequest.getProvideId()));

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

		if (userRequest.getAddresses() != null && !userRequest.getAddresses().isEmpty()) {
            List<Address> addresses = new ArrayList<>();
            userRequest.getAddresses().forEach(item -> addresses.add(addressConverter.convertToEntity(item)));
            user.setAddresses(addresses);
        }

		return user;
	}

	public UserResponse convertToResponse(User user) {
		UserResponse userResponse = modelMapper.map(user, UserResponse.class);

		if (user.getProvider() != null) userResponse.setProvider(user.getProvider().name());
		userResponse
				.setRoleCode(user.getRoles().stream().map(item -> item.getCode().name()).toList().getFirst());

		if (user.getAddresses() != null && !user.getAddresses().isEmpty()) {
            List<AddressDTO> addresses = new ArrayList<>();
            user.getAddresses().forEach(item -> addresses.add(addressConverter.convertToDTO(item)));
            userResponse.setAddresses(addresses);
        }

		return userResponse;
	}

}
