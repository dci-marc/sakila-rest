package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.List;

public final class FilmPageResponse extends PageImpl<FilmResponse> implements Serializable {

  public FilmPageResponse(@NotNull List<FilmResponse> content) {
    super(content);
  }
}
