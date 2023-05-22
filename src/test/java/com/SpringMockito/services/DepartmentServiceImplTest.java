package com.SpringMockito.services;


import com.SpringMockito.excemptions.DepartmentNotFoundException;
import com.SpringMockito.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    private DepartmentServiceImpl out;

    @BeforeEach
    void setUp() {
        out = new DepartmentServiceImpl(employeeService);
    }

    @Test
    public void testSumSalaryByDepartment() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = new Employee("Egor", "Egorov", 200_000, 2);
        Employee employee3 = new Employee("Oleg", "Olegov", 150_000, 1);

        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        int sum = out.sumSalaryByDepartment(1);
        assertEquals(250_000, sum);

        verify(employeeService, times(2)).findAllEmployees();
    }

    @Test
    public void testMinSalaryByDepartment() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = new Employee("Egor", "Egorov", 200_000, 2);
        Employee employee3 = new Employee("Oleg", "Olegov", 150_000, 1);

        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        int minSalary = out.minSalaryByDepartment(1);
        assertEquals(100_000, minSalary);

        verify(employeeService, times(2)).findAllEmployees();
    }

    @Test
    public void testMaxSalaryByDepartment() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = new Employee("Egor", "Egorov", 200_000, 2);
        Employee employee3 = new Employee("Oleg", "Olegov", 150_000, 1);

        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        int maxSalary = out.maxSalaryByDepartment(1);
        assertEquals(150_000, maxSalary);

        verify(employeeService, times(2)).findAllEmployees();
    }

    @Test
    public void testAllEmployeesByDepartment() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = new Employee("Egor", "Egorov", 200_000, 2);
        Employee employee3 = new Employee("Oleg", "Olegov", 150_000, 1);

        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        List<Employee> employees = out.allEmployeesByDepartment(1);
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee3));

        verify(employeeService, times(1)).findAllEmployees();
    }

    @Test
    public void testFindAll() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = new Employee("Egor", "Egorov", 200_000, 2);
        Employee employee3 = new Employee("Oleg", "Olegov", 150_000, 1);

        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        Map<Integer, List<Employee>> departmentEmployees = out.findAll();
        assertEquals(2, departmentEmployees.size());
        assertEquals(Arrays.asList(employee1, employee3), departmentEmployees.get(1));
        assertEquals(Collections.singletonList(employee2), departmentEmployees.get(2));

        verify(employeeService, times(1)).findAllEmployees();
    }
    @Test
    public void testDepartmentNotFoundException(){
        Employee employee1 = new Employee("Ivan", "Ivanov", 100_000, 1);
        Employee employee3 = new Employee("Oleg", "Olegov", 150_000, 1);

        when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(employee1, employee3));

        assertThrows(DepartmentNotFoundException.class,
                ()-> out.maxSalaryByDepartment(2));
        assertThrows(DepartmentNotFoundException.class,
                ()-> out.minSalaryByDepartment(2));
        assertThrows(DepartmentNotFoundException.class,
                ()-> out.sumSalaryByDepartment(2));
    }

}