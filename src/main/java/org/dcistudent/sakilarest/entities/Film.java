package org.dcistudent.sakilarest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "film", schema = "sakila", indexes = {
    @Index(name = "idx_title", columnList = "title"),
    @Index(name = "idx_fk_language_id", columnList = "language_id"),
    @Index(name = "idx_fk_original_language_id", columnList = "original_language_id")
})
public class Film {
  @Id
  @Column(name = "film_id", columnDefinition = "int UNSIGNED not null")
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "release_year")
  private Integer releaseYear;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "language_id", nullable = false)
  private Language language;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "original_language_id")
  private Language originalLanguage;

  @ColumnDefault("'3'")
  @Column(name = "rental_duration", columnDefinition = "tinyint UNSIGNED not null")
  private Short rentalDuration;

  @ColumnDefault("4.99")
  @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
  private BigDecimal rentalRate;

  @Column(name = "length", columnDefinition = "smallint UNSIGNED")
  private Integer length;

  @ColumnDefault("19.99")
  @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
  private BigDecimal replacementCost;

  @ColumnDefault("'G'")
  @Lob
  @Column(name = "rating")
  private String rating;

  @Lob
  @Column(name = "special_features")
  private String specialFeatures;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "last_update", nullable = false)
  private Instant lastUpdate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(Language originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public Short getRentalDuration() {
    return rentalDuration;
  }

  public void setRentalDuration(Short rentalDuration) {
    this.rentalDuration = rentalDuration;
  }

  public BigDecimal getRentalRate() {
    return rentalRate;
  }

  public void setRentalRate(BigDecimal rentalRate) {
    this.rentalRate = rentalRate;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public BigDecimal getReplacementCost() {
    return replacementCost;
  }

  public void setReplacementCost(BigDecimal replacementCost) {
    this.replacementCost = replacementCost;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getSpecialFeatures() {
    return specialFeatures;
  }

  public void setSpecialFeatures(String specialFeatures) {
    this.specialFeatures = specialFeatures;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}