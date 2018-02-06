package com.example.demo.service;

import com.example.demo.events.EventQueues;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
class RabbitMQServiceTest {

    @Autowired
    private RabbitMQService rabbitMQService;

    @org.junit.jupiter.api.Test
    void send() {
        rabbitMQService.send(EventQueues.TEST, "Hello World Test from Arun!!");
    }
}