package org.dcistudent.sakilarest.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.requests.FilmRequest;
import org.dcistudent.sakilarest.models.responses.domain.FilmPageResponse;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.services.FilmService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/films") // plural nouns
public class FilmController {

  private final @NotNull FilmService filmService;

  public FilmController(@NotNull FilmService filmService) {
    this.filmService = filmService;
  }

  @GetMapping
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully fetched films.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = FilmPageResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request or no films found.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<Page<FilmResponse>>> getFilms(
      @NotNull @ModelAttribute @Valid FilmRequest request
  ) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "films:fetch:success",
              this.filmService.routeSearch(request)
          )
      );
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "films:fetch:not.found",
              Page.empty()
          ));
    }
  }

  @GetMapping("/{id}")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully fetched film by ID.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = FilmResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Film not found or invalid ID.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<ResponsePayload>> getFilm(@NotNull @PathVariable UUID id) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "film:fetch:success",
              this.filmService.getFilm(id)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "film:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }
}
