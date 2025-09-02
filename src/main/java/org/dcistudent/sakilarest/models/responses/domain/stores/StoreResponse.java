package org.dcistudent.sakilarest.models.responses.domain.stores;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.dcistudent.sakilarest.models.responses.domain.StaffResponse;
import org.dcistudent.sakilarest.models.responses.domain.customers.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.domain.films.FilmResponse;
import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@JsonDeserialize(builder = StoreResponse.Builder.class)
public final class StoreResponse extends AbstractUuidResponse implements DomainResponse {

  private final @NotNull String lastUpdate;
  private final @NotNull StaffResponse staff;
  private final @NotNull List<CustomerResponse> customers;
  private final @NotNull List<FilmResponse> films;

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

  @JsonPOJOBuilder(withPrefix = "set")
  public static final class Builder implements Buildable<StoreResponse> {
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

    @JsonCreator
    public @NotNull StoreResponse build() {
      return new StoreResponse(this);
    }
  }
}
