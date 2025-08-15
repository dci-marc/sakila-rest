package org.dcistudent.sakilarest.models.responses.shared;

import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class DictionaryListResponse implements ResponsePayload {

  @NotNull List<Map<String, String>> items;

  public DictionaryListResponse(@NotNull List<Map<String, String>> items) {
    this.items = items;
  }

  public @NotNull List<Map<String, String>> getItems() {
    return this.items;
  }
}
