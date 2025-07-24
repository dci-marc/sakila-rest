package org.dcistudent.sakilarest.exceptions;

import org.dcistudent.sakilarest.models.responses.Auth0ErrorResponse;
import org.jetbrains.annotations.NotNull;

public class Auth0Exception extends RuntimeException {

  private final transient @NotNull Auth0ErrorResponse error;

  public Auth0Exception(Auth0ErrorResponse error) {
    super(error.getMessage());
    this.error = error;
  }

  public @NotNull Auth0ErrorResponse getError() {
    return error;
  }
}

