package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
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
  private @NotNull LocalDateTime datetime;

  @Column(name = "message", nullable = false, columnDefinition = "MEDIUMTEXT")
  private @NotNull String message;

  public Log(@NotNull Integer level, @NotNull LocalDateTime datetime, @NotNull String message) {
    this.level = level;
    this.datetime = datetime;
    this.message = message;
  }

  public Log() {
  }
}