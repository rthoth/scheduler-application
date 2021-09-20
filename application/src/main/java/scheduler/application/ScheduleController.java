package scheduler.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oidc.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import scheduler.application.DTO.Event;
import scheduler.core.ScheduleService;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
@Slf4j
public class ScheduleController {

  private final ScheduleService scheduleService;

  @GetMapping
  public Flux<Event> reactiveListEvents(UserInfo userInfo) {
    return Flux.empty();
  }
}
