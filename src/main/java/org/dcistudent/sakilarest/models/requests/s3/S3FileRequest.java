package org.dcistudent.sakilarest.models.requests.s3;

import org.dcistudent.sakilarest.interfaces.models.responses.Buildable;
import org.jetbrains.annotations.NotNull;

public final class S3FileRequest {

  private final @NotNull String filePath;
  private final @NotNull String fileName;
  private final @NotNull S3FileContentTypeRequest contentType;
  private final @NotNull String base64Content;

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

  public @NotNull String getBase64Content() {
    return this.base64Content;
  }

  public static final class Builder implements Buildable<S3FileRequest> {
    private @NotNull String filePath = "";
    private @NotNull String fileName = "";
    private @NotNull String contentType = "";
    private @NotNull String base64Content = "";

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

    public @NotNull Builder setBase64Content(@NotNull String base64Content) {
      this.base64Content = base64Content;
      return this;
    }

    public @NotNull S3FileRequest build() {
      return new S3FileRequest(this);
    }
  }
}
