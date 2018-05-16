package com.example.demo.config.rxrouters;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.example.demo.repository.cassandra.UserRepository;
import com.example.demo.repository.data.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
@Profile("webflux")
public class UserFeatureRouterConfig {

  @Bean
  public RouterFunction<?> routeForUserFeature(UserRepository userRepository) {
    return route(GET("/api/user/v1/getAll"),
        serverRequest -> ok().body(userRepository.findAll(), User.class))
        .andRoute(GET("/api/user/v1/getBySID"),
            serverRequest -> ok()
                .body(userRepository.findById(serverRequest.queryParam("standardId").get()),
                    User.class))
        .andRoute(POST("/api/user/v1/saveAll"), serverRequest -> ok().body(userRepository.saveAll(
            serverRequest.bodyToFlux(User.class)), User.class))
        ;
  }
}
