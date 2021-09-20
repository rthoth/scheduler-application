package oidc;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface UserInfoService {

  Pattern BEARER_TOKEN_PATTERN = Pattern.compile("^Bearer\\s([\\d\\w\\-._~+\\/]+)$");

  default String extractAccessTokenFromValues(List<String> values) {
    if (values != null && values.size() == 1) {
      var matcher = BEARER_TOKEN_PATTERN.matcher(values.get(0));
      if (matcher.matches()) {
        return matcher.group(1);
      }
    }

    throw new OIDCException("Invalid bearer token!");
  }

  @SuppressWarnings("unused")
  default UserInfo getFromBearerToken(HttpRequest request) {
    String token = extractAccessTokenFromValues(
        request.getHeaders().get(HttpHeaders.AUTHORIZATION));

    return getFromAccessToken(token).block();
  }

  default Mono<UserInfo> getFromBearerToken(ServerWebExchange exchange) {
    String token;

    try {
      token = extractAccessTokenFromValues(
          exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION));
    } catch (Exception cause) {
      return Mono.error(cause);
    }

    return getFromAccessToken(token);
  }


  Mono<UserInfo> getFromAccessToken(String accessToken);
}
