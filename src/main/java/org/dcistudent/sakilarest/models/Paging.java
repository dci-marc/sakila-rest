package org.dcistudent.sakilarest.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;

public final class Paging {

  @NotNull
  @Min(value = 1, message = "Limit must be at least 1")
  @Max(value = 100, message = "Limit must not exceed 100")
  private Integer limit;

  @NotNull
  @Min(value = 1, message = "Offset must be at least 1")
  private Integer offset;

  public Paging(@NotNull Integer limit, @NotNull Integer offset) {
    this.limit = limit;
    this.offset = offset;
  }

  public @NotNull Integer getLimit() {
    return this.limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public @NotNull Integer getOffset() {
    return this.offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }
}
