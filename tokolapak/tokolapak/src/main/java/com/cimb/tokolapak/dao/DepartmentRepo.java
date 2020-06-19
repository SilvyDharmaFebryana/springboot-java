package com.cimb.tokolapak.dao;

import com.cimb.tokolapak.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository; 

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    public Department findByName(String name);

	public void deleteById(Department findDepartment);
}