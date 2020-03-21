package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "jiriki-bbp-spreadsheet")
public class Reciever {
  @RabbitHandler
  public void receive(String in) {
	System.out.println("[x] Recieved: '" + in + "'");
  }
}
