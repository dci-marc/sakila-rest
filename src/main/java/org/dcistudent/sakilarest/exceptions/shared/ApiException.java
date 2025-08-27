package org.dcistudent.sakilarest.exceptions.shared;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.jetbrains.annotations.NotNull;

public abstract class ApiException extends RuntimeException {

  private final @NotNull ResponsePayload data;

  protected ApiException(@NotNull String message, @NotNull ResponsePayload data) {
    super(message);
    this.data = data;
  }

  public @NotNull ResponsePayload getData() {
    return this.data;
  }
}
