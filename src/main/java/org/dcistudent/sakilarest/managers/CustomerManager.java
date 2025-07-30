package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.entities.Customer;
import org.dcistudent.sakilarest.repositories.CustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager {

  private final @NotNull CustomerRepository customerRepository;

  public CustomerManager(@NotNull CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public @NotNull Page<Customer> findCustomersAddressAbove100(@NotNull Integer limit, @NotNull Integer offset) {
    Pageable pageable = PageRequest.of(offset, limit);
    return this.customerRepository.findCustomersAddressAbove100(pageable);
  }
}
