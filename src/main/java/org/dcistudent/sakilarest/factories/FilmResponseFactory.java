package org.dcistudent.sakilarest.factories;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.time.ZoneId;

public final class FilmResponseFactory {

  private FilmResponseFactory() {
  }

  public static @NotNull FilmResponse create(@NotNull Film film) {
    return new FilmResponse(
        film.getTitle(),
        film.getDescription(),
        film.getReleaseYear(),
        film.getLength(),
        film.getRating(),
        film.getSpecialFeatures(),
        film.getLastUpdate().atZone(ZoneId.systemDefault()).toString()
    );
  }

  public static @NotNull Page<FilmResponse> create(@NotNull Page<Film> films) {
    return films.map(FilmResponseFactory::create);
  }
}
