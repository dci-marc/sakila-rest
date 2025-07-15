package org.dcistudent.sakilarest.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.Valid;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.models.Response;
import org.dcistudent.sakilarest.models.auth.UserRequest;
import org.dcistudent.sakilarest.models.auth.UserResponse;
import org.dcistudent.sakilarest.services.auth.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final @NotNull UserService userService;

  public AuthController(@NotNull UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public String register(@NotNull @RequestBody @Valid UserRequest request) {
    try {
      if (Boolean.FALSE.equals(userService.create(request))) {
        return ResponseFactory.create(
            Response.Status.BAD_REQUEST.get(),
            "auth:user:creation:fail"
        ).toString();
      }
    } catch (Exception e) {
      return ResponseFactory.create(
          Response.Status.INTERNAL_SERVER_ERROR.get(),
          "server:internal:error"
      ).toString();
    }

    return ResponseFactory.create(
        Response.Status.OK.get(),
        "auth:user:creation:success",
        (new UserResponse(request.getName(), request.getEmail()).toString())
    ).toString();
  }
}
