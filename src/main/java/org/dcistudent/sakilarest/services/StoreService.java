package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.factories.StoreResponseFactory;
import org.dcistudent.sakilarest.managers.StoreManager;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.StoreResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoresResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StoreService {

  @NotNull
  private final StoreManager storeManager;

  public StoreService(@NotNull StoreManager storeManager) {
    this.storeManager = storeManager;
  }

  public @NotNull Page<StoresResponse> getAll(LimitOffsetRequest request) {
    return StoreResponseFactory.create(this.storeManager.findAll(request.getLimit(), request.getOffset()));
  }

  public @NotNull StoreResponse getByUuid(@NotNull UUID id) {
    return StoreResponseFactory.create(
        this.storeManager.findStoreByUuidEager(id)
    );
  }
}
