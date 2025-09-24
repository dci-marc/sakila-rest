package org.dcistudent.sakilarest.controllers.stores;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoresResponse;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@MessageMapping("rsocket")
public class StoreRSocketController {

  private final @NotNull StoreService service;

  public StoreRSocketController(@NotNull StoreService service) {
    this.service = service;
  }

  @MessageMapping("stores")
  @RequestBody(
      description = "Limit and offset for pagination",
      required = true,
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = LimitOffsetRequest.class)
      )
  )
  public @NotNull Mono<Paged<StoresResponse>> getStores(
      @NotNull LimitOffsetRequest request
  ) {
    return Mono.just(this.service.getAll(request));
  }

  @MessageMapping("stores.{id}")
  @RequestBody(
      description = "UUID of the store to fetch",
      required = true,
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = UUID.class)
      )
  )
  public @NotNull Mono<StoreResponse> getStoreById(@NotNull @DestinationVariable UUID id) {
    return Mono.just(this.service.getByUuid(id));
  }
}
