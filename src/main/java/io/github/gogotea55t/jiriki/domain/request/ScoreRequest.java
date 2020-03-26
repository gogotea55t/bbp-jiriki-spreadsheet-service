package io.github.gogotea55t.jiriki.domain.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ScoreRequest {
  private String songId;
  
  private String userId;
  
  private BigDecimal score;
}
