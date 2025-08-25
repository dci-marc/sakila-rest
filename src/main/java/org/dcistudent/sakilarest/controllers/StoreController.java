package org.dcistudent.sakilarest.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.StorePageResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoreResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoresResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/stores") // plural nouns
public class StoreController {

  @NotNull
  private final StoreService storeService;

  public StoreController(@NotNull StoreService storeService) {
    this.storeService = storeService;
  }

  @GetMapping
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Stores fetched successfully",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StorePageResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "No stores found",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<Page<StoresResponse>>> getStores(
      @NotNull @ModelAttribute @Valid LimitOffsetRequest request
  ) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "stores:fetch:success",
              this.storeService.getAll(request)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "stores:fetch:not.found",
              Page.empty()
          ));
    }
  }

  @GetMapping("/{id}")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Store fetched successfully",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StoreResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Store not found",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<ResponsePayload>> getStoreById(@NotNull @PathVariable UUID id) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "store:fetch:success",
              this.storeService.getByUuid(id)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "store:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }
}
