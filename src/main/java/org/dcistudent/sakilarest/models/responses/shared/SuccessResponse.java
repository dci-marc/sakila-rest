package org.dcistudent.sakilarest.models.responses.shared;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.jetbrains.annotations.NotNull;

public class SuccessResponse implements ResponsePayload {

  private final boolean success;

  public SuccessResponse(Builder builder) {
    this.success = builder.success;
  }

  public boolean isSuccess() {
    return this.success;
  }

  public static class Builder {
    private boolean success;

    public @NotNull Builder setSuccess(boolean success) {
      this.success = success;
      return this;
    }

    public @NotNull SuccessResponse build() {
      return new SuccessResponse(this);
    }
  }
}
