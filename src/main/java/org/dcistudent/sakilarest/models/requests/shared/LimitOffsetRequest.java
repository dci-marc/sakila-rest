package org.dcistudent.sakilarest.models.requests.shared;

import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;

public class LimitOffsetRequest {
  @NotNull
  @Min(10)
  private Integer limit = 10;

  @NotNull
  @Min(0)
  private Integer offset = 0;

  public @NotNull @Min(10) Integer getLimit() {
    return this.limit;
  }

  public @NotNull @Min(0) Integer getOffset() {
    return this.offset;
  }

  public void setLimit(@NotNull @Min(10) Integer limit) {
    this.limit = limit;
  }

  public void setOffset(@NotNull @Min(0) Integer offset) {
    this.offset = offset;
  }
}