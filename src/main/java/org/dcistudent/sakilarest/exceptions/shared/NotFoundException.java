package org.dcistudent.sakilarest.exceptions.shared;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class NotFoundException extends ApiException {
  public NotFoundException(@NotNull String message, @NotNull ResponsePayload payload) {
    super(message, payload);
  }
}
