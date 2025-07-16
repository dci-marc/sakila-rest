package org.dcistudent.sakilarest.models.auth;

import org.jetbrains.annotations.NotNull;

public class UserResponse {

  @NotNull
  private String email;

  public UserResponse(@NotNull String email) {
    this.email = email;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }
}
