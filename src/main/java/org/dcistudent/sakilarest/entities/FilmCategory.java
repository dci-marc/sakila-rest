package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Entity
@Table(name = "film_category", schema = "sakila")
@Getter
@Setter
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
}