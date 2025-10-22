package com.multishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multishop.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
