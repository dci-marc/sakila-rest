package org.dcistudent.sakilarest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
abstract public class AbstractUuidEntity {

  @Column(name = "uuid", columnDefinition = "uuid", nullable = false, unique = true)
  protected @NotNull String uuid;

  @PrePersist
  protected void onCreate() {
    this.uuid = UUID.randomUUID().toString();
  }
}
