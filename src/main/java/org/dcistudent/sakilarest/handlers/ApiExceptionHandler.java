package org.dcistudent.sakilarest.handlers;

import org.dcistudent.sakilarest.exceptions.shared.ApiException;
import org.dcistudent.sakilarest.factories.shared.ResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ApiExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public @NotNull ResponseEntity<Response<ResponsePayload>> handleApiException(@NotNull ApiException e) {
    return ResponseEntity
        .status(e.getClass().getAnnotation(ResponseStatus.class).value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            e.getClass().getAnnotation(ResponseStatus.class).value(),
            e.getMessage(),
            e.getData()
        ));
  }
}
