package com.cimb.tokolapak.dao;

import com.cimb.tokolapak.entity.EmployeeAddress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepo extends JpaRepository<EmployeeAddress, Integer> {
    
}