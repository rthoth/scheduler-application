package oidc.impl;

import oidc.UserInfoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@SuppressWarnings("unused")
@Configuration
public class UserInfoServiceAutoConfiguration {

  @SuppressWarnings("unused")
  @Bean
  public UserInfoService createUserInfoService(@Qualifier("oidc.client") WebClient client) {
    return new UserInfoServiceImpl(client);
  }
}
