package com.thbs.Spring.Project.service;

import com.thbs.Spring.Project.entity.Employee;
import com.thbs.Spring.Project.exception.EmployeeAlreadyExistsException;
import com.thbs.Spring.Project.exception.EmployeeNotExistsException;
import com.thbs.Spring.Project.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public List<Employee> getAll(){
        LOG.info("get all employees");
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee emp){
        LOG.info("add employee");
        if(!employeeRepository.existsById(emp.getEmployeeId())) {
            return employeeRepository.save(emp);
        } else
            throw new EmployeeAlreadyExistsException();
    }

    public Employee getEmployeeById(int eid) {
        LOG.info("get employee By Id");
        if (employeeRepository.existsById(eid)) {
            return employeeRepository.findById(eid).get();
        } else
            throw new EmployeeNotExistsException();
    }
    public Employee updateEmployeeById(Employee emp) {
        LOG.info("update employee");
        return employeeRepository.save(emp);
    }
    public Employee deleteEmployee(int eid) {
        LOG.info("delete employee By Id");
        employeeRepository.deleteById(eid);
        return null;
    }
}
