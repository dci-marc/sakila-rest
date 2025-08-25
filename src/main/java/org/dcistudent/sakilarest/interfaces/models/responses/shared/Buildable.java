package org.dcistudent.sakilarest.interfaces.models.responses.shared;

import org.jetbrains.annotations.NotNull;

public interface Buildable<T> {
  @NotNull T build();
}
