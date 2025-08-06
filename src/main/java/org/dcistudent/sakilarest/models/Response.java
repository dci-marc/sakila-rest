package org.dcistudent.sakilarest.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
@Setter
public class Response<T> {

  private final @NotNull HttpStatus status;
  @NotBlank
  private final @NotNull String message;
  private final @NotNull T data;

  protected Response(@NotNull HttpStatus status, @NotNull String message, @Nullable T data) {
    this.status = status;
    this.message = message;
    Objects.requireNonNull(data, "Data must not be null");
    this.data = data;
  }

  /**
   * Creates a Response with specific data.
   *
   * @param status  The HTTP status code.
   * @param message The response message.
   * @param data    The payload data. Must not be null.
   * @param <T>     The generic type of data.
   * @return A new Response instance.
   */
  public static <T> Response<T> create(@NotNull HttpStatus status, @NotNull String message, @NotNull T data) {
    return new Response<>(status, message, data);
  }

  /**
   * Creates a Response with an empty string as data.
   * Use this if you require the 'data' field to always be present,
   * even when no specific payload is available.
   *
   * @param status  The HTTP status code.
   * @param message The response message.
   * @return A new Response instance with an empty string for data.
   */
  public static Response<String> create(@NotNull HttpStatus status, @NotNull String message) {
    return new Response<>(status, message, "");
  }
}
