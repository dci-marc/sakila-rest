package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.films.Film;
import org.dcistudent.sakilarest.exceptions.NotFoundException;
import org.dcistudent.sakilarest.factories.responses.FilmResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.dcistudent.sakilarest.managers.FilmManager;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.films.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class FilmService {

  final @NotNull FilmManager filmManager;

  public FilmService(@NotNull FilmManager filmManager) {
    this.filmManager = filmManager;
  }

  public @NotNull Paged<FilmResponse> routeSearch(FilmRequest request) {
    Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit());

    return FilmResponseFactory.create(
        this.filmManager.findAll(request, pageable)
    );
  }

  public @NotNull FilmResponse getFilm(@NotNull UUID id) {
    Film film = this.filmManager
        .findById(id)
        .orElseThrow(() -> new NotFoundException("film:fetch:not.found", EmptyResponse.INSTANCE));

    return FilmResponseFactory.create(film);
  }
}
