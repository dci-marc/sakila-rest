package org.dcistudent.sakilarest.models.responses.shared;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractUuidResponse {

  @NotNull UUID uuid;

  protected AbstractUuidResponse(@NotNull UUID uuid) {
    this.uuid = uuid;
  }

  public @NotNull UUID getUuid() {
    return this.uuid;
  }
}
