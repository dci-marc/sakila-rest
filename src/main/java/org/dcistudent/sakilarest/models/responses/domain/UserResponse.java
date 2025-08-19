package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;

public final class UserResponse implements DomainResponse {

  public UserResponse(@NotNull Builder builder) {
    this.email = builder.email;
  }

  @NotNull String email;

  public @NotNull String getEmail() {
    return this.email;
  }

  public static class Builder {
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
