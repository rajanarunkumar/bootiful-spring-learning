package com.example.demo.repository.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee implements Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @Column(length = 7, name = "EMPLOYEE_SID")
  private String employeeSID;
  @Column(length = 100, name = "PREFERRED_NAME")
  private String preferredName;
  @Column(length = 50, name = "FIRST_NAME")
  private String firstName;
  @Column(length = 50, name = "LAST_NAME")
  private String lastName;
  @Column(name = "ORG")
  private String organization;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "employee")
  private Set<Contact> employeeContacts;
}
