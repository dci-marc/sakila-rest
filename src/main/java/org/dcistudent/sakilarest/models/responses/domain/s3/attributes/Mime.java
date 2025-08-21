package org.dcistudent.sakilarest.models.responses.domain.s3.attributes;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

public final class Mime {

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
