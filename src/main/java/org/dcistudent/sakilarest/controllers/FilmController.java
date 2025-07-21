package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Paging;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.models.responses.FilmResponse;
import org.dcistudent.sakilarest.services.FilmService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

  final @NotNull FilmService filmService;

  public FilmController(@NotNull FilmService filmService) {
    this.filmService = filmService;
  }

  @PostMapping("")
  public @NotNull Response<List<FilmResponse>> getFilms(@NotNull @RequestBody @Valid Paging paging) {
    try {
      return ResponseFactory.create(
          Response.Status.OK.get(),
          "film:fetch:success",
          this.filmService.getFilms(paging.getLimit(), paging.getOffset())
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "film:fetch:not.found",
          List.of()
      );
    }
  }

  @PostMapping("/by-title")
  public @NotNull Response<List<FilmResponse>> getFilms(@NotNull @RequestBody @Valid FilmRequest request) {
    try {
      return ResponseFactory.create(
          Response.Status.OK.get(),
          "film:fetch:by.title:success",
          this.filmService.getFilmsByTitle(request.getTitle())
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "film:fetch:by.title:not.found",
          List.of()
      );
    }
  }
}
