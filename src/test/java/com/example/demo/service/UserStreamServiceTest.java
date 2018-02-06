package com.example.demo.service;

import static java.lang.System.out;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.demo.AbstractTestHelper;
import com.example.demo.repository.data.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.time.Instant;
import java.util.Calendar;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@Slf4j
class UserStreamServiceTest extends AbstractTestHelper {

  @Test
  void save() {
    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DATE, 1);
    try {
      mockMvc.perform(post("/api/user/v1/save").contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(ImmutableList.of(
              User.builder().firstName("Arun").lastName("Rajan").userId("n590502")
                  .emails(ImmutableSet.of("a.r@jp.com", "ak.r@me.com"))
                  .scores(ImmutableList.of(100, 90, 80, 70, 60, 50)).todo(ImmutableMap
                  .of(Calendar.getInstance().getTime(), "Coding against Cassandra",
                      tomorrow.getTime(), "Code for Reactive Streams")).build()))))
          .andReturn();
    } catch (Exception e) {
      log.error("Exception occurred while writing into/reading from JSON String", e);
    }
  }

  @RepeatedTest(1)
  void getAll() {
    Stream.iterate(0, integer -> integer + 1).limit(5).forEach(integer -> {
      ResponseSpec responseSpec = webTestClient.get().uri("/api/user/v1/getAll")
          .accept(MediaType.APPLICATION_JSON).exchange()
          .expectStatus().isOk();
      FluxExchangeResult<User> response = responseSpec.returnResult(User.class);
      log.info("Subscribing for results at {} ", Instant.now());
      response.getResponseBody().collectList()
          .subscribe(users -> log.info("Retrieved at {} Printing users :: {} ",
              Instant.now(), users));
    });
  }

  @RepeatedTest(1)
  void getByStandardId() {
    Stream.iterate(0, integer -> integer + 1).limit(5).forEach(integer -> {
      ResponseSpec responseSpec = webTestClient.get().uri(
          uriBuilder -> uriBuilder.path("/api/user/v1/getBySID")
              .queryParam("standardId", "n590502").build())
          .accept(MediaType.APPLICATION_JSON).exchange()
          .expectStatus().isOk();
      FluxExchangeResult<User> response = responseSpec.returnResult(User.class);
      log.info("Subscribing for results at {} ", Instant.now());
      response.getResponseBody().collectList()
          .subscribe(users -> log.info("Retrieved at {} Printing users :: {} ",
              Instant.now(), users));
    });
  }

}