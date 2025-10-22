package com.multishop.controller.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    // Endpoint cho phép tạo một cửa hàng mới.
    // Chỉ người dùng có quyền ROLE_SELLER mới được phép truy cập.
    @PostMapping("/shops")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<String> createShop() {
        // Lấy thông tin người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Logic để tạo cửa hàng cho người dùng
        return ResponseEntity.ok("Shop created successfully for user: " + username);
    }

    // Endpoint cho phép người bán xem dashboard của một cửa hàng cụ thể.
    // Phân quyền chi tiết: hasPermission(shopId, 'shop', 'VIEW_SHOP_DASHBOARD')
    @GetMapping("/shops/{shopId}/dashboard")
    @PreAuthorize("hasPermission(#shopId, 'shop', 'VIEW_SHOP_DASHBOARD')")
    public ResponseEntity<String> viewShopDashboard(@PathVariable Long shopId) {
        // Logic để lấy dữ liệu dashboard của cửa hàng
        return ResponseEntity.ok("Viewing dashboard for shop ID: " + shopId);
    }

    // Endpoint cho phép người bán tạo sản phẩm trong một cửa hàng cụ thể.
    // Phân quyền chi tiết: hasPermission(shopId, 'shop', 'CREATE_PRODUCT')
    @PostMapping("/shops/{shopId}/products")
    @PreAuthorize("hasPermission(#shopId, 'shop', 'CREATE_PRODUCT')")
    public ResponseEntity<String> createProduct(@PathVariable Long shopId) {
        // Logic để tạo sản phẩm
        return ResponseEntity.ok("Product created in shop ID: " + shopId);
    }
    
    // Endpoint cho phép xóa sản phẩm trong một cửa hàng cụ thể.
    // Phân quyền chi tiết: hasPermission(shopId, 'shop', 'DELETE_PRODUCT')
    @DeleteMapping("/shops/{shopId}/products/{productId}")
    @PreAuthorize("hasPermission(#shopId, 'shop', 'DELETE_PRODUCT')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long shopId, @PathVariable Long productId) {
        return ResponseEntity.ok("Product " + productId + " deleted from shop " + shopId);
    }
}
