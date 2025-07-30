package org.dcistudent.sakilarest.models.responses.error;

import lombok.Getter;
import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.jetbrains.annotations.NotNull;

@Getter
public final class ErrorResponse implements ResponsePayload {

  private final @NotNull String message;

  public ErrorResponse(@NotNull String message) {
    this.message = message;
  }
}
