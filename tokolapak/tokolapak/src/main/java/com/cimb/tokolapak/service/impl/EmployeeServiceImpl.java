package com.cimb.tokolapak.service.impl;

import com.cimb.tokolapak.dao.EmployeeAddressRepo;
// import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // @Autowired
    // private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeAddressRepo employeeAddressRepo;
    
    @Override
	public void deleteEmployeeAddress(EmployeeAddress employeeAddress) {
		employeeAddress.getEmployee().setEmployeeAddress(null); //putus hubungan dari employee ke address
		employeeAddress.setEmployee(null); // putus hubungan dari address ke employee
		
		employeeAddressRepo.delete(employeeAddress);
    }

    
}