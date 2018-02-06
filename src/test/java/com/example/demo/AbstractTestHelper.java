package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
@Slf4j
public class AbstractTestHelper {
    protected MockMvc mockMvc = null;
    protected ObjectMapper mapper = new ObjectMapper();
    @Autowired
    protected WebApplicationContext context;
    protected WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:61666").build();

    @PostConstruct
    void initialize() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
