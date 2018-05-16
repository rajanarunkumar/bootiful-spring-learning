package com.example.demo.repository.jpa;

import com.example.demo.repository.data.Employee;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Stream<Employee> getAllByFirstNameLikeOrderByLastName(String firstName);

  @Query(value = "SELECT e FROM Employee e WHERE e.firstName LIKE %:firstName% ")
  Stream<Employee> getAllEmployeeThisIsACustomMethodNameThatUserQueryAnnotation(
      @Param("firstName") String firstName);
}
