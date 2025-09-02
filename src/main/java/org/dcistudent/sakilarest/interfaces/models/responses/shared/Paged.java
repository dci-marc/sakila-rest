package org.dcistudent.sakilarest.interfaces.models.responses.shared;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dcistudent.sakilarest.models.responses.shared.PagedResponse;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@JsonDeserialize(as = PagedResponse.class)
public interface Paged<T> extends Page<T>, Serializable {
}
