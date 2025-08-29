package org.dcistudent.sakilarest.models.responses.domain.customers;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class CustomerResponse extends AbstractUuidResponse implements DomainResponse {

  private final @NotNull String firstName;
  private final @NotNull String lastName;
  private final @NotNull String email;
  private final @NotNull Boolean active;
  private final @NotNull String createDate;
  private final @NotNull String lastUpdate;

  public CustomerResponse(@NotNull Builder builder) {
    super(builder.uuid);
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.active = builder.active;
    this.createDate = builder.createDate;
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

  public @NotNull String getCreateDate() {
    return this.createDate;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }

  public static final class Builder implements Buildable<CustomerResponse> {
    private @NotNull UUID uuid = UUID.randomUUID();
    private @NotNull String firstName = "";
    private @NotNull String lastName = "";
    private @NotNull String email = "";
    private @NotNull Boolean active = true;
    private @NotNull String createDate = "";
    private @NotNull String lastUpdate = "";

    public @NotNull Builder setUuid(@NotNull UUID uuid) {
      this.uuid = uuid;
      return this;
    }

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

    public @NotNull Builder setCreateDate(@NotNull String createDate) {
      this.createDate = createDate;
      return this;
    }

    public @NotNull Builder setLastUpdate(@NotNull String lastUpdate) {
      this.lastUpdate = lastUpdate;
      return this;
    }

    public @NotNull CustomerResponse build() {
      return new CustomerResponse(this);
    }
  }
}
