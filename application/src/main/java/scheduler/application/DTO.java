package scheduler.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class DTO {

  private DTO() {

  }

  @AllArgsConstructor
  public static class Event {

    @Getter
    private final String title;

    @Getter
    private final String description;

    @Getter
    private final boolean done;

  }
}
