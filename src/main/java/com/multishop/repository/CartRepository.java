package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
