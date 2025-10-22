package com.multishop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.multishop.entity.Role;
import com.multishop.enums.ERole;
import com.multishop.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Chỉ seed nếu chưa có bản ghi nào
        if (roleRepository.count() == 0) {
            seedRole(ERole.ROLE_ADMIN, "Administrator with full access");
            seedRole(ERole.ROLE_SELLER, "Seller can manage their shop and products");
            seedRole(ERole.ROLE_CUSTOMER, "Customer can browse and order products");
            System.out.println("Seeded all roles because DB was empty");
        } else {
            System.out.println("Roles already exist, skipping seeding");
        }
    }

    private void seedRole(ERole roleName, String description) {
        Role role = new Role();
        role.setCode(roleName);
        role.setName(description);
        roleRepository.save(role);
        System.out.println("Seeded role: " + roleName);
    }
}
