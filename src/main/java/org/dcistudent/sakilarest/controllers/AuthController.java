package org.dcistudent.sakilarest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.requests.UserRequest;
import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.dcistudent.sakilarest.models.responses.domain.UserResponse;
import org.dcistudent.sakilarest.models.responses.error.ErrorResponse;
import org.dcistudent.sakilarest.services.Auth0Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final @NotNull Auth0Service auth0Service;

  public AuthController(@NotNull Auth0Service auth0Service) {
    this.auth0Service = auth0Service;
  }

  @PostMapping("/register")
  public @NotNull Response<ResponsePayload> register(
      @NotNull @RequestBody @Valid UserRequest request
  ) throws JsonProcessingException {
    try {
      this.auth0Service.registerUser(request.getEmail(), request.getPassword());
    } catch (HttpClientErrorException e) {
      return ResponseFactory.create(
          e.getStatusCode().value(),
          "auth:user:creation:fail",
          new ErrorResponse(
              (new ObjectMapper()).readValue(e.getResponseBodyAsString(), Map.class).get("message").toString()

          )
      );
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "auth:user:creation:fail",
          new ErrorResponse(e.getMessage())
      );
    }

    return ResponseFactory.create(
        Response.Status.OK.get(),
        "auth:user:creation:success",
        new UserResponse(request.getEmail())
    );
  }

  @PostMapping("/login")
  public @NotNull Response<String> login(@NotNull @RequestBody @Valid UserRequest request) {
    @NotNull Map<String, String> token;

    try {
      token = this.auth0Service.loginUser(request.getEmail(), request.getPassword());
    } catch (IllegalArgumentException e) {
      return ResponseFactory.create(
          Response.Status.BAD_REQUEST.get(),
          "auth:user:login:fail",
          e.getMessage()
      );
    }

    return ResponseFactory.create(
        Response.Status.OK.get(),
        "auth:user:login:success",
        token.get("access_token")
    );
  }
}
