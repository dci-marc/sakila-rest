package org.dcistudent.sakilarest.factories.responses;

import org.dcistudent.sakilarest.models.requests.UserRequest;
import org.dcistudent.sakilarest.models.responses.UserResponse;
import org.jetbrains.annotations.NotNull;

public final class UserResponseFactory {

  private UserResponseFactory() {
  }

  public static @NotNull UserResponse create(UserRequest request) {
    return new UserResponse.Builder()
        .setEmail(request.getEmail())
        .build();
  }
}
