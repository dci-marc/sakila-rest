package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.customers.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("SELECT c FROM Customer c WHERE c.store.uuid = :storeId AND c.active = true")
  @NotNull Page<Customer> findCustomersByStoreId(@NotNull UUID storeId, @NotNull Pageable pageable);

  @Query("SELECT c FROM Customer c WHERE c.store.uuid = :storeId AND c.uuid = :customerId AND c.active = true")
  @NotNull Optional<Customer> findCustomerByStoreId(@NotNull UUID storeId, @NotNull UUID customerId);
}
