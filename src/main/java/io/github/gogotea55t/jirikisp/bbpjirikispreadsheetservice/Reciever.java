package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.gogotea55t.jiriki.domain.request.ScoreRequest;
import io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.service.SheetService;

@RabbitListener(queues = "jiriki-bbp-spreadsheet")
public class Reciever {
  @Autowired
  private SheetService sheetService;

  @RabbitHandler
  public void receive(ScoreRequest in) {
    System.out.println("[x] Recieved: '" + in + "'");
    try {
      sheetService.updateScore(in);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
