package org.dcistudent.sakilarest.controllers;

import jakarta.validation.Valid;
import org.dcistudent.sakilarest.exceptions.Auth0Exception;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.requests.UserRequest;
import org.dcistudent.sakilarest.models.responses.domain.UserResponse;
import org.dcistudent.sakilarest.models.responses.error.ErrorResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.services.Auth0Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final @NotNull Auth0Service auth0Service;

  public AuthController(@NotNull Auth0Service auth0Service) {
    this.auth0Service = auth0Service;
  }

  @PostMapping("/register")
  public @NotNull ResponseEntity<Response<ResponsePayload>> register(@NotNull @RequestBody @Valid UserRequest request) {
    try {
      this.auth0Service.registerUser(request.getEmail(), request.getPassword());
    } catch (Auth0Exception e) {
      return ResponseEntity
          .status(e.getError().getStatus())
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              e.getError().getStatus(),
              e.getMessage(),
              EmptyResponse.INSTANCE
          ));
    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "auth:user:creation:fail",
              new ErrorResponse.Builder()
                  .setMessage(e.getMessage())
                  .build()
          ));
    }

    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.CREATED,
            "auth:user:creation:success",
            new UserResponse.Builder()
                .setEmail(request.getEmail())
                .build()
        ));
  }

  @PostMapping("/login")
  public @NotNull ResponseEntity<Response<String>> login(@NotNull @RequestBody @Valid UserRequest request) {
    String token;

    try {
      token = this.auth0Service.loginUser(request.getEmail(), request.getPassword());
    } catch (Auth0Exception e) {
      return ResponseEntity
          .status(e.getError().getStatus())
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              e.getError().getStatus(),
              e.getMessage(),
              ""
          ));
    }

    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.OK,
            "auth:user:login:success",
            token
        ));
  }
}
