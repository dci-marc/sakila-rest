package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

@Entity
@Table(name = "log")
public class Log {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, unique = true)
  private String id;

  @Column(name = "level", nullable = false)
  @NotNull
  private Integer level;

  @Column(name = "datetime", nullable = false)
  @NotNull
  private ZonedDateTime datetime;

  @Column(name = "message", nullable = false)
  @NotNull
  private String message;

  public Log(@NotNull String id,@NotNull Integer level,@NotNull ZonedDateTime datetime,@NotNull String message) {
    this.id = id;
    this.level = level;
    this.datetime = datetime;
    this.message = message;
  }

  public Log() {}

  public @NotNull String getId() {
    return this.id;
  }

  public void setId(@NotNull String id) {
    this.id = id;
  }

  public @NotNull Integer getLevel() {
    return this.level;
  }

  public void setLevel(@NotNull Integer level) {
    this.level = level;
  }

  public @NotNull ZonedDateTime getDatetime() {
    return this.datetime;
  }

  public void setDatetime(@NotNull ZonedDateTime datetime) {
    this.datetime = datetime;
  }

  public @NotNull String getMessage() {
    return this.message;
  }

  public void setMessage(@NotNull String message) {
    this.message = message;
  }
}
