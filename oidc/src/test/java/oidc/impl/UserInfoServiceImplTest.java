package oidc.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class UserInfoServiceImplTest implements KeyCloakUtil, WebClientUtil {

  @Container
  public final GenericContainer<?> keycloak = KeyCloakContainers.basic();

  private String urlKeycloakToken() {
    return String.format(
        "http://localhost:%d/auth/realms/com.github.rthoth/protocol/openid-connect/token",
        keycloak.getMappedPort(8080));
  }

  private String urlKeycloakUserInfo() {
    return String.format(
        "http://localhost:%d/auth/realms/com.github.rthoth/protocol/openid-connect/userinfo",
        keycloak.getMappedPort(8080));
  }

  @Test
  public void shouldGetValidUserInfo() {
    final var token = requestAccessToken(urlKeycloakToken(), "oidca", "oidca");
    final var userInfo = new UserInfoServiceImpl(
        createWebClient(urlKeycloakUserInfo())).getFromAccessToken(token).block();

    assert userInfo != null;
    assertThat(userInfo.getName()).isEqualTo("A oidc");
  }
}
