package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.factories.FilmResponseFactory;
import org.dcistudent.sakilarest.managers.FilmManager;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    return FilmResponseFactory.create(this.filmManager.findById(id));
  }
}
