package com.test.car.controller;

import com.test.car.Repository.Model.Employee;
import com.test.car.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    Employee create(@RequestBody Employee employee)  {
        return employeeService.save(employee);
    }

    @GetMapping("/employee")
    Iterable<Employee> read() {
        return employeeService.findAll();
    }

    @PutMapping("/employee")
    Employee update(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    void delete(@PathVariable Integer id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/employee/{id}")
    Optional<Employee> getOne(@PathVariable Integer id) {
        return employeeService.findOne(id);
    }


    @GetMapping("/wrong")
    Employee somethingIsWrong() throws ValidationException {
        throw new ValidationException("Something is wrong");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    String exceptionHandler(ValidationException e) {
        return e.getMessage();
    }
}
