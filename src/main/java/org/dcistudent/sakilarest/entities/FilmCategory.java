package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "film_category", schema = "sakila")
public class FilmCategory {
  @EmbeddedId
  private @NotNull FilmCategoryId id;

  @MapsId("filmId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "film_id", nullable = false)
  private @NotNull Film film;

  @MapsId("categoryId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private @NotNull Category category;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;

  public @NotNull FilmCategoryId getId() {
    return this.id;
  }

  public @NotNull Film getFilm() {
    return this.film;
  }

  public @NotNull Category getCategory() {
    return this.category;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull FilmCategoryId id) {
    this.id = id;
  }

  public void setFilm(@NotNull Film film) {
    this.film = film;
  }

  public void setCategory(@NotNull Category category) {
    this.category = category;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}