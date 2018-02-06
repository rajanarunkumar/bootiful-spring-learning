package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

@Configuration
@EnableWebFlux
public class DemoWebfluxConfig {

  private final ServerProperties serverProperties;

  @Autowired
  public DemoWebfluxConfig(ServerProperties serverProperties) {
    this.serverProperties = serverProperties;
  }

  @Bean
  public NettyContext nettyContext(ApplicationContext context) {
    HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
    HttpServer httpServer = HttpServer.create("localhost", serverProperties.getPort());
    return httpServer.newHandler(adapter).block();
  }

}
