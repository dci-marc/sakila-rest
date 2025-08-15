package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;

public final class UserResponse implements DomainResponse {

  public UserResponse(@NotNull String email) {
    this.email = email;
  }

  @NotNull String email;

  public @NotNull String getEmail() {
    return this.email;
  }
}
