package org.dcistudent.sakilarest.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.loggers.SqlLogger;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.DictionaryListResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final @NotNull SqlLogger sqlLogger;

  public GlobalExceptionHandler(@NotNull SqlLogger sqlLogger) {
    this.sqlLogger = sqlLogger;
  }

  @ExceptionHandler(Exception.class)
  public @NotNull ResponseEntity<Response<EmptyResponse>> handleGeneric(@NotNull Exception e) {
    this.sqlLogger.logFatal(Arrays.toString(e.getStackTrace()));

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            Response.Status.INTERNAL_SERVER_ERROR.get(),
            "server:internal:error"
        ));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public @NotNull ResponseEntity<Response<EmptyResponse>> handleUnreadable(@NotNull HttpMessageNotReadableException e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            Response.Status.BAD_REQUEST.get(),
            "error:request:unreadable"
        ));
  }

  @ExceptionHandler(JsonProcessingException.class)
  public @NotNull ResponseEntity<Response<EmptyResponse>> handleJsonProcessing(@NotNull JsonProcessingException e) {
    this.sqlLogger.logFatal(Arrays.toString(e.getStackTrace()));

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            Response.Status.INTERNAL_SERVER_ERROR.get(),
            "error:json:processing"
        ));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @NotNull ResponseEntity<Response<DictionaryListResponse>> handleValidation(MethodArgumentNotValidException e) {
    List<Map<String, String>> errors = e.getBindingResult().getFieldErrors().stream()
        .map(err -> {
          Map<String, String> errorDetail = new HashMap<>();
          errorDetail.put("field", err.getField());
          errorDetail.put("message", Objects.requireNonNull(err.getDefaultMessage()));
          return errorDetail;
        })
        .toList();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            Response.Status.BAD_REQUEST.get(),
            "error:validation:fail",
            new DictionaryListResponse(errors)
        ));
  }
}