package com.thbs.Spring.Project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thbs.Spring.Project.entity.Employee;
import com.thbs.Spring.Project.exception.EmployeeNotExistsException;
import com.thbs.Spring.Project.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getAllEmployeesTest() throws Exception {
        List<Employee> employeeList=new ArrayList<>();
        Employee e1=new Employee(101,"Rama","Finance");
        Employee e2= new Employee(201,"Ram","Marketing");
        Employee e3= new Employee(301,"Sita","Marketing");
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        when(employeeService.getAll()).thenReturn(employeeList);
//        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/employee/emp").accept(MediaType.APPLICATION_JSON)).andReturn();
//        int status=result.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(),status,"Success");
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/emp").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(3)));
    }

    @Test
    public void addEmployeeTest() throws Exception {
        Employee e1=new Employee(101,"Rama","Finance");
        String body= (new ObjectMapper()).valueToTree(e1).toString();
        when(employeeService.addEmployee(e1)).thenReturn(e1);
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/employee/emp").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(body)).andReturn();
        int status=result.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(),status,"Successfully added");
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        List<Employee> employeeList=new ArrayList<>();
        Employee e1=new Employee(101,"Ram","Finance");
        employeeList.add(e1);

//        positive test case
//        when(employeeService.getEmployeeById(101)).thenReturn(e1);
//        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/employee/emp/{eid}",101).accept(MediaType.APPLICATION_JSON)).andReturn();
//        int status=result.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(),status,"Success");

//        negative test case
        when(employeeService.getEmployeeById(1)).thenReturn(e1);
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/employee/emp/{eid}",1).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status=result.getResponse().getStatus();
        assertNotEquals(HttpStatus.NOT_FOUND.value(),status,"Success");

//        mockMvc.perform(MockMvcRequestBuilders.get("/employee/emp/{eid}",101).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Ram")));

    }

    @Test
    public void updateEmployeeTest() throws Exception {
        Employee e1=new Employee(101,"Darshan","Design");
        String body= (new ObjectMapper()).valueToTree(e1).toString();
        when(employeeService.addEmployee(e1)).thenReturn(e1);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/employee/emp/{eid}", 101).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(body)).andReturn();
        int status=result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(),status,"Successfully updated");

    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        List<Employee> employeeList=new ArrayList<>();
        Employee e1=new Employee(101,"Rama","Finance");
        Employee e2= new Employee(201,"Ram","Marketing");
        employeeList.add(e1);
        employeeList.add(e2);
        when(employeeService.deleteEmployee(201)).thenReturn(null);
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.delete("/employee/emp/{eid}",201).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status=result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(),status,"Successfully deleted");
    }
}
