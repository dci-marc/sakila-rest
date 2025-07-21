package org.dcistudent.sakilarest.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

public class FilmRequest {

  @NotBlank
  @Min(value = 3, message = "Title must be at least 3 characters long.")
  private String title;

  public FilmRequest(@NotNull String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
