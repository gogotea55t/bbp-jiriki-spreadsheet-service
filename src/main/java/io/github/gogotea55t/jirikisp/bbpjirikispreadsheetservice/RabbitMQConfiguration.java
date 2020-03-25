package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
  String queueName = "jiriki-bbp-spreadsheet";

  @Bean
  public Queue hello() {
    return new Queue(queueName, false);
  }

  @Bean
  public Reciever reciever() {
    return new Reciever();
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
