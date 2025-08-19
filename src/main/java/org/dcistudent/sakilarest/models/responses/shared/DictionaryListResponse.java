package org.dcistudent.sakilarest.models.responses.shared;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class DictionaryListResponse implements ResponsePayload {

  @NotNull List<Map<String, String>> items;

  public DictionaryListResponse(@NotNull Builder builder) {
    this.items = builder.items;
  }

  public @NotNull List<Map<String, String>> getItems() {
    return this.items;
  }

  public static class Builder {
    private @NotNull List<Map<String, String>> items = List.of();

    public @NotNull Builder setItems(@NotNull List<Map<String, String>> items) {
      this.items = items;
      return this;
    }

    public @NotNull DictionaryListResponse build() {
      return new DictionaryListResponse(this);
    }
  }
}
