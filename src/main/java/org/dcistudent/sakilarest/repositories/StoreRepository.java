package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Store;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

  @NotNull Optional<Store> findByUuid(@NotNull UUID uuid);

  @NotNull Page<Store> findAll(@NotNull Pageable pageable);

  @EntityGraph(value = "Store.eager", type = EntityGraph.EntityGraphType.LOAD)
  @NotNull Optional<Store> findById(@NotNull Long id);
}
