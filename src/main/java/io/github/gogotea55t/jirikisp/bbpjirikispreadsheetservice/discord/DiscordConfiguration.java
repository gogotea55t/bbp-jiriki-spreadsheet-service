package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.discord;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "discord")
public class DiscordConfiguration {
  private String webhookurl;
}
