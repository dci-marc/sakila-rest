package org.dcistudent.sakilarest.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public final class DictionaryListResponse implements ResponsePayload {
  @NotNull List<Map<String, String>> items;
}
