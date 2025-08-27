package org.dcistudent.sakilarest.models.responses.shared;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractUuidResponse implements Serializable {

  @NotNull UUID uuid;

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
