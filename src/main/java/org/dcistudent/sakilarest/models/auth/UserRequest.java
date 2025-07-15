package org.dcistudent.sakilarest.models.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

public class UserRequest {

  @NotBlank
  @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters long.")
  @Pattern(regexp = "^[\\w]+$", message = "Name must be alphanumeric and can include underscores.")
  private String name;

  @NotBlank
  @Email(message = "Email should be valid.")
  private String email;

  @NotBlank
  @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters long.")
  @Pattern(
      regexp = "^[\\w@#$%^&+=]+$",
      message = "Password must be alphanumeric and can include special characters like @, #, $, %, ^, &, +, =."
  )
  private String password;

  public UserRequest(@NotNull String name, @NotNull String email, @NotNull String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public @NotNull String getName() {
    return this.name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public void setEmail(@NotNull String email) {
    this.email = email;
  }

  public @NotNull String getPassword() {
    return this.password;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }
}
