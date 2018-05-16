package com.example.demo.config;

import com.example.demo.events.EventQueues;
import com.example.demo.service.AMQPMessageReceiver;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
@Profile("amqp")
public class DemoRabbitMQConfig {

  @Bean
  public Queue testQueue() {
    return new Queue(EventQueues.TEST);
  }

  @Bean
  public MessageListenerAdapter listenerAdapter(AMQPMessageReceiver receiver) {
    return new MessageListenerAdapter(receiver, "receive");
  }

  @Bean
  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(EventQueues.TEST);
    container.setMessageListener(listenerAdapter);
    return container;
  }
}
