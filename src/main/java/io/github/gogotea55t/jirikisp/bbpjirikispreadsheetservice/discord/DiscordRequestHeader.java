package io.github.gogotea55t.jirikisp.bbpjirikispreadsheetservice.discord;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DiscordRequestHeader {
  public static HttpHeaders requestHeader() {
	HttpHeaders header = new HttpHeaders();
	header.setContentType(MediaType.APPLICATION_JSON);
	header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	return header;
  }
}
