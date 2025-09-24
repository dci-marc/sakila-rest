package org.dcistudent.sakilarest.controllers.stores;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.responses.ResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.Response;
import org.dcistudent.sakilarest.models.responses.stores.StorePageResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoresResponse;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/stores") // plural nouns
public final class StoreController {

  @NotNull
  private final StoreService storeService;

  public StoreController(@NotNull StoreService storeService) {
    this.storeService = storeService;
  }

  @GetMapping
  @RequestBody(
      description = "Limit and offset for pagination",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = LimitOffsetRequest.class)
      )
  )
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
  public @NotNull ResponseEntity<Response<Paged<StoresResponse>>> getStores(
      @NotNull @ModelAttribute @Valid LimitOffsetRequest request
  ) {
    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.OK,
            "stores:fetch:success",
            this.storeService.getAll(request)
        ));
  }

  @GetMapping("/{id}")
  @RequestBody(
      description = "UUID of the store to fetch",
      required = true,
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = UUID.class)
      )
  )
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
  public @NotNull ResponseEntity<Response<StoreResponse>> getStoreById(@NotNull @PathVariable UUID id) {
    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.OK,
            "store:fetch:success",
            this.storeService.getByUuid(id)
        ));
  }
}
