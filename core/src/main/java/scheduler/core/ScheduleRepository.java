package scheduler.core;

import reactor.core.publisher.Flux;

public interface ScheduleRepository {

  Flux<Event> search(User user);
}
