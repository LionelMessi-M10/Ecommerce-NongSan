package com.multishop.entity;

import java.io.Serial;
import java.util.Set;

import com.multishop.enums.PermissionScope;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
    @Column(nullable = false, length = 100, unique = true)
    private String code; // Mã quyền, ví dụ: "CREATE_PRODUCT", "VIEW_ORDER"

    @Column(length = 500)
    private String name; // Tên hiển thị của quyền, ví dụ: "Tạo/Sửa sản phẩm"
    
    @OneToMany(mappedBy = "permission", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Set<RolePermission> rolePermissions;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PermissionScope scope; // SYSTEM, SHOP

}
