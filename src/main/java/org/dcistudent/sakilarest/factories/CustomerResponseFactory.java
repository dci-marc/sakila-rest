package org.dcistudent.sakilarest.factories;

import org.dcistudent.sakilarest.entities.Customer;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.time.ZoneId;

public final class CustomerResponseFactory {

  private CustomerResponseFactory() {
  }

  public static @NotNull CustomerResponse create(@NotNull Customer customer) {
    return new CustomerResponse(
        customer.getUuid(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmail(),
        customer.getActive(),
        customer.getCreateDate().atZone(ZoneId.systemDefault()).toString(),
        customer.getLastUpdate().atZone(ZoneId.systemDefault()).toString()
    );
  }

  public static @NotNull Page<CustomerResponse> create(@NotNull Page<Customer> customers) {
    return customers.map(CustomerResponseFactory::create);
  }
}
