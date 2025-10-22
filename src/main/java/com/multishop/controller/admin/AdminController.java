package com.multishop.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // Endpoint cho phép quản trị viên xem danh sách tất cả người dùng.
    // Chỉ người dùng có quyền ROLE_ADMIN mới được phép truy cập.
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("Displaying all users in the system.");
    }
    
    // Endpoint cho phép quản trị viên xem thông tin chi tiết của một người dùng bất kỳ.
    // Chỉ người dùng có quyền ROLE_ADMIN mới được phép truy cập.
    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getUserDetails(@PathVariable Long userId) {
        return ResponseEntity.ok("Viewing details for user ID: " + userId);
    }

    // Endpoint cho phép quản trị viên xóa một cửa hàng bất kỳ.
    // Chỉ người dùng có quyền ROLE_ADMIN mới được phép truy cập.
    @DeleteMapping("/shops/{shopId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteShop(@PathVariable Long shopId) {
        return ResponseEntity.ok("Shop " + shopId + " has been deleted by an Admin.");
    }
}
