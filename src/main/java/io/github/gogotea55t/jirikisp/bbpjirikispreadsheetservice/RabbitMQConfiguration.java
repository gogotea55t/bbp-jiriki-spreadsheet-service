package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
  String topicName = "jiriki-bbp-spreadsheet";
  
  @Bean
  public TopicExchange topic() {
	return new TopicExchange(topicName, false, true);
  }

  @Bean
  public Reciever reciever() {
    return new Reciever();
  }
  
  @Bean
  public Queue autoDeleteQueueForUpdate() {
    return new AnonymousQueue();
  }
  
  @Bean
  public Queue autoDeleteQueueForDelete() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding bindingForUpdate(TopicExchange topic, Queue autoDeleteQueueForUpdate) {
    return BindingBuilder.bind(autoDeleteQueueForUpdate).to(topic).with("update");
  }

  @Bean
  public Binding bindingForDelete(TopicExchange topic, Queue autoDeleteQueueForDelete) {
    return BindingBuilder.bind(autoDeleteQueueForDelete).to(topic).with("delete");
  }
  
  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
