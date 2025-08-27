package org.dcistudent.sakilarest.models.responses.shared;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractUuidResponse {

  private final @NotNull UUID uuid;

  protected AbstractUuidResponse() {
    this.uuid = UUID.randomUUID();
  }

  protected AbstractUuidResponse(@NotNull UUID uuid) {
    this.uuid = uuid;
  }

  public @NotNull UUID getUuid() {
    return this.uuid;
  }
}
