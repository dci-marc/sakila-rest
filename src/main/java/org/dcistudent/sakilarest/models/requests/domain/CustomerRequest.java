package org.dcistudent.sakilarest.models.requests.domain;

import org.dcistudent.sakilarest.models.requests.shared.LimitOffsetRequest;
import org.jetbrains.annotations.NotNull;

public final class CustomerRequest extends LimitOffsetRequest {

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
