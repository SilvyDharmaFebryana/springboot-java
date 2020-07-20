package com.cimb.tokolapak.controller;

import java.util.Optional;

import com.cimb.tokolapak.dao.DepartmentRepo;
import com.cimb.tokolapak.dao.EmployeeAddressRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.dao.ProjectRepo;
import com.cimb.tokolapak.entity.Department;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.entity.Project;
// import com.cimb.tokolapak.service.DepartmentService;
import com.cimb.tokolapak.service.EmployeeService;
import com.cimb.tokolapak.util.EmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
// @CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeAddressRepo employeeAddressRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private EmailUtil emailUtil;

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {
        emailUtil.sendEmail(employee.getEmail(), "Registrasi Karyawan", "<h1> Selamat ! </h1> \n anda telah bergabung");
        return employeeRepo.save(employee);
    }

    @GetMapping("/")
    public Iterable<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    // @PostMapping Employee 

    @DeleteMapping("/address/{id}")
    public void deleteEmployeeAddressById(@PathVariable int id) {
        Optional<EmployeeAddress> employeeAddress = employeeAddressRepo.findById(id);

        if (employeeAddress.get() == null) {
            throw new RuntimeException("Employee address not found");
        }
        employeeService.deleteEmployeeAddress(employeeAddress.get());
    }

    @PostMapping("/address")
    public EmployeeAddress addEmployee(@RequestBody EmployeeAddress employeeAddress) {
        return employeeAddressRepo.save(employeeAddress);
    }

    @PutMapping("/{employeeId}/address")
    public Employee addAddressToEmployee(@RequestBody EmployeeAddress employeeAddress, @PathVariable int employeeId) {

        Employee findEmployee = employeeRepo.findById(employeeId).get();

        if (findEmployee ==  null) 
            throw new RuntimeException("employee not found");

            findEmployee.setEmployeeAddress(employeeAddress);
            return employeeRepo.save(findEmployee);
    }


    @PostMapping("/department/{departmentId}")
    public Employee addEmployee(@RequestBody Employee employee, @PathVariable int departmentId) {

        Department findDepartment = departmentRepo.findById(departmentId).get();

        if (findDepartment ==  null) 
            throw new RuntimeException("Department not found");

        employee.setDepartment(findDepartment);
        return employeeRepo.save(employee);
    }
    

    @PutMapping("/{employeeId}/department/{departmentId}")
    public Employee addEmployeeToDepartment(@PathVariable int departmentId, @PathVariable int employeeId) {
        Employee findEmployee = employeeRepo.findById(employeeId).get(); //employee yang id nya kita kirim

        if ( findEmployee == null)
            throw new RuntimeException("Employee not found");

        
        Department findDepartment = departmentRepo.findById(departmentId).get();

        if (findDepartment ==  null) 
            throw new RuntimeException("Department not found");

        
        findEmployee.setDepartment(findDepartment);
        return employeeRepo.save(findEmployee);

        // return departmentRepo.findById(departmentId).map(department -> {
        //     findEmployee.setDepartment(department);
        //     return employeeRepo.save(findEmployee);
        // }).orElseThrow(() -> new RuntimeException("department not found"));

    }

    @PostMapping("/{employeeId}/projects/{projectId}")
    public Employee addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        Employee findEmployee = employeeRepo.findById(employeeId).get();

        Project findProject = projectRepo.findById(projectId).get();

        findEmployee.getProjects().add(findProject);

        return employeeRepo.save(findEmployee);
    }
}