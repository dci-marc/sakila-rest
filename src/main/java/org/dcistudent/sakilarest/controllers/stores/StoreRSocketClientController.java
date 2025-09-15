package org.dcistudent.sakilarest.controllers.stores;

import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.PagedResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoresResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
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

  public StoreRSocketClientController(RSocketRequester.@NotNull Builder builder) {
    this.requester = builder.tcp("localhost", 7000);
  }

  @GetMapping
  public @NotNull Mono<PagedResponse<StoresResponse>> getStores() {
    return Objects.requireNonNull(
        this.requester.route("rsocket.stores")
            .data(new LimitOffsetRequest())
            .retrieveMono(new ParameterizedTypeReference<>() {
            })
    );
  }

  @GetMapping("/{id}")
  public @NotNull Mono<StoreResponse> getStoreById(@NotNull @PathVariable UUID id) {
    return Objects.requireNonNull(
        this.requester.route("rsocket.stores.{id}", id)
            .data(id)
            .retrieveMono(new ParameterizedTypeReference<>() {
            })
    );
  }
}
