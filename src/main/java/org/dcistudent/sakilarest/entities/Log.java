package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "log")
@Getter
@Setter
public class Log {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, unique = true)
  private UUID id;

  @Column(name = "level", nullable = false)
  private @NotNull Integer level;

  @Column(name = "datetime", nullable = false)
  @CurrentTimestamp
  private @NotNull Instant datetime;

  @Column(name = "message", nullable = false, columnDefinition = "MEDIUMTEXT")
  private @NotNull String message;

  public Log(@NotNull Integer level, @NotNull String message) {
    this.level = level;
    this.message = message;
  }

  public Log() {
  }
}