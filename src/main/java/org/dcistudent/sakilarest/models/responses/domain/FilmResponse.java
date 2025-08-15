package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.models.responses.shared.AbstractUuidResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class FilmResponse extends AbstractUuidResponse implements DomainResponse {

  @NotNull String title;
  @NotNull String description;
  int releaseYear;
  int length;
  @NotNull String rating;
  @NotNull String specialFeatures;
  @NotNull String lastUpdate;

  public FilmResponse(
      @NotNull UUID uuid,
      @NotNull String title,
      @NotNull String description,
      int releaseYear,
      int length,
      @NotNull String rating,
      @NotNull String specialFeatures,
      @NotNull String lastUpdate
  ) {
    super(uuid);
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

  public @NotNull String getDescription() {
    return this.description;
  }

  public int getReleaseYear() {
    return this.releaseYear;
  }

  public int getLength() {
    return this.length;
  }

  public @NotNull String getRating() {
    return this.rating;
  }

  public @NotNull String getSpecialFeatures() {
    return this.specialFeatures;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }
}
