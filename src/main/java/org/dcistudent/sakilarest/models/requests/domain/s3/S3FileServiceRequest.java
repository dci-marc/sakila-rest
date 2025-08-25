package org.dcistudent.sakilarest.models.requests.domain.s3;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

public final class S3FileServiceRequest {

  private final @NotNull String bucket;
  private final @NotNull String path;
  private final @NotNull InputStream inputStream;
  private final long contentLength;

  public S3FileServiceRequest(@NotNull Builder builder) {
    this.bucket = builder.bucket;
    this.path = builder.path;
    this.inputStream = builder.inputStream;
    this.contentLength = builder.contentLength;
  }

  public @NotNull GetObjectRequest get() {
    return GetObjectRequest.builder()
        .bucket(this.bucket)
        .key(this.path)
        .build();
  }

  public @NotNull PutObjectRequest put() {
    return PutObjectRequest.builder()
        .bucket(this.bucket)
        .key(this.path)
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

  public static class Builder implements Buildable {

    private @NotNull String bucket = "";
    private @NotNull String path = "";
    private @NotNull InputStream inputStream = InputStream.nullInputStream();
    private long contentLength = 0L;

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

    public @NotNull S3FileServiceRequest build() {
      return new S3FileServiceRequest(this);
    }
  }
}
