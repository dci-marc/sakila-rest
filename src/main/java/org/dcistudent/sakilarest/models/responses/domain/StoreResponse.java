package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public final class StoreResponse extends AbstractUuidResponse implements DomainResponse {

  @NotNull String lastUpdate;
  @NotNull StaffResponse staff;
  @NotNull List<CustomerResponse> customers;
  @NotNull List<FilmResponse> films;

  public StoreResponse(@NotNull Builder builder) {
    super(builder.uuid);
    this.lastUpdate = builder.lastUpdate;
    this.staff = builder.staff;
    this.customers = builder.customers;
    this.films = builder.films;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }

  public @NotNull StaffResponse getStaff() {
    return this.staff;
  }

  public @NotNull List<CustomerResponse> getCustomers() {
    return this.customers;
  }

  public @NotNull List<FilmResponse> getFilms() {
    return this.films;
  }

  public static class Builder {
    private @NotNull UUID uuid = UUID.randomUUID();
    private @NotNull String lastUpdate = "";
    private @NotNull StaffResponse staff = new StaffResponse.Builder().build();
    private @NotNull List<CustomerResponse> customers = List.of();
    private @NotNull List<FilmResponse> films = List.of();

    public @NotNull Builder setUuid(@NotNull UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public @NotNull Builder setLastUpdate(@NotNull String lastUpdate) {
      this.lastUpdate = lastUpdate;
      return this;
    }

    public @NotNull Builder setStaff(@NotNull StaffResponse staff) {
      this.staff = staff;
      return this;
    }

    public @NotNull Builder setCustomers(@NotNull List<CustomerResponse> customers) {
      this.customers = customers;
      return this;
    }

    public @NotNull Builder setFilms(@NotNull List<FilmResponse> films) {
      this.films = films;
      return this;
    }

    public @NotNull StoreResponse build() {
      return new StoreResponse(this);
    }
  }
}
