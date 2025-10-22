package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.OrderStatusLog;

@Repository
public interface OrderStatusLogRepository extends JpaRepository<OrderStatusLog, Long> {

}
