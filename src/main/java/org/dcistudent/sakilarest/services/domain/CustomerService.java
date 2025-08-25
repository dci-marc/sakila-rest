package org.dcistudent.sakilarest.services.domain;

import org.dcistudent.sakilarest.factories.CustomerResponseFactory;
import org.dcistudent.sakilarest.managers.domain.CustomerManager;
import org.dcistudent.sakilarest.models.requests.shared.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

  private final @NotNull CustomerManager customerManager;

  public CustomerService(@NotNull CustomerManager customerManager) {
    this.customerManager = customerManager;
  }

  public @NotNull Page<CustomerResponse> getAll(@NotNull UUID storeId, @NotNull LimitOffsetRequest request) {
    return CustomerResponseFactory.create(
        this.customerManager.findCustomersByStore(storeId, request.getLimit(), request.getOffset())
    );
  }

  public @NotNull CustomerResponse getCustomer(@NotNull UUID storeId, @NotNull UUID customerId) {
    return CustomerResponseFactory.create(
        this.customerManager.findCustomerInStore(storeId, customerId)
    );
  }
}
