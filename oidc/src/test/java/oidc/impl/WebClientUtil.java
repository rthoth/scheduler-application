package oidc.impl;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientUtil {

  default WebClient createWebClient(String baseURL) {
    return WebClient
        .builder()
        .baseUrl(baseURL)
        .build();
  }
}
