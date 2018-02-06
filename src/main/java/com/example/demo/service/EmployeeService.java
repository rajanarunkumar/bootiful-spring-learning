package com.example.demo.service;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/v1/add")
    public Employee add(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping(path = "/v1/getAll")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

}
