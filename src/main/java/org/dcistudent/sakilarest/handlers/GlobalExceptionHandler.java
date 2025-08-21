package org.dcistudent.sakilarest.handlers;

import com.bugsnag.Bugsnag;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dcistudent.sakilarest.controllers.ProblemController;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.loggers.SqlLogger;
import org.dcistudent.sakilarest.models.responses.shared.DictionaryListResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final @NotNull SqlLogger sqlLogger;
  private final @NotNull Bugsnag bugsnag;

  public GlobalExceptionHandler(@NotNull SqlLogger sqlLogger, @NotNull Bugsnag bugsnag) {
    this.sqlLogger = sqlLogger;
    this.bugsnag = bugsnag;
  }

  @ExceptionHandler(Exception.class)
  @SuppressWarnings({
      "java:S112", // General exception handling
  })
  public @NotNull ResponseEntity<Response<EmptyResponse>> handleGeneric(
      @NotNull Exception e,
      @NotNull WebRequest request
  ) throws Exception {
    if (request.getDescription(false).contains(ProblemController.MAPPING_BASE)) {
      throw e;
    }

    this.bugsnag.notify(e);
    this.sqlLogger.logFatal(Arrays.toString(e.getStackTrace()));

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "server:internal:error"
        ));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public @NotNull ResponseEntity<Response<EmptyResponse>> handleUnreadable(@NotNull HttpMessageNotReadableException e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST.value())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(ResponseFactory.create(
            HttpStatus.BAD_REQUEST,
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
            HttpStatus.BAD_REQUEST,
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
            HttpStatus.BAD_REQUEST,
            "error:validation:fail",
            new DictionaryListResponse.Builder()
                .setItems(errors)
                .build()
        ));
  }
}