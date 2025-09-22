package org.dcistudent.sakilarest.controllers.stores;

import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoresResponse;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
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
  public @NotNull Mono<Paged<StoresResponse>> getStores(
      @NotNull LimitOffsetRequest request
  ) {
    return Mono.just(this.service.getAll(request));
  }

  @MessageMapping("stores.{id}")
  public @NotNull Mono<StoreResponse> getStoreById(@NotNull @DestinationVariable UUID id) {
    return Mono.just(this.service.getByUuid(id));
  }
}
