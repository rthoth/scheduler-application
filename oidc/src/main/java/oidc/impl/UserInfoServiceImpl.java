package oidc.impl;

import lombok.AllArgsConstructor;
import oidc.OIDCException;
import oidc.UserInfo;
import oidc.UserInfoService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

  private final WebClient client;

  @Override
  public Mono<UserInfo> getFromAccessToken(String accessToken) {
    try {
      return client
          .get()
          .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", accessToken))
          .retrieve()
          .bodyToMono(UserInfo.class)
          .onErrorMap(cause -> new OIDCException("Error on userInfo request!", cause))
          .map(userInfo -> userInfo.withAccessToken(accessToken));
    } catch (Exception cause) {
      return Mono.error(new OIDCException("Invalid request!", cause));
    }
  }
}
