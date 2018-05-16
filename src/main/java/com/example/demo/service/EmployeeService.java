package com.example.demo.service;

import com.example.demo.repository.jpa.EmployeeRepository;
import com.example.demo.repository.data.Employee;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/employee")
@Transactional
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @PostMapping("/v1/addAll")
  public List<Employee> add(@RequestBody List<Employee> employee) {
    return employeeRepository.saveAll(employee);
  }

  @GetMapping(path = "/v1/getAll")
  public List<Employee> getAll() {
    return employeeRepository.findAll();
  }

  @GetMapping("/v1/getAllMatching")
  public List<Employee> getAllMatching(@RequestParam String firstName) {
    List<Employee> streamOfEmployee;
    try (Stream<Employee> employeeStream = employeeRepository
        .getAllByFirstNameLikeOrderByLastName(firstName)) {
      streamOfEmployee = employeeStream.collect(Collectors.toList());
    }
    return streamOfEmployee;
  }
}
