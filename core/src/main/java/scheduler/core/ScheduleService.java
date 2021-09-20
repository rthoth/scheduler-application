package scheduler.core;

import reactor.core.publisher.Flux;

public interface ScheduleService {

  Flux<Event> searchEvents(EmailID emailID);
}
