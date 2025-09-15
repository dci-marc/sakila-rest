package org.dcistudent.sakilarest.factories.responses;

import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.dcistudent.sakilarest.interfaces.models.responses.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public final class ResponseFactory {

  private ResponseFactory() {
  }

  public static @NotNull <T extends ResponsePayload> Response<T> create(
      @NotNull HttpStatus status,
      @NotNull String message,
      @NotNull T data
  ) {
    return Response.create(status, message, data);
  }

  public static <T extends ResponsePayload> Response<Paged<T>> create(
      @NotNull HttpStatus status,
      @NotNull String message,
      @NotNull Paged<T> page
  ) {
    return Response.create(status, message, page);
  }

  public static @NotNull Response<String> create(
      @NotNull HttpStatus status,
      @NotNull String message,
      @NotNull String data
  ) {
    return Response.create(status, message, data);
  }

  public static @NotNull Response<EmptyResponse> create(
      @NotNull HttpStatus status,
      @NotNull String message
  ) {
    return Response.create(status, message, EmptyResponse.INSTANCE);
  }
}
