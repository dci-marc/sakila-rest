package org.dcistudent.sakilarest.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
