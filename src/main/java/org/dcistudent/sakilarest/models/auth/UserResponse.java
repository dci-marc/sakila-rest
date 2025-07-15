package org.dcistudent.sakilarest.models.auth;

import org.jetbrains.annotations.NotNull;

public class UserResponse {

  @NotNull
  private String name;
  @NotNull
  private String email;

  public UserResponse(@NotNull String name, @NotNull String email) {
    this.name = name;
    this.email = email;
  }

  public @NotNull String getName() {
    return this.name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }
}
