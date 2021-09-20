package scheduler.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"scheduler"})
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @SuppressWarnings("unused")
  @Bean("oidc.client")
  public WebClient createOIDCClient(@Value("${oidc.userinfo-url}") String userInfoURL) {
    return WebClient.create(userInfoURL);
  }
}
