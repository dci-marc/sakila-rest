package org.dcistudent.sakilarest.models.requests.s3;

import org.dcistudent.sakilarest.interfaces.models.responses.Buildable;
import org.dcistudent.sakilarest.normalizers.PathNormalizer;
import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class S3FileServiceRequest {

  public static final @NotNull String METADATA_FILENAME_KEY = "filename";
  public static final @NotNull String METADATA_CONTENT_TYPE_KEY = "content-type";

  private final @NotNull String bucket;
  private final @NotNull String path;
  private final @NotNull InputStream inputStream;
  private final long contentLength;
  private final @NotNull String contentType;

  public S3FileServiceRequest(@NotNull Builder builder) {
    this.bucket = builder.bucket;
    this.path = builder.path;
    this.inputStream = builder.inputStream;
    this.contentLength = builder.contentLength;
    this.contentType = builder.contentType;
  }

  public @NotNull GetObjectRequest get() {
    return GetObjectRequest.builder()
        .bucket(this.bucket)
        .key(this.path)
        .build();
  }

  public @NotNull PutObjectRequest put() {
    if (this.inputStream.equals(InputStream.nullInputStream())) {
      throw new IllegalStateException("InputStream must be set for PUT requests");
    }

    Map<String, String> metadata = new HashMap<>();
    metadata.put(S3FileServiceRequest.METADATA_FILENAME_KEY, PathNormalizer.getFilename(this.path));
    metadata.put(S3FileServiceRequest.METADATA_CONTENT_TYPE_KEY, this.contentType);

    return PutObjectRequest.builder()
        .bucket(this.bucket)
        .key(this.path)
        .contentLength(this.contentLength)
        .contentType(this.contentType)
        .metadata(metadata)
        .build();
  }

  public @NotNull DeleteObjectRequest delete() {
    return DeleteObjectRequest.builder()
        .bucket(this.bucket)
        .key(this.path)
        .build();
  }

  public @NotNull RequestBody body() {
    return RequestBody.fromInputStream(
        this.inputStream,
        this.contentLength
    );
  }

  public static final class Builder implements Buildable<S3FileServiceRequest> {
    private @NotNull String bucket = "";
    private @NotNull String path = "";
    private @NotNull InputStream inputStream = InputStream.nullInputStream();
    private long contentLength = 0L;
    private @NotNull String contentType = "application/octet-stream";

    public @NotNull Builder setBucket(@NotNull String bucket) {
      this.bucket = bucket;
      return this;
    }

    public @NotNull Builder setPath(@NotNull String path) {
      this.path = path;
      return this;
    }

    public @NotNull Builder setInputStream(@NotNull InputStream inputStream) {
      this.inputStream = inputStream;
      return this;
    }

    public @NotNull Builder setContentLength(long contentLength) {
      this.contentLength = contentLength;
      return this;
    }

    public @NotNull Builder setContentType(@NotNull String contentType) {
      this.contentType = contentType;
      return this;
    }

    public @NotNull S3FileServiceRequest build() {
      return new S3FileServiceRequest(this);
    }
  }
}
