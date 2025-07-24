package org.dcistudent.sakilarest.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
}
