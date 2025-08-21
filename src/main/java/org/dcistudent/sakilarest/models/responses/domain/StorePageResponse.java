package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class StorePageResponse extends PageImpl<StoreResponse> {

  public StorePageResponse(@NotNull List<StoreResponse> content) {
    super(content);
  }
}
