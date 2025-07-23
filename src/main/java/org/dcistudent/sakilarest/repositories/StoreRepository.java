package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Store;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

  @NotNull Optional<Store> findById(@NotNull Long id);
}
