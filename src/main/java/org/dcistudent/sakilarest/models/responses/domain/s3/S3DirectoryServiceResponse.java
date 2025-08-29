package org.dcistudent.sakilarest.models.responses.domain.s3;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

public final class S3DirectoryServiceResponse {

  private final @NotNull Set<S3FileServiceResponse> files;

  public S3DirectoryServiceResponse(@NotNull Builder builder) {
    this.files = builder.files;
  }

  public @NotNull Set<S3FileServiceResponse> getFiles() {
    return this.files;
  }

  public static final class Builder {
    private final @NotNull Set<S3FileServiceResponse> files = new LinkedHashSet<>();

    public void addFile(@NotNull S3FileServiceResponse file) {
      this.files.add(file);
    }

    public S3DirectoryServiceResponse build() {
      return new S3DirectoryServiceResponse(this);
    }
  }
}
