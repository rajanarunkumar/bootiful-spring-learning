package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AMQPMessageReceiver {

  public void receive(String payload) {
    log.info("Printing payload {} ", payload);
  }
}
