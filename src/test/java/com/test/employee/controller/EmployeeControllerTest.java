package com.test.employee.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.test.employee.repository.model.Employee;
import com.test.employee.service.EmployeeService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        Employee employee = new Employee("Hayder", "Barrak");
        Employee employee1 = new Employee("Hayder1", "Barrak1");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);

        Mockito.when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Hayder")))
                .andExpect(jsonPath("$[1].firstName", Matchers.is("Hayder")));
    }

    @Test
    public void testingGetOne() throws Exception {
        Optional<Employee> employee = Optional.of(new Employee(1,"Hayder", "Barrak"));

        Mockito.when(employeeService.findOne(1)).thenReturn(employee);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.is("Hayder")));
    }

    @Test
    public void testingSave() throws Exception {
        Employee employee = new Employee("Hayder", "Barrak");
        Mockito.when(employeeService.save(employee)).thenReturn(employee);

        MvcResult result =   mockMvc.perform(get("/employee"))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
    }





}
