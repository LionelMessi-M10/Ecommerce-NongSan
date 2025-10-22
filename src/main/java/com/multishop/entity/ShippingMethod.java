package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
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
@Table(name = "shipping_methods")
public class ShippingMethod extends Base { // Phương thức vận chuyển

    @Serial
    private static final long serialVersionUID = 1L;
	
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name; // ví dụ: "Giao hàng Tiết kiệm", "Giao hàng Nhanh"

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "base_cost", precision = 10, scale = 2)
    private BigDecimal baseCost; // Chi phí cơ bản

    @Column(name = "per_kg_cost", precision = 10, scale = 2)
    private BigDecimal perKgCost; // Chí phí mỗi kg thêm (nếu tính theo trọng lượng)

    @Column(name = "min_delivery_days")
    private Integer minDeliveryDays; // Số ngày giao hàng tối thiểu

    @Column(name = "max_delivery_days")
    private Integer maxDeliveryDays; // Số ngày giao hàng tối đa

    @ManyToMany(mappedBy = "shippingMethods")
    private Set<Shop> shops;

}
