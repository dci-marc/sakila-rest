package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.managers.FilmManager;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.models.responses.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class FilmService {

  final @NotNull FilmManager filmManager;

  public FilmService(@NotNull FilmManager filmManager) {
    this.filmManager = filmManager;
  }

  public @NotNull Page<FilmResponse> routeSearch(FilmRequest request) {
    if (request.getTitle().length() > 0) {
      return this.getFilmsByTitle(request.getLimit(), request.getOffset(), request.getTitle());
    }

    if (request.getDescription().length() > 0) {
      return this.getFilmsByDescription(request.getLimit(), request.getOffset(), request.getDescription());
    }

    if (request.getReleaseYear() > 0) {
      return this.getFilmsByReleaseYear(request.getLimit(), request.getOffset(), request.getReleaseYear());
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

  public @NotNull Page<FilmResponse> getFilms(@NotNull Integer limit, @NotNull Integer offset) {
    return this.build(this.filmManager.findAll(limit, offset));
  }

  public @NotNull Page<FilmResponse> getFilmsByTitle(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull String pattern
  ) {
    return this.build(this.filmManager.findByTitle(limit, offset, pattern));
  }

  public @NotNull Page<FilmResponse> getFilmsByDescription(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull String pattern
  ) {
    return this.build(this.filmManager.findByDescription(limit, offset, pattern));
  }

  public @NotNull Page<FilmResponse> getFilmsByReleaseYear(
      @NotNull Integer limit,
      @NotNull Integer offset,
      @NotNull Integer pattern
  ) {
    return this.build(this.filmManager.findByReleaseYear(limit, offset, pattern));
  }

  private @NotNull Page<FilmResponse> build(@NotNull Page<Film> films) {
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
