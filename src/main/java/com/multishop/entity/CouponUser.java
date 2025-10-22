package com.multishop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_coupons")
public class CouponUser extends Base { // Coupon của người dùng
    
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "is_used")
    private Boolean isUsed; // check có sử dụng hay không

    @Column(name = "used_at")
    private LocalDateTime usedAt; // null nếu chưa sử dụng
}
