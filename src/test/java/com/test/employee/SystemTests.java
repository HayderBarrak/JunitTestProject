package com.test.employee;


import com.test.employee.repository.model.Employee;
import com.test.employee.controller.EmployeeController;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SystemTests {
    @Autowired
    private EmployeeController employeeController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreateReadDelete() {
        String url = "http://localhost:"+port+"/employee";

        Employee employee = new Employee("Hayder", "Barrak");
        ResponseEntity<Employee> entity = restTemplate.postForEntity(url, employee, Employee.class);

        Employee[] employees = restTemplate.getForObject(url, Employee[].class);
        Assertions.assertThat(employees).extracting(Employee::getFirstName).containsOnly("Hayder");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url, Employee[].class)).isEmpty();
    }


}
