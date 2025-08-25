package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.models.responses.domain.s3.Directory;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;


public final class DirectoryResponse extends Response<Directory> {

  public DirectoryResponse(@NotNull HttpStatus status, @NotNull String message, @Nullable Directory data) {
    super(status, message, data);
  }
}
