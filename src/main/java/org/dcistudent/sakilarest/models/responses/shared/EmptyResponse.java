package org.dcistudent.sakilarest.models.responses.shared;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.serializers.responses.EmptyResponseSerializer;
import org.jetbrains.annotations.NotNull;

@JsonSerialize(using = EmptyResponseSerializer.class)
public final class EmptyResponse implements ResponsePayload {
  public static final @NotNull EmptyResponse INSTANCE;

  static {
    INSTANCE = new EmptyResponse();
  }

  private EmptyResponse() {
  }
}
