package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "film_actor", schema = "sakila", indexes = {
    @Index(name = "idx_fk_film_id", columnList = "film_id")
})
public class FilmActor {
  @EmbeddedId
  private @NotNull FilmActorId id;

  @MapsId("actorId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "actor_id", nullable = false)
  private @NotNull Actor actor;

  @MapsId("filmId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "film_id", nullable = false)
  private @NotNull Film film;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull FilmActorId getId() {
    return this.id;
  }

  public @NotNull Actor getActor() {
    return this.actor;
  }

  public @NotNull Film getFilm() {
    return this.film;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull FilmActorId id) {
    this.id = id;
  }

  public void setActor(@NotNull Actor actor) {
    this.actor = actor;
  }

  public void setFilm(@NotNull Film film) {
    this.film = film;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}