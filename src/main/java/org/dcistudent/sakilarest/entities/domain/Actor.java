package org.dcistudent.sakilarest.entities.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "actor", schema = "sakila", indexes = {
    @Index(name = "idx_actor_last_name", columnList = "last_name")
})
public class Actor {
  @Id
  @Column(name = "actor_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "first_name", nullable = false, length = 45)
  private @NotNull String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private @NotNull String lastName;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getFirstName() {
    return this.firstName;
  }

  public @NotNull String getLastName() {
    return this.lastName;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setFirstName(@NotNull String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(@NotNull String lastName) {
    this.lastName = lastName;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}