package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
