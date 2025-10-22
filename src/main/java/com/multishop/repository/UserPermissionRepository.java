package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.UserPermission;
import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
    List<UserPermission> findByUserRoleUserId(Long userId); // via join
    List<UserPermission> findByUserRoleUserIdAndUserRoleShopId(Long userId, Long shopId);
}
