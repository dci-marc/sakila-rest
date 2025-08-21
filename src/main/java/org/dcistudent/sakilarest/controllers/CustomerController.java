package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.services.CustomerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/stores/{id}") // plural nouns
public class CustomerController {

  private final @NotNull CustomerService service;

  public CustomerController(@NotNull CustomerService service) {
    this.service = service;
  }

  @GetMapping("/customers")
  public @NotNull ResponseEntity<Response<Page<CustomerResponse>>> getStoreCustomers(
      @NotNull @PathVariable UUID id,
      @NotNull @ModelAttribute @Valid LimitOffsetRequest request
  ) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "store:customers:fetch:success",
              this.service.getAll(id, request)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "store:customers:fetch:not.found",
              Page.empty()
          ));
    }
  }

  @GetMapping("/customers/{customerId}")
  public @NotNull ResponseEntity<Response<ResponsePayload>> getStoreCustomer(
      @NotNull @PathVariable UUID id,
      @NotNull @PathVariable UUID customerId
  ) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "store:fetch:success",
              this.service.getCustomer(id, customerId)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "store:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }
}
