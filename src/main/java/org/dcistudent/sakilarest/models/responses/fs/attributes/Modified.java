package org.dcistudent.sakilarest.models.responses.fs.attributes;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;

public final class Modified implements Serializable {

  private @NotNull Instant point;

  public Modified(@NotNull Instant point) {
    this.point = point;
  }

  @JsonValue
  public @NotNull Instant get() {
    return this.point;
  }

  public void set(@NotNull Instant point) {
    this.point = point;
  }

  @Override
  public @NotNull String toString() {
    return this.point.toString();
  }
}
