package org.dcistudent.sakilarest.interfaces.models.responses;

import org.jetbrains.annotations.NotNull;

public interface Buildable<T> {
  @NotNull T build();
}
