package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.ReturnOrder;

@Repository
public interface ReturnOrderRepository extends JpaRepository<ReturnOrder, Long> {

}
