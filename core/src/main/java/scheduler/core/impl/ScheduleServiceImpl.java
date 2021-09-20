package scheduler.core.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import scheduler.core.EmailID;
import scheduler.core.Event;
import scheduler.core.ScheduleException;
import scheduler.core.ScheduleRepository;
import scheduler.core.ScheduleService;
import scheduler.core.UserRepository;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  private final UserRepository userRepository;
  private final ScheduleRepository scheduleRepository;

  @Override
  public Flux<Event> searchEvents(EmailID emailID) {
    return Mono
        .fromSupplier(() -> userRepository.find(emailID))
        .flatMapMany(scheduleRepository::search)
        .onErrorMap(cause -> new ScheduleException(
            String.format("Impossible search events for %s!", emailID), cause));

  }
}
