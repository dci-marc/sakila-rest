package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "film", schema = "sakila", indexes = {
    @Index(name = "idx_title", columnList = "title"),
    @Index(name = "idx_fk_language_id", columnList = "language_id"),
    @Index(name = "idx_fk_original_language_id", columnList = "original_language_id"),
    @Index(name = "idx_uuid", columnList = "uuid")
})
public class Film extends AbstractUuidEntity implements Serializable {
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

  public @NotNull Long getId() {
    return this.id;
  }

  public @NotNull String getTitle() {
    return this.title;
  }

  public @NotNull String getDescription() {
    return this.description;
  }

  public @NotNull Integer getReleaseYear() {
    return this.releaseYear;
  }

  public @NotNull Language getLanguage() {
    return this.language;
  }

  public @NotNull Language getOriginalLanguage() {
    return this.originalLanguage;
  }

  public @NotNull Short getRentalDuration() {
    return this.rentalDuration;
  }

  public @NotNull BigDecimal getRentalRate() {
    return this.rentalRate;
  }

  public @NotNull Integer getLength() {
    return this.length;
  }

  public @NotNull BigDecimal getReplacementCost() {
    return this.replacementCost;
  }

  public @NotNull String getRating() {
    return this.rating;
  }

  public @NotNull String getSpecialFeatures() {
    return this.specialFeatures;
  }

  public @NotNull Instant getLastUpdate() {
    return this.lastUpdate;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public void setTitle(@NotNull String title) {
    this.title = title;
  }

  public void setDescription(@NotNull String description) {
    this.description = description;
  }

  public void setReleaseYear(@NotNull Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public void setLanguage(@NotNull Language language) {
    this.language = language;
  }

  public void setOriginalLanguage(@NotNull Language originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public void setRentalDuration(@NotNull Short rentalDuration) {
    this.rentalDuration = rentalDuration;
  }

  public void setRentalRate(@NotNull BigDecimal rentalRate) {
    this.rentalRate = rentalRate;
  }

  public void setLength(@NotNull Integer length) {
    this.length = length;
  }

  public void setReplacementCost(@NotNull BigDecimal replacementCost) {
    this.replacementCost = replacementCost;
  }

  public void setRating(@NotNull String rating) {
    this.rating = rating;
  }

  public void setSpecialFeatures(@NotNull String specialFeatures) {
    this.specialFeatures = specialFeatures;
  }

  public void setLastUpdate(@NotNull Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}