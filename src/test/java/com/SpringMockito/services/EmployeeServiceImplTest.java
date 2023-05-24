package com.SpringMockito.services;

import com.SpringMockito.excemptions.EmployeeAlreadyAddedException;
import com.SpringMockito.excemptions.EmployeeNotFoundException;
import com.SpringMockito.excemptions.InvalidInputException;
import com.SpringMockito.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    void testAddEmployee() {
        Employee employee = out.addEmployee("Ivan", "Ivanov", 100_000, 1);
        assertEquals("Ivan", employee.getFirstName());
        assertEquals("Ivanov", employee.getLastName());
        assertEquals(100_000, employee.getSalary());
        assertEquals(1, employee.getDepartment());
    }

    @Test
    void testFindEmployee() {
        Employee employee1 = out.addEmployee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = out.findEmployee("Ivan", "Ivanov");
        assertEquals(employee1, employee2);

    }

    @Test
    void testFindAllEmployees() {
        Employee employee1 = out.addEmployee("Ivan", "Ivanov", 100_000, 1);
        Employee employee2 = out.addEmployee("Egor", "Egorov", 200_000, 2);
        Collection<Employee> collection = List.of(employee1, employee2);
        assertIterableEquals(collection, out.findAllEmployees());
    }

    @Test
    void testRemoveEmployee() {
        Employee employee = out.addEmployee("Ivan", "Ivanov", 100_000, 1);
        Collection<Employee> collection = new ArrayList<>();
        assertEquals(employee, out.removeEmployee("Ivan", "Ivanov"));
        assertIterableEquals(collection, out.findAllEmployees());
    }

    @Test
    public void testEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class,
                () -> out.removeEmployee("Egor", "Egorov"));
    }

    @Test
    public void testEmployeeAlreadyAddedException() {
        out.addEmployee("Ivan", "Ivanov", 100_000, 1);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee("Ivan", "Ivanov", 100_000, 1));
    }
    @Test
    public void testInvalidInputException(){
        assertThrows(InvalidInputException.class,
                () -> out.addEmployee("Ivan123", "Ivanov", 100_000, 1));
    }
}