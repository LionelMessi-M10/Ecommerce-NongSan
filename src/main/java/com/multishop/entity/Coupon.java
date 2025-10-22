package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.multishop.enums.CouponApply;
import com.multishop.enums.DiscountType;

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
@Table(name = "coupons")
public class Coupon extends Base {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "code", nullable = false, unique = true)
    private String code; // Ma giam gia

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue; // ví dụ: 10.0 cho 10%, 50000.0 cho 50K

    @Column(name = "min_order_amount", precision = 10, scale = 2)
    private BigDecimal minOrderAmount; // giá trị tổi thiểu đơn hàng để áp dụng

    @Column(name = "max_order_amount", precision = 10, scale = 2)
    private BigDecimal maxOrderAmount; // giá trị tổi đa đơn hàng để áp dụng

    @Column(name = "usage_limit")
    private Integer usageLimit; // Số lần sử dụng, nếu không có thì dùng vô hạn

    @Column(name = "used_count")
    private Integer usedCount = 0; // Số lần sử dụng coupon

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_apply")
    private CouponApply couponApply; // Phạm vi áp dụng coupon

    @Column(name = "applies_to_id")
    private Long appliesToId; // Id áp dụng, có thể là category_id / shop_id / product_id 

    @OneToMany(mappedBy = "coupon")
    private List<CouponUser> usages;
}
