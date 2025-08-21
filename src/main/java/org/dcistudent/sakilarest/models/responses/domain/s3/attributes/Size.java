package org.dcistudent.sakilarest.models.responses.domain.s3.attributes;

import com.fasterxml.jackson.annotation.JsonValue;

public final class Size {

  private int bytes = 0;

  public Size(int bytes) {
    this.set(bytes);
  }

  public void set(int bytes) {
    if (bytes < 0) {
      throw new IllegalArgumentException("Size in bytes must be a non-negative integer.");
    }

    this.bytes = bytes;
  }

  @JsonValue
  public int get() {
    return this.bytes;
  }

  public int get(int exponent) {
    if (exponent < 0) {
      throw new IllegalArgumentException("Exponent must be an unsigned integer.");
    }

    return this.bytes / (int) Math.pow(1024, exponent);
  }

  @Override
  public String toString() {
    return String.format("%d", this.bytes);
  }
}
