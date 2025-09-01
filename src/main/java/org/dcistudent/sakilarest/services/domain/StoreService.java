package org.dcistudent.sakilarest.services.domain;

import jakarta.persistence.NoResultException;
import org.dcistudent.sakilarest.exceptions.shared.NotFoundException;
import org.dcistudent.sakilarest.factories.responses.domain.StoreResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Paged;
import org.dcistudent.sakilarest.managers.domain.StoreManager;
import org.dcistudent.sakilarest.models.requests.shared.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.domain.stores.StoresResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class StoreService {

  @NotNull
  private final StoreManager storeManager;

  public StoreService(@NotNull StoreManager storeManager) {
    this.storeManager = storeManager;
  }

  public @NotNull Paged<StoresResponse> getAll(LimitOffsetRequest request) {
    return StoreResponseFactory.create(this.storeManager.findAll(request.getLimit(), request.getOffset()));
  }

  public @NotNull StoreResponse getByUuid(@NotNull UUID id) {
    try {
      return StoreResponseFactory.create(this.storeManager.findStoreByUuidEager(id));
    } catch (NoResultException e) {
      throw new NotFoundException("store:fetch:not.found", EmptyResponse.INSTANCE);
    }
  }
}
