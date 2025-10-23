package com.multishop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

  Optional<District> findByDistrictId(Integer districtId);

}
