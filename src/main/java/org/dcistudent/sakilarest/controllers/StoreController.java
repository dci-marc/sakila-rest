package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.requests.LimitOffsetRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.domain.StoresResponse;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stores")
public class StoreController {

  @NotNull
  public final StoreService storeService;

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
              Response.Status.OK.get(),
              "stores:fetch:success",
              this.storeService.getAll(request)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              Response.Status.BAD_REQUEST.get(),
              "stores:fetch:not.found",
              Page.empty()
          ));
    }
  }

  @GetMapping("/{id}")
  public @NotNull ResponseEntity<Response<ResponsePayload>> getStoreById(@NotNull @PathVariable Long id) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              Response.Status.OK.get(),
              "store:fetch:success",
              this.storeService.getById(id)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              Response.Status.BAD_REQUEST.get(),
              "store:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }
}
