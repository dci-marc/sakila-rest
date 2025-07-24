package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.managers.FilmManager;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
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

  public @NotNull List<FilmResponse> routeSearch(FilmRequest request) {
    if (request.getTitle().length() > 0) {
      return this.getFilmsByTitle(request.getLimit(), request.getOffset(), request.getTitle());
    }

    if (request.getDescription().length() > 0) {
      return this.getFilmsByDescription(request.getLimit(), request.getOffset(), request.getDescription());
    }

    return this.getFilms(request.getLimit(), request.getOffset());
  }

  public @NotNull FilmResponse getFilm(@NotNull Long id) {
    Film film = this.filmManager.findById(id);
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

  public @NotNull List<FilmResponse> getFilms(@NotNull Integer limit, @NotNull Integer offset) {
    return this.filmManager.findAll(limit, offset)
        .map(film -> new FilmResponse(
            film.getTitle(),
            film.getDescription(),
            film.getReleaseYear(),
            film.getLength(),
            film.getRating(),
            film.getSpecialFeatures(),
            film.getLastUpdate().atZone(ZoneId.systemDefault()).toString()
        )).toList();
  }

  public @NotNull List<FilmResponse> getFilmsByTitle(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull String pattern
  ) {
    return this.filmManager.findByTitle(limit, offset, pattern)
        .stream()
        .map(film -> new FilmResponse(
            film.getTitle(),
            film.getDescription(),
            film.getReleaseYear(),
            film.getLength(),
            film.getRating(),
            film.getSpecialFeatures(),
            film.getLastUpdate().atZone(ZoneId.systemDefault()).toString()
        )).toList();
  }

  public @NotNull List<FilmResponse> getFilmsByDescription(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull String pattern
  ) {
    return this.filmManager.findByDescription(limit, offset, pattern)
        .stream()
        .map(film -> new FilmResponse(
            film.getTitle(),
            film.getDescription(),
            film.getReleaseYear(),
            film.getLength(),
            film.getRating(),
            film.getSpecialFeatures(),
            film.getLastUpdate().atZone(ZoneId.systemDefault()).toString()
        )).toList();
  }
}
