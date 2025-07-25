package org.dcistudent.sakilarest.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class FilmRequest extends LimitOffsetRequest {

  @NotBlank
  @Min(value = 3, message = "Title must be at least 3 characters long.")
  private @NotNull String title = "";

  @NotBlank
  @Min(value = 3, message = "Description must be at least 3 characters long.")
  private @NotNull String description = "";

  @NotNull
  @Size(min = 4, max = 4, message = "Release year must be a 4-digit number.")
  @Min(value = 1850, message = "Release year must be after 1850.")
  private Integer releaseYear = 0;
}
