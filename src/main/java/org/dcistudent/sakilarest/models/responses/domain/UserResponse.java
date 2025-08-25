package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;

public final class UserResponse implements DomainResponse {

  public UserResponse(@NotNull Builder builder) {
    this.email = builder.email;
  }

  @NotNull String email;

  public @NotNull String getEmail() {
    return this.email;
  }

  public static final class Builder implements Buildable<UserResponse> {
    private @NotNull String email = "";

    public @NotNull Builder setEmail(@NotNull String email) {
      this.email = email;
      return this;
    }

    public @NotNull UserResponse build() {
      return new UserResponse(this);
    }
  }
}
