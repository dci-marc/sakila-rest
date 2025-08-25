package org.dcistudent.sakilarest.exceptions.shared;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(@NotNull String message) {
    super(message);
  }
}
