package org.dcistudent.sakilarest.factories.domain;

import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.models.responses.domain.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

public final class StoreResponseFactory {

  private StoreResponseFactory() {
  }

  public static @NotNull StoreResponse create(@NotNull Store store) {
    return new StoreResponse.Builder()
        .setUuid(store.getUuid())
        .setLastUpdate(store.getLastUpdate().toString())
        .setStaff(new StaffResponse.Builder()
            .setFirstName(store.getManagerStaff().getFirstName())
            .setLastName(store.getManagerStaff().getLastName())
            .setEmail(store.getManagerStaff().getEmail())
            .setActive(store.getManagerStaff().getActive())
            .setLastUpdate(store.getManagerStaff().getLastUpdate().toString())
            .build()
        )
        .setCustomers(
            store.getCustomer().stream()
                .map(customer -> new CustomerResponse.Builder()
                    .setUuid(customer.getUuid())
                    .setFirstName(customer.getFirstName())
                    .setLastName(customer.getLastName())
                    .setEmail(customer.getEmail())
                    .setActive(customer.getActive())
                    .setCreateDate(customer.getCreateDate().toString())
                    .setLastUpdate(customer.getLastUpdate().toString())
                    .build())
                .toList()
        )
        .setFilms(
            store.getInventory().stream()
                .map(inventory -> new FilmResponse.Builder()
                    .setUuid(inventory.getFilm().getUuid())
                    .setTitle(inventory.getFilm().getTitle())
                    .setDescription(inventory.getFilm().getDescription())
                    .setReleaseYear(inventory.getFilm().getReleaseYear())
                    .setLength(inventory.getFilm().getLength())
                    .setRating(inventory.getFilm().getRating())
                    .setSpecialFeatures(inventory.getFilm().getSpecialFeatures())
                    .setLastUpdate(inventory.getFilm().getLastUpdate().toString())
                    .build())
                .toList()
        )
        .build();
  }

  public static @NotNull Page<StoresResponse> create(@NotNull Page<Store> stores) {
    return stores.map(store -> new StoresResponse.Builder()
        .setUuid(store.getUuid())
        .setLastUpdate(store.getLastUpdate().toString())
        .setStaff(new StaffResponse.Builder()
            .setFirstName(store.getManagerStaff().getFirstName())
            .setLastName(store.getManagerStaff().getLastName())
            .setEmail(store.getManagerStaff().getEmail())
            .setActive(store.getManagerStaff().getActive())
            .setLastUpdate(store.getManagerStaff().getLastUpdate().toString())
            .build())
        .build()
    );
  }
}
