package com.test.employee.repository;
import com.test.employee.repository.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void testCreateReadDelete() {
        Employee employee = new Employee("Hayder", "Barrak");

        employeeRepository.save(employee);

        Iterable<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees).extracting(Employee::getFirstName).containsOnly("Hayder");

        employeeRepository.deleteAll();
        Assertions.assertThat(employeeRepository.findAll()).isEmpty();
    }


}
