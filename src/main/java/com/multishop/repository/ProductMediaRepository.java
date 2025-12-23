package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.ProductMedia;

@Repository
public interface ProductMediaRepository extends JpaRepository<ProductMedia, Long> {

}
