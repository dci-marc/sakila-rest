package org.dcistudent.sakilarest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.services.StoreService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController {

  @NotNull public final StoreService storeService;

  public StoreController(@NotNull StoreService storeService) {
    this.storeService = storeService;
  }

  @GetMapping("/{id}")
  public @NotNull Response<Object> getStoreById(@NotNull @PathVariable Long id) throws JsonProcessingException {
    try {
      return ResponseFactory.create(
          Response.Status.OK.get(),
          "store:fetch:success",
          this.storeService.getById(id)
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "store:fetch:not.found",
          new Object()
      );
    }
  }
}
