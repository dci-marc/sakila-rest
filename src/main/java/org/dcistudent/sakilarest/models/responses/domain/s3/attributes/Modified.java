package org.dcistudent.sakilarest.models.responses.domain.s3.attributes;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public final class Modified {

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
