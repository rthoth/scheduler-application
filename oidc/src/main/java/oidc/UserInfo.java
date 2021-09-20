package oidc;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserInfo {

  private final Map<String, Object> attributes;

  @Getter
  private final String accessToken;

  @JsonCreator
  public static UserInfo create(Map<String, Object> attributes) {
    return new UserInfo(attributes, null);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(String attribute) {
    return (T) attributes.get(attribute);
  }

  public String getName() {
    return get("name");
  }

  public String getEmail() {
    return get("email");
  }

  public UserInfo withAccessToken(String accessToken) {
    return new UserInfo(attributes, accessToken);
  }
}
