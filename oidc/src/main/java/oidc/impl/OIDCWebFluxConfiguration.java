package oidc.impl;


import lombok.AllArgsConstructor;
import oidc.UserInfoService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(WebFluxAutoConfiguration.class)
@AllArgsConstructor
public class OIDCWebFluxConfiguration implements WebFluxConfigurer {

  private final BeanFactory factory;

  @Override
  public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
    configurer.addCustomResolver(factory.getBean(WebReactiveParamHandler.class));
  }

  @Bean
  public WebReactiveParamHandler createParamHandler(UserInfoService service) {
    return new WebReactiveParamHandler(service);
  }
}
