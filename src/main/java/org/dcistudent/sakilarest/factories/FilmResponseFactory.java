package org.dcistudent.sakilarest.factories;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.models.responses.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.time.ZoneId;

public final class FilmResponseFactory {

  public static @NotNull Page<FilmResponse> create(@NotNull Page<Film> films) {
    return films.map(film -> new FilmResponse(
        film.getTitle(),
        film.getDescription(),
        film.getReleaseYear(),
        film.getLength(),
        film.getRating(),
        film.getSpecialFeatures(),
        film.getLastUpdate().atZone(ZoneId.systemDefault()).toString()
    ));
  }
}
