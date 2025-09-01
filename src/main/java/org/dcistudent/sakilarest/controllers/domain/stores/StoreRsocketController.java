package org.dcistudent.sakilarest.controllers.domain;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Paged;
import org.dcistudent.sakilarest.models.requests.shared.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.domain.stores.StoresResponse;
import org.dcistudent.sakilarest.services.domain.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@MessageMapping("stores")
public class RsocketController {

  private final @NotNull StoreService service;

  public RsocketController(@NotNull StoreService service) {
    this.service = service;
  }

  @MessageMapping
  public @NotNull Mono<Paged<StoresResponse>> getStores(
      @NotNull LimitOffsetRequest request
  ) {
    return Mono.just(this.service.getAll(request));
  }

  @MessageMapping("{id}")
  public @NotNull Mono<StoreResponse> getStoreById(@NotNull @DestinationVariable UUID id) {
    return Mono.just(this.service.getByUuid(id));
  }
}
