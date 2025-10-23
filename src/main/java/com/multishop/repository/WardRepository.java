package com.multishop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Ward;


@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

  Optional<Ward> findByWardCode(Integer wardCode);
}
