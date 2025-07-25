package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.Film;
import org.dcistudent.sakilarest.factories.FilmResponseFactory;
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
      return FilmResponseFactory.create(
          this.filmManager.findByTitle(request.getLimit(), request.getOffset(), request.getTitle())
      );
    }

    if (request.getDescription().length() > 0) {
      FilmResponseFactory.create(
          this.filmManager.findByDescription(request.getLimit(), request.getOffset(), request.getDescription())
      );
    }

    if (request.getReleaseYear() > 0) {
      return FilmResponseFactory.create(
          this.filmManager.findByReleaseYear(request.getLimit(), request.getOffset(), request.getReleaseYear())
      );
    }

    return FilmResponseFactory.create(this.filmManager.findAll(request.getLimit(), request.getOffset()));
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
}
