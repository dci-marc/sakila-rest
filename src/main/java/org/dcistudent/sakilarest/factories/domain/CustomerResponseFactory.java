package org.dcistudent.sakilarest.factories.domain;

import org.dcistudent.sakilarest.entities.domain.Customer;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.shared.PagedResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.time.ZoneId;

public final class CustomerResponseFactory {

  private CustomerResponseFactory() {
  }

  public static @NotNull CustomerResponse create(@NotNull Customer customer) {
    return new CustomerResponse.Builder()
        .setUuid(customer.getUuid())
        .setFirstName(customer.getFirstName())
        .setLastName(customer.getLastName())
        .setEmail(customer.getEmail())
        .setActive(customer.getActive())
        .setCreateDate(customer.getCreateDate().atZone(ZoneId.systemDefault()).toString())
        .setLastUpdate(customer.getLastUpdate().atZone(ZoneId.systemDefault()).toString())
        .build();
  }

  public static @NotNull PagedResponse<CustomerResponse> create(@NotNull Page<Customer> customers) {
    return new PagedResponse(customers.getContent(), customers.getPageable(), customers.getTotalElements());
  }
}
