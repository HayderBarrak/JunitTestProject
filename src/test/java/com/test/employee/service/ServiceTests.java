package com.test.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.test.employee.repository.EmployeeRepository;
import com.test.employee.repository.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @InjectMocks
    EmployeeService service;

    @Mock
    EmployeeRepository dao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllEmployees()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee("H1", "H1");
        Employee empTwo = new Employee("H2", "H2");
        Employee empThree = new Employee("H3", "H3");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(dao.findAll()).thenReturn(list);

        //test
        List<Employee> empList = service.findAll();

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }


    @Test
    void testCreateOrSaveEmployee()
    {
        Employee employee = new Employee("Hayder","Barrak");

        service.save(employee);

        verify(dao, times(1)).save(employee);
    }



}



