package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class StoresResponse extends AbstractUuidResponse implements DomainResponse {

  @NotNull String lastUpdate;
  @NotNull StaffResponse staff;

  public StoresResponse(@NotNull Builder builder) {
    super(builder.uuid);
    this.lastUpdate = builder.lastUpdate;
    this.staff = builder.staff;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }

  public @NotNull StaffResponse getStaff() {
    return this.staff;
  }

  public static final class Builder implements Buildable<StoresResponse> {
    private @NotNull UUID uuid = UUID.randomUUID();
    private @NotNull String lastUpdate = "";
    private @NotNull StaffResponse staff = new StaffResponse.Builder().build();

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

    public @NotNull StoresResponse build() {
      return new StoresResponse(this);
    }
  }
}
