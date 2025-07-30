package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.factories.CustomerResponseFactory;
import org.dcistudent.sakilarest.managers.CustomerManager;
import org.dcistudent.sakilarest.models.requests.CustomerRequest;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final @NotNull CustomerManager customerManager;

  public CustomerService(@NotNull CustomerManager customerManager) {
    this.customerManager = customerManager;
  }

  public @NotNull Page<CustomerResponse> routeSearch(CustomerRequest request) {
    return CustomerResponseFactory.create(
        this.customerManager.findCustomersAddressAbove100(request.getLimit(), request.getOffset())
    );
  }
}
