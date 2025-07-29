package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "film_actor", schema = "sakila", indexes = {
    @Index(name = "idx_fk_film_id", columnList = "film_id")
})
@Getter
@Setter
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
}