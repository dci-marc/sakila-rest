package org.dcistudent.sakilarest.repositories;

import org.dcistudent.sakilarest.entities.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @NativeQuery(value = "SELECT * FROM customer WHERE address_id >= 100")
  @NotNull Page<Customer> findCustomersAddressAbove100(@NotNull Pageable pageable);
}