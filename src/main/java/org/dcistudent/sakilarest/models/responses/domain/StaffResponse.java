package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;

public final class StaffResponse {

  @NotNull String firstName;
  @NotNull String lastName;
  @NotNull String email;
  @NotNull Boolean active;
  @NotNull String lastUpdate;

  public StaffResponse(@NotNull Builder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.active = builder.active;
    this.lastUpdate = builder.lastUpdate;
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

  public static class Builder {
    private @NotNull String firstName = "";
    private @NotNull String lastName = "";
    private @NotNull String email = "";
    private @NotNull Boolean active = false;
    private @NotNull String lastUpdate = "";

    public @NotNull Builder setFirstName(@NotNull String firstName) {
      this.firstName = firstName;
      return this;
    }

    public @NotNull Builder setLastName(@NotNull String lastName) {
      this.lastName = lastName;
      return this;
    }

    public @NotNull Builder setEmail(@NotNull String email) {
      this.email = email;
      return this;
    }

    public @NotNull Builder setActive(@NotNull Boolean active) {
      this.active = active;
      return this;
    }

    public @NotNull Builder setLastUpdate(@NotNull String lastUpdate) {
      this.lastUpdate = lastUpdate;
      return this;
    }

    public @NotNull StaffResponse build() {
      return new StaffResponse(this);
    }
  }
}
