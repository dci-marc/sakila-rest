package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "inventory", schema = "sakila", indexes = {
    @Index(name = "idx_fk_film_id", columnList = "film_id"),
    @Index(name = "idx_store_id_film_id", columnList = "store_id, film_id")
})
public class Inventory implements Serializable {
  @Id
  @Column(name = "inventory_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "film_id", nullable = false)
  private @NotNull Film film;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "store_id", nullable = false)
  private @NotNull Store store;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull Film getFilm() {
    return this.film;
  }

  public @NotNull Store getStore() {
    return this.store;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setFilm(@NotNull Film film) {
    this.film = film;
  }

  public void setStore(@NotNull Store store) {
    this.store = store;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}