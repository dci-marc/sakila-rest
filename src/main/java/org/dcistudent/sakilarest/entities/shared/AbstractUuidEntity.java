package org.dcistudent.sakilarest.entities.shared;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@MappedSuperclass
abstract public class AbstractUuidEntity {

  @Column(name = "uuid", columnDefinition = "uuid", nullable = false, unique = true)
  protected @NotNull UUID uuid;

  @PrePersist
  protected void onCreate() {
    this.uuid = UUID.randomUUID();
  }

  public @NotNull UUID getUuid() {
    return this.uuid;
  }
}
