package com.multishop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Role;
import com.multishop.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByCode(ERole code);
    
}
