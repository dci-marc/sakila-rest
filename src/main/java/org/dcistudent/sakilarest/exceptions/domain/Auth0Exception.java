package org.dcistudent.sakilarest.exceptions.domain;

import org.dcistudent.sakilarest.exceptions.shared.ApiException;
import org.dcistudent.sakilarest.models.responses.error.Auth0ErrorResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class Auth0Exception extends ApiException {

  private final transient @NotNull Auth0ErrorResponse error;

  public Auth0Exception(Auth0ErrorResponse error) {
    super(error.getMessage(), EmptyResponse.INSTANCE);
    this.error = error;
  }

  public @NotNull Auth0ErrorResponse getError() {
    return this.error;
  }
}
