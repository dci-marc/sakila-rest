package org.dcistudent.sakilarest.models.responses.error;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.jetbrains.annotations.NotNull;

public final class ErrorResponse implements ResponsePayload {

  @NotNull String message;

  public ErrorResponse(@NotNull Builder builder) {
    this.message = builder.message;
  }

  public @NotNull String getMessage() {
    return this.message;
  }

  public static class Builder {
    private @NotNull String message = "";

    public @NotNull Builder setMessage(@NotNull String message) {
      this.message = message;
      return this;
    }

    public @NotNull ErrorResponse build() {
      return new ErrorResponse(this);
    }
  }
}
