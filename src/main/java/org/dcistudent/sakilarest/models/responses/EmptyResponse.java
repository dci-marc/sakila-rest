package org.dcistudent.sakilarest.models.responses;

import org.jetbrains.annotations.NotNull;

public final class EmptyResponse implements ResponsePayload {
  public static final @NotNull EmptyResponse INSTANCE;

  static {
    INSTANCE = new EmptyResponse();
  }

  private EmptyResponse() {}
}
