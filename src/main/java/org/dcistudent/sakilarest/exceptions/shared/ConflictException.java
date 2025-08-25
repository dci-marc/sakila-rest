package org.dcistudent.sakilarest.exceptions.shared;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public final class ConflictException extends RuntimeException {
  public ConflictException(@NotNull String message) {
    super(message);
  }
}
