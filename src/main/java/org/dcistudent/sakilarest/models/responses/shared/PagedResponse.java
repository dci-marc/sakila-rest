package org.dcistudent.sakilarest.models.responses.shared;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Paged;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PagedResponse<T extends Serializable> extends PageImpl<T> implements Paged<T> {
  public PagedResponse(@NotNull List<T> content, @NotNull Pageable pageable, long total) {
    super(content, pageable, total);
  }

  public PagedResponse(@NotNull List<T> content) {
    super(content);
  }
}
