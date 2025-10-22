package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
