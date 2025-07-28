package org.dcistudent.sakilarest.models.requests;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class FilmRequest extends LimitOffsetRequest {

  private @NotNull String title = "";
  private @NotNull String description = "";
  private @NotNull Integer releaseYear = 0;
}
