package org.dcistudent.sakilarest.handlers;

import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.loggers.SqlLogger;
import org.dcistudent.sakilarest.models.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final @NotNull SqlLogger sqlLogger;

  public GlobalExceptionHandler(@NotNull SqlLogger sqlLogger) {
    this.sqlLogger = sqlLogger;
  }

  @ExceptionHandler(Exception.class)
  public @NotNull Response<String> handleGeneric(@NotNull Exception e) {
    this.sqlLogger.logFatal(Arrays.toString(e.getStackTrace()));

    return ResponseFactory.create(
        Response.Status.INTERNAL_SERVER_ERROR.get(),
        "server:internal:error"
    );
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public @NotNull Response<String> handleUnreadable(@NotNull HttpMessageNotReadableException ex) {
    return ResponseFactory.create(
        Response.Status.BAD_REQUEST.get(),
        "error:request:unreadable"
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @NotNull Response<String> handleValidation(@NotNull MethodArgumentNotValidException ex) {
    return ResponseFactory.create(
        Response.Status.BAD_REQUEST.get(),
        "error:validation:fail",
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