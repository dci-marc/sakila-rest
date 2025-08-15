package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CustomerResponse extends AbstractUuidResponse implements DomainResponse {

  @NotNull String firstName;
  @NotNull String lastName;
  @NotNull String email;
  @NotNull Boolean active;
  @NotNull String createDate;
  @NotNull String lastUpdate;

  public CustomerResponse(
      @NotNull UUID uuid,
      @NotNull String firstName,
      @NotNull String lastName,
      @NotNull String email,
      @NotNull Boolean active,
      @NotNull String createDate,
      @NotNull String lastUpdate) {
    super(uuid);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.active = active;
    this.createDate = createDate;
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

  public @NotNull String getCreateDate() {
    return this.createDate;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }
}
