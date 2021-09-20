package scheduler.core;

import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

public abstract class When {

  @AllArgsConstructor
  public static class WInstant extends When {

    @Getter
    private final Instant begin;
    @Getter
    private final Instant end;
  }

  @AllArgsConstructor
  public static class WDay extends When {

    @Getter
    private final LocalDate date;
  }
}
