package scheduler.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class User {

  @Getter
  private final String name;

  @Getter
  private final String email;
}
