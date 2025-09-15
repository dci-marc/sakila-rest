package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.entities.customers.Customer;
import org.dcistudent.sakilarest.repositories.CustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public final class CustomerManager {

  private final @NotNull CustomerRepository customerRepository;

  public CustomerManager(@NotNull CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public @NotNull Page<Customer> findCustomersByStore(
      @NotNull UUID storeId,
      @NotNull Integer limit,
      @NotNull Integer offset
  ) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.customerRepository.findCustomersByStoreId(storeId, pageable);
  }

  public @NotNull Optional<Customer> findCustomerInStore(
      @NotNull UUID storeId,
      @NotNull UUID customerId
  ) {
    return this.customerRepository.findCustomerByStoreId(storeId, customerId);
  }
}
