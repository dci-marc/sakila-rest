package org.dcistudent.sakilarest.models.responses.error;

import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.jetbrains.annotations.NotNull;

public final class ErrorResponse implements ResponsePayload {

  @NotNull String message;

  public ErrorResponse(@NotNull String message) {
    this.message = message;
  }

  public @NotNull String getMessage() {
    return this.message;
  }
}
