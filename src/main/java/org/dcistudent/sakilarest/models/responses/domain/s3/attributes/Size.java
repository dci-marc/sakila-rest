package org.dcistudent.sakilarest.models.responses.domain.s3.attributes;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

public final class Size {

  private @NotNull Long bytes = 0L;

  public Size(@NotNull Long bytes) {
    this.set(bytes);
  }

  public void set(@NotNull Long bytes) {
    if (bytes < 0) {
      throw new IllegalArgumentException("Size in bytes must be a non-negative integer.");
    }

    this.bytes = bytes;
  }

  @JsonValue
  public @NotNull Long get() {
    return this.bytes;
  }

  public long get(@NotNull Long exponent) {
    if (exponent < 0) {
      throw new IllegalArgumentException("Exponent must be a non-negative integer.");
    }

    return (long) (this.bytes / Math.pow(1024, exponent));
  }

  @Override
  public String toString() {
    return String.format("%d", this.bytes);
  }
}
