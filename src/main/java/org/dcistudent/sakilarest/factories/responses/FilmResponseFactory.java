package org.dcistudent.sakilarest.factories.responses;

import org.dcistudent.sakilarest.entities.films.Film;
import org.dcistudent.sakilarest.models.responses.films.FilmResponse;
import org.dcistudent.sakilarest.models.responses.PagedResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.time.ZoneId;

public final class FilmResponseFactory {

  private FilmResponseFactory() {
  }

  public static @NotNull FilmResponse create(@NotNull Film film) {
    return new FilmResponse.Builder()
        .setUuid(film.getUuid())
        .setTitle(film.getTitle())
        .setDescription(film.getDescription())
        .setReleaseYear(film.getReleaseYear())
        .setLength(film.getLength())
        .setRating(film.getRating())
        .setSpecialFeatures(film.getSpecialFeatures())
        .setLastUpdate(film.getLastUpdate().atZone(ZoneId.systemDefault()).toString())
        .build();
  }

  public static @NotNull PagedResponse<FilmResponse> create(@NotNull Page<Film> films) {
    return new PagedResponse<>(
        films.map(FilmResponseFactory::create).toList(),
        films.getPageable(),
        films.getTotalElements()
    );
  }
}
