package org.dcistudent.sakilarest.controllers.domain;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.responses.shared.ResponseFactory;
import org.dcistudent.sakilarest.factories.responses.shared.UserResponseFactory;
import org.dcistudent.sakilarest.models.requests.domain.UserRequest;
import org.dcistudent.sakilarest.models.responses.domain.UserResponse;
import org.dcistudent.sakilarest.models.responses.error.ErrorResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.services.domain.auth.Auth0Service;
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
public final class AuthController {

  private final @NotNull Auth0Service auth0Service;

  public AuthController(@NotNull Auth0Service auth0Service) {
    this.auth0Service = auth0Service;
  }

  @PostMapping("/register")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "201",
              description = "User successfully registered.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = UserResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid request or user already exists.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<UserResponse>> register(@NotNull @RequestBody @Valid UserRequest request) {
    this.auth0Service.registerUser(request.getEmail(), request.getPassword());

    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.CREATED,
            "auth:user:creation:success",
            UserResponseFactory.create(request)
        ));
  }

  @PostMapping("/login")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "User successfully logged in.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid credentials or user not found.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = Response.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<String>> login(@NotNull @RequestBody @Valid UserRequest request) {
    return ResponseEntity.ok(
        ResponseFactory.create(
            HttpStatus.OK,
            "auth:user:login:success",
            this.auth0Service.loginUser(request.getEmail(), request.getPassword())
        ));
  }
}
