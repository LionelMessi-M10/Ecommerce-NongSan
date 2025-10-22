package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.CouponUser;

@Repository
public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {

}
