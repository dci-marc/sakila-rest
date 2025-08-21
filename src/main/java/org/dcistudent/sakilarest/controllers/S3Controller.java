package org.dcistudent.sakilarest.controllers;

import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.services.S3Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/s3")
public class S3Controller {

  private final @NotNull S3Service service;

  public S3Controller(@NotNull S3Service service) {
    this.service = service;
  }

  @GetMapping("/files/{directoryName}")
  public @NotNull ResponseEntity<Response<ResponsePayload>> getFiles(@NotNull @PathVariable String directoryName) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "s3:files:fetch:success",
              this.service.getFiles(directoryName)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.NOT_FOUND,
              "s3:files:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }
}
