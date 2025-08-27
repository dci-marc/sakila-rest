package org.dcistudent.sakilarest.interfaces.models.responses.shared;

import org.springframework.data.domain.Page;

import java.io.Serializable;

public interface Paged<T> extends Page<T>, Serializable {
}
