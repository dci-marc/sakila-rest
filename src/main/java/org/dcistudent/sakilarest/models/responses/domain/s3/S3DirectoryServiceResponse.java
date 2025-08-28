package org.dcistudent.sakilarest.models.responses.domain.s3;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class S3DirectoryServiceResponse {

  private @NotNull List<S3FileServiceResponse> files;

  public S3DirectoryServiceResponse(@NotNull Builder builder) {
    if (builder.files.isEmpty()) {
      throw new IllegalArgumentException("Files cannot be null");
    }
    this.files = builder.files;
  }

  public static final class Builder {
    private @NotNull List<S3FileServiceResponse> files = List.of();

    public void addFile(@NotNull S3FileServiceResponse file) {
      this.files.add(file);
    }

    public @NotNull Builder setFiles(@NotNull List<S3FileServiceResponse> files) {
      this.files = files;
      return this;
    }

    public S3DirectoryServiceResponse build() {
      return new S3DirectoryServiceResponse(this);
    }
  }
}
