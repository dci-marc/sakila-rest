package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class FilmPageResponse extends PageImpl<FilmResponse> {

  public FilmPageResponse(@NotNull List<FilmResponse> content) {
    super(content);
  }
}
