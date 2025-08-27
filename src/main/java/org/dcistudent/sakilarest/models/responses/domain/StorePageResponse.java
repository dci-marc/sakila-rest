package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.List;

public final class StorePageResponse extends PageImpl<StoreResponse> implements Serializable {

  public StorePageResponse(@NotNull List<StoreResponse> content) {
    super(content);
  }
}
