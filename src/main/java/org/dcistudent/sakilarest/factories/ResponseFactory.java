package org.dcistudent.sakilarest.factories;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
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

  public static <T extends ResponsePayload> Response<Page<T>> create(
      @NotNull HttpStatus status,
      @NotNull String message,
      @NotNull Page<T> page
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
