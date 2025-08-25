package org.dcistudent.sakilarest.models.responses.shared;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.jetbrains.annotations.NotNull;

public final class SuccessResponse implements ResponsePayload {

  private final boolean success;

  public SuccessResponse(Builder builder) {
    this.success = builder.success;
  }

  public boolean isSuccess() {
    return this.success;
  }

  public static final class Builder implements Buildable<SuccessResponse> {
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
