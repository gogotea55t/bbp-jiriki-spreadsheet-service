package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.sheets;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("google.sheet")
public class GoogleSpreadSheetConfig {
  private String id;
  private String name;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
