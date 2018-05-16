package com.example.demo.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.demo.AbstractTestHelper;
import com.example.demo.repository.data.Contact;
import com.example.demo.repository.data.ContactType;
import com.example.demo.repository.data.Employee;
import com.example.demo.repository.data.Phone;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

@Slf4j
class EmployeeServiceTest extends AbstractTestHelper {

  @org.junit.jupiter.api.Test
  void getAll() throws Exception {
    MvcResult addEmployeeResult = addEmployees(5);
    log.info(
        "Printing added Employee::" + addEmployeeResult.getResponse().getContentAsString());
    MvcResult getAllResult = mockMvc.perform(get("/api/employee/v1/getAll")).andReturn();
    log.info(
        "Printing Get All Employee Result:: " + getAllResult.getResponse().getContentAsString());
  }

  @Test
  void getAllMatching() throws Exception {
    addEmployees(4);
    MvcResult getAllResult = mockMvc
        .perform(get("/api/employee/v1/getAllMatching").param("firstName", "a")).andReturn();
    log.info("Get all matching employees {}", getAllResult.getResponse().getContentAsString());
  }

  private MvcResult addEmployees(int employeeCount) throws Exception {
    Random telephone = new Random();
    Builder<Object> employees = ImmutableSet.builder();
    Stream.iterate(0, i -> i + 1).limit(employeeCount).forEach(
        integer -> {
          Set<Contact> contacts = new HashSet<>();
          contacts.add(Contact.builder().contact(
              Phone.builder().contactType(ContactType.PERSONAL).number(telephone.nextLong())
                  .build()).build());
          contacts.add(Contact.builder().contact(
              Phone.builder().contactType(ContactType.WORK).number(telephone.nextLong()).build())
              .build());
          employees.add(
              Employee.builder().firstName(RandomString.make()).lastName(RandomString.make())
                  .employeeSID(RandomString.make().substring(0, 6)).employeeContacts(contacts)
                  .build());
        }
    );

    return mockMvc.perform(post("/api/employee/v1/addAll").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(employees.build())))
        .andReturn();
  }
}