package com.thbs.Spring.Project.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    int employeeId;
    String name;
    String designation;

    public Employee() {
    }

    public Employee(int employeeId, String name, String designation) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
    }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
