package scheduler.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class Event {

  @Getter
  private final User user;
  @Getter
  private final String title;
  @Getter
  private final String description;
  @Getter
  private final When when;
}
