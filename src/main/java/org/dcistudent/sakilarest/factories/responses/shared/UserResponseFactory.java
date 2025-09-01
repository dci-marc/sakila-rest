package org.dcistudent.sakilarest.factories.responses.shared;

import org.dcistudent.sakilarest.models.requests.domain.UserRequest;
import org.dcistudent.sakilarest.models.responses.domain.UserResponse;
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
