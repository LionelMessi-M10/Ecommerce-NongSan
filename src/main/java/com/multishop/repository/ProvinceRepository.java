package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

}
