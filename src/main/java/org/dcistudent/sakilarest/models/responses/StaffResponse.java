package org.dcistudent.sakilarest.models.responses;

import org.jetbrains.annotations.NotNull;

public final class StaffResponse {

  @NotNull String firstName;
  @NotNull String lastName;
  @NotNull String email;
  @NotNull Boolean active;
  @NotNull String lastUpdate;

  public StaffResponse(
      @NotNull String firstName,
      @NotNull String lastName,
      @NotNull String email,
      @NotNull Boolean active,
      @NotNull String lastUpdate
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.active = active;
    this.lastUpdate = lastUpdate;
  }

  public @NotNull String getFirstName() {
    return this.firstName;
  }

  public @NotNull String getLastName() {
    return this.lastName;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public @NotNull Boolean getActive() {
    return this.active;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }
}
