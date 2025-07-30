package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.requests.CustomerRequest;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.services.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final @NotNull CustomerService customerService;

  public CustomerController(@NotNull CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public @NotNull Response<Page<CustomerResponse>> getCustomersAddressAbove100(
      @NotNull @ModelAttribute @Valid CustomerRequest request
  ) {
    try {
      return ResponseFactory.create(
          Response.Status.OK.get(),
          "customers:fetch:success",
          this.customerService.routeSearch(request)
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "customers:fetch:not.found",
          Page.empty()
      );
    }
  }
}
