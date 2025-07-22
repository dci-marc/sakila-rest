package org.dcistudent.sakilarest.models.responses;

import org.jetbrains.annotations.NotNull;

public final class Auth0ErrorResponse {
  private final int statusCode;
  private final @NotNull String error;
  private final @NotNull String message;
  private final @NotNull String errorCode;
  private final @NotNull StatusCategory statusCategory;

  public Auth0ErrorResponse(
      int statusCode,
      @NotNull String error,
      @NotNull String message,
      @NotNull String errorCode,
      @NotNull StatusCategory statusCategory
  ) {
    this.statusCode = statusCode;
    this.error = error;
    this.message = message;
    this.errorCode = errorCode;
    this.statusCategory = statusCategory;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public @NotNull String getError() {
    return this.error;
  }

  public @NotNull String getMessage() {
    return this.message;
  }

  public @NotNull String getErrorCode() {
    return this.errorCode;
  }

  public @NotNull StatusCategory getStatusCategory() {
    return this.statusCategory;
  }

  public enum StatusCategory {
    BAD_REQUEST(400, "auth:user:bad.request"),
    UNAUTHORIZED(401, "auth:unauthorized"),
    FORBIDDEN(403, "auth:forbidden"),
    NOT_FOUND(404, "auth:user:not.found"),
    CONFLICT(409, "auth:user:exists"),
    TOO_MANY_REQUESTS(429, "too.many.requests"),
    SERVER_ERROR(500, "server:internal:error"),
    SERVICE_UNAVAILABLE(503, "auth:temporarily.unavailable"),
    UNKNOWN(-1, "auth:unknown");

    public final int code;
    public final String description;

    StatusCategory(int code, String description) {
      this.code = code;
      this.description = description;
    }

    public static @NotNull StatusCategory fromStatusCode(int code) {
      for (StatusCategory c : values()) {
        if (c.code == code) return c;
      }
      if (code >= 500) return SERVER_ERROR;
      if (code >= 400) return BAD_REQUEST;
      return UNKNOWN;
    }

    @Override
    public @NotNull String toString() {
      return this.description;
    }
  }
}
