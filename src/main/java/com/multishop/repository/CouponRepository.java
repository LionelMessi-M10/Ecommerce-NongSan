package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
