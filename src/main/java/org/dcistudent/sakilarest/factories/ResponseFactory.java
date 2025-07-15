package org.dcistudent.sakilarest.factories;

import org.dcistudent.sakilarest.models.Response;
import org.jetbrains.annotations.NotNull;

public final class ResponseFactory {

  private ResponseFactory() {}

  public static <T> Response<T> create(int status, @NotNull String message, @NotNull T data) {
    return Response.create(status, message, data);
  }

  public static Response<String> create(int status, @NotNull String message) {
    return Response.create(status, message, "");
  }
}