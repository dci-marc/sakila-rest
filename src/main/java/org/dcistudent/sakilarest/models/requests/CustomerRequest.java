package org.dcistudent.sakilarest.models.requests;

import org.jetbrains.annotations.NotNull;

public class CustomerRequest extends LimitOffsetRequest {

  private @NotNull String title = "";
  private @NotNull String description = "";
  private @NotNull Integer releaseYear = 0;

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

  public @NotNull Integer getReleaseYear() {
    return this.releaseYear;
  }

  public void setReleaseYear(@NotNull Integer releaseYear) {
    this.releaseYear = releaseYear;
  }
}
