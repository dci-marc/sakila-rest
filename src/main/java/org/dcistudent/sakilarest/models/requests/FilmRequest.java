package org.dcistudent.sakilarest.models.requests;

import org.jetbrains.annotations.NotNull;

public class FilmRequest extends LimitOffsetRequest {

  private @NotNull String title = "";
  private @NotNull String description = "";
  private @NotNull Integer releaseYear = 0;

  public @NotNull Object getRequestValue(@NotNull String fieldName) {
    return switch (fieldName) {
      case "title" -> this.getTitle();
      case "description" -> this.getDescription();
      case "releaseYear" -> this.getReleaseYear();
      default -> throw new IllegalStateException("Unexpected value: " + fieldName);
    };
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

  public void setTitle(@NotNull String title) {
    this.title = title;
  }

  public void setDescription(@NotNull String description) {
    this.description = description;
  }

  public void setReleaseYear(@NotNull Integer releaseYear) {
    this.releaseYear = releaseYear;
  }
}
