package org.dcistudent.sakilarest.factories;

import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.domain.FilmResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoreResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoresResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

public final class StoreResponseFactory {

  private StoreResponseFactory() {
  }

  public static @NotNull StoreResponse create(@NotNull Store store) {
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

  public static @NotNull Page<StoresResponse> create(@NotNull Page<Store> stores) {
    return stores.map(store -> new StoresResponse(
        store.getLastUpdate().toString(),
        new StaffResponse(
            store.getManagerStaff().getFirstName(),
            store.getManagerStaff().getLastName(),
            store.getManagerStaff().getEmail(),
            store.getManagerStaff().getActive(),
            store.getManagerStaff().getLastUpdate().toString()
        )
    ));
  }
}
