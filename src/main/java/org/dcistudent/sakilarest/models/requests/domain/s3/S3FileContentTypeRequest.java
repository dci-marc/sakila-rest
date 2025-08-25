package org.dcistudent.sakilarest.models.requests.domain.s3;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;

public final class S3FileContentTypeRequest implements Buildable<S3FileContentTypeRequest> {

  public static final @NotNull String IMAGE_JPEG = "image/jpeg";
  public static final @NotNull String IMAGE_PNG = "image/png";
  public static final @NotNull String IMAGE_GIF = "image/gif";
  public static final @NotNull String APPLICATION_PDF = "application/pdf";
  public static final @NotNull String TEXT_PLAIN = "text/plain";
  public static final @NotNull String APPLICATION_JSON = "application/json";

  private @NotNull String contentType = "";

  public @NotNull S3FileContentTypeRequest set(@NotNull String contentType) {
    this.contentType = S3FileContentType.fromString(contentType);
    return this;
  }

  public @NotNull S3FileContentTypeRequest build() {
    if (this.contentType.isEmpty()) {
      throw new IllegalStateException("Content type is not set.");
    }
    return this;
  }

  public enum S3FileContentType {
    IMAGE_JPEG(S3FileContentTypeRequest.IMAGE_JPEG),
    IMAGE_PNG(S3FileContentTypeRequest.IMAGE_PNG),
    IMAGE_GIF(S3FileContentTypeRequest.IMAGE_GIF),
    APPLICATION_PDF(S3FileContentTypeRequest.APPLICATION_PDF),
    TEXT_PLAIN(S3FileContentTypeRequest.TEXT_PLAIN),
    APPLICATION_JSON(S3FileContentTypeRequest.APPLICATION_JSON);

    private final @NotNull String contentType;

    S3FileContentType(@NotNull String contentType) {
      this.contentType = contentType;
    }

    public static @NotNull String fromString(@NotNull String contentType) {
      for (S3FileContentType type : S3FileContentTypeRequest.S3FileContentType.values()) {
        if (type.contentType.equalsIgnoreCase(contentType)) {
          return type.contentType;
        }
      }
      throw new IllegalArgumentException("Unknown content type: " + contentType);
    }
  }
}
