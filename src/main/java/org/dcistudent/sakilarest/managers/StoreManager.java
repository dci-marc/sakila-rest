package org.dcistudent.sakilarest.managers;

import jakarta.persistence.*;
import org.dcistudent.sakilarest.entities.Store;
import org.dcistudent.sakilarest.repositories.StoreRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public final class StoreManager {

  @PersistenceContext
  private @NotNull EntityManager entityManager;

  @NotNull
  private final StoreRepository repository;

  public StoreManager(@NotNull EntityManager entityManager, @NotNull StoreRepository repository) {
    this.entityManager = entityManager;
    this.repository = repository;
  }

  public @NotNull Page<Store> findAll(@NotNull Integer limit, @NotNull Integer offset) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.repository.findAll(pageable);
  }

  public @NotNull Optional<Store> findStoreByUuid(@NotNull UUID id) {
    return this.repository.findByUuid(id);
  }

  public @NotNull Store findStoreByUuidEager(@NotNull UUID id)
      throws NoResultException {
    EntityGraph<?> entityGraph = this.entityManager.getEntityGraph("Store.eager");

    TypedQuery<Store> query = this.entityManager
        .createQuery("SELECT s FROM Store s WHERE s.uuid = :uuid", Store.class)
        .setParameter("uuid", id)
        .setHint(FetchHints.GRAPH_LOAD, entityGraph);

    return query.getSingleResult();
  }
}
