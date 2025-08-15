package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public final class StoreResponse extends AbstractUuidResponse implements DomainResponse {

  @NotNull String lastUpdate;
  @NotNull StaffResponse staff;
  @NotNull List<CustomerResponse> customers;
  @NotNull List<FilmResponse> films;

  public StoreResponse(
      @NotNull UUID uuid,
      @NotNull String lastUpdate,
      @NotNull StaffResponse staff,
      @NotNull List<CustomerResponse> customers,
      @NotNull List<FilmResponse> films
  ) {
    super(uuid);
    this.lastUpdate = lastUpdate;
    this.staff = staff;
    this.customers = customers;
    this.films = films;
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
}
