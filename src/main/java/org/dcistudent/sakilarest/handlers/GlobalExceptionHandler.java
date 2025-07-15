package org.dcistudent.sakilarest.handlers;

import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public Response handleUnreadable(HttpMessageNotReadableException ex) {
    return ResponseFactory.create(
        Response.Status.BAD_REQUEST.get(),
        "Malformed JSON or missing required fields"
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Response handleValidation(MethodArgumentNotValidException ex) {
    return ResponseFactory.create(
        Response.Status.BAD_REQUEST.get(),
        "Validation failed",
        ex.getBindingResult().getFieldErrors().stream()
            .map(err -> Map.of(
                "field", err.getField(),
                "message", Objects.requireNonNull(err.getDefaultMessage())
            ))
            .toList()
            .toString()
    );
  }
}