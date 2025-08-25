package org.dcistudent.sakilarest.models.responses.shared;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class DictionaryListResponse<K, V> implements ResponsePayload {

  @NotNull List<Map<K, V>> items;

  public DictionaryListResponse(@NotNull Builder<K, V> builder) {
    this.items = builder.items;
  }

  public @NotNull List<Map<K, V>> getItems() {
    return this.items;
  }

  public static final class Builder<K, V> implements Buildable<DictionaryListResponse<K, V>> {
    private @NotNull List<Map<K, V>> items = List.of();

    public @NotNull Builder<K, V> setItems(@NotNull List<Map<K, V>> items) {
      this.items = items;
      return this;
    }

    public @NotNull DictionaryListResponse<K, V> build() {
      return new DictionaryListResponse<>(this);
    }
  }
}
