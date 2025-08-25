package org.dcistudent.sakilarest.models.responses.error;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.Auth0Response;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public final class Auth0ErrorResponse extends Response<String> implements Auth0Response {

  public Auth0ErrorResponse(@NotNull HttpStatus status) {
    super(status, StatusCategory.fromStatusCode(status.value()).toString(), "");
  }

  public Auth0ErrorResponse(@NotNull HttpStatus status, @NotNull String message, int statusCode) {
    super(status, message, StatusCategory.fromStatusCode(statusCode).toString());
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
    public final @NotNull String description;

    StatusCategory(int code, @NotNull String description) {
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
