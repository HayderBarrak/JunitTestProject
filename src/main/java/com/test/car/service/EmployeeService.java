package com.test.car.service;

import com.test.car.Configuration.Handlers.RecordNotFoundException;
import com.test.car.Repository.EmployeeRepository;
import com.test.car.Repository.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee = employeeRepository.save(employee);
            return employee;
        } else {
            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

            if (employeeOptional.isPresent()) {
                Employee newEntity = employeeOptional.get();
                newEntity.setFirstName(employee.getFirstName());
                newEntity.setLastName(employee.getLastName());

                newEntity = employeeRepository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("No employee record exist for given id");
            }
        }
    }

    public List<Employee> findAll() {
        List<Employee> result = (List<Employee>) employeeRepository.findAll();

        if (!result.isEmpty()) {
            return result;
        } else {
            return new ArrayList<Employee>();
        }
    }

    public Optional<Employee> findOne(Integer id) {
        Optional<Employee> result = employeeRepository.findById(id);

        if (result.isPresent()) {
            return result;
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }


    public void deleteById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    void deleteAll() {
        employeeRepository.deleteAll();
    }
}
