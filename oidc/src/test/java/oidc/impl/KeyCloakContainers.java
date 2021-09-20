package oidc.impl;

import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class KeyCloakContainers {

  public static GenericContainer<?> basic() {
    return withResourceRealmJSON("com.github.rthoth.json");
  }

  public static GenericContainer<?> withResourceRealmJSON(String resource) {
    return new GenericContainer<>(DockerImageName.parse("quay.io/keycloak/keycloak:15.0.2"))
        .withClasspathResourceMapping(resource, "/realm.json", BindMode.READ_WRITE)
        .withEnv("KEYCLOAK_IMPORT", "/realm.json")
        .withExposedPorts(8080)
        .waitingFor(Wait.forHttp("/").forStatusCode(200));
  }
}
