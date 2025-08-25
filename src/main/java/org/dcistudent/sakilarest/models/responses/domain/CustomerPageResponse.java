package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public final class CustomerPageResponse extends PageImpl<CustomerResponse> {

  public CustomerPageResponse(@NotNull List<CustomerResponse> content) {
    super(content);
  }
}
