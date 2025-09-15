package org.dcistudent.sakilarest.models.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.dcistudent.sakilarest.interfaces.models.responses.Paged;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PagedResponse<T extends Serializable> extends PageImpl<T> implements Paged<T> {

  public PagedResponse(@NotNull List<T> content, @NotNull Pageable pageable, long total) {
    super(content, pageable, total);
  }

  @JsonCreator
  public static <T extends Serializable> PagedResponse<T> create(
      @JsonProperty("content") @NotNull List<T> content,
      @JsonProperty("pageable") JsonNode pageableNode,
      @JsonProperty("totalElements") long total
  ) {
    int number = pageableNode.get("pageNumber").asInt();
    int size = pageableNode.get("pageSize").asInt();

    Pageable pageable = PageRequest.of(number, size);
    return new PagedResponse<>(content, pageable, total);
  }
}
