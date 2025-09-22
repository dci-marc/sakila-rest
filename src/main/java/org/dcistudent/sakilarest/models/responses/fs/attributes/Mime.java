package org.dcistudent.sakilarest.models.responses.fs.attributes;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public final class Mime implements Serializable {

  private @NotNull String type = "";

  public Mime(@NotNull String type) {
    this.setType(type);
  }

  @JsonValue
  public @NotNull String getType() {
    return this.type;
  }

  public void setType(@NotNull String type) {
    if (type.isEmpty()) {
      throw new IllegalArgumentException("MIME type cannot be empty");
    }
    this.type = type;
  }

  @Override
  public @NotNull String toString() {
    return this.type;
  }
}
