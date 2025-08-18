package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.domain.CustomerResponse;
import org.dcistudent.sakilarest.models.responses.domain.StoresResponse;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/stores") // plural nouns
public class StoreController {

  @NotNull
  private final StoreService storeService;

  public StoreController(@NotNull StoreService storeService) {
    this.storeService = storeService;
  }

  @GetMapping
  public @NotNull ResponseEntity<Response<Page<StoresResponse>>> getStores(
      @NotNull @ModelAttribute @Valid LimitOffsetRequest request
  ) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "stores:fetch:success",
              this.storeService.getAll(request)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.NOT_FOUND,
              "stores:fetch:not.found",
              Page.empty()
          ));
    }
  }

  @GetMapping("/{id}")
  public @NotNull ResponseEntity<Response<ResponsePayload>> getStoreById(@NotNull @PathVariable UUID id) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "store:fetch:success",
              this.storeService.getByUuid(id)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.NOT_FOUND,
              "store:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }

  @GetMapping("/{id}/customers")
  public @NotNull ResponseEntity<Response<Page<CustomerResponse>>> getStoreCustomers(
      @NotNull @PathVariable UUID id,
      @NotNull @ModelAttribute @Valid LimitOffsetRequest request
  ) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "store:customers:fetch:success",
              this.storeService.getCustomersByStoreUuid(id, request)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.NOT_FOUND,
              "store:customers:fetch:not.found",
              Page.empty()
          ));
    }
  }
}
