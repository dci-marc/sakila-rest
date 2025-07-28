package org.dcistudent.sakilarest.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.loggers.SqlLogger;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.responses.DictionaryListResponse;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.jetbrains.annotations.NotNull;
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
  public @NotNull Response<EmptyResponse> handleGeneric(@NotNull Exception e) {
    this.sqlLogger.logFatal(Arrays.toString(e.getStackTrace()));

    return ResponseFactory.create(
        Response.Status.INTERNAL_SERVER_ERROR.get(),
        "server:internal:error"
    );
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public @NotNull Response<EmptyResponse> handleUnreadable(@NotNull HttpMessageNotReadableException e) {
    return ResponseFactory.create(
        Response.Status.BAD_REQUEST.get(),
        "error:request:unreadable"
    );
  }

  @ExceptionHandler(JsonProcessingException.class)
  public @NotNull Response<EmptyResponse> handleJsonProcessing(@NotNull JsonProcessingException e) {
    this.sqlLogger.logFatal(Arrays.toString(e.getStackTrace()));

    return ResponseFactory.create(
        Response.Status.INTERNAL_SERVER_ERROR.get(),
        "error:json:processing"
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @NotNull Response<DictionaryListResponse> handleValidation(MethodArgumentNotValidException e) {
    List<Map<String, String>> errors = e.getBindingResult().getFieldErrors().stream()
        .map(err -> {
          Map<String, String> errorDetail = new HashMap<>();
          errorDetail.put("field", err.getField());
          errorDetail.put("message", Objects.requireNonNull(err.getDefaultMessage()));
          return errorDetail;
        })
        .toList();

    return ResponseFactory.create(
        Response.Status.BAD_REQUEST.get(),
        "error:validation:fail",
        new DictionaryListResponse(errors)
    );
  }
}