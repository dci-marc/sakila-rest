package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("SELECT c FROM Customer c WHERE c.store.uuid = :storeId")
  @NotNull Optional<Page<Customer>> findCustomersByStoreId(@NotNull UUID storeId, @NotNull Pageable pageable);

  @Query("SELECT c FROM Customer c WHERE c.store.uuid = :storeId AND c.uuid = :customerId")
  @NotNull Optional<Customer> findCustomerByStoreId(@NotNull UUID storeId, @NotNull UUID customerId);
}
