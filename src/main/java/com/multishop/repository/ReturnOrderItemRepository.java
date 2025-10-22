package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.ReturnOrderItem;

@Repository
public interface ReturnOrderItemRepository extends JpaRepository<ReturnOrderItem, Long> {

}
