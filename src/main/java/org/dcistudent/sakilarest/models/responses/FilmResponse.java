package org.dcistudent.sakilarest.models.responses;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public final class FilmResponse {

  private @NotNull String title;
  private @NotNull String description;
  private int releaseYear;
  private int length;
  private @NotNull String rating;
  private @NotNull String specialFeatures;
  private @NotNull ZonedDateTime lastUpdate;

  public FilmResponse(
      @NotNull String title,
      @NotNull String description,
      int releaseYear,
      int length,
      @NotNull String rating,
      @NotNull String specialFeatures,
      @NotNull ZonedDateTime lastUpdate
  ) {
    this.title = title;
    this.description = description;
    this.releaseYear = releaseYear;
    this.length = length;
    this.rating = rating;
    this.specialFeatures = specialFeatures;
    this.lastUpdate = lastUpdate;
  }

  public @NotNull String getTitle() {
    return this.title;
  }

  public void setTitle(@NotNull String title) {
    this.title = title;
  }

  public @NotNull String getDescription() {
    return this.description;
  }

  public void setDescription(@NotNull String description) {
    this.description = description;
  }

  public int getReleaseYear() {
    return this.releaseYear;
  }

  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }

  public int getLength() {
    return this.length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public @NotNull String getRating() {
    return this.rating;
  }

  public void setRating(@NotNull String rating) {
    this.rating = rating;
  }

  public @NotNull String getSpecialFeatures() {
    return this.specialFeatures;
  }

  public void setSpecialFeatures(@NotNull String specialFeatures) {
    this.specialFeatures = specialFeatures;
  }

  public @NotNull ZonedDateTime getLastUpdate() {
    return this.lastUpdate;
  }

  public void setLastUpdate(@NotNull ZonedDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
