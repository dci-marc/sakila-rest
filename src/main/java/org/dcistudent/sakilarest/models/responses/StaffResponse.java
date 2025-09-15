package org.dcistudent.sakilarest.models.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.dcistudent.sakilarest.interfaces.models.responses.Buildable;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@JsonDeserialize(builder = StaffResponse.Builder.class)
public final class StaffResponse implements Serializable {

  private final @NotNull String firstName;
  private final @NotNull String lastName;
  private final @NotNull String email;
  private final @NotNull Boolean active;
  private final @NotNull String lastUpdate;

  public StaffResponse(@NotNull Builder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.active = builder.active;
    this.lastUpdate = builder.lastUpdate;
  }

  public @NotNull String getFirstName() {
    return this.firstName;
  }

  public @NotNull String getLastName() {
    return this.lastName;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public @NotNull Boolean getActive() {
    return this.active;
  }

  public @NotNull String getLastUpdate() {
    return this.lastUpdate;
  }

  @JsonPOJOBuilder(withPrefix = "set")
  public static final class Builder implements Buildable<StaffResponse> {
    private @NotNull String firstName = "";
    private @NotNull String lastName = "";
    private @NotNull String email = "";
    private @NotNull Boolean active = false;
    private @NotNull String lastUpdate = "";

    public @NotNull Builder setFirstName(@NotNull String firstName) {
      this.firstName = firstName;
      return this;
    }

    public @NotNull Builder setLastName(@NotNull String lastName) {
      this.lastName = lastName;
      return this;
    }

    public @NotNull Builder setEmail(@NotNull String email) {
      this.email = email;
      return this;
    }

    public @NotNull Builder setActive(@NotNull Boolean active) {
      this.active = active;
      return this;
    }

    public @NotNull Builder setLastUpdate(@NotNull String lastUpdate) {
      this.lastUpdate = lastUpdate;
      return this;
    }

    @JsonCreator
    public @NotNull StaffResponse build() {
      return new StaffResponse(this);
    }
  }
}
