package org.dcistudent.sakilarest.models.requests.s3;

import org.jetbrains.annotations.NotNull;

public final class S3FileRequest {

  private final @NotNull String filePath;
  private final @NotNull String fileName;
  private final @NotNull S3FileContentTypeRequest contentType;
  private final byte @NotNull [] base64Content;

  public S3FileRequest(@NotNull Builder builder) {
    this.filePath = builder.filePath;
    this.fileName = builder.fileName;
    this.contentType = new S3FileContentTypeRequest().set(builder.contentType).build();
    this.base64Content = builder.base64Content;
  }

  public @NotNull String getFilePath() {
    return this.filePath;
  }

  public @NotNull String getFileName() {
    return this.fileName;
  }

  public @NotNull S3FileContentTypeRequest getContentType() {
    return this.contentType;
  }

  public byte @NotNull [] getBase64Content() {
    return this.base64Content;
  }

  public static class Builder {
    private @NotNull String filePath = "";
    private @NotNull String fileName = "";
    private @NotNull String contentType = "";
    private byte @NotNull [] base64Content = new byte[0];

    public @NotNull Builder setFilePath(@NotNull String filePath) {
      this.filePath = filePath;
      return this;
    }

    public @NotNull Builder setFileName(@NotNull String fileName) {
      this.fileName = fileName;
      return this;
    }

    public @NotNull Builder setContentType(@NotNull String contentType) {
      this.contentType = contentType;
      return this;
    }

    public @NotNull Builder setBase64Content(byte @NotNull [] base64Content) {
      this.base64Content = base64Content;
      return this;
    }

    public @NotNull S3FileRequest build() {
      return new S3FileRequest(this);
    }
  }
}
