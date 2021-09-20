package oidc.impl;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public interface KeyCloakUtil {

  default String formEncode(E... entries) {
    var builder = new StringBuilder();
    var it = Arrays.asList(entries).iterator();
    while (it.hasNext()) {
      final var e = it.next();
      builder.append(e.k).append("=").append(URLEncoder.encode(e.v, StandardCharsets.UTF_8));
      if (it.hasNext()) {
        builder.append("&");
      }
    }

    return builder.toString();
  }

  default E e(String name, String value) {
    return new E(name, value);
  }

  default String requestAccessToken(String url, String username, String password) {
    var request = HttpRequest
        .newBuilder(URI.create(url))
        .POST(BodyPublishers.ofString(
            formEncode(
                e("scope", "openid"),
                e("grant_type", "password"),
                e("username", username),
                e("password", password),
                e("client_id", "oidc-client")
            )
        ))
        .header("Content-type", "application/x-www-form-urlencoded")
        .build();
    try {
      var json = HttpClient
          .newHttpClient()
          .send(request, BodyHandlers.ofString())
          .body();

      return (String) Objects.requireNonNull(
          new ObjectMapper().readValue(json, Map.class).get("access_token"));
    } catch (Exception cause) {
      throw new RuntimeException(cause);
    }
  }

  @AllArgsConstructor
  class E {

    final String k;
    final String v;
  }
}
