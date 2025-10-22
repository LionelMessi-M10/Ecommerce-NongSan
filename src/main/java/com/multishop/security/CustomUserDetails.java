package com.multishop.security;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.multishop.entity.Permission;
import com.multishop.entity.User;
import com.multishop.enums.AccountStatus;
import com.multishop.enums.PermissionScope;

public class CustomUserDetails implements UserDetails {
	
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getUserRoles() == null) {
            return Set.of();
        }

        return user.getUserRoles().stream()
                .filter(Objects::nonNull)
                .filter(ur -> ur.getRole() != null && ur.getRole().getRolePermissions() != null)
                .flatMap(userRole -> userRole.getRole().getRolePermissions().stream()
                        .filter(Objects::nonNull)
                        .map(rolePerm -> {
                            Permission perm = rolePerm.getPermission();
                            if (perm == null) return null;

                            // Ví dụ: "SHOP:VIEW_ORDER:123" hoặc "SYSTEM:CREATE_USER"
                            if (perm.getScope() == PermissionScope.SHOP && userRole.getShopId() != null) {
                                return new SimpleGrantedAuthority(
                                        perm.getScope() + ":" + perm.getCode() + ":" + userRole.getShopId()
                                );
                            }
                            return new SimpleGrantedAuthority(perm.getScope() + ":" + perm.getCode());
                        })
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }


    @Override
    public String getPassword() { return user.getPassword(); }

    @Override
    public String getUsername() { return user.getEmail(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountStatus() != AccountStatus.INACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return user.getAccountStatus() == AccountStatus.ACTIVE;
    }

    public User getUser() { return user; }
}

