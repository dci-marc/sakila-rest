package org.dcistudent.sakilarest.services.domain;

import org.dcistudent.sakilarest.factories.FilmResponseFactory;
import org.dcistudent.sakilarest.managers.domain.FilmManager;
import org.dcistudent.sakilarest.models.requests.domain.FilmRequest;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FilmService {

  final @NotNull FilmManager filmManager;

  public FilmService(@NotNull FilmManager filmManager) {
    this.filmManager = filmManager;
  }

  public @NotNull Page<FilmResponse> routeSearch(FilmRequest request) {
    Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit());

    return FilmResponseFactory.create(
        this.filmManager.findAll(request, pageable)
    );
  }

  public @NotNull FilmResponse getFilm(@NotNull UUID id) {
    return FilmResponseFactory.create(this.filmManager.findById(id));
  }
}
