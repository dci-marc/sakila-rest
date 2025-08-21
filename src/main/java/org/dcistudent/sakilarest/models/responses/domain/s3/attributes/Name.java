package org.dcistudent.sakilarest.models.responses.domain.s3.attributes;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

public final class Name {

  private @NotNull String characters = "";

  public Name(@NotNull String name) {
    this.set(name);
  }

  @JsonValue
  public @NotNull String get() {
    return this.characters;
  }

  public void set(@NotNull String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.characters = name;
  }

  @Override
  public @NotNull String toString() {
    return this.characters;
  }
}
