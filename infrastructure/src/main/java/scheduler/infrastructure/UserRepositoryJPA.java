package scheduler.infrastructure;

import org.springframework.stereotype.Service;
import scheduler.core.EmailID;
import scheduler.core.User;
import scheduler.core.UserRepository;

@Service
public class UserRepositoryJPA implements UserRepository {

  @Override
  public User find(EmailID emailID) {
    return null;
  }
}
