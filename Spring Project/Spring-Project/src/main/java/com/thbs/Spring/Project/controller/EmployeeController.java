package com.thbs.Spring.Project.controller;

import com.thbs.Spring.Project.entity.Employee;
import com.thbs.Spring.Project.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/emp")
    public List<Employee> getAllEmp(){
        LOG.info("Get all employees");
        return employeeService.getAll();
    }
    @PostMapping("/emp")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee emp) {
        LOG.info("employee added " + emp.toString());
        Employee empTemp = employeeService.addEmployee(emp);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee added successfully.");
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<Employee>(empTemp, headers, status);
    }
    @GetMapping("/emp/{eid}")
    public ResponseEntity<Employee> getEmpById(@PathVariable(name = "eid") int eid) {
        LOG.info("getEmpById " + eid);
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(eid), HttpStatus.OK);
    }
    @PutMapping("/emp/{eid}")
    public ResponseEntity<Employee> updateEmpById(@RequestBody Employee emp) {
        LOG.info("employee updated"+ emp);
        return new ResponseEntity<Employee>(employeeService.updateEmployeeById(emp),HttpStatus.OK);
    }
    @DeleteMapping("/emp/{eid}")
    public Employee deleteEmp(@PathVariable(name = "eid") int eid) {
        LOG.info("employee deleted " + eid);
        return employeeService.deleteEmployee(eid);
    }
}
