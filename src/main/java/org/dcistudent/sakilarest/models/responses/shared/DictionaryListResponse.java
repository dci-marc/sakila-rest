package org.dcistudent.sakilarest.models.responses.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public final class DictionaryListResponse implements ResponsePayload {
  @NotNull List<Map<String, String>> items;
}
