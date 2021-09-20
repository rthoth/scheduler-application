package oidc.impl;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import oidc.UserInfo;
import oidc.UserInfoService;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class WebReactiveParamHandler implements HandlerMethodArgumentResolver {

  private final UserInfoService service;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().isAssignableFrom(UserInfo.class);
  }

  @Override
  public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
      ServerWebExchange exchange) {
    return service.getFromBearerToken(exchange).map(Function.identity());
  }
}
