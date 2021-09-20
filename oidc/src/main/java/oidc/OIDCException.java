package oidc;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OIDCException extends ResponseStatusException {

  public OIDCException(String message) {
    super(HttpStatus.UNAUTHORIZED, message);
  }

  public OIDCException(String message, Throwable cause) {
    super(HttpStatus.UNAUTHORIZED, message, cause);
  }
}
