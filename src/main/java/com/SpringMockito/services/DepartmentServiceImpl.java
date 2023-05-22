package com.SpringMockito.services;


import com.SpringMockito.excemptions.DepartmentNotFoundException;
import com.SpringMockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public int sumSalaryByDepartment(int department) {
        if(this.allEmployeesByDepartment(department).size() == 0){
            throw new DepartmentNotFoundException("Сотрудников в отделе нет / отдела не существует");
        }
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .sum()
                ;
    }

    @Override
    public int minSalaryByDepartment(int department) {
        if(this.allEmployeesByDepartment(department).size() == 0){
            throw new DepartmentNotFoundException("Сотрудников в отделе нет / отдела не существует");
        }
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .min()
                .orElse(0)
                ;
    }

    @Override
    public int maxSalaryByDepartment(int department) {
        if(this.allEmployeesByDepartment(department).size() == 0){
            throw new DepartmentNotFoundException("Сотрудников в отделе нет / отдела не существует");
        }
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0)
                ;
    }

    @Override
    public List<Employee> allEmployeesByDepartment(int department) {
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment()==department).toList()
                ;
    }

    @Override
    public Map<Integer, List<Employee>> findAll() {
        return employeeService.findAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
