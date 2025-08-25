package org.dcistudent.sakilarest.managers.domain;

import org.dcistudent.sakilarest.entities.Customer;
import org.dcistudent.sakilarest.repositories.domain.CustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CustomerManager {

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
    return this.customerRepository
        .findCustomersByStoreId(storeId, pageable)
        .orElseThrow(() -> new NoSuchElementException("No customers found for store with id " + storeId));
  }

  public @NotNull Customer findCustomerInStore(
      @NotNull UUID storeId,
      @NotNull UUID customerId
  ) {
    return this.customerRepository
        .findCustomerByStoreId(storeId, customerId)
        .orElseThrow(() -> new NoSuchElementException("No customer found with id " + customerId + " for store " + storeId));
  }
}
