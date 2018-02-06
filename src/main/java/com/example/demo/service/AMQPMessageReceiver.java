package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AMQPMessageReceiver {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void receive(String payload) {
        LOGGER.info("Printing payload inside receive :::: " + payload);
    }
}
