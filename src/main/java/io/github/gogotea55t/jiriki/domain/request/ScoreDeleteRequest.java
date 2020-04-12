package io.github.gogotea55t.jiriki.domain.request;

import lombok.Data;

@Data
public class ScoreDeleteRequest {
  private String userId;
  private String songId;
}
