package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.factories.CustomerResponseFactory;
import org.dcistudent.sakilarest.factories.StoreResponseFactory;
import org.dcistudent.sakilarest.managers.CustomerManager;
import org.dcistudent.sakilarest.managers.StoreManager;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
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

  @NotNull
  private final CustomerManager customerManager;

  public StoreService(
      @NotNull StoreManager storeManager,
      @NotNull CustomerManager customerManager
  ) {
    this.storeManager = storeManager;
    this.customerManager = customerManager;
  }

  public @NotNull Page<StoresResponse> getAll(LimitOffsetRequest request) {
    return StoreResponseFactory.create(this.storeManager.findAll(request.getLimit(), request.getOffset()));
  }

  public @NotNull StoreResponse getByUuid(@NotNull UUID id) {
    Store store = this.storeManager.findStoreByUuidEager(id);
    return StoreResponseFactory.create(store);
  }

  public @NotNull Page<CustomerResponse> getCustomersByStoreUuid(
      @NotNull UUID storeId,
      @NotNull LimitOffsetRequest request
  ) {
    Store store = this.storeManager.findStoreByUuidEager(storeId);
    return CustomerResponseFactory.create(
        this.customerManager.findCustomersByStoreId(store.getId(), request.getLimit(), request.getOffset())
    );
  }
}
