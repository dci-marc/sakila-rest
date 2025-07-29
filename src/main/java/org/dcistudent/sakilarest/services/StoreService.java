package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.managers.StoreManager;
import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoreResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  @NotNull
  private final StoreManager storeManager;

  public StoreService(@NotNull StoreManager storeManager) {
    this.storeManager = storeManager;
  }

  @NotNull
  public StoreResponse getById(@NotNull Long id) {
    Store store = this.storeManager.findStoreByIdEager(id);
    return new StoreResponse(
        store.getLastUpdate().toString(),
        new StaffResponse(
            store.getManagerStaff().getFirstName(),
            store.getManagerStaff().getLastName(),
            store.getManagerStaff().getEmail(),
            store.getManagerStaff().getActive(),
            store.getManagerStaff().getLastUpdate().toString()),
        store.getCustomer().stream()
            .map(customer -> new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getActive(),
                customer.getCreateDate().toString(),
                customer.getLastUpdate().toString()))
            .toList(),
        store.getInventory().stream()
            .map(inventory -> new FilmResponse(
                inventory.getFilm().getTitle(),
                inventory.getFilm().getDescription(),
                inventory.getFilm().getReleaseYear(),
                inventory.getFilm().getLength(),
                inventory.getFilm().getRating(),
                inventory.getFilm().getSpecialFeatures(),
                inventory.getFilm().getLastUpdate().toString()))
            .toList()
    );
  }
}
