package org.dcistudent.sakilarest.factories.responses;

import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.dcistudent.sakilarest.models.responses.customers.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.films.FilmResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoreResponse;
import org.dcistudent.sakilarest.models.responses.stores.StoresResponse;
import org.dcistudent.sakilarest.models.responses.PagedResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.util.List;

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

  public static @NotNull PagedResponse<StoresResponse> create(@NotNull Page<Store> stores) {
    List<StoresResponse> models = stores.map(store -> new StoresResponse.Builder()
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
    ).toList();

    return new PagedResponse<>(models, stores.getPageable(), stores.getTotalElements());
  }
}
