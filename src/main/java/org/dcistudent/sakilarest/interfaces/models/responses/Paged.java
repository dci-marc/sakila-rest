package org.dcistudent.sakilarest.interfaces.models.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dcistudent.sakilarest.models.responses.PagedResponse;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@JsonDeserialize(as = PagedResponse.class)
public interface Paged<T> extends Page<T>, Serializable {
}
