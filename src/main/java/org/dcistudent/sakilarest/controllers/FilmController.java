package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.dcistudent.sakilarest.services.FilmService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {

  final @NotNull FilmService filmService;

  public FilmController(@NotNull FilmService filmService) {
    this.filmService = filmService;
  }

  @GetMapping("/{id}")
  public @NotNull Response<ResponsePayload> getFilm(@NotNull @PathVariable Integer id) {
    try {
      return ResponseFactory.create(
          Response.Status.OK.get(),
          "film:fetch:success",
          this.filmService.getFilm(id.longValue())
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "film:fetch:not.found",
          EmptyResponse.INSTANCE
      );
    }
  }

  @GetMapping
  public @NotNull Response<Page<FilmResponse>> getFilms(@NotNull @ModelAttribute @Valid FilmRequest request) {
    try {
      return ResponseFactory.create(
          Response.Status.OK.get(),
          "films:fetch:success",
          this.filmService.routeSearch(request)
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "films:fetch:not.found",
          Page.empty()
      );
    }
  }
}
