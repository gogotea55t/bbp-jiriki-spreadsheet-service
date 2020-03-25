package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice;

import java.util.LinkedHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import io.github.gogotea55t.jiriki.domain.request.ScoreRequest;

@RabbitListener(queues = "jiriki-bbp-spreadsheet")
public class Reciever {
  @RabbitHandler
  public void receive(ScoreRequest in) {
	System.out.println("[x] Recieved: '" + in + "'");
  }
}
