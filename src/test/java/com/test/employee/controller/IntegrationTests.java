package com.test.employee.controller;


import com.test.employee.repository.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTests {

    @Autowired
    EmployeeController employeeController;


    @Test
    public void testCreateReadDelete() {
        Employee employee = new Employee("Hayder", "Barrak");

        Employee employeeResult = employeeController.create(employee);

        Iterable<Employee> employees = employeeController.read();
        Assertions.assertThat(employees).first().hasFieldOrPropertyWithValue("firstName", "Hayder");

        employeeController.delete(employeeResult.getId());
        Assertions.assertThat(employeeController.read()).isEmpty();
    }

}
