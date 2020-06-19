package com.cimb.tokolapak.controller;

import com.cimb.tokolapak.dao.DepartmentRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
// import com.cimb.tokolapak.dao.DepartmentRepo;
import com.cimb.tokolapak.entity.Department;
import com.cimb.tokolapak.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping
    public Iterable<Department> getDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable int departmentId) {
        Department findDepartment =  departmentRepo.findById(departmentId).get();

        findDepartment.getEmployees().forEach(employee -> {
            employee.setDepartment(null);
            employeeRepo.save(employee);
        });

        // departmentRepo.deleteById(id);

    }
}