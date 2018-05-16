package com.example.demo.repository;

import com.example.demo.AbstractTestHelper;
import com.example.demo.repository.data.Employee;
import com.example.demo.repository.jpa.EmployeeRepository;
import com.google.common.collect.ImmutableList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class EmployeeRepositoryTest extends AbstractTestHelper {

  @Autowired
  private EmployeeRepository employeeRepository;
  private ImmutableList<String> organization = ImmutableList.of("CT", "AWM", "IB", "CIO", "CCB");
  private Random random = new Random(organization.size() - 1);

  @Test
  void getAllEmployeeThisIsACustomMethodNameThatUsesQueryAnnotation() {
    createRandomEmployees(10);
    log.info("Printing first employee {} ", employeeRepository.findById(1L));
    String firstNameContains = "A";
    Stream<Employee> printableEmployees = employeeRepository
        .getAllEmployeeThisIsACustomMethodNameThatUserQueryAnnotation(firstNameContains);
    log.info("Printing Random Employees that has firstNameContains {}", firstNameContains);
    printableEmployees.forEach(employee -> log.info("Printing Employees {}", employee));
  }

  @Test
  void getAllByFirstNameLikeOrderByLastName() {
    createRandomEmployees(15);
    employeeRepository.getAllByFirstNameLikeOrderByLastName("a");
  }

  private void createRandomEmployees(int limit) {
    Builder<Employee> randomEmployeeBuilder = Stream.builder();
    Stream.iterate(0, integer -> integer + 1).limit(limit).forEach(integer ->
        randomEmployeeBuilder.add(Employee.builder().firstName("A".concat(RandomString.make()))
            .employeeSID(RandomString.make()).lastName(RandomString.make().concat("A"))
            .organization(organization.get(random.nextInt(organization.size()))).build())
    );
    employeeRepository.saveAll(randomEmployeeBuilder.build().collect(
        Collectors.toList()));//Verify if spring-data has support for streaming input for persist
  }

}