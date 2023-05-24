package com.SpringMockito.services;



import com.SpringMockito.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    int sumSalaryByDepartment(int department);
    int minSalaryByDepartment(int department);
    int maxSalaryByDepartment(int department);
    List<Employee> allEmployeesByDepartment(int department);
    Map<Integer, List<Employee>> findAll();
}

