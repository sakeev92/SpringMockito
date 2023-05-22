package com.SpringMockito.controllers;


import com.SpringMockito.model.Employee;
import com.SpringMockito.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/salary/sum")
    public int sumSalaryByDepartment(@PathVariable int id) {
        return departmentService.sumSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public int minSalaryByDepartment(@PathVariable int id) {
        return departmentService.minSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public int maxSalaryByDepartment(@PathVariable int id) {
        return departmentService.maxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> allEmployeesByDepartment(@PathVariable int id) {
        return departmentService.allEmployeesByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findAll(){
        return departmentService.findAll();
    }
}
