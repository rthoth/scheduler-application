package scheduler.infrastructure;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import scheduler.core.Event;
import scheduler.core.ScheduleRepository;
import scheduler.core.User;

@Service
public class SchedulerRepositoryImpl implements ScheduleRepository {

  @Override
  public Flux<Event> search(User user) {
    return Flux.empty();
  }
}
