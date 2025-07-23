package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.repositories.StoreRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class StoreManager {

  @NotNull private final StoreRepository repository;

  public StoreManager(@NotNull StoreRepository repository) {
    this.repository = repository;
  }

  public @NotNull Store getStoreById(@NotNull Long id) {
    return this.repository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Store with id " + id + " not found"));
  }
}
