package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.entities.customers.Customer;
import org.dcistudent.sakilarest.exceptions.NotFoundException;
import org.dcistudent.sakilarest.factories.responses.CustomerResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.dcistudent.sakilarest.managers.CustomerManager;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.customers.CustomerResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class CustomerService {

  private final @NotNull CustomerManager customerManager;

  public CustomerService(@NotNull CustomerManager customerManager) {
    this.customerManager = customerManager;
  }

  public @NotNull Paged<CustomerResponse> getAll(@NotNull UUID storeId, @NotNull LimitOffsetRequest request) {
    return CustomerResponseFactory.create(
        this.customerManager.findCustomersByStore(storeId, request.getLimit(), request.getOffset())
    );
  }

  public @NotNull CustomerResponse getCustomer(@NotNull UUID storeId, @NotNull UUID customerId) {
    Customer customer = this.customerManager
        .findCustomerInStore(storeId, customerId)
        .orElseThrow(() -> new NotFoundException("customer:fetch:not.found", EmptyResponse.INSTANCE));

    return CustomerResponseFactory.create(customer);
  }
}
