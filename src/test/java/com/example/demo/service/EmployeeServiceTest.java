package com.example.demo.service;

import com.example.demo.AbstractTestHelper;
import com.example.demo.repository.data.Contact;
import com.example.demo.repository.data.ContactType;
import com.example.demo.repository.data.Employee;
import com.example.demo.repository.data.Phone;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class EmployeeServiceTest extends AbstractTestHelper {

    @org.junit.jupiter.api.Test
    void getAll() throws Exception {
        Set<Contact> contacts = new HashSet<>();
        contacts.add(Contact.builder().contact(Phone.builder().contactType(ContactType.PERSONAL).number(2035510080L).build()).build());
        contacts.add(Contact.builder().contact(Phone.builder().contactType(ContactType.WORK).number(3026342239L).build()).build());
        MvcResult addEmployeeResult = mockMvc.perform(post("/api/employee/v1/add").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Employee.builder().firstName("Arun").lastName("Rajan").employeeSID("n590502").employeeContacts(contacts).build())))
                .andReturn();
        System.out.println("Printing added Employee::" + addEmployeeResult.getResponse().getContentAsString());
        MvcResult getAllResult = mockMvc.perform(get("/api/employee/v1/getAll")).andReturn();
        System.out.println("Printing Get All Employee Result:: " + getAllResult.getResponse().getContentAsString());
    }
}