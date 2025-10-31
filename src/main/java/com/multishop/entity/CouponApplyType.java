package com.multishop.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "apply_coupon_types")
public class CouponApplyType extends Base {

  @Column(name = "apply_type", nullable = true, unique = true)
  private String applyType;

  @Column(name = "apply_name")
  private String applyName;

  @OneToMany(mappedBy = "couponApplyType", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  private List<Coupon> coupons = new ArrayList<>();

}
