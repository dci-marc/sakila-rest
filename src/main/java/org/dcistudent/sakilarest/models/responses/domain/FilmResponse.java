package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
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

  public FilmResponse(@NotNull Builder builder) {
    super(builder.uuid);
    this.title = builder.title;
    this.description = builder.description;
    this.releaseYear = builder.releaseYear;
    this.length = builder.length;
    this.rating = builder.rating;
    this.specialFeatures = builder.specialFeatures;
    this.lastUpdate = builder.lastUpdate;
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

  public static final class Builder implements Buildable {
    private @NotNull UUID uuid = UUID.randomUUID();
    private @NotNull String title = "";
    private @NotNull String description = "";
    private int releaseYear;
    private int length;
    private @NotNull String rating = "";
    private @NotNull String specialFeatures = "";
    private @NotNull String lastUpdate = "";

    public @NotNull Builder setUuid(@NotNull UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public @NotNull Builder setTitle(@NotNull String title) {
      this.title = title;
      return this;
    }

    public @NotNull Builder setDescription(@NotNull String description) {
      this.description = description;
      return this;
    }

    public @NotNull Builder setReleaseYear(int releaseYear) {
      this.releaseYear = releaseYear;
      return this;
    }

    public @NotNull Builder setLength(int length) {
      this.length = length;
      return this;
    }

    public @NotNull Builder setRating(@NotNull String rating) {
      this.rating = rating;
      return this;
    }

    public @NotNull Builder setSpecialFeatures(@NotNull String specialFeatures) {
      this.specialFeatures = specialFeatures;
      return this;
    }

    public @NotNull Builder setLastUpdate(@NotNull String lastUpdate) {
      this.lastUpdate = lastUpdate;
      return this;
    }

    public @NotNull FilmResponse build() {
      return new FilmResponse(this);
    }
  }
}
