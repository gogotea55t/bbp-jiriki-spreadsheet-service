package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.gogotea55t.jiriki.domain.request.ScoreDeleteRequest;
import io.github.gogotea55t.jiriki.domain.request.ScoreRequest;
import io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.service.SheetService;

public class Reciever {
  @Autowired
  private SheetService sheetService;

  @RabbitListener(queues = "#{autoDeleteQueueForUpdate.name}")
  public void update(ScoreRequest in) {
    System.out.println("[x] Recieved: '" + in + "'");
    try {
      sheetService.updateScore(in);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @RabbitListener(queues = "#{autoDeleteQueueForDelete.name}")
  public void delete(ScoreDeleteRequest in) {
	System.out.println("[y] Recieved: '" + in + "'");
	try {
	  sheetService.deleteScore(in);
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }
}
