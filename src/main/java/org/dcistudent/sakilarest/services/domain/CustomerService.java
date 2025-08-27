package org.dcistudent.sakilarest.services.domain;

import org.dcistudent.sakilarest.entities.domain.Customer;
import org.dcistudent.sakilarest.exceptions.shared.NotFoundException;
import org.dcistudent.sakilarest.factories.domain.CustomerResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.Paged;
import org.dcistudent.sakilarest.managers.domain.CustomerManager;
import org.dcistudent.sakilarest.models.requests.shared.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
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
