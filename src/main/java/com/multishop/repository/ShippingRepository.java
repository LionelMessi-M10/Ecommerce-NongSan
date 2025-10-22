package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
