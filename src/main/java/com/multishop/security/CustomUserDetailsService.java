package com.multishop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.multishop.entity.*;
import com.multishop.entity.User;
import com.multishop.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserPermissionRepository userPermissionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Build granted authorities:
        // - role names prefixed "ROLE_..."
        // - permission codes prefixed "PERM_..." (effective permissions)
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Add roles (system/shop) from userRoles
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        userRoles.stream()
                .map(ur -> new SimpleGrantedAuthority(ur.getRole().getCode().name()))
                .forEach(authorities::add);

        // Effective permissions from RolePermission (aggregate)
        // collect role permissions
        Set<Permission> rolePerms = userRoles.stream()
            .flatMap(ur -> ur.getRole().getRolePermissions().stream())
            .map(RolePermission::getPermission)
            .collect(Collectors.toSet());

        // Collect user overrides (userPermissions)
        List<UserPermission> userPerms = userPermissionRepository.findByUserRoleUserId(user.getId());
        Set<Permission> userAllowed = userPerms.stream()
            .filter(UserPermission::isAllowed)
            .map(UserPermission::getPermission)
            .collect(Collectors.toSet());

        // Merge: rolePerms U userAllowed (you can add logic to remove denied)
        Set<Permission> effectivePerms = new HashSet<>(rolePerms);
        effectivePerms.addAll(userAllowed);

        effectivePerms.stream()
                .map(p -> new SimpleGrantedAuthority("PERM_" + p.getCode()))
                .forEach(authorities::add);

        // Build UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // make sure password is encoded
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
