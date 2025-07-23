package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Join Table / Junction Table / Mapping Table
 */
@Entity
@Table(name = "film", schema = "sakila", indexes = {
    @Index(name = "idx_title", columnList = "title"),
    @Index(name = "idx_fk_language_id", columnList = "language_id"),
    @Index(name = "idx_fk_original_language_id", columnList = "original_language_id")
})
@Getter
@Setter
public class Film {
  @Id
  @Column(name = "film_id", columnDefinition = "int UNSIGNED not null")
  private @NotNull Long id;

  @Column(name = "title", nullable = false)
  private @NotNull String title;

  @Lob
  @Column(name = "description")
  private @NotNull String description;

  @Column(name = "release_year")
  private @NotNull Integer releaseYear;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "language_id", nullable = false)
  private @NotNull Language language;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "original_language_id")
  private @NotNull Language originalLanguage;

  @ColumnDefault("'3'")
  @Column(name = "rental_duration", columnDefinition = "tinyint UNSIGNED not null")
  private @NotNull Short rentalDuration;

  @ColumnDefault("4.99")
  @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
  private @NotNull BigDecimal rentalRate;

  @Column(name = "length", columnDefinition = "smallint UNSIGNED")
  private @NotNull Integer length;

  @ColumnDefault("19.99")
  @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
  private @NotNull BigDecimal replacementCost;

  @ColumnDefault("'G'")
  @Lob
  @Column(name = "rating")
  private @NotNull String rating;

  @Lob
  @Column(name = "special_features")
  private @NotNull String specialFeatures;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private @NotNull Instant lastUpdate;
}