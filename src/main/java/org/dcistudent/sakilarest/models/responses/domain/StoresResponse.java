package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class StoresResponse extends AbstractUuidResponse implements DomainResponse {

  @NotNull String lastUpdate;
  @NotNull StaffResponse staff;

  public StoresResponse(@NotNull UUID uuid, @NotNull String lastUpdate, @NotNull StaffResponse staff) {
    super(uuid);
    this.lastUpdate = lastUpdate;
    this.staff = staff;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }

  public @NotNull StaffResponse getStaff() {
    return this.staff;
  }
}
