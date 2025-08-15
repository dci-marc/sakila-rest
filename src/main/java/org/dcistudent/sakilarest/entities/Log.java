package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "log")
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

  public UUID getId() {
    return this.id;
  }

  public @NotNull Integer getLevel() {
    return this.level;
  }

  public @NotNull Instant getDatetime() {
    return this.datetime;
  }

  public @NotNull String getMessage() {
    return this.message;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setLevel(@NotNull Integer level) {
    this.level = level;
  }

  public void setDatetime(@NotNull Instant datetime) {
    this.datetime = datetime;
  }

  public void setMessage(@NotNull String message) {
    this.message = message;
  }
}