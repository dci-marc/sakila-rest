package org.dcistudent.sakilarest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Response<T> {

  private final int status;
  @NotBlank
  private final String message;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("data")
  private final T data;

  private Response(int status, @NotNull String message, @Nullable T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  /**
   * Creates a Response with specific data.
   *
   * @param status The HTTP status code.
   * @param message The response message.
   * @param data The payload data. Must not be null.
   * @param <T> The generic type of data.
   * @return A new Response instance.
   */
  public static <T> Response<T> create(int status, @NotNull String message, @NotNull T data) {
    return new Response<>(status, message, data);
  }

  /**
   * Creates a Response with an empty string as data.
   * Use this if you require the 'data' field to always be present,
   * even when no specific payload is available.
   *
   * @param status The HTTP status code.
   * @param message The response message.
   * @return A new Response instance with an empty string for data.
   */
  public static Response<String> create(int status, @NotNull String message) {
    return new Response<>(status, message, "");
  }

  public int getStatus() {
    return this.status;
  }

  public @NotNull String getMessage() {
    return this.message;
  }

  @Nullable
  public T getData() {
    return this.data;
  }

  public enum Status {
    OK(200),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500);

    private final int value;

    Status(int value) {
      this.value = value;
    }

    public int get() {
      return this.value;
    }
  }

  public enum Message {
    OK("OK"),
    BAD_REQUEST("Bad Request"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String value;

    Message(@NotNull String value) {
      this.value = value;
    }

    public @NotNull String get() {
      return this.value;
    }
  }
}