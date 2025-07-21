package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.managers.FilmManager;
import org.dcistudent.sakilarest.models.responses.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
public class FilmService {

  final @NotNull FilmManager filmManager;

  public FilmService(@NotNull FilmManager filmManager) {
    this.filmManager = filmManager;
  }

  public @NotNull List<FilmResponse> getFilms(@NotNull Integer limit, @NotNull Integer offset) {
    return this.filmManager.findAll(limit, offset)
        .map(film -> new FilmResponse(
            film.getTitle(),
            film.getDescription(),
            film.getReleaseYear(),
            film.getLength(),
            film.getRating(),
            film.getSpecialFeatures(),
            film.getLastUpdate().atZone(ZoneId.systemDefault())
        )).toList();
  }

  public @NotNull List<FilmResponse> getFilmsByTitle(@NotNull String pattern) {
    return this.filmManager.findByTitle(pattern)
        .stream()
        .map(film -> new FilmResponse(
            film.getTitle(),
            film.getDescription(),
            film.getReleaseYear(),
            film.getLength(),
            film.getRating(),
            film.getSpecialFeatures(),
            film.getLastUpdate().atZone(ZoneId.systemDefault())
        )).toList();
  }
}
