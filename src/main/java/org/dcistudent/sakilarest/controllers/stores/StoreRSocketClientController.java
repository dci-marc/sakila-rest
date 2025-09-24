package org.dcistudent.sakilarest.controllers.stores;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.PagedResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoresResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/rsocket/stores")
public final class StoreRSocketClientController {

  private final @NotNull RSocketRequester requester;

  public StoreRSocketClientController(@NotNull RSocketRequester.Builder builder) {
    this.requester = builder.tcp("localhost", 7000);
  }

  @GetMapping
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Stores fetched successfully",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = StoreResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "No stores found",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
              )
          )
      }
  )
  public @NotNull Mono<PagedResponse<StoresResponse>> getStores() {
    return Objects.requireNonNull(
        this.requester.route("rsocket.stores")
            .data(new LimitOffsetRequest())
            .retrieveMono(new ParameterizedTypeReference<>() {
            })
    );
  }

  @GetMapping("/{id}")
  @RequestBody(
      description = "UUID of the store to fetch",
      required = true,
      content = @io.swagger.v3.oas.annotations.media.Content(
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
              description = "No store found with the given ID",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
              )
          )
      }
  )
  public @NotNull Mono<StoreResponse> getStoreById(@NotNull @PathVariable UUID id) {
    return Objects.requireNonNull(
        this.requester.route("rsocket.stores.{id}", id)
            .data(id)
            .retrieveMono(new ParameterizedTypeReference<>() {
            })
    );
  }
}
